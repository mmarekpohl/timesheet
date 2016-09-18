package org.timesheet.util;


import org.testng.Assert;
import org.testng.annotations.Test;
import org.timesheet.TestGroups;

import java.time.LocalDate;
import java.time.Month;

public class DateUtilsTest {

    @Test(groups = TestGroups.UNIT)
    public void shouldParseDate() throws Exception {
        Assert.assertEquals(DateUtils.getDate("2016-8-1"), LocalDate.of(2016, Month.AUGUST, 1));
        Assert.assertEquals(DateUtils.getDate("2016-12-12"), LocalDate.of(2016, Month.DECEMBER, 12));
    }
}