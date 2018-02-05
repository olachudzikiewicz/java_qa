package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTest {

  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("28accbe43ea112d9feb328d2c00b3eed", "");
  }

  @Test
  public void testCreateIssue1() throws IOException {
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue10").withDescription("Description1");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssue, oldIssues);
  }

  private int createIssue(Issue newIssue) throws IOException {
  /*  String json = getExecutor1().execute(Request.Post("http://demo.bugify.com/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription()))).returnContent()
            .asString(); */

  //to co wyżej z użyciem biblioteki RestAssured
    String json = RestAssured.given().parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json").asString();

    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  private Set<Issue> getIssues() throws IOException {
    /*String json = getExecutor1().execute(Request.Get("http://demo.bugify.com/api/issues.json"))
            .returnContent()
            .asString(); */

    //to co wyżej z użyciem biblioteki RestAssured
    String json = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  //zamiast tego jest init() w BeforeClass
  /*
  private Executor getExecutor1() {
    return Executor.newInstance().auth("28accbe43ea112d9feb328d2c00b3eed", "");
  }
  */
}
