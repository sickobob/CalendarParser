package org.example;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarHendler {

public DateTime dateFrom;
public DateTime dateEnd;
public Calendar service;


    public  void getItems(List<Event> items){
        for (Event event : items) {
            DateTime start = event.getStart().getDateTime();
            DateTime end = event.getEnd().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
                end = event.getEnd().getDate();
            }
            Date date1 = new Date(start.getValue());
            Date date2 = new Date(end.getValue());
            if (!date1.equals(date2)){
                LocalDate dateStart = LocalDate.of(1900+date1.getYear(), date1.getMonth()+1,date1.getDate());
                LocalDate dateEnd= LocalDate.of(1900+date2.getYear(), date2.getMonth()+1,date2.getDate());
                int dateDiff = dateEnd.compareTo(dateStart);
                System.out.printf("Название мероприятия: %s дата: %s - %s\n", event.getSummary(), new java.sql.Date(start.getValue()),new java.sql.Date(end.getValue()));
                for (int i = 1; i < dateDiff; i++) {
                    java.sql.Date newDate = new java.sql.Date(start.getValue());
                    newDate.setDate(date1.getDate()+i);
                    System.out.printf("         Название мероприятия: %s дата: %s - %s\n", event.getSummary(), newDate, newDate);
                }
            }
            else System.out.printf("Название мероприятия: %s дата: %s - %s\n", event.getSummary(), new java.sql.Date(start.getValue()),new java.sql.Date(end.getValue()));




        }
    }
    public  Events executeEvents(CalendarHendler calendarHendler) throws IOException {
        Events events =service.events().list("primary")
                .setTimeMin(calendarHendler.dateFrom)
                .setTimeMax(calendarHendler.dateEnd)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events;
    }
    public CalendarHendler(Calendar service, DateTime dateFrom, DateTime dateEnd){
        this.service=service;
        this.dateFrom= dateFrom;
        this.dateEnd= dateEnd;
    }
}
