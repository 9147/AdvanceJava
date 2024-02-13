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
            "NAME varchar(200),"+
            "BALANCE int )";
    public static void main(String args[]){
        try(Connection conn = DriverManager.getConnection(url,user,password)){
            Statement statement = conn.createStatement();
            statement.executeUpdate("USE java_db");
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
                        String insertQuery = "INSERT INTO ACCOUNT (ID, NAME, BALANCE) VALUES ('"+id+"','"+name+"','"+amount+"')";
                        statement.executeUpdate(insertQuery);
                        System.out.println("Account added successfully");
                        break;
                    case 2:
                        String selectQuery = "SELECT * FROM ACCOUNT";
                        ResultSet rs = statement.executeQuery(selectQuery);
                        while(rs.next()){
                            System.out.println("Account ID: "+rs.getInt("ID")+" Account Name: "+rs.getString("NAME")+" Balance: "+rs.getInt("BALANCE"));
                        }
                        break;
                    case 3:
                        System.out.println("Eneter sender account id:");
                        int senderId = scn.nextInt();
                        System.out.println("Enter receiver account id:");
                        int receiverId = scn.nextInt();
                        System.out.println("Enter amount to transfer:");
                        int transferAmount = scn.nextInt();
                        String selectSenderQuery = "Select * from ACCOUNT where ID = '"+senderId+"'";
                        ResultSet senderRs = statement.executeQuery(selectSenderQuery);
                        int senderBalance = 0;
                        if(senderRs.next()){
                            senderBalance = senderRs.getInt("BALANCE");
                        }
                        if(senderBalance<transferAmount){
                            System.out.println("Insufficient balance");
                            break;
                        }
                        String UpdateReceiverQuery = "UPDATE ACCOUNT SET BALANCE = BALANCE +'"+transferAmount+"' where ID = '"+receiverId+"'";
                        String UpdateSenderQuery = "UPDATE ACCOUNT SET BALANCE = BALANCE -'"+transferAmount+"' where ID = '"+senderId+"'";
                        statement.executeUpdate(UpdateReceiverQuery);
                        statement.executeUpdate(UpdateSenderQuery);
                        System.out.println("Transaction successfull");
                        break;
                    case 4:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice");

                }
            }

        }catch(SQLException ex){
            System.out.println("Error "+ex);
        }

    }
}

