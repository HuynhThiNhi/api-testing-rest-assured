package controller;

import builder.JsonBuilder;
import com.google.gson.Gson;
import common.config.Config;
import common.utils.RequestCapability;
import common.utils.Utils;
import io.restassured.response.Response;
import model.Issue;

import java.util.HashMap;
import java.util.Map;

public class JiraIssue implements RequestCapability {

    public static void main(String[] args) {
//        JiraProject jiraProject = new JiraProject();
//        String issueTypeId = jiraProject.getIssueTypeIdByName("Task");
//
//        Field field = new IssueFieldBuilder().buildProject("RA").buildIssueType(issueTypeId).buildSummary("Test with builder").build();
        JiraIssue jiraIssue = new JiraIssue();
//        String id = jiraIssue.createIssue(new Issue(field));
//
//        Issue issue = jiraIssue.getIssueById(id);  // id= 10041

        jiraIssue.getTransitionIssue("10041");
        jiraIssue.updateTransitionIssue("10041","71");
        //Issue issue = jiraIssue.getIssueById("10041");  // id= 10041

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
        response.prettyPrint();
        return response.as(Issue.class);

    }

    public void getTransitionIssue(String issueId) {
        Response response = request.baseUri(Config.JiraUri)
                .header(defaultHeader)
                .header(acceptJsonHeader)
                .header(getAuthenticatedHeader.apply(Utils.encodeCredentials()))
                .get(Config.getTransitionIssue.concat(issueId).concat("/transitions"));
        response.prettyPrint();
    }

    public void updateTransitionIssue(String issueId, String transitionId) {
        class Body {
            public IssueTransition transition;

            public Body(IssueTransition transition) {
                this.transition = transition;
            }

            @Override
            public String toString() {
                Gson gson = new Gson();
                return gson.toJson(this);
            }
        }
        IssueTransition transition = new IssueTransition(transitionId);
        Map<String, IssueTransition> body = new HashMap<>();
        body.put("transition", transition);
        System.out.println(body.toString());

        Response response = request.baseUri(Config.JiraUri)
                .header(defaultHeader)
                .header(acceptJsonHeader)
                .header(getAuthenticatedHeader.apply(Utils.encodeCredentials()))
                .body(JsonBuilder.toJson(body))
                .post(Config.getTransitionIssue.concat(issueId).concat("/transitions"));
        response.prettyPrint();
    }
}
