package org.example;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.time.LocalDate;
import java.util.*;

public class SingleEvent extends MyEvent{
 /*   @Override
    public void printItems(List<Object> items) {
        for (Object obj : items) {

            DateTime start = event.getStart().getDateTime();
            DateTime end = event.getEnd().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
                end = event.getEnd().getDate();
            }
            System.out.printf("Название мероприятия: %s дата: %s - %s\n", event.getSummary(), new java.sql.Date(start.getValue()), new java.sql.Date(end.getValue()));
        }
    }
    */
    @Override
    public String toString(){
        return String.format("Название: %s дата: %s - %s", getName(),format1.format(dateStart.getTime()), format1.format(dateEnd.getTime()));
    }
    public List<SingleEvent> setArrayOfSingleEvents(List<Event> items){
        List<SingleEvent> singleEvents = new ArrayList<>();
        for (Event event : items) {
            DateTime start = event.getStart().getDateTime();
            DateTime end = event.getEnd().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
                end = event.getEnd().getDate();
            }
            SingleEvent singleEvent = new SingleEvent();
            singleEvent.setName(event.getSummary());
            singleEvent.dateStart.setTimeInMillis(start.getValue());
            singleEvent.dateEnd.setTimeInMillis(end.getValue());
            singleEvents.add(singleEvent);
        }
        return singleEvents;
    }

}
