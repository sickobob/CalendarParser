package org.example;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SingleTransformer implements ITransformer{
    @Override
    public List<CalendarEvent> transformGoogleEvents(List<Event> items) {
        List<CalendarEvent> calendarEvents = new ArrayList<>();
        for (Event event : items) {
            DateTime start = event.getStart().getDateTime();
            DateTime end = event.getEnd().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
                end = event.getEnd().getDate();
            }
            CalendarEvent calendarEvent = new CalendarEvent();
            calendarEvent = convertGoogleEvent(event);
            calendarEvents.add(calendarEvent);
        }
        return calendarEvents;
    }
    protected CalendarEvent convertGoogleEvent(Event event){
        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setName(event.getSummary());
        DateTime start = event.getStart().getDateTime();
        DateTime end = event.getEnd().getDateTime();
        if (start == null) {
            start = event.getStart().getDate();
            end = event.getEnd().getDate();
        }
        calendarEvent.dateStart.setTimeInMillis(start.getValue());
        calendarEvent.dateEnd.setTimeInMillis(end.getValue());
        return calendarEvent;
    }
    protected CalendarEvent convertGoogleEvent(Event event, int i){
        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setName(event.getSummary());
        DateTime start = event.getStart().getDateTime();
        if (start == null) {
            start = event.getStart().getDate();
        }
        calendarEvent.dateStart.setTimeInMillis(start.getValue());
        calendarEvent.dateStart.add(Calendar.DAY_OF_YEAR,i);
        calendarEvent.dateEnd =calendarEvent.dateStart;
        return calendarEvent;
    }


}
