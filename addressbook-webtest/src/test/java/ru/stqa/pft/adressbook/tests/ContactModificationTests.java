package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

public class ContactModificationTests extends TestBase {
        @BeforeMethod public void ensurePreconditions() {
        if(app.db().contacts().size() ==0){
            app.contact().create(new ContactData().withFirstname("Aaaaa").withLastname("Ccccccc"), true);
        }
    }

     @Test (enabled = true)
    public void testAddressModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Aaaa").withLastname("Bbbbb");
        app.contact().modify(contact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }

   }

