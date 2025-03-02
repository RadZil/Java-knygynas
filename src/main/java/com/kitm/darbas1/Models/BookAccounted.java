package com.kitm.darbas1.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class BookAccounted {
    private IntegerProperty id;
    private IntegerProperty BookId;
    private IntegerProperty ReaderId;
    private StringProperty TakenDate;
    private StringProperty ReturnDate;
    private StringProperty ReturnedDate;
    private StringProperty Status;


    public BookAccounted(int id,int BookId, int ReaderId, String TakenDate, String ReturnDate, String ReturnedDate, String Status) {
        this.id = new SimpleIntegerProperty(id);
        this.BookId = new SimpleIntegerProperty(BookId);
        this.ReaderId = new SimpleIntegerProperty(ReaderId);
        this.TakenDate = new SimpleStringProperty(TakenDate);
        this.ReturnDate = new SimpleStringProperty(ReturnDate);
        this.ReturnedDate = new SimpleStringProperty(ReturnedDate);
        this.Status = new SimpleStringProperty(Status);


    }




    public int getBookId() {
        return BookId.get();
    }

    public int getId() {
        return id.get();
    }

    public int getReaderId() {
        return ReaderId.get();
    }

    public String getReturnDate() {
        return ReturnDate.get();
    }

    public String getReturnedDate() {
        return ReturnedDate.get();
    }

    public String getStatus() {
        return Status.get();
    }

    public String getTakenDate() {
        return TakenDate.get();
    }



    public void setId(int id) {
        this.id.set(id);
    }

    public void setBookId(int bookId) {
        this.BookId.set(bookId);
    }

    public void setReaderId(int readerId) {
        this.ReaderId.set(readerId);
    }

    public void setReturnDate(String returnDate) {
        this.ReturnDate.set(returnDate);
    }

    public void setReturnedDate(String returnedDate) {
        this.ReturnedDate.set(returnedDate);
    }

    public void setStatus(String status) {
        this.Status.set(status);
    }
    public void setTakenDate(String takenDate) {
        this.TakenDate.set(takenDate);
    }


}
