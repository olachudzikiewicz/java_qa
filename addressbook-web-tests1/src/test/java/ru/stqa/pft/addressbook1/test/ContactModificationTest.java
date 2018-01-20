package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.Contact;
import ru.stqa.pft.addressbook1.model.ContactData;

import java.io.File;
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
    if (app.db().contacts().size() == 0) {
      app.getContactHelper().createContact(new ContactData().withName("Baza").withSurname("Danych").withPhoneNumber
              ("222-333-444").withEmail("ola@wp.pl"));
    }
  }

  @Test
  public void testContactModification(){
    Contact before =app.db().contacts();
    File photo = new File("src/test/resources/test1.png");
    ContactData modifiedContact = before.iterator().next();
    ContactData group = new ContactData().withId(modifiedContact.getId()).withName("Martyna")
            .withSurname("Siwy").withPhoneNumber("222-333-444").withEmail("ola@wp.pl").withPhoto(photo);
    app.getContactHelper().modifyContact(group);
    Contact after =app.db().contacts();

    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.without(modifiedContact).withAddedContact(group)));
  }


}
