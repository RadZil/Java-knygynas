package com.kitm.darbas1.Controllers;

import com.kitm.darbas1.Models.Book;
import com.kitm.darbas1.Models.BookAccounted;
import com.kitm.darbas1.Models.Model;
import com.kitm.darbas1.Utilities.AlertUtility;
import com.kitm.darbas1.Utilities.DialogUtility;
import com.kitm.darbas1.Views.MenuItems;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BookAccountingController implements Initializable {


    public Button GiveBook_btn;
    public TableView Book_table;
    public TableColumn col_Id;
    public TableColumn col_BookId;
    public TableColumn col_takenDate;
    public TableColumn col_returnDate;
    public TableColumn col_returnedDate;
    public TableColumn col_Status;
    public MenuItem remove_Book;
    public MenuItem return_book;
    public Button filterButton;
    public ChoiceBox filter_choice;
    private FilteredList<BookAccounted> filteredBooks;

    String[] FilterOptions= {"Taken","Returned"};

    @Override
    public void initialize(URL url,ResourceBundle resourceBundle){
        GiveBook_btn.setOnAction(event -> onAddBookAccounting());

        filter_choice.getItems().addAll(FilterOptions);
        filter_choice.setValue("");

        initTableCollumns();

    }


    public void onAddBookAccounting(){
        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.TAKE_BOOK);
    }

    private void initTableCollumns(){
        col_Id.setCellValueFactory(new PropertyValueFactory<Book, String>("Id"));
        col_BookId.setCellValueFactory(new PropertyValueFactory<Book, String>("BookId"));
        col_returnDate.setCellValueFactory(new PropertyValueFactory<Book, String>("ReturnDate"));
        col_takenDate.setCellValueFactory(new PropertyValueFactory<Book, String>("TakenDate"));
        col_returnedDate.setCellValueFactory(new PropertyValueFactory<Book, String>("ReturnedDate"));
        col_Status.setCellValueFactory(new PropertyValueFactory<Book, String>("Status"));
    }

    public void loadBookAccounting(){
        ObservableList<BookAccounted> bookAccounted = Model.getInstance().findAllAccounted();

        filteredBooks = new FilteredList<>(bookAccounted);
        Book_table.setItems(filteredBooks);

    }

    public void returnBook(){
        BookAccounted selectedBook = (BookAccounted) Book_table.getSelectionModel().getSelectedItem();

        if (selectedBook.getStatus() == "Returned") {
            AlertUtility.displayError("Knyga grazintaa");

        }else{
            AlertUtility.displayError("Taken");
            loadBookAccounting();
        }
    }

    private void onRemoveAccount()
    {
        BookAccounted selectedBook = (BookAccounted) Book_table.getSelectionModel().getSelectedItem();
        if (selectedBook == null) {
            AlertUtility.displayError("Pasirinkite knyga");
        }

        if (selectedBook.getStatus() == "Taken") {
            Model.getInstance().setTaken(selectedBook.getBookId(),"Returned");
        }
    }

}
