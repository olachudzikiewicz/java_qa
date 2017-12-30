package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;


public class ContactDelete extends TestBase {

  @Test
  public void testContactDelete(){
    app.getNavigationHelper().goToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Imie", null, "222-333-444", "ola@wp.pl"));
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().chooseContact(before - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().windowAccept();

    int after = app.getContactHelper().getContactCount();

    Assert.assertEquals(after, before - 1);
  }


}
