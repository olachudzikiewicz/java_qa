package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.Contact;
import ru.stqa.pft.addressbook1.model.ContactData;
import ru.stqa.pft.addressbook1.model.GroupData1;
import ru.stqa.pft.addressbook1.model.Groups;

public class ContactAddToGroup extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHome();
    if (app.db().contacts().size() == 0) {
      app.getContactHelper().createContact(new ContactData().withName("Baza").withSurname("Danych").withPhoneNumber
              ("222-333-444").withEmail("ola@wp.pl"));
    }
    app.getNavigationHelper().gotoGroupPage();
    if (app.db().groups().size() == 0) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData1().withName("test_to_contact"));
    }
  }

  @Test
  public void testAddContactToGroup() {
    Contact before = app.db().contacts();
    Groups groupList = app.db().groups();
    ContactData choosedContact = before.iterator().next();
    app.getNavigationHelper().goToHome();
    app.getContactHelper().addContactToGroup(choosedContact, groupList);
  }
}
