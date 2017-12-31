package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class GroupTest1 extends TestBase {

  @Test
  public void testGroup() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData1> before = app.getGroupHelper().getGroupList();
    GroupData1 group = new GroupData1("testLambda", null, null);
    app.getGroupHelper().createGroup(group);
    List<GroupData1> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(group);
    Comparator<? super GroupData1> byId = (g1 , g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
