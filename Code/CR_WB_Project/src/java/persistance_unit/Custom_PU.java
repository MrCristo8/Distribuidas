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
}
