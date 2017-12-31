package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;

import java.util.List;


public class ContactDelete extends TestBase {

  @Test
  public void testContactDelete(){
    app.getNavigationHelper().goToHome();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Imie", null, "222-333-444", "ola@wp.pl"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().chooseContact(before.size() - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().windowAccept();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
    Assert.assertEquals(after, before);
  }


}
