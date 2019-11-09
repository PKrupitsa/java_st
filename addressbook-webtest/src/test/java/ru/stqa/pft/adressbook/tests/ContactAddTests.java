package ru.stqa.pft.adressbook.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import java.util.Set;
import static org.testng.Assert.assertEquals;

public class ContactAddTests extends TestBase {

    @Test (enabled = true)
    public void testAdressAdding() throws Exception {
        app.goTo().goToHomePage();
        Set<ContactData> before = app.contact().all();
        app.contact().initContactAdd();
        ContactData contact = new ContactData().withFirstname("Ffff").withLastname("Ccccc").withGroup("test1");
        app.contact().fillContactForm(contact, true);
        app.contact().submitContactAdd();
        app.contact().returnToHomePage();
        Set<ContactData> after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        assertEquals(before, after);

    }


}