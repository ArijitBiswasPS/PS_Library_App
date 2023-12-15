package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
    public String bookName, bookAuthor, bookCategory;
    Date bookPublishDate;
    int bookCost = 0;
    public Book(BufferedReader buff) throws Exception{
        String bookName="";
        String bookAuthor="";
        Date bookPublishDate = null;
        String bookCategory="";
        int bookCost = 0;
        System.out.print("Enter book's name: ");
        try{
            bookName = buff.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        this.bookName = bookName;
        System.out.print("Enter author's name: ");
        try{
            bookAuthor = buff.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        this.bookAuthor = bookAuthor;
        System.out.print("Enter publishing date in (YYYY-MM-DD) format: ");
        try{
            String bookPublishD = buff.readLine();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            bookPublishDate = sdf.parse(bookPublishD);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        this.bookPublishDate = bookPublishDate;
        System.out.print("Enter book's category: ");
        try{
            bookCategory = buff.readLine();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        this.bookCategory = bookCategory;
        System.out.print("Enter book's cost: ");
        try{
            bookCost = Integer.parseInt(buff.readLine());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        this.bookCost = bookCost;
    }
    String getBookName(){
        return this.bookName;
    }
    String getBookAuthor(){
        return bookAuthor;
    }
    Date getBookPublishDate(){
        return bookPublishDate;
    }
    String getBookCategory(){
        return bookCategory;
    }
    int getBookCost(){
        return bookCost;
    }
}
