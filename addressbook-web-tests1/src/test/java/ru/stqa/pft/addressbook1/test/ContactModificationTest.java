package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTest extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().goToHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Imie", null, "222-333-444", "ola@wp.pl"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().chooseContact(before.size() - 1);
    app.getContactHelper().chooseUpdateOption(before.get(before.size() - 1).getId());
    ContactData group = new ContactData(before.get(before.size() - 1).getId(),"Martyna", "Siwy", "222-333-444",
            "ola@wp" +
            ".pl");
    app.getContactHelper().fillData(group);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
