package ru.stqa.pft.addressbook1;

import org.testng.annotations.Test;

public class ContactTest extends TestBase {

  @Test
  public void testContact() {

    goToContact();
    fillData(new ContactData("Imie", "Nazwisko", "222-333-444", "ola@wp.pl"));
    submitContactData();
    returnToPage();
  }

}
