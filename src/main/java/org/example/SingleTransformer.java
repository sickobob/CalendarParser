package org.example;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class SingleTransformer implements ITransformer, IRenamer{
    @Override
    public List<CalendarEvent> transformGoogleEvents(List<Event> items,Map<String,String> surnames) {
        List<CalendarEvent> calendarEvents = new ArrayList<>();
        for (Event event : items) {
            CalendarEvent calendarEvent;
            calendarEvent = convertGoogleEvent(event);
            calendarEvent.setName(renameEvent(calendarEvent,surnames));
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
    protected CalendarEvent convertGoogleEvent(CalendarEvent event, int i){
        CalendarEvent calendarEvent = new CalendarEvent();
        calendarEvent.setName(event.getName());
        calendarEvent.dateStart.setTimeInMillis(event.dateStart.getTimeInMillis());
        calendarEvent.dateStart.add(Calendar.DAY_OF_YEAR,i);
        calendarEvent.dateEnd = calendarEvent.dateStart;
        return calendarEvent;
    }

    @Override
    public String renameEvent(CalendarEvent calendarEvent, Map<String,String> surnameDictionary) {
            for (Map.Entry<String,String> item : surnameDictionary.entrySet()){
                if (item.getKey().contains(calendarEvent.getName())){
                    return item.getValue();
                }
            }
            return calendarEvent.getName();
    }
}
