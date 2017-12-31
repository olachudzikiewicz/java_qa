package ru.stqa.pft.addressbook1.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook1.model.ContactData;


import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {


  public ContactHelper(WebDriver wd) {
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

  public void chooseContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void chooseUpdateOption(int index) {
   // click(By.xpath("//table[@id='maintable']/tbody/tr["+liczba+"]/td[8]/a/img"));
   // click(By.xpath("//*[@title='Edit']"));
   click(By.xpath("//*[@href='edit.php?id="+index+"']" ));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }


  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact( ContactData contact) {
    click(By.linkText("add new"));
    fillData(contact);
    submitContactData();
    returnToPage();
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> contact = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.name("entry"));
      for (WebElement element : elements) {
        String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
        String surname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
        int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        ContactData group = new ContactData(id,name, surname, null,null);
        contact.add(group);
    }

    return contact;
  }
}
