package ru.stqa.pft.addressbook1.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;


public class GroupTest1 extends TestBase {

  @Test
  public void testGroup() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().createGroup(new GroupData1("test2", null, null));
    int after = app.getGroupHelper().getGroupCount();

    Assert.assertEquals(after, before + 1);
  }

}
