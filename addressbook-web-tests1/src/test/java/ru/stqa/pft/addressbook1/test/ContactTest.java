package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.Contact;
import ru.stqa.pft.addressbook1.model.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTest extends TestBase {

  @Test
  public void testContact() {
    app.getNavigationHelper().goToHome();
    Contact before = app.getContactHelper().allContact();
    app.getNavigationHelper().goToContact();
    ContactData group = new ContactData().withName("Imie").withSurname("Nowak").withPhoneNumber("222-333-444")
            .withEmail("ola@wp.pl");
    app.getContactHelper().fillData(group);
    app.getContactHelper().submitContactData();
    app.getContactHelper().returnToPage();
    Contact after = app.getContactHelper().allContact();
    assertThat(after.size(), equalTo(before.size() + 1));

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAddedContact(group.withId(after.stream().mapToInt((g) -> g.getId()).max()
            .getAsInt()))));
  }

}
