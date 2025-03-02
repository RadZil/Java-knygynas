package com.kitm.darbas1.Controllers;

import com.kitm.darbas1.Models.Book;
import com.kitm.darbas1.Models.Model;
import com.kitm.darbas1.Models.Reader;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReaderController implements Initializable {

    public Button add_reader_btn;
    public TableView reader_table;
    public TableColumn col_Id;
    public TableColumn col_firstName;
    public TableColumn col_lastName;
    public MenuItem remove_reader;
    public TextField filterFirstName;
    public TextField filterLastname;
    public Button filterButton;
    public TableColumn col_city;
    public TableColumn col_email;

    private FilteredList<Reader> filteredReaders;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add_reader_btn.setOnAction(event -> onAddReader());

        initTableCollumns();
        loadReaderData();
        setRowFactoryForAuthorsTable();

        filterButton.setOnAction(event -> applyFilters());
    }


    public void onAddReader(){

        Model.getInstance().getViewFactory().getUserSelectedMenuItem().set(MenuItems.CREATE_READER);

    }

    private void initTableCollumns() {
        col_Id.setCellValueFactory(new PropertyValueFactory<>("Id"));
        col_firstName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));

    }

    private void loadReaderData() {
        List<Reader> readersList = Model.getInstance().getReaders();
        if (readersList == null) {
            readersList = new ArrayList<>();
        }
        ObservableList<Reader> readers = FXCollections.observableArrayList(readersList);
        filteredReaders = new FilteredList<>(readers);
        reader_table.setItems(filteredReaders);
    }



    private void onRemoverBook(){
        Reader selectedReader = (Reader) reader_table.getSelectionModel().getSelectedItem();

        if(selectedReader == null) {
            AlertUtility.displayError("Pasirinkite skaitytoja");
        }

        boolean confirmed = AlertUtility.displayConfirmation("Ar tirkai norite pašalinti skaitytoja?");

        if (confirmed){
            Model.getInstance().deleteReader(selectedReader.getUserId());

            loadReaderData();
            AlertUtility.displayInformation("Skaitytojas pasalinta sekmingai");
        }
    }

    private void setRowFactoryForAuthorsTable(){
        reader_table.setRowFactory(tv ->{
            TableRow<Reader> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    Reader selectedReader = row.getItem();
                    editReader(selectedReader);
                }
            });

            return row;
        });
    }

    private void editReader(Reader reader)
    {
        Optional<Reader> result = DialogUtility.showEditReaderDialog(reader);
        result.ifPresent(updateBook -> {
            Model.getInstance().updateReader(updateBook);
            loadReaderData();
        });
    }

    private void applyFilters(){
        String NameFilter = filterFirstName.getText().toLowerCase();
        String LastNameFilter = filterLastname.getText().toLowerCase();


        filteredReaders.setPredicate(reader->{
            if(!NameFilter.isEmpty() && !reader.getName().toLowerCase().contains(NameFilter)){
                return false;
            }

            if(!LastNameFilter.isEmpty() && !reader.getLastName().toLowerCase().contains(LastNameFilter)){
                return false;
            }


            return true;
        });
    }
}
