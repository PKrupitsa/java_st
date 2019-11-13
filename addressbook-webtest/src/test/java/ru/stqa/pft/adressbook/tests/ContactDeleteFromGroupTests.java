package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.adressbook.model.ContactData;
import ru.stqa.pft.adressbook.model.Contacts;
import ru.stqa.pft.adressbook.model.GroupData;
import ru.stqa.pft.adressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteFromGroupTests extends TestBase {

    @BeforeMethod
    public void  ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1"));
        }
        if (app.db().contacts().size() == 0) {
            app.goTo().goToHomePage();
            GroupData newGroup = app.db().groups().iterator().next();
            app.contact().create(new ContactData().withFirstname("test1").withLastname("test2").inGroup(newGroup), true);
        }
        app.goTo().goToHomePage();
    }

    @Test
    public void testContactDeletionFromGroup() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        if (!contacts.stream().filter((s) -> (s.getGroups().size() > 0)).findAny().isPresent()) {
            app.contact().addToGroup(contacts.iterator().next(), groups.iterator().next());
        }
        ContactData removedContact = app.db().contacts().stream().filter((s) -> (s.getGroups().size() > 0)).findAny().get();
        Groups before = removedContact.getGroups();
        GroupData modifiedGroup = before.iterator().next();
        app.contact().deleteFromGroup(modifiedGroup, removedContact);
        Groups after = app.db().contacts().stream().filter((s) -> s.equals(removedContact)).findFirst().get().getGroups();;
        assertThat(after, equalTo(before.without(modifiedGroup)));
    }
}