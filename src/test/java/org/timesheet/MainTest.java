package org.timesheet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.io.Resources;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.timesheet.util.TestUtils.IN_CSV_RESOURCE;

public class MainTest {

    @Test
    public void shouldProcessTimesheet() throws Exception {
        URL url = Resources.getResource(IN_CSV_RESOURCE);
        String out;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())); StringWriter writer = new StringWriter()) {
            Main.process(reader, writer);

            writer.flush();
            out = writer.getBuffer().toString();
        }

        List<String> outLines = ImmutableList.copyOf(StringUtils.split(out, "\r\n"));
        assertEquals(outLines.size(), 32);
        assertEquals(Iterables.getFirst(outLines,null), "Date,Description");
        assertEquals(outLines.get(1), "2016-8-1,Task1 Summary1 Task2 Summary2");
        assertEquals(outLines.get(6), "2016-8-6,");
        assertEquals(outLines.get(7), "2016-8-7,");
        assertEquals(Iterables.getLast(outLines), "2016-8-31,Task45 Summary45");
    }
}