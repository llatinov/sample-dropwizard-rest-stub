package com.automationrhapsody.reststub.persistence;

import com.automationrhapsody.reststub.data.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDB {
    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();

    static {
        persons.put(1, new Person(1, "FN1", "LN1", "email1@email.na"));
        persons.put(2, new Person(2, "FN2", "LN2", "email2@email.na"));
        persons.put(3, new Person(3, "FN3", "LN3", "email3@email.na"));
        persons.put(4, new Person(4, "FN4", "LN4", "email4@email.na"));
    }

    public static Person getById(int id) {
        return persons.get(id);
    }

    public static List<Person> getAll() {
        List<Person> result = new ArrayList<Person>();
        for (Integer key : persons.keySet()) {
            result.add(persons.get(key));
        }
        return result;
    }

    public static int getCount() {
        return persons.size();
    }

    public static void remove() {
        if (!persons.keySet().isEmpty()) {
            persons.remove(persons.keySet().toArray()[0]);
        }
    }

    public static String save(Person person) {
        String result = "";
        if (persons.get(person.getId()) != null) {
            result = "Updated Person with id=" + person.getId();
        } else {
            result = "Added Person with id=" + person.getId();
        }
        persons.put(person.getId(), person);
        return result;
    }
}
