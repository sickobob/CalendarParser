package org.example;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import org.checkerframework.checker.units.qual.C;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DateTranformer extends SingleTransformer{
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
            long daysBetween = ChronoUnit.DAYS.between(calendarEvent.dateStart.toInstant(), calendarEvent.dateEnd.toInstant());
            if (daysBetween!=0){
                for (int i = 1; i <= daysBetween; i++) {
                    CalendarEvent calendarEvent1 = new CalendarEvent();
                    calendarEvent1 = convertGoogleEvent(event,i);
                    calendarEvents.add(calendarEvent1);
                }

            }

        }
        return calendarEvents;
    }



}
