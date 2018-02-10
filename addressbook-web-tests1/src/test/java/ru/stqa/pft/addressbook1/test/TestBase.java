package ru.stqa.pft.addressbook1.test;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook1.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook1.model.GroupData1;
import ru.stqa.pft.addressbook1.model.Groups;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  protected static final ApplicationManager app
          = new ApplicationManager(System.getProperty("browser",BrowserType.FIREFOX ));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  public ApplicationManager getApp() {
    return app;
  }

  public void verifyGroupListInUI() {
    //verifyUI jest zmienną systemową (ustawiamy true or false w teście gdzie wywołujemy tą metodę)
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.getGroupHelper().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData1().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }

  }
}
