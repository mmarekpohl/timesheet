package org.timesheet.model;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.timesheet.TestGroups;
import org.timesheet.util.DateUtils;

import java.time.LocalDate;
import java.time.Month;

public class MandayTest {
    private static final LocalDate DATE = LocalDate.of(2016, Month.JUNE, 10);
    private static final LocalDate DATE2 = DateUtils.getTomorrow(DATE);

    @Test(groups = TestGroups.UNIT)
    public void shouldAddIssue() throws Exception {
        Manday manday = new Manday();
        //1st time
        Assert.assertTrue(manday.addIssue(new Issue("name", DATE)));
        Assert.assertEquals(manday.getDate(), DATE);
        Assert.assertEquals(manday.getIssues().size(), 1);
        //the same date should be added
        Assert.assertTrue(manday.addIssue(new Issue("name2", DATE)));
        Assert.assertEquals(manday.getIssues().size(), 2);
        //should not add issue from another day
        Assert.assertFalse(manday.addIssue(new Issue("name3", DATE2)));
        Assert.assertEquals(manday.getIssues().size(), 2);
    }

    @Test(groups = TestGroups.UNIT)
    public void shouldFormatSummary() throws Exception {
        Manday manday = new Manday();
        manday.addIssue(new Issue("name1", DATE));
        manday.addIssue(new Issue("name2", DATE));
        Assert.assertEquals(manday.getSummary(), "name1 name2");
    }

}