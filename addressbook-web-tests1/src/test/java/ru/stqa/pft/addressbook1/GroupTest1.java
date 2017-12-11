package ru.stqa.pft.addressbook1;

import org.testng.annotations.Test;


public class GroupTest1 extends TestBase {

  @Test
  public void testGroup() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData1("test", "test1", "test2"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
