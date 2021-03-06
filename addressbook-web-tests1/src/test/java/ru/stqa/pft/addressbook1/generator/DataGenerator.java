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

public class DataGenerator {

  @Parameter(names = "-c", description = "Count")
  public int count ;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  @Parameter (names = "-fc", description = "Target file")
  public String fileContact;

  @Parameter (names = "-d", description = "Data format")
  public String format;

  public static void main (String[] args) throws IOException {
    DataGenerator generator= new DataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.runContact();
    generator.run();

  }

  private void runContact() throws IOException {
    List<ContactData> contact = generateContact(count);
    if (format.equals("xml")) {
      saveAsXmlContact(contact, new File(fileContact));
    } else {
      System.out.println("Nierozpoznany format: " + format);
    }
  }

  private List<ContactData> generateContact(int count) {
    List<ContactData> contact = new ArrayList<ContactData>();
  //  File photo = new File("src/test/resources/test1.png");
    for (int i=0; i<count; i++) {
      contact.add(new ContactData().withName(String.format("Imie %s",i)).withSurname(String.format("Nazwisko %s",i))
              .withHomePhone((String.format("444-44%s-%s-22",i,i))).withEmail(String.format("email%s@onet.eu",i)));
    }
    return contact;
  }

  private void saveAsXmlContact(List<ContactData> contact, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.alias("contact", ContactData.class);
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contact);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void run() throws IOException {
    List<GroupData1> groups = generateGroups(count);
    if (format.equals("xml")) {
      saveAsXmlGroup(groups, new File(file));
    } else {
      System.out.println("Nierozpoznany format: " + format);
    }
  }

  private void saveAsXmlGroup(List<GroupData1> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.alias("group", GroupData1.class);
    xstream.processAnnotations(GroupData1.class);
    String xml = xstream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private List<GroupData1> generateGroups(int count) {
    List<GroupData1> groups = new ArrayList<GroupData1>();
    for (int i=0; i<count; i++) {
      groups.add(new GroupData1().withName(String.format("Test %s",i)).withHeader(String.format("Header %s",i))
              .withFooter(String.format("Footer %s",i)));
    }
    return groups;
  }






}
