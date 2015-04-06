package com.automationrhapsody.reststub.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person() {
        // Needed by Jackson deserialization
    }

    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    @JsonProperty
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty
    public String getLastName() {
        return lastName;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }
}