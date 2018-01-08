package ru.stqa.pft.addressbook1.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook1.model.GroupData1;
import ru.stqa.pft.addressbook1.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupTest1 extends TestBase {

  @Test
  public void testGroup() {
    app.getNavigationHelper().gotoGroupPage();
    Groups before = app.getGroupHelper().all();
    GroupData1 group = new GroupData1().withName("test2");
    app.getGroupHelper().createGroup(group);
   Groups after = app.getGroupHelper().all();

    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
