package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;

public class GroupDelete extends TestBase {

  @Test
  public void testGroupDelete() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData1("test", null, null));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }
}
