package org.example;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class MultyEvent extends MyEvent{
    @Override
    public String toString(){
            return String.format("Название: %s дата: %s - %s", getName(),format1.format(dateStart.getTime()), format1.format(dateEnd.getTime()));
    }
    public List<MultyEvent> setArrayOfMultyEvents(List<Event> items){
        List<MultyEvent> multyEvents = new ArrayList<>();
        for (Event event : items) {
            MultyEvent multyEvent = new MultyEvent();
            DateTime start = event.getStart().getDateTime();
            DateTime end = event.getEnd().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
                end = event.getEnd().getDate();
            }
            multyEvent.dateStart.setTimeInMillis(start.getValue());
            multyEvent.dateEnd.setTimeInMillis(end.getValue());
            multyEvent.setName(event.getSummary());
            multyEvents.add(multyEvent);
            long daysBetween = ChronoUnit.DAYS.between(multyEvent.dateStart.toInstant(), multyEvent.dateEnd.toInstant());
            if (daysBetween!=0){
                for (int i = 1; i <= daysBetween; i++) {
                    MultyEvent multyEvent1 = new MultyEvent();
                    multyEvent1.setName(event.getSummary());
                    multyEvent1.dateStart.setTimeInMillis(start.getValue());
                    multyEvent1.dateStart.add(Calendar.DAY_OF_YEAR,i);
                    multyEvent1.dateEnd =multyEvent1.dateStart;


                    multyEvents.add(multyEvent1);
                }

            }

        }
        return multyEvents;
    }
}
