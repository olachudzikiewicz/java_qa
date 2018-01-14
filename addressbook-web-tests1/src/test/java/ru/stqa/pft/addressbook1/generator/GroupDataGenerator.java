package ru.stqa.pft.addressbook1.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook1.model.GroupData1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  @Parameter(names = "-c", description = "Group count")
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
    List<GroupData1> groups = generateGroups(count);
    if (format.equals("xml")) {
      saveAsXml(groups, new File(file));
    } else {
      System.out.println("Nierozpoznany format: " + format);
    }
  }

  private void saveAsXml(List<GroupData1> groups, File file) throws IOException {
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
