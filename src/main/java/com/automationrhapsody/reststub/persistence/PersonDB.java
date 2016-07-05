package com.automationrhapsody.reststub.persistence;

import com.automationrhapsody.reststub.data.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDB {
    private static final Map<Integer, Person> PERSONS = new HashMap<>();

    static {
        PERSONS.put(1, new Person(1, "FN1", "LN1", "email1@email.na"));
        PERSONS.put(2, new Person(2, "FN2", "LN2", "email2@email.na"));
        PERSONS.put(3, new Person(3, "FN3", "LN3", "email3@email.na"));
        PERSONS.put(4, new Person(4, "FN4", "LN4", "email4@email.na"));
    }

    public static Person getById(int id) {
        return PERSONS.get(id);
    }

    public static List<Person> getAll() {
        List<Person> result = new ArrayList<Person>();
        for (Integer key : PERSONS.keySet()) {
            result.add(PERSONS.get(key));
        }
        return result;
    }

    public static int getCount() {
        return PERSONS.size();
    }

    public static void remove() {
        if (!PERSONS.keySet().isEmpty()) {
            PERSONS.remove(PERSONS.keySet().toArray()[0]);
        }
    }

    public static String save(Person person) {
        String result = "";
        if (PERSONS.get(person.getId()) != null) {
            result = "Updated Person with id=" + person.getId();
        } else {
            result = "Added Person with id=" + person.getId();
        }
        PERSONS.put(person.getId(), person);
        return result;
    }
}
