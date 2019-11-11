package ru.stqa.pft.adressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static org.testng.Assert.assertEquals;

public class ContactAddTests extends TestBase {
    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson= new Gson();
        List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
    }

    @Test (dataProvider = "validContactsFromJson")
    public void ContactAddTests(ContactData contact) throws Exception {
        app.goTo().goToHomePage();
        Set<ContactData> before = app.contact().all();
        app.contact().initContactAdd();
        app.contact().create((contact), true);
        Set<ContactData> after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);
        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        assertEquals(before, after);
    }

    @Test (enabled = true)
    public void testContactAdd() throws Exception {
        app.goTo().goToHomePage();
        Set<ContactData> before = app.contact().all();
        app.contact().initContactAdd();
        File photo = new File("src/test/resources/image.jpg");
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
@Test (enabled = false)
    public void testCurrentDir(){
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/image.jpg");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
}


}