package com.kitm.darbas1.Controllers;

import com.kitm.darbas1.Models.Model;
import com.kitm.darbas1.Utilities.AlertUtility;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateReader implements Initializable {

    public TextField field_firstName;
    public TextField field_lastName;
    public TextField field_email;
    public TextField field_city;
    public Button create_reader_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_reader_btn.setOnAction(even -> onReader());
    }


    private void onReader()
    {
        String fName = field_firstName.getText();
        String lName = field_lastName.getText();
        String email = field_email.getText();
        String city = field_city.getText();

        // Create the reader

        Model.getInstance().createReader(fName, lName, email, city);

        AlertUtility.displayInformation("Skaitytojas sekmingai sukurtas");

        emptyFields();
    }

    private void emptyFields()
    {
        field_firstName.setText("");
        field_lastName.setText("");
        field_email.setText("");
        field_city.setText("");
    }

}
