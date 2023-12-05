package controller;

import builder.IssueFieldBuilder;
import builder.JsonBuilder;
import common.config.Config;
import common.utils.RequestCapability;
import common.utils.Utils;
import io.restassured.response.Response;
import model.Issue;

import static model.Issue.Field;

public class JiraIssue implements RequestCapability {

    public static void main(String[] args) {
        JiraProject jiraProject = new JiraProject();
        String issueTypeId = jiraProject.getIssueTypeIdByName("Task");

        Field field = new IssueFieldBuilder().buildProject("RA").buildIssueType(issueTypeId).buildSummary("Test with builder").build();
        JiraIssue jiraIssue = new JiraIssue();
        String id = jiraIssue.createIssue(new Issue(field));

        Issue issue = jiraIssue.getIssueById(id);
        System.out.println(issue.toString());

    }
    public String createIssue(Issue issue) {
        String body = JsonBuilder.toJson(issue);
        Response response = request.baseUri(Config.JiraUri)
                .header(defaultHeader)
                .header(acceptJsonHeader)
                .header(getAuthenticatedHeader.apply(Utils.encodeCredentials()))
                .body(body)
                .post(Config.createNewIssue);
        response.prettyPrint();
        return response.getBody().jsonPath().get("id");
    }

    public Issue getIssueById(String id) {
        Response response = request.baseUri(Config.JiraUri)
                .header(defaultHeader)
                .header(acceptJsonHeader)
                .header(getAuthenticatedHeader.apply(Utils.encodeCredentials()))
                .get(Config.getIssueById.concat(id));
//        response.prettyPrint();
        return response.as(Issue.class);

    }
}
