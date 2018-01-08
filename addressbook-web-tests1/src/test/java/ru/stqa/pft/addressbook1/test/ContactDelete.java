package ru.stqa.pft.addressbook1.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.Contact;
import ru.stqa.pft.addressbook1.model.ContactData;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;


public class ContactDelete extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData().withName("imie").withSurname(null).withPhoneNumber
              ("222-333-444").withEmail("ola@wp.pl"));
    }
  }

  @Test
  public void testContactDelete(){
    Contact before = app.getContactHelper().allContact();
    ContactData deletedContact = before.iterator().next();
    app.getContactHelper().deleteContact(deletedContact);
    Contact after = app.getContactHelper().allContact();

    assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    assertThat(after, equalTo(before.without(deletedContact)));
  }




}
