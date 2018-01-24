package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;
import ru.stqa.pft.addressbook1.model.GroupData1;
import ru.stqa.pft.addressbook1.model.Groups;

public class ContactDeleteFromGroup extends TestBase {
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
  public void testDeleteContactFromGroup() {
    Groups groupList = app.db().groups();
    app.getNavigationHelper().goToHome();
    app.getContactHelper().deleteContactFromGroup(groupList);
  }
}
