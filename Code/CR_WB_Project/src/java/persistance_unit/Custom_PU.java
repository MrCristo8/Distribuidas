/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance_unit;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.pool.OracleDataSource;

/**
 *
 * @author wason
 */
public class Custom_PU
{

    private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String USER = "wason";
    private static final String PWD = "ROOT";
    private static OracleDataSource ods;

    public static ArrayList<Object> GetObjList(Object template, String related_table) throws SQLException, InstantiationException, IllegalAccessException
    {
        ods = new OracleDataSource();
        ods.setURL(URL);
        ods.setUser(USER);
        ods.setPassword(PWD);
        ArrayList<Object> objList = new ArrayList<>();
        try (Connection conn = ods.getConnection(); Statement stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery("SELECT * FROM " + related_table))
        {
            while (rset.next())
            {
                Object x = template.getClass().newInstance();
                for (Field field : x.getClass().getDeclaredFields())
                {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(RelatedColumn.class))
                    {
                        String col_name = field.getAnnotation(RelatedColumn.class).value();
                        if (field.getType().equals(String.class))
                        {
                            field.set(x, rset.getString(col_name));
                        } else if (field.getType().equals(Integer.class))
                        {
                            field.set(x, rset.getInt(col_name));
                        } else if (field.getType().equals(Date.class))
                        {
                            field.set(x, rset.getDate(col_name));
                        } else if (field.getType().equals(Double.class))
                        {
                            field.set(x, rset.getDouble(col_name));
                        } else if (field.getType().equals(Float.class))
                        {
                            field.set(x, rset.getFloat(col_name));
                        }
                    } else if (field.isAnnotationPresent(ManyToOne.class))
                    {
                        String table_name = field.getAnnotation(ManyToOne.class).Table();
                        String col_name = field.getAnnotation(ManyToOne.class).Column();
                        PreparedStatement prepared_stmt = conn.prepareStatement("SELECT * FROM " + table_name + " WHERE " + col_name + "= ?");
                        int id = rset.getInt(col_name);
                        prepared_stmt.setInt(1, id);
                        ResultSet result = prepared_stmt.executeQuery();
                        Object inner_x = field.getType().newInstance();
                        while (result.next())
                        {
                            for (Field inner_field : inner_x.getClass().getDeclaredFields())
                            {
                                inner_field.setAccessible(true);
                                if (inner_field.isAnnotationPresent(RelatedColumn.class))
                                {
                                    String inner_col_name = inner_field.getAnnotation(RelatedColumn.class).value();
                                    if (inner_field.getType().equals(String.class))
                                    {
                                        inner_field.set(inner_x, result.getString(inner_col_name));
                                    } else if (inner_field.getType().equals(Integer.class))
                                    {
                                        inner_field.set(inner_x, result.getInt(inner_col_name));
                                    } else if (inner_field.getType().equals(Date.class))
                                    {
                                        inner_field.set(inner_x, result.getDate(inner_col_name));
                                    } else if (inner_field.getType().equals(Double.class))
                                    {
                                        inner_field.set(inner_x, result.getDouble(inner_col_name));
                                    } else if (inner_field.getType().equals(Float.class))
                                    {
                                        inner_field.set(inner_x, result.getFloat(inner_col_name));
                                    }
                                }
                            }
                        }
                        field.set(x, inner_x);
                    }
                }
                objList.add(x);
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
        }
        return objList;
    }

