package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;
public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
       

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }


    public void initContactAdd() {

        click(By.linkText("add new"));
    }

    public void submitContactAdd() {

        click(By.name("submit"));
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("(//img[@alt='Edit'])")).get(index).click();
    }

    public void submitContactModification() {

        click(By.name("update"));
    }

    public void submitContactDeletion() {

        submit();
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
        
    }

    public void deleteSelectedContact() {

        click(By.xpath("//input[@value='Delete']"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void createContact(ContactData contactData, boolean creation) {
        initContactAdd();
        fillContactForm(contactData, creation);
        submitContactAdd();
        returnToHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("(//img[@alt='Edit'])"));
    }
    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name= 'entry']"));
        for (WebElement element : elements) {
            List<WebElement> field = element.findElements(By.tagName("td"));
            String firstname = field.get(2).getText();
            String lastname = field.get(1).getText();
            ContactData contact = new ContactData (firstname, null, lastname,null,null , null, null, null);
            contacts.add(contact);
                    }
        return contacts;
    }
}

 
