package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.Contact;
import ru.stqa.pft.addressbook1.model.ContactData;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTest extends TestBase {

  @Test
  public void testContact() {
    app.getNavigationHelper().goToHome();
    Contact before = app.getContactHelper().allContact();
    app.getNavigationHelper().goToContact();
    File photo = new File("src/test/resources/test1.png");
    ContactData group = new ContactData().withName("Test").withSurname("Plik").withHomePhone("222-333-444")
            .withMobilePhone("333").withWorkPhone("444")
            .withEmail("ola@wp.pl").withEmail1("uuu@wp.pl").withEmail2("rrr@wp.pl").withAddress("ul. Kościuszki 23 " +
                    "Katowice").withPhoto(photo);
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
