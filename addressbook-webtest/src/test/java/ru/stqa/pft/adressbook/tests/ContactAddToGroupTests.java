package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddToGroupTests extends TestBase {
    @BeforeMethod
    public void  ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
        app.goTo().goToHomePage();
    }

    @Test
    public void testContactAddToGroupTests() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        if (!contacts.stream().filter((s) -> (s.getGroups().size() < groups.size())).findAny().isPresent()) {
            app.contact().create(new ContactData().withFirstname("test1"), false);
        }
        ContactData addedContact =
                app.db().contacts().stream().filter((s) -> (s.getGroups().size() < groups.size())).findAny().get();
        Groups before = addedContact.getGroups();
        GroupData group = groups.without(addedContact.getGroups()).iterator().next();
        app.contact().addToGroup(addedContact, group);
        Groups after = app.db().contacts().stream().filter((s) -> s.equals(addedContact)).findFirst().get().getGroups();
        assertThat(after, equalTo(before.withAdded(group)));
    }
}
