package org.example;

import org.checkerframework.checker.units.qual.C;

import java.util.List;
import java.util.Map;

public interface IRenamer {
    public String renameEvent(CalendarEvent calendarEvent, Map<String,String> surnameDictionary);
}
