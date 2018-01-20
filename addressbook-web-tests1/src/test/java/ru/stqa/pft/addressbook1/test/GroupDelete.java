package ru.stqa.pft.addressbook1.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;
import ru.stqa.pft.addressbook1.model.Groups;

import java.security.acl.Group;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDelete extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.getNavigationHelper().gotoGroupPage();
      app.getGroupHelper().createGroup(new GroupData1().withName("test"));
    }
  }

  @Test
  public void testGroupDelete() {
    Groups before = app.db().groups();
    GroupData1 deletedGroup = before.iterator().next();
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().delete(deletedGroup);
    Groups after = app.db().groups();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedGroup);
    assertThat(after, equalTo(before.without(deletedGroup)));


  }


}
