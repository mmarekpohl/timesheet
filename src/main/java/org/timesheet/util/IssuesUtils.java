package org.timesheet.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.timesheet.model.Issue;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class IssuesUtils {

    public static void sortByDate(List<Issue> issues) {
        Collections.sort(issues, (Issue i1, Issue i2) -> i1.getDate().compareTo(i2.getDate()));
    }

    private static final String KEY_ISSUE_ID = "Issue Key";
    private static final String KEY_ISSUE_TITLE = "Issue summary";
    private static final String KEY_DATE = "Work date";

    public static List<Issue> readIssues(Reader reader) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT
                .withHeader(KEY_ISSUE_ID,KEY_ISSUE_TITLE,"Hours",KEY_DATE,"Username","Full name","Period","Account Key","Account Name","Account Lead","Account Category","Account Customer","Activity Name","Component","All Components","Version Name","Issue Type","Issue Status","Project Key","Project Name","Epic","Epic Link")
                .withSkipHeaderRecord(true);

        try(CSVParser csvParser = new CSVParser(reader, csvFormat)) {
            return csvParser.getRecords().stream().map(record -> {
                String name = record.get(KEY_ISSUE_ID) + " " + record.get(KEY_ISSUE_TITLE);
                String stringDate = record.get(KEY_DATE);
                return new Issue(name, DateUtils.getDate(stringDate));
            }).collect(toList());
        }
    }
}
