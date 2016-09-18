package org.timesheet;

import com.google.common.collect.ImmutableList;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.timesheet.model.Issue;
import org.timesheet.model.Manday;
import org.timesheet.util.IssuesUtils;
import org.timesheet.util.MandayUtils;

import java.io.*;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    protected static final String DEFAULT_IN_FILE_NAME = "in/in.csv";
    protected static final String DEFAULT_OUT_FILE_NAME = "out/out.csv";

    public static void main (String ... args) throws Exception {
        List<String> params = ImmutableList.copyOf(args);
        String in=DEFAULT_IN_FILE_NAME;
        String out=DEFAULT_OUT_FILE_NAME;
        if(params.size()==2) {
            in=params.get(0);
            out=params.get(1);
        } else {
            logger.info("using default filenames {}, {}", DEFAULT_IN_FILE_NAME, DEFAULT_OUT_FILE_NAME);
        }
        FileUtils.touch(new File(out));
        try (Reader reader = new FileReader(in); Writer writer = new FileWriter(out)) {
            process(reader, writer);
        }
    }

    protected static void process(Reader reader, Writer writer) throws IOException {
        List<Issue> issues = IssuesUtils.readIssues(reader);
        IssuesUtils.sortByDate(issues);
        List<Manday> mandays = MandayUtils.getMandays(issues);
        MandayUtils.writeMandays(writer, mandays);
    }
}
