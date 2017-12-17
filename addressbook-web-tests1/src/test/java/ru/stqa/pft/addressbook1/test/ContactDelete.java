package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;


public class ContactDelete extends TestBase {

  @Test
  public void testContactDelete(){
    app.getNavigationHelper().goToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Imie", null, "222-333-444", "ola@wp.pl"));
    }
    app.getContactHelper().chooseContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().windowAccept();
  }
}
