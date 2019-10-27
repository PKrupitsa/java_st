package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }
    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }
    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }
    public void goToHomePage() {
        wd.findElement(By.linkText("home")).click();
    }
}