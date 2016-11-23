package com.automationrhapsody.reststub.persistence;

import com.automationrhapsody.reststub.data.Person;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonDBTest {

    @Test
    public void testGetById() {
        assertEquals("FN1", PersonDB.getById(1).getFirstName());
    }

    @Test
    public void testGetAll() {
        assertEquals("FN1", PersonDB.getAll().get(0).getFirstName());
    }

    @Test
    public void testGetCount() {
        assertEquals(4, PersonDB.getCount());
    }

    @Test
    public void testSaveAndRemove() {
        assertEquals(4, PersonDB.getCount());
        assertEquals("FN4", PersonDB.getAll().get(3).getFirstName());

        PersonDB.remove();

        assertEquals(3, PersonDB.getCount());
        assertEquals("FN3", PersonDB.getAll().get(2).getFirstName());

        Person person = new Person(11, "FN_New", "LN_New", "email@email.com");
        PersonDB.save(person);

        assertEquals(4, PersonDB.getCount());
        assertEquals("FN_New", PersonDB.getAll().get(3).getFirstName());
    }
}
