package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public interface Stock {
    void createBook(Book book, Connection connection) throws SQLException;
    void updateBook(Book book, Connection connection, int bookID) throws SQLException;
    void deleteBook(Connection connection, int bookID) throws SQLException;
    void findBookByName(Connection connection, String bookName);
    void findBookByCost(Connection connection, int bookCost);
    void findBookByCategory(Connection connection, String bookCategory);
    void findBookByCostRange(Connection connection, int bookStartingCost, int bookEndingCost);
    String findCostliestBook(Connection connection);
    String findCheapestBook(Connection connection);
    int deleteOldBooks(Connection connection, int year);
}
