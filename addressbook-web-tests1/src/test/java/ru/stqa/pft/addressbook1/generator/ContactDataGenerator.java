package ru.stqa.pft.addressbook1.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook1.model.ContactData;
import ru.stqa.pft.addressbook1.model.GroupData1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count ;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main (String[] args) throws IOException {
    GroupDataGenerator generator= new GroupDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contact = generateContact(count);
    if (format.equals("xml")) {
      saveAsXml(contact, new File(file));
    } else {
      System.out.println("Nierozpoznany format: " + format);
    }
  }

  private void saveAsXml(List<ContactData> contact, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.alias("contact", ContactData.class);
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contact);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private List<ContactData> generateContact(int count) {
    List<ContactData> contact = new ArrayList<ContactData>();
    for (int i=0; i<count; i++) {
      contact.add(new ContactData().withName(String.format("Imie %s",i)).withSurname(String.format("Nazwisko %s",i))
              .withHomePhone((String.format("444-44%s-%s-22",i,i))).withEmail(String.format("email%s@onet.eu",i)));
    }
    return contact;
  }




}
