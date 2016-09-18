package org.timesheet.util;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.timesheet.TestGroups;
import org.timesheet.model.Issue;

import java.util.ArrayList;
import java.util.List;

public class MandayUtilsTest {

    @Test(groups = TestGroups.UNIT)
    public void shouldGetEmpty() throws Exception {
        List<Issue> issues = new ArrayList<>();
        Assert.assertTrue(MandayUtils.getMandays(issues).isEmpty());
    }

    @Test(groups = TestGroups.UNIT)
    public void shouldGet2workMD() throws Exception {
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue("is1", DateUtils.getDate("2016-08-01 00:00")));
        issues.add(new Issue("is2", DateUtils.getDate("2016-08-02 00:00")));
        Assert.assertEquals(MandayUtils.getMandays(issues).size(), 2);
    }

    @Test(groups = TestGroups.UNIT)
    public void shouldGetFridayWeekendMonday() throws Exception {
        List<Issue> issues = new ArrayList<>();
        issues.add(new Issue("is1", DateUtils.getDate("2016-08-01 00:00")));
        issues.add(new Issue("is4", DateUtils.getDate("2016-08-04 00:00")));
        Assert.assertEquals(MandayUtils.getMandays(issues).size(), 4);
        Assert.assertEquals(MandayUtils.getMandays(issues).get(0).getSummary().trim(), "is1");
        Assert.assertEquals(MandayUtils.getMandays(issues).get(1).getSummary().trim(), "");
        Assert.assertEquals(MandayUtils.getMandays(issues).get(2).getSummary().trim(), "");
        Assert.assertEquals(MandayUtils.getMandays(issues).get(3).getSummary().trim(), "is4");
    }


}