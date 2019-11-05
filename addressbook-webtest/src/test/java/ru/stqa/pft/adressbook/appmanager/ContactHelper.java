package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.adressbook.model.ContactData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public void editContactById(int id) {
        wd.findElement(By.xpath("//input[@id= '" + id + "']/parent::td/following-sibling::td[7]/a/img")).click();
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
    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "'")).click();
    }

    public void deleteSelectedContact() {

        click(By.xpath("//input[@value='Delete']"));
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }
    public void homePage () {
    	
    	 click(By.linkText("home")); 
    	  
    	 }

    public void createContact(ContactData contactData, boolean creation) {
        initContactAdd();
        fillContactForm(contactData, creation);
        submitContactAdd();
        returnToHomePage();
    }


    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        submitContactDeletion();
        homePage();
    }

    public void modify(ContactData contact) {
        editContactById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
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
            int id = Integer.parseInt(field.get(0).findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
                    }
        return contacts;
    }
    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name= 'entry']"));
        for (WebElement element : elements) {
            List<WebElement> field = element.findElements(By.tagName("td"));
            String firstname = field.get(2).getText();
            String lastname = field.get(1).getText();
            int id = Integer.parseInt(field.get(0).findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }

}


 
