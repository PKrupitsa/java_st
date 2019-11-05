package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import java.util.Set;

public class ContactModificationTests extends TestBase {
        @BeforeMethod public void ensurePreconditions() {
        if (app.getContactHelper().all().size() == 0){
            app.getContactHelper().createContact(new ContactData().withFirstname("Aaaaa").withLastname("Ccccccc"), true);
        }
    }

     @Test (enabled = true)
    public void testAddressModification() {
        Set<ContactData> before = app.getContactHelper().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("Aaaa").withLastname("Bbbbb");
        app.getContactHelper().modify(contact);
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);

    }

   }

