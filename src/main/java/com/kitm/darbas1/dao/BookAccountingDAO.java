package com.kitm.darbas1.dao;

import com.kitm.darbas1.Controllers.BookAccountingController;
import com.kitm.darbas1.Models.Book;
import com.kitm.darbas1.Models.BookAccounted;
import com.kitm.darbas1.Models.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.logging.Logger;

public class BookAccountingDAO {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BookAccountingDAO.class);
    private Connection conn;

    private static final Logger logger = Logger.getLogger(BookAccountingDAO.class.getName());

    public BookAccountingDAO(Connection conn)
    {
        this.conn = conn;
    }

    public void create(int bookID, int readerID, LocalDate giveBackDate)
    {
        String sql = "INSERT INTO AccountedBooks(BookID, ReaderID, TakenDate, GiveBackDate, GivenBackDate, Status, userID) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int userId = Model.getInstance().getLoggedUserId();

        try(PreparedStatement stmt = this.conn.prepareStatement(sql))
        {
            stmt.setInt(1, bookID);
            stmt.setInt(2, readerID);
            stmt.setString(3, LocalDate.now().toString());
            stmt.setString(4, giveBackDate.toString());
            stmt.setString(5, "-");
            stmt.setInt(6, 1);
            stmt.setInt(7, userId);

            stmt.executeUpdate();

            Model.getInstance().setTaken(bookID, "Returned");
        }
        catch (SQLException e)
        {
            logger.severe("Error creating GiveBook: " + e);
        }
    }

    public void update(BookAccounted bookAcc) {
        String sql = "UPDATE AccountedBooks SET ReturnDate = ?, Status = ? WHERE id = ?";

        try (PreparedStatement stmt = this.conn.prepareStatement(sql))
        {
            stmt.setString(1, bookAcc.getReturnedDate());
            stmt.setString(2, bookAcc.getStatus());
            stmt.setInt(3, bookAcc.getId());

            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.severe("Failed to update Book account " + e);
        }

    }

    public ObservableList<BookAccounted> findAll()
    {
        ObservableList<BookAccounted> bookAccs = FXCollections.observableArrayList();
        String sql = "SELECT id, BookID, ReaderID, TakenDate, ReturnDate, ReturnedDate, Status FROM AccountedBooks";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql))
        {
            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next())
            {
                int id = resultSet.getInt("id");
                int bookID = resultSet.getInt("BookID");
                int readerID = resultSet.getInt("ReaderID");
                String takenDate = resultSet.getString("TakenDate");
                String ReturnDate = resultSet.getString("ReturnDate");
                String ReturnedDate = resultSet.getString("ReturnedDate");
                String status = resultSet.getString("Status");

                BookAccounted bookAcc = new BookAccounted(id, bookID, readerID, takenDate, ReturnDate, ReturnedDate, status);

                bookAccs.add(bookAcc);
            }
        }
        catch (SQLException e)
        {
            logger.severe(e.getMessage());
        }

        return  bookAccs;
    }

    public void delete(int id)
    {
        String sql = "DELETE FROM AccountedBooks WHERE id = ?";

        try(PreparedStatement stmt = this.conn.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch (SQLException e)
        {
            logger.severe("Failled to delete book account " + e);
        }
    }
}