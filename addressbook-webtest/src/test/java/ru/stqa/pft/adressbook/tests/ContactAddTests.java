package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactAddTests extends TestBase {

    @Test
    public void testContactAdding() throws Exception {
        app.getContactHelper().initContactAdd();
        app.getContactHelper().fillContactForm(new ContactData("ffffff", "qwert", "t12223", "11111", "313131", "qwewqeqw", "eeeee"));
        app.getContactHelper().submitContactAdd();
        app.getNavigationHelper().returnToHomePage();
    }
}
