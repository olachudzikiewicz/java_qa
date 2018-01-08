package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.Contact;
import ru.stqa.pft.addressbook1.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData().withName("imie").withSurname(null).withPhoneNumber
              ("222-333-444").withEmail("ola@wp.pl"));
    }
  }

  @Test
  public void testContactModification(){
    Contact before = app.getContactHelper().allContact();
    ContactData modifiedContact = before.iterator().next();
    ContactData group = new ContactData().withId(modifiedContact.getId()).withName("Martyna")
            .withSurname("Siwy").withPhoneNumber("222-333-444").withEmail("ola@wp.pl");
    app.getContactHelper().modifyContact(group);
    Contact after = app.getContactHelper().allContact();

    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAddedContact(group)));
  }


}
