package builder;

import model.Issue.Project;

import model.Issue.IssueType;
import model.Issue.Field;

public class IssueFieldBuilder implements FieldsBuilder{
    private Project project;
    private IssueType issuetype;
    private String summary;
    @Override
    public FieldsBuilder buildProject(String project) {
        this.project = new Project(project);
        return this;
    }

    @Override
    public FieldsBuilder buildIssueType(String issueType) {
        this.issuetype = new IssueType(issueType);
        return this;
    }

    @Override
    public FieldsBuilder buildSummary(String summary) {
        this.summary = summary;
        return this;
    }

    @Override
    public Field build() {
        return new Field(project, issuetype, summary);
    }
}
