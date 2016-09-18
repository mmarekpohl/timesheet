package org.timesheet.util;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.timesheet.model.Issue;
import org.timesheet.model.Manday;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import static org.timesheet.util.DateUtils.getTomorrow;

public class MandayUtils {

    /**
     * @param issues sorted by date
     * group by date
     * add empty rows for weekends, day off
     */
    public static List<Manday> getMandays(List<Issue> issues) {
        List<Manday> mandays = new ArrayList<>();
        for(Issue issue : issues) {
            if(mandays.isEmpty()) {
                mandays.add(new Manday());
            }
            Manday manday = Iterables.getLast(mandays);
            while(!manday.addIssue(issue)) {
                // insert empty lines for weekends...
                mandays.add(Manday.createEmpty(getTomorrow(manday.getDate())));
                manday = Iterables.getLast(mandays);
            }
        }
        return mandays;
    }

    public static void writeMandays(Writer writer, List<Manday> mandays) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT
                .withHeader("Date","Description");
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat)) {
            for (Manday manday : mandays) {
                csvPrinter.printRecord(ImmutableList.of(
                        DateUtils.formatDate(manday.getDate()),
                        String.valueOf(manday.getSummary())
                ));
            }
        }
    }


}
