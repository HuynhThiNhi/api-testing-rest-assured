package builder;

import model.Issue.Field;

public interface FieldsBuilder {
    FieldsBuilder buildProject(String project);
    FieldsBuilder buildIssueType(String issueType);
    FieldsBuilder buildSummary(String summary);
    Field build();
}


