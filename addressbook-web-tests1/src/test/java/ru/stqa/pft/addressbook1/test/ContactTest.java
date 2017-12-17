package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;

public class ContactTest extends TestBase {

  @Test
  public void testContact() {

    app.getNavigationHelper().goToContact();
    app.getContactHelper().fillData(new ContactData("Imie", null, "222-333-444", "ola@wp.pl"));
    app.getContactHelper().submitContactData();
    app.getContactHelper().returnToPage();
  }

}
