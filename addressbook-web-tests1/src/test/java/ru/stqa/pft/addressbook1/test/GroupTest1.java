package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;

import java.util.HashSet;
import java.util.List;


public class GroupTest1 extends TestBase {

  @Test
  public void testGroup() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData1> before = app.getGroupHelper().getGroupList();
    GroupData1 group = new GroupData1("test2", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData1> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() + 1);

      int max = 0;
    for (GroupData1 g: after) {
      if (g.getId()>max) {
        max = g.getId();
      }
    }
    group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }

}
