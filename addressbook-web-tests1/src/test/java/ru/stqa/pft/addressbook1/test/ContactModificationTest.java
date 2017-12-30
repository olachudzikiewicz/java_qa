package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Imie", null, "222-333-444", "ola@wp.pl"));
    }
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().chooseContact(before - 1);
    app.getContactHelper().chooseUpdateOption();
    app.getContactHelper().fillData(new ContactData("Anna", "Nowak", "222-333-444", "ola@wp.pl"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

    int after = app.getContactHelper().getContactCount();

    Assert.assertEquals(after, before);
  }
}
