package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.Test;

public class ContactDelete extends TestBase {

  @Test
  public void testContactDelete(){
    app.getNavigationHelper().goToHome();
    app.getContactHelper().chooseContact("14");
    app.getContactHelper().deleteContact();
    app.getContactHelper().windowAccept();
  }
}
