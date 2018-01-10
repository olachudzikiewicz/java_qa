package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHome();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData().withName("imie").withSurname(null).withPhoneNumber
              ("222-333-444").withEmail("ola@wp.pl"));
    }
  }

  @Test
  public void testContactEmail() {
    app.getNavigationHelper().goToHome();
    ContactData contact = app.getContactHelper().allContact().iterator().next();
    ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);

    assertThat(contact.getEmail(), equalTo(contactInfoFromEditForm.getEmail()));
    assertThat(contact.getEmail1(), equalTo(contactInfoFromEditForm.getEmail1()));
    assertThat(contact.getEmail2(), equalTo(contactInfoFromEditForm.getEmail2()));
  }

}
