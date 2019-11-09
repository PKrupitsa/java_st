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
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getPhoneHome());
        type(By.name("mobile"), contactData.getPhone());
        type(By.name("work"), contactData.getPhoneWork());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
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
            String address = field.get(3).getText();
            String allEmails = field.get(4).getText();
            String allPhones = field.get(5).getText();
            int id = Integer.parseInt(field.get(0).findElement(By.tagName("input")).getAttribute("value"));

            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAddress(address).withAllEmails(allEmails)
            .withAllPhones(allPhones));
        }
        return contacts;
    }
    public ContactData infoFromEditForm(ContactData contact) {
        editContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homephone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workphone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withPhoneHome(homephone).withPhone(mobilephone).withPhoneWork(workphone)
                .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3);
    }
}