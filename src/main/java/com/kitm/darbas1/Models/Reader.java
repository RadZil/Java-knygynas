package com.kitm.darbas1.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reader {
    private StringProperty name;
    private StringProperty lastName;
    private StringProperty email;
    private StringProperty city;
    private IntegerProperty UserId;

    public Reader(String name, String lastName, String email, String city, int UserId) {
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.city = new SimpleStringProperty(city);
        this.UserId = new SimpleIntegerProperty(UserId);


    }

    public String getName() {
        return name.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setName(String name) {

        this.name.set(name);
    }
    public String getEmail() {
        return email.get();
    }
    public void setEmail(String email) {
        this.email.set(email);
    }
    public String getCity() {
        return city.get();
    }
    public void setCity(String city) {
        this.city.set(city);
    }
    public int getUserId() {

        return UserId.get();
    }
    public void setUserId(int userId) {

        UserId.set(userId);
    }


}