    public static String UpdateObject(Object obj_update, String related_table) throws SQLException
    {
        String query_string = "UPDATE " + related_table + " SET ";
        int update = 0;
        try (Connection conn = ods.getConnection();)
        {
            String table_id = "";
            for (Field field : obj_update.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                if (field.isAnnotationPresent(TableID.class))
                {
                    table_id = field.getAnnotation(TableID.class).value();

                } else if (field.isAnnotationPresent(RelatedColumn.class))
                {
                    String col_name = field.getAnnotation(RelatedColumn.class).value();
                    query_string += col_name + "=?, ";
                }

            }
            query_string = query_string.substring(0, query_string.length() - 2);
            query_string += " WHERE " + table_id + "=?";
            System.out.println(query_string);
            PreparedStatement stmt = conn.prepareStatement(query_string);
            int index = 1;
            int value = 0;
            for (Field field : obj_update.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                if (field.isAnnotationPresent(TableID.class))
                {
                    value = (Integer) field.get(obj_update);
                    continue;
                } else if (field.isAnnotationPresent(RelatedColumn.class))
                {
                    if (field.getType().equals(String.class))
                    {
                        stmt.setString(index, (String) field.get(obj_update));
                    } else if (field.getType().equals(Integer.class))
                    {
                        stmt.setInt(index, (Integer) field.get(obj_update));
                    } else if (field.getType().equals(Date.class))
                    {
                        stmt.setDate(index, (java.sql.Date) (Date) field.get(obj_update));
                    } else if (field.getType().equals(Double.class))
                    {
                        stmt.setDouble(index, (double) field.get(obj_update));
                    } else if (field.getType().equals(Float.class))
                    {
                        stmt.setFloat(index, (float) field.get(obj_update));
                    }
                    index++;
                }
            }
            //System.out.println(index);
            stmt.setInt(index, value);
            update = stmt.executeUpdate();
            stmt.close();
        } catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
            return "Update failed rows affected = " + update;
        }
        return "Update Successfull! rows affected = " + update;
    }

    public static String DeleteObject(Object obj_delete, String related_table)
    {
        int id_value = 0;
        String column_name = "";
        for (Field field : obj_delete.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if (field.isAnnotationPresent(TableID.class))
            {
                column_name = field.getAnnotation(TableID.class).value();
                try
                {
                    id_value = (Integer) field.get(obj_delete);
                    break;

                } catch (IllegalArgumentException | IllegalAccessException ex)
                {
                    Logger.getLogger(Custom_PU.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        String query_string = "DELETE " + related_table + " WHERE " + column_name + "=?";
        try (Connection conn = ods.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query_string);)
        {
            stmt.setInt(1, id_value);
            stmt.executeUpdate();
        } catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
            return "Delete failed rows affected";
        }
        return "Delete Sucesfull!!";
    }

    public static String PersistObject(Object obj_insert, String related_table)
    {
        String query_string = "INSERT INTO " + related_table + " (";
        int count = 0;
        try (Connection conn = ods.getConnection();)
        {
            for (Field field : obj_insert.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                if (field.isAnnotationPresent(RelatedColumn.class))
                {
                    String col_name = field.getAnnotation(RelatedColumn.class).value();
                    query_string += col_name + ", ";
                    count++;
                }

            }
            query_string = query_string.substring(0, query_string.length() - 2);
            query_string += " ) VALUES (";
            for (int i = 0; i < count; i++)
            {
                query_string += "?, ";
            }
            query_string = query_string.substring(0, query_string.length() - 2);
            query_string += ")";
            System.out.println(query_string);
            int index = 1;
            PreparedStatement stmt = conn.prepareStatement(query_string);
            for (Field field : obj_insert.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                if (field.isAnnotationPresent(RelatedColumn.class))
                {
                    if (field.getType().equals(String.class))
                    {
                        stmt.setString(index, (String) field.get(obj_insert));
                    } else if (field.getType().equals(Integer.class))
                    {
                        stmt.setInt(index, (Integer) field.get(obj_insert));
                    } else if (field.getType().equals(Date.class))
                    {
                        stmt.setDate(index, (java.sql.Date) (Date) field.get(obj_insert));
                    } else if (field.getType().equals(Double.class))
                    {
                        stmt.setDouble(index, (double) field.get(obj_insert));
                    } else if (field.getType().equals(Float.class))
                    {
                        stmt.setFloat(index, (float) field.get(obj_insert));
                    }
                    index++;
                }
            }
            stmt.executeUpdate();
            conn.close();
            return "Insert Successfull";
        } catch (SQLException ex)
        {
            System.out.println(ex.getMessage());
        } catch (IllegalArgumentException | IllegalAccessException ex)
        {
            System.out.println(ex.getMessage());
        }
        return "Success!!";
    }
}
