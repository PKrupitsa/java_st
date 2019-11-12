package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
        public void ensurePreconditions() {
            if (app.contact().all().size() == 0){
                app.contact().create(new ContactData().withFirstname("Aaaaa").withLastname("Bbbbbb"), true);
            }
        }
    @Test (enabled = true)
    public void testContactModification() {
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
        Contacts after = app.db().contacts();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
