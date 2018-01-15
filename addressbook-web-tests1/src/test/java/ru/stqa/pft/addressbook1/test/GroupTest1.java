package ru.stqa.pft.addressbook1.test;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;
import ru.stqa.pft.addressbook1.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupTest1 extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml+=line;
      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData1.class);
    List<GroupData1> groups = (List<GroupData1>) xstream.fromXML(xml);
    return groups.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validGroupsFromXml")
  public void testGroup(GroupData1 group) {
    app.getNavigationHelper().gotoGroupPage();
    Groups before = app.getGroupHelper().all();
    app.getGroupHelper().createGroup(group);
   Groups after = app.getGroupHelper().all();

    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
