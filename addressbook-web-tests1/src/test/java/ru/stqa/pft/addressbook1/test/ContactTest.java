package ru.stqa.pft.addressbook1.test;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.Contact;
import ru.stqa.pft.addressbook1.model.ContactData;
import ru.stqa.pft.addressbook1.model.GroupData1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml+=line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    List<ContactData> contact = (List<ContactData>) xstream.fromXML(xml);
    return contact.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContactFromXml")
  public void testContact(ContactData group) {
    app.getNavigationHelper().goToHome();
    Contact before =app.db().contacts();
    app.getNavigationHelper().goToContact();
    File photo = new File("src/test/resources/test1.png");
    group.withPhoto(photo);
    app.getContactHelper().fillData(group);
    app.getContactHelper().submitContactData();
    app.getContactHelper().returnToPage();
    Contact after =app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));

    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    assertThat(after, equalTo(before.withAddedContact(group.withId(after.stream().mapToInt((g) -> g.getId()).max()
            .getAsInt()))));
  }


}
