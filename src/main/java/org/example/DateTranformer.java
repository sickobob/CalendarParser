package org.example;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import org.checkerframework.checker.units.qual.C;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class DateTranformer extends SingleTransformer{
    @Override
    public List<CalendarEvent> transformGoogleEvents(List<Event> items, Map<String,String> surnames) {
        List<CalendarEvent> calendarEvents=  new ArrayList<>();
        for (CalendarEvent calendarEvent : super.transformGoogleEvents(items,surnames)) {
            long daysBetween = ChronoUnit.DAYS.between(calendarEvent.dateStart.toInstant(), calendarEvent.dateEnd.toInstant());
            if (daysBetween>2) {
                for (int i = 0; i <= daysBetween; i++) {
                    CalendarEvent calendarEvent1;
                    calendarEvent1 = convertGoogleEvent(calendarEvent, i);
                    calendarEvents.add(calendarEvent1);
                }
            } else if (daysBetween==2) {
                for (int i = 0; i < daysBetween; i++) {
                    CalendarEvent calendarEvent1;
                    calendarEvent1 = convertGoogleEvent(calendarEvent, i);
                    calendarEvents.add(calendarEvent1);
                }
            } else calendarEvents.add(calendarEvent);
        }
        return calendarEvents;
    }



}
