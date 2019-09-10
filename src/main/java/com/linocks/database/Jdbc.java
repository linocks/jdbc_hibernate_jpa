package com.linocks.database;

import java.sql.*;

/**
 * Hello world!
 *
 */
public class Jdbc
{
    private static final String DRIVER ="com.mysql.cj.jdbc.Driver";
    private static final String URL ="jdbc:mysql://localhost:3306/skyte?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";



    public static void main( String[] args )
    {
        System.out.println( "Hello jdbc!" );

        try(Connection conn = DriverManager.getConnection(URL,"root","")) {
            Class.forName(DRIVER);


            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM user ");

            while(resultSet.next()){
                System.out.println("User: "+resultSet.getInt("id")+" "+resultSet.getString("firstName")+" "+resultSet.getString("lastName"));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
