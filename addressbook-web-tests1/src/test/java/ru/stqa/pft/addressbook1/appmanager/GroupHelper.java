package ru.stqa.pft.addressbook1.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook1.model.GroupData1;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends BaseHelper {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData1 groupData1) {
    type(By.name("group_name"), groupData1.getName());
    type(By.name("group_header"), groupData1.getHeader());
    type(By.name("group_footer"), groupData1.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  public void createGroup(GroupData1 group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
   return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData1> getGroupList() {
    List<GroupData1> groups = new ArrayList<GroupData1>();
   List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData1 group = new GroupData1(id, name, null, null);
      groups.add(group);
    }
    return groups;
  }
}
