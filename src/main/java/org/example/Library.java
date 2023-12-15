package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class Library implements Stock {
    public static int counter = 1;

    public Library(BufferedReader buff,Connection connection, int choice) throws Exception {
        boolean check_flag;
        Statement statement;
        ResultSet rs;
        int bookID = 0;
        switch (choice){
            case 1:
                System.out.println("Enter details of your book:");
                check_flag = true;
                while(check_flag) {
                    Book book = new Book(buff);
                    createBook(book,connection);
                    System.out.print("Do you want to add another book? (yes/no) ");
                    String choice_flag = "";
                    try{
                        choice_flag = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    if(Objects.equals(choice_flag, "no")) {
                        check_flag = false;
                    }
                }
                break;
            case 2:
                check_flag = true;
                while(check_flag) {
                    System.out.print("Enter your BookID: ");
                    try{
                        bookID = Integer.parseInt(buff.readLine());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    // Check bookId is correct or not
                    String book_exists = "select count(*) as book_count from books where book_id = ";
                    book_exists += bookID;
                    statement = connection.createStatement();
                    rs = statement.executeQuery(book_exists);
                    rs.next();
                    if(rs.getInt(1) == 0){
                        System.out.println("This book didn't exists please type correct id");
                        System.out.print("Do you want to update another book? (yes/no) ");
                        String choice_flag = "";
                        try{
                            choice_flag = buff.readLine();
                        }
                        catch (IOException e){
                            e.printStackTrace();
                        }
                        if(Objects.equals(choice_flag, "no")) {
                            check_flag = false;
                            continue;
                        }
                    }

                    Book book = new Book(buff);
                    updateBook(book,connection,bookID);
                    System.out.print("Do you want to update another book? (yes/no) ");
                    String choice_flag = "";
                    try{
                        choice_flag = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    if(Objects.equals(choice_flag, "no")) {
                        check_flag = false;
                    }
                }
                break;
            case 3:
                check_flag = true;
                while(check_flag) {
                    System.out.print("Enter your BookID: ");
                    try{
                        bookID = Integer.parseInt(buff.readLine());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    // Check bookId is correct or not
                    String book_delete = "select count(*) as book_count from books where book_id = ";
                    book_delete += bookID;
                    statement = connection.createStatement();
                    rs = statement.executeQuery(book_delete);
                    rs.next();
                    if(rs.getInt(1) == 0){
                        System.out.println("This book didn't exists please type correct id");
                        return;
                    }
                    deleteBook(connection,bookID);
                    System.out.print("Do you want to delete another book? (yes/no) ");
                    String choice_flag = "";
                    try{
                        choice_flag = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    if(Objects.equals(choice_flag, "no")) {
                        check_flag = false;
                    }
                }
                break;
            case 4:
                System.out.print("Enter your book's name: ");
                check_flag = true;
                while(check_flag) {
                    String bookName="";
                    try{
                        bookName = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    findBookByName(connection,bookName);
                    System.out.print("Do you want to search another book? (yes/no) ");
                    String choice_flag = "";
                    try{
                        choice_flag = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    if(Objects.equals(choice_flag, "no")) {
                        check_flag = false;
                    }
                }
                break;
            case 5:
                System.out.print("Enter your book's cost: ");
                check_flag = true;
                while(check_flag) {
                    int bookCost=0;
                    try{
                        bookCost = Integer.parseInt(buff.readLine());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    findBookByCost(connection,bookCost);
                    System.out.print("Do you want to search another book? (yes/no) ");
                    String choice_flag = "";
                    try{
                        choice_flag = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    if(Objects.equals(choice_flag, "no")) {
                        check_flag = false;
                    }
                }
                break;
            case 6:
                check_flag = true;
                while(check_flag) {
                    System.out.print("Enter your book's category: ");
                    String bookCategory = "";
                    try{
                        bookCategory = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    findBookByCategory(connection,bookCategory);
                    System.out.print("Do you want to search another book? (yes/no) ");
                    String choice_flag = "";
                    try{
                        choice_flag = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    if(Objects.equals(choice_flag, "no")) {
                        check_flag = false;
                    }
                }
                break;
            case 7:
                check_flag = true;
                while(check_flag) {
                    System.out.print("Enter your book's starting cost: ");
                    int bookStartingCost = 0, bookEndingCost = 0;
                    try{
                        bookStartingCost = Integer.parseInt(buff.readLine());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    System.out.print("Enter your book's ending cost: ");
                    try{
                        bookEndingCost = Integer.parseInt(buff.readLine());
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    findBookByCostRange(connection,bookStartingCost,bookEndingCost);
                    System.out.print("Do you want to search another book? (yes/no) ");
                    String choice_flag = "";
                    try{
                        choice_flag = buff.readLine();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    if(Objects.equals(choice_flag, "no")) {
                        check_flag = false;
                    }
                }
                break;
            case 8:
                System.out.println("Costliest book in this library is: " + findCostliestBook(connection));
                break;
            case 9:
                System.out.println("Cheapest book in this library is: " + findCheapestBook(connection));
                break;
            case 10:
                int year = 0;
                System.out.print("Enter year that old books you want to delete: ");
                try{
                    year = Integer.parseInt(buff.readLine());
                }
                catch (IOException e){
                    e.printStackTrace();
                }
                System.out.println("Successfully deleted " + deleteOldBooks(connection,year) + " books");
                break;
            default:
        }
    }

    public static void endMessage(){
    }

    @Override
    public void createBook(Book book, Connection connection) {
        int bookID = counter++;
        String bookName = book.getBookName();
        String bookAuthor = book.getBookAuthor();
        java.sql.Date bookPublishDate = new java.sql.Date(book.getBookPublishDate().getTime());
        String bookCategory = book.getBookCategory();
        int bookCost = book.getBookCost();
        try{
            String book_exists = "select count(*) as book_count from books where book_id = '"+bookID+"'";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(book_exists);
            rs.next();
            if(rs.getInt(1) > 0){
                System.out.println("This book already exists please add another book");
                return;
            }
            PreparedStatement book_add = connection.prepareStatement("insert into books values(?,?,?,?,?,?)");
            book_add.setInt(1,bookID);
            book_add.setString(2,bookName);
            book_add.setString(3,bookAuthor);
            book_add.setDate(4,bookPublishDate);
            book_add.setString(5,bookCategory);
            book_add.setInt(6,bookCost);
            book_add.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Your bookID is: " + bookID);
    }

    @Override
    public void updateBook(Book book, Connection connection, int bookID) {
        // Remove Book
        try{
            PreparedStatement book_remove = connection.prepareStatement("select * from books where book_name = '"+bookID+"'");
            ResultSet resultSet = book_remove.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // Add Book
        String bookName = book.getBookName();
        String bookAuthor = book.getBookAuthor();
        java.sql.Date bookPublishDate = new java.sql.Date(book.getBookPublishDate().getTime());
        String bookCategory = book.getBookCategory();
        int bookCost = book.getBookCost();
        try{
            PreparedStatement book_add = connection.prepareStatement("insert into books values(?,?,?,?,?,?)");
            book_add.setInt(1,bookID);
            book_add.setString(2,bookName);
            book_add.setString(3,bookAuthor);
            book_add.setDate(4,bookPublishDate);
            book_add.setString(5,bookCategory);
            book_add.setInt(6,bookCost);
            book_add.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBook(Connection connection, int bookID) {
        try{
            PreparedStatement book_remove = connection.prepareStatement("select * from books where book_name = '"+bookID+"'");
            ResultSet resultSet = book_remove.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findBookByName(Connection connection, String bookName){
        try {
            PreparedStatement book_find = connection.prepareStatement("select * from books where book_name = '"+bookName+"'");
            ResultSet resultSet = book_find.executeQuery();
            int book_counter = 1;
            while (resultSet.next()){
                System.out.println("Book: " + book_counter++);
                System.out.println("book_id: " + resultSet.getInt(1) +
                        "\nbook_name: " + resultSet.getString(2) +
                        "\nbook_author: " + resultSet.getString(3) +
                        "\nbook_publish_date: " + resultSet.getDate(4) +
                        "\nbook_category: " + resultSet.getString(5) +
                        "\nbook_cost: " + resultSet.getInt(6)
                );
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findBookByCost(Connection connection, int bookCost){
        try {
            PreparedStatement book_find = connection.prepareStatement("select * from books where book_cost = "+ bookCost);
            ResultSet resultSet = book_find.executeQuery();
            int book_counter = 1;
            while (resultSet.next()){
                System.out.println("Book: " + book_counter++);
                System.out.println("book_id: " + resultSet.getInt(1) +
                        "\nbook_name: " + resultSet.getString(2) +
                        "\nbook_author: " + resultSet.getString(3) +
                        "\nbook_publish_date: " + resultSet.getDate(4) +
                        "\nbook_category: " + resultSet.getString(5) +
                        "\nbook_cost: " + resultSet.getInt(6)
                );
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findBookByCategory(Connection connection, String bookCategory){
        try {
            PreparedStatement book_find = connection.prepareStatement("select * from books where book_category = '"+bookCategory+"'");
            ResultSet resultSet = book_find.executeQuery();
            int book_counter = 1;
            while (resultSet.next()){
                System.out.println("Book: " + book_counter++);
                System.out.println("book_id: " + resultSet.getInt(1) +
                        "\nbook_name: " + resultSet.getString(2) +
                        "\nbook_author: " + resultSet.getString(3) +
                        "\nbook_publish_date: " + resultSet.getDate(4) +
                        "\nbook_category: " + resultSet.getString(5) +
                        "\nbook_cost: " + resultSet.getInt(6)
                );
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void findBookByCostRange(Connection connection, int bookStartingCost, int bookEndingCost){
        try {
            PreparedStatement book_find = connection.prepareStatement("select * from books where book_cost between " + bookStartingCost + " and " + bookEndingCost);
            ResultSet resultSet = book_find.executeQuery();
            int book_counter = 1;
            while (resultSet.next()){
                System.out.println("Book: " + book_counter++);
                System.out.println("book_id: " + resultSet.getInt(1) +
                        "\nbook_name: " + resultSet.getString(2) +
                        "\nbook_author: " + resultSet.getString(3) +
                        "\nbook_publish_date: " + resultSet.getDate(4) +
                        "\nbook_category: " + resultSet.getString(5) +
                        "\nbook_cost: " + resultSet.getInt(6)
                );
                System.out.println("------------------------------");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findCostliestBook(Connection connection){
        String costliest_book;
        try {
            PreparedStatement book_find = connection.prepareStatement("select * from books order by book_cost desc limit 1");
            ResultSet resultSet = book_find.executeQuery();
            resultSet.next();
            costliest_book = resultSet.getString(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return costliest_book;
    }

    @Override
    public String findCheapestBook(Connection connection){
        String cheapest_book;
        try {
            PreparedStatement book_find = connection.prepareStatement("select * from books order by book_cost asc limit 1");
            ResultSet resultSet = book_find.executeQuery();
            resultSet.next();
            cheapest_book = resultSet.getString(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cheapest_book;
    }

    @Override
    public int deleteOldBooks(Connection connection, int year){
        int deleted_books_count;
        try {
            PreparedStatement book_find = connection.prepareStatement("select count(*) as book_count from books where book_publish_date <= curdate() - interval "+year+" year");
            ResultSet resultSet = book_find.executeQuery();
            resultSet.next();
            deleted_books_count = resultSet.getInt(1);

            book_find = connection.prepareStatement("delete from books where book_publish_date <= curdate() - interval "+year+" year");
            book_find.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return deleted_books_count;
    }
}
