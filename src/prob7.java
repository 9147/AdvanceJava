/*
Write a Java program to search and display details of book(s) authored by a particular author
from a “BOOKS” table. Assume an appropriate structure and attributes for the table.
 */

import java.sql.*;
import java.util.Scanner;
public class prob7 {
    private static String Create_Table_Book = "CREATE TABLE IF NOT EXISTS BOOKS(" +
            "ID int PRIMARY KEY," +
            "NAME varchar(100)," +
            "AUTHOR varchar(100))";
    public static void main(String args[]){
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "0Manojpa9147";
    try(Connection conn = DriverManager.getConnection(url,user,password);){
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("USE java_db");
        stmt.executeUpdate(Create_Table_Book);
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("1. Add new book\n 2. Search book by author\n 3. Exit");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter book ID");
                    int id = sc.nextInt();
                    System.out.println("Enter book name");
                    String name = sc.next();
                    System.out.println("Enter author name");
                    String author = sc.next();
                    String insertQuery = "INSERT INTO books (ID, NAME, AUTHOR) VALUES ('"+id+"', '"+name+"','"+ author+"')";

                    stmt.executeUpdate(insertQuery);

                    System.out.println("Book added successfully");
                    break;
                case 2:
                    System.out.println("Enter author name: ");
                    String authorName = sc.next();
                    String searchQuery = "Select * from Books where author = '"+authorName+"'";
                    ResultSet rs = stmt.executeQuery(searchQuery);
                    while(rs.next()){
                        System.out.println("Book ID: "+rs.getInt("ID")+" Book Name: "+rs.getString("NAME")+" Author: "+rs.getString("AUTHOR"));
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");

            }
        }
    }catch(SQLException ex){
        ex.printStackTrace();
    }
    }
}

