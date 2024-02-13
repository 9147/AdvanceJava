/*
Program to demonstrate transaction processing. Assume an appropriate database/table.
 */

import java.sql.*;
import java.util.*;
public class prob8 {
    static String  url = "jdbc:mysql://localhost:3306/";
    static String user = "root";
    static String password = "0Manojpa9147";
    static String Create_Table_Account = "CREATE TABLE IF NOT EXISTS ACCOUNT("+
            "ID int PRIMARY KEY,"+
            "NAME varchar(200,"+
            "BALANCE int)";
    public static void main(String args[]){
        try(Connection conn = DriverManager.getConnection(url,user,password)){
            Statement statement = conn.createStatement();
            statement.executeUpdate(Create_Table_Account);
            Scanner scn = new Scanner(System.in);
            int choice;
            while(true) {
                System.out.println("Enter the choice: ");
                System.out.println("1)Add account\n2)show avaliable accounts\n3)Perform transaction\n4)exit");
                choice=scn.nextInt();
                switch(choice){
                    case 1:
                        System.out.println("Enter account id: ");
                        int id = scn.nextInt();
                        System.out.println("Enter account name:");
                        String name = scn.next();
                        System.out.println("Enter ammount:");
                        int amount = scn.nextInt();

                }
            }

        }catch(SQLException ex){
            System.out.println("Error "+ex);
        }

    }
}

