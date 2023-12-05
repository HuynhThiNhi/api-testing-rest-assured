package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;

import java.lang.reflect.Field;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Issue {
    private Field fields;

    public Field getFields() {
        return fields;
    }

    public void setFields(Field fields) {
        this.fields = fields;
    }

    public Issue() {
    }

    public Issue(Field fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Field {
        public Field() {
        }

        private   Project project;
        private IssueType issuetype;
        private String summary;

        public Field(Project project, IssueType issueType, String summary) {
            this.project = project;
            this.issuetype = issueType;
            this.summary = summary;
        }

        public void setProject(Project project) {
            this.project = project;
        }

        public void setIssuetype(IssueType issuetype) {
            this.issuetype = issuetype;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public Project getProject() {
            return project;
        }

        public IssueType getIssueType() {
            return issuetype;
        }

        public String getSummary() {
            return summary;
        }

    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Project {
        private String key;

        public String getKey() {
            return key;
        }

        public Project() {
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Project(String key) {
            this.key = key;
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class IssueType {
        public IssueType() {
        }

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public IssueType(String id) {
            this.id = id;
        }

    }
    public static void main(String[] args) {
        Issue issue = new Issue(new Field(new Project("a"), new IssueType("10007"), "Test"));
        Gson json = new Gson();
        System.out.println(json.toJson(issue));
    }
}
