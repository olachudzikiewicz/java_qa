package ru.stqa.pft.addressbook1.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook1.model.ContactData;

public class ContactHelper extends BaseHelper {


  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactData() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillData(ContactData contactData) {
    type(By.name("firstname"),contactData.getName());
    type(By.name("lastname"),contactData.getSurname());
    type(By.name("home"),contactData.getPhoneNumber());
    type(By.name("email"),contactData.getEmail());
  }

  public void returnToPage() {
    click(By.xpath("//div/div[4]/div/i/a[2]"));
  }
}
