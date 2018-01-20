package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInformationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().goToHome();
    if (app.db().contacts().size() == 0) {
      app.getContactHelper().createContact(new ContactData().withName("Baza").withSurname("Danych").withPhoneNumber
              ("222-333-444").withEmail("ola@wp.pl"));
    }
  }

  @Test
  public void testContactInformation() {
    app.getNavigationHelper().goToHome();
    ContactData contact = app.getContactHelper().allContact().iterator().next();
    ContactData contactInfoFromEditForm = app.getContactHelper().infoFromEditForm(contact);
    String tekst = app.getContactHelper().infoFromVcard(contact);

    assertThat(cleaned(tekst), equalTo(mergeAll(contactInfoFromEditForm)));
  }

  private String mergeAll(ContactData contact) {
    return Arrays.asList(contact.getName(), contact.getSurname(), contact.getAddress(), contact.getHomePhone(), contact
                    .getMobilePhone(),
            contact.getWorkPhone(), contact.getEmail(), contact.getEmail1(), contact.getEmail2())
            .stream()
            .filter((s) -> !s.equals(""))
            .map(ContactInformationTest::cleaned1)
            .collect(Collectors.joining(""));

  }

  public static String cleaned(String text) {
    return text.replaceAll("M:", "").replaceAll("W:", "")
            .replaceAll("H:", "").replaceAll("\n", "").replaceAll(" ", "");

  }

  public static String cleaned1 (String daneZFormularza) {
    return daneZFormularza.replaceAll(" ", "").replaceAll("\n", "");

  }

}
