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
import org.postgresql.ds.PGSimpleDataSource;

/**
 *
 * @author wason
 */
public class Custom_PU
{

    private static final String BASE_URL = "jdbc:postgresql://localhost:5432/";
    private static String URL = "";
    private static final String USER = "postgres";
    private static final String PWD = "Crsdb008.";
    private static PGSimpleDataSource ods;

    public static void setDB(String db)
    {
        URL = BASE_URL + db;
    }

    public static ArrayList<Object> GetObjList(Object template, String related_table) throws SQLException, InstantiationException, IllegalAccessException
    {
        ods = new PGSimpleDataSource();
        ods.setURL(URL);
        ods.setUser(USER);
        ods.setPassword(PWD);
        ArrayList<Object> objList = new ArrayList<>();
        String table_id = "";
        for (Field field : template.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if (field.isAnnotationPresent(TableID.class))
            {
                table_id = field.getAnnotation(TableID.class).value();
                break;
            }
        }
        try (Connection conn = ods.getConnection(); Statement stmt = conn.createStatement();
                ResultSet rset = stmt.executeQuery("SELECT * FROM " + related_table + " ORDER BY " + table_id + " ASC"))
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
        ods = new PGSimpleDataSource();
        ods.setURL(URL);
        ods.setUser(USER);
        ods.setPassword(PWD);
        String query_string = "UPDATE " + related_table + " SET ";
        int update = 0;
        try (Connection conn = ods.getConnection();)
        {
            String table_id = "";
            String table_id_fk = "";
            boolean is_many_to_many = false;
            for (Field field : obj_update.getClass().getDeclaredFields())
            {
                field.setAccessible(true);
                if (field.isAnnotationPresent(ManyToMany.class))
                {
                    table_id = field.getAnnotation(ManyToMany.class).FirstFK();
                    table_id_fk = field.getAnnotation(ManyToMany.class).SeccondFK();
                    is_many_to_many = true;
                } else if (field.isAnnotationPresent(TableID.class))
                {
                    table_id = field.getAnnotation(TableID.class).value();

                } else if (field.isAnnotationPresent(RelatedColumn.class))
                {
                    String col_name = field.getAnnotation(RelatedColumn.class).value();
                    query_string += col_name + "=?, ";
                }

            }
            query_string = query_string.substring(0, query_string.length() - 2);
            if (is_many_to_many)
            {
                query_string += " WHERE " + table_id + "=? and " + table_id_fk + "=?";
            } else
            {
                query_string += " WHERE " + table_id + "=?";
            }

            System.out.println(query_string);
            try (PreparedStatement stmt = conn.prepareStatement(query_string))
            {
                int index = 1;
                int value = 0;
                int value_fk = 0;
                for (Field field : obj_update.getClass().getDeclaredFields())
                {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(TableID.class))
                    {
                        value = (Integer) field.get(obj_update);
                    } else if (field.isAnnotationPresent(ManyToMany.class))
                    {
                        value_fk = (Integer) field.get(obj_update);
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
                stmt.setInt(index, value);
                if (is_many_to_many)
                {
                    stmt.setInt(index + 1, value_fk);
                }
                update = stmt.executeUpdate();
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
            return "Update failed rows affected = " + update;
        }
        return "Update Successfull! rows affected = " + update;
    }

    public static String DeleteObject(Object obj_delete, String related_table)
    {
        ods = new PGSimpleDataSource();
        ods.setURL(URL);
        ods.setUser(USER);
        ods.setPassword(PWD);
        int id_value = 0;
        int seccond_id_value = 0;
        boolean is_manytoMany = false;
        String column_name = "";
        String seccond_column = "";
        for (Field field : obj_delete.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            try
            {
                if (field.isAnnotationPresent(TableID.class))
                {
                    column_name = field.getAnnotation(TableID.class).value();

                    id_value = (Integer) field.get(obj_delete);

                } else if (field.isAnnotationPresent(ManyToMany.class))
                {
                    is_manytoMany = true;
                    seccond_column = field.getAnnotation(ManyToMany.class).SeccondFK();
                    seccond_id_value = (Integer) field.get(obj_delete);
                }
            } catch (IllegalArgumentException | IllegalAccessException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        String query_string = "DELETE FROM " + related_table + " WHERE " + column_name;
        if (is_manytoMany)
        {
            query_string = query_string + "=? and " + seccond_column + "=?";
        } else
        {
            query_string = query_string + "=?";
        }
        System.out.println(query_string);

        try (Connection conn = ods.getConnection();
                PreparedStatement stmt = conn.prepareStatement(query_string);)
        {
            if (is_manytoMany)
            {
                stmt.setInt(1, id_value);
                stmt.setInt(2, seccond_id_value);
            } else
            {
                stmt.setInt(1, id_value);
            }
            stmt.executeUpdate();
        } catch (Exception e)
        {
            System.out.println(e.getMessage() + " " + e.getCause());
            return "Delete failed rows not affected";
        }
        return "Delete Sucesfull!!";
    }

    public static String PersistObject(Object obj_insert, String related_table)
    {
        String query_string = "INSERT INTO " + related_table + " (";
        int count = 0;
        ods = new PGSimpleDataSource();
        ods.setURL(URL);
        ods.setUser(USER);
        ods.setPassword(PWD);
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
                        stmt.setDate(index, new java.sql.Date(((Date) field.get(obj_insert)).getTime()));
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
        } catch (SQLException | IllegalArgumentException
                | IllegalAccessException ex)
        {
            return ex.getMessage();
        }
    }
}
