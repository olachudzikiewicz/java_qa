package ru.stqa.pft.addressbook1.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.pft.addressbook1.model.Contact;
import ru.stqa.pft.addressbook1.model.ContactData;


import java.util.*;

import static java.util.concurrent.TimeUnit.SECONDS;

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
   // type(By.name("home"),contactData.getPhoneNumber());
    type(By.name("email"),contactData.getEmail());
    type(By.name("email2"),contactData.getEmail1());
    type(By.name("email3"),contactData.getEmail2());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getHomePhone());
    type(By.name("mobile"),contactData.getMobilePhone());
    type(By.name("work"),contactData.getWorkPhone());
    attach(By.name("photo"),contactData.getPhoto());
  }

  public void returnToPage() {
    click(By.xpath("//div/div[4]/div/i/a[2]"));
  }

  public void chooseContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void chooseContactById(int id) {
    wd.findElement(By.cssSelector("input[value='"+ id +"']")).click();
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

  public void deleteContact(int index) {
    chooseContact(index);
    deleteContact();
    windowAccept();
  }

  public void deleteContact(ContactData deletedContact) {
    chooseContactById(deletedContact.getId());
    deleteContact();
    windowAccept();
  }

  public void modifyContact(ContactData group) {
    chooseContactById(group.getId());
    chooseUpdateOption(group.getId());
    fillData(group);
    submitContactModification();
    returnToHomePage();
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
        contact.add(new ContactData().withId(id).withName(name).withSurname(surname));
    }

    return contact;
  }

  public Contact allContact() {
    Contact contact = new Contact();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String surname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
     // String[] phones = allPhones.split("\n");
      String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText();
      String allEmail = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
     // String[] email = allEmail.split("\n");
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contact.add(new ContactData().withId(id).withName(name).withSurname(surname).withAllPhones(allPhones)
              .withAllEmail(allEmail).withAddress(address));
    }

    return contact;
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withName(firstname).withSurname(lastname).withHomePhone(home)
            .withMobilePhone(mobile).withWorkPhone(work).withAddress(address).withEmail(email).withEmail1(email2)
            .withEmail2(email3);
  }

  //to samo realizuje co chooseContactById
  private void initContactModificationById (int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s'",id )));
    WebElement row = checkbox.findElement(By.xpath("./../..")); //robimy dwa przejścia w górę od bieżącego elementu
    List<WebElement> cells = row.findElements(By.tagName("td")); //znajdujemy wszystkie kolumny
    cells.get(7).findElement(By.tagName("a")).click(); //wybieramy 8 kolumnę (numeracja od 0) żeby wybrać ołówek
    // (funkcja modyfikacji)

    //alternatywne rozwiazania (zamiast tych 4 linijek):
  //  wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
   // wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
   // wd.findElement(By.cssSelector(String.format("a[href='edit.php?id='%s']", id))).click();
  }

  public String infoFromVcard(ContactData contact) {
    int id = contact.getId();
    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[7]/a", id))).click();
    String tekst = wd.findElement(By.xpath("//div[@id='content']")).getText();
            return tekst;
  }

  public void CheckIfDeleted() {
    WebDriverWait wait = new WebDriverWait(wd, 10);
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("searchstring")));
    }
}
