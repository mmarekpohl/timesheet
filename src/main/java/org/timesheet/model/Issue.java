package org.timesheet.model;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.time.LocalDate;

public class Issue {
    private String name;
    private LocalDate date;

    public Issue(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
