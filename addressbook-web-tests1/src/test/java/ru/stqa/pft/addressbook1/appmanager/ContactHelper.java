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

  public void chooseContact(String id) {
    click(By.id(id));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void chooseUpdateOption() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[3]/td[8]/a/img"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }


  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }
}
