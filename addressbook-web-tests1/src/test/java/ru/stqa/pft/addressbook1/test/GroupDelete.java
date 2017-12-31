package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;

import java.util.List;

public class GroupDelete extends TestBase {

  @Test
  public void testGroupDelete() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData1("test", null, null));
    }
    List<GroupData1> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();

    List<GroupData1> after = app.getGroupHelper().getGroupList();

    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(before.size() - 1);
      Assert.assertEquals(after, before);

  }
}
