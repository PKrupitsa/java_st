package ru.stqa.pft.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import java.util.Set;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
        public void ensurePreconditions() {
            if (app.getContactHelper().all().size() == 0){
                app.getContactHelper().createContact(new ContactData().withFirstname("Aaaaa").withLastname("Bbbbbb"), true);
            }
        }
    @Test (enabled = true)
    public void testAddressModification() {
        Set<ContactData> before = app.getContactHelper().all();
        ContactData deletedContact = before.iterator().next();
        app.getContactHelper().delete(deletedContact);
        Set<ContactData> after = app.getContactHelper().all();
        Assert.assertEquals(after.size(), before.size() - 1);
        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }

}
