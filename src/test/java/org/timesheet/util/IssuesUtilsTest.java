package org.timesheet.util;

import com.google.common.collect.Iterables;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.timesheet.TestGroups;
import org.timesheet.model.Issue;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;


public class IssuesUtilsTest {

    @Test(groups = TestGroups.UNIT)
    public void shouldParseIssues() throws Exception {
        List<Issue> issues = TestUtils.getIssues();
        Assert.assertEquals(issues.size(), 45);
        Iterables.getLast(issues);
        Assert.assertEquals(Iterables.getFirst(issues,null).getName(), "Task1 Summary1");
        Assert.assertEquals(Iterables.getLast(issues).getName(), "Task45 Summary45");
        issues.stream().allMatch(issue -> isNotBlank(issue.getName()) && issue.getDate()!=null);
    }
}