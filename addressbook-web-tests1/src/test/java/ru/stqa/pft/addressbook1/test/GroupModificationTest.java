package ru.stqa.pft.addressbook1.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;
import ru.stqa.pft.addressbook1.model.Groups;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData1().withName("test"));
    }
  }

  @Test
  public void  testGroupModification() {
    Groups before = app.getGroupHelper().all();
    GroupData1 modifiedGroup = before.iterator().next();
    GroupData1 group = new GroupData1().withId(modifiedGroup.getId()).withName("test").withHeader
            ("test3").withFooter("Test6");
    app.getGroupHelper().modify(group);
    Groups after = app.getGroupHelper().all();
    Assert.assertEquals(after.size(), before.size());

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }


}
