package com.kitm.darbas1.Controllers;

import com.kitm.darbas1.Models.Author;
import com.kitm.darbas1.Models.Book;
import com.kitm.darbas1.Models.Model;
import com.kitm.darbas1.Models.Reader;
import com.kitm.darbas1.Utilities.AlertUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddBookAccounting implements Initializable {

    public ChoiceBox Books;
    public ChoiceBox Readers;
    public DatePicker datePicker;
    public Button create_Book_btn;

    List<String> allReaders = new ArrayList<>();
    List<String> allAuthors = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setChoiceBoxOptions();
//        create_Book_btn.setOnAction(event -> onCreate());
    }

   private void setChoiceBoxOptions(){
        ObservableList<Reader> readers = Model.getInstance().getReaders();
        ObservableList<Book> books = Model.getInstance().getBooks();



        for (Reader reader : readers) {
            allReaders.add(reader.getName());
        }

        for (Book book : books) {
            allAuthors.add(book.getName());
        }

   }


//   private void onCreate(){
//
//        String bookString = Books.
//
//   }

}
