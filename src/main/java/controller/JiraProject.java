package controller;

import common.config.Config;
import common.utils.RequestCapability;
import common.utils.Utils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static io.restassured.RestAssured.given;

public class JiraProject implements RequestCapability {
    Map<String, List<Map<String, String>>> projectInfo;
    List<Map<String, String>> issueTypes;

    public static void main(String[] args) {
        JiraProject jiraProject = new JiraProject();

        System.out.println(jiraProject.getIssueTypeIdByName("Task"));
        //jiraProject.getIssueTypes();


    }

    public JiraProject() {
        this.projectInfo = getProjectInfo();
        this.issueTypes = getIssueTypes();
    }

    private Map<String, List<Map<String, String>>> getProjectInfo() {
//        solution 1
//       return  request.baseUri(baseUri)
//               .header("Authorization", "Basic " + encodeCredentials())
//                .header("Content-Type", "application/json; charset=UTF-8")
//                .get(endPoint);
        Response response = request.baseUri(Config.JiraUri)
                .header(defaultHeader)
//               .header(RequestCapability.getAuthenticatedHeader(encodeCredentials()))
                .header(acceptJsonHeader)
                .header(getAuthenticatedHeader.apply(Utils.encodeCredentials()))
                .get(Config.getProjectEndPoint);

        //response.jsonPath().getJsonObject("issueTypes");
        return JsonPath.from(response.asString()).get();
    }

    private List<Map<String, String>> getIssueTypes() {
//        System.out.println(projectInfo.get("issueTypes"));
        return projectInfo.get("issueTypes");
    }
    public String getIssueTypeIdByName(String issueTypeName) {
        String issueId = "";
        for (Map<String, String> issue : issueTypes) {
            if (issue.get("name").equalsIgnoreCase(issueTypeName)) {
                issueId = issue.get("id");
                break; // Exit the loop once the issue with the desired name is found
            }
        }
        if (issueId.isEmpty()) {
            throw new RuntimeException("not found");
        }
        return issueId;
    }

}
