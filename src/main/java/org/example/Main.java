package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.sql.Connection;
import java.util.Objects;

public class Main {
    BufferedReader buff;
    InputStreamReader isr;
    public Main(){
        isr = new InputStreamReader(System.in);
        if(buff == null)
            buff = new BufferedReader(isr);
    }

    public static void main(String[] args) {
        // DB Connection
        String url = System.getenv("dburl");
        String user = System.getenv("dbuser");
        String password = System.getenv("dbpassword");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //
        Main obj = new Main();
        boolean check_flag=true;
        int choice = 0;
        while(check_flag){
            System.out.println("Welcome to Library :)");
            System.out.println("""
                    What do you want?
                    1. Add Book
                    2. Update Book
                    3. Delete Book
                    4. Find book by Name
                    5. Find book by cost
                    6. Find book by category
                    7. Find book by cost range
                    8. Find costliest book
                    9. Find cheapest book
                    10. Delete given year old books""");
            try{
                choice = Integer.parseInt(obj.buff.readLine());
            }
            catch (IOException e){
                e.printStackTrace();
            }
            try {
                Library library = new Library(obj.buff, connection, choice);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.print("Do you want to exit?? (yes/no) ");
            String choice_flag = "";
            try{
                choice_flag = obj.buff.readLine();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            if(Objects.equals(choice_flag, "yes")) {
                if(connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                check_flag = false;
            }
        }
        System.out.println("Current library books are: ");
        Library.endMessage();
    }
}