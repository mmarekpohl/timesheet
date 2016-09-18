package org.timesheet.util;


import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import org.timesheet.model.Issue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class TestUtils {
    public static final String IN_CSV_RESOURCE = "in/in.csv";

    public static ImmutableList<Issue> getIssues() throws IOException {
        URL url = Resources.getResource(IN_CSV_RESOURCE);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            return ImmutableList.copyOf(IssuesUtils.readIssues(reader));
        }
    }

}
