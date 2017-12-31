package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactTest extends TestBase {

  @Test
  public void testContact() {
    app.getNavigationHelper().goToHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().goToContact();
    ContactData group = new ContactData("Imie", "Nowak", "222-333-444", "ola@wp.pl");
    app.getContactHelper().fillData(group);
    app.getContactHelper().submitContactData();
    app.getContactHelper().returnToPage();
    List<ContactData> after = app.getContactHelper().getContactList();

    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super ContactData> byId = (g1 , g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }

}
