package org.timesheet.model;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Manday {
    private List<Issue> issues = new ArrayList<>();
    private LocalDate date;

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSummary() {
        return StringUtils.join(issues.stream().map(issue -> issue.getName()).collect(toList()), " ");
    }

    /**
     * @return added
     */
    public boolean addIssue(Issue issue) {
        Preconditions.checkNotNull(issue.getDate());
        Preconditions.checkArgument(StringUtils.isNotBlank(issue.getName()));
        if(date==null) {  //init 1st time
            issues.add(issue);
            date = issue.getDate();
            return true;
        } else if (date.equals(issue.getDate())) { //another issue same day
            issues.add(issue);
            return true;
        } else { //issue doesnt fit this manday (by date)
            return false;
        }
    }

    /**
     * Represents an empty row in the timesheet
     * @return day off, weekend
     */
    public static Manday createEmpty(LocalDate date){
        Manday manday = new Manday();
        manday.setDate(date);
        return manday;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
