package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class RegistrationHelper extends HelperBase {


  public RegistrationHelper(ApplicationManager app)  {
    super(app);
    //wd=app.wd; //tym sposobem przeglądarka będzie otwierać się zawsze
  //  wd=app.getDriver(); //tzw leniwa inicjalizacja, żeby przeglądarka nie otwierała się jak nie trzeba
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"),username);
    type(By.name("email"),email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void goToPage() {
    wd.get("http://localhost/mantisbt-1.2.20/login_page.php");
  }

  public void loginByInterface() {
    type(By.name("username"), "administrator");
    type(By.name("password"), "root");
    click(By.xpath("//input[@value='Login']"));
  }

  public void chooseUser() {
    click(By.xpath("//a[contains(text(),'Manage')]"));
    click(By.xpath("//a[contains(text(),'Manage Users')]"));
    click(By.xpath("//a[contains(text(),'user1517344313081')]"));
  }

  public void resetPassword() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public String getEmail() {
    String email = wd.findElement(By.name("email")).getAttribute("value");
    return  email;
  }

  public String getUser() {
    String user = wd.findElement(By.name("username")).getAttribute("value");
    return  user;
  }
}
