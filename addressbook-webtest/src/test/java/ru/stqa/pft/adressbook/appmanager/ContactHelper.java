package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());

    }


    public void initContactAdd() {

        click(By.linkText("add new"));
    }

    public void submitContactAdd() {

        click(By.name("submit"));
    }
	public void initContactModification() {
	
        click(By.xpath("(//img[@alt='Edit'])"));
    }

    public void submitContactModification() {
		
        click(By.name("update"));
    }
    public void submitContactDeletion() {
		
        submit();
    }

    public void selectContact() {
		
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
		
        click(By.xpath("//input[@value='Delete']"));
    }
}
