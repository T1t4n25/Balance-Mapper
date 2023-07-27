package com.zeyadh.balancemapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

 static Connection connection;
 static Statement statement;


    public static void getConnect () {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/balancemapper",
                        "zeyad",
                        "test");
                statement = connection.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
//add new row
//                r.moveToInsertRow();
//                r.updateInt("id", 20);
//                r.updateString("st_division", "cs");
//                r.updateString("st-name", "mamdoooh");
//                r.insertRow();
//update current row
//                if (r.getRow() == 2){
//                    r.updateInt("id", 25);
//                    r.updateString("st_division", "it");
//                    r.updateString("st-name", "zakaria elnobbbbbyyyyyyy");
//                    r.updateRow();
//                }
//formatter
//                    System.out.println(String.format("%-20s%-30s%-30s",
//                            r.getInt("id"),
//                            r.getString("st-name"),
//                            r.getString("st_division")));



