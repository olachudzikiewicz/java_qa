package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHome();
    app.getContactHelper().chooseContact();
    app.getContactHelper().chooseUpdateOption();
    app.getContactHelper().fillData(new ContactData("Anna", "Nowak", "222-333-444", "ola@wp.pl"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

  }
}
