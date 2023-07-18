package org.example;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class CalendarHendler {
   public Date DateFrom = new Date( );
 //   private Date _dateFrom = new Date();
    public  void getItems(List<Event> items){
        for (Event event : items) {
            DateTime start = event.getStart().getDateTime();
            DateTime end = event.getEnd().getDateTime();
            if (start == null) {
                start = event.getStart().getDate();
                end = event.getEnd().getDate();
            }
            System.out.printf("Название мероприятия: %s дата начала: %s - %s\n", event.getSummary(), new Date(start.getValue()),new Date(end.getValue()));
        }
    }
    //возвращать дату с и дату по и наименование - 1 class GetDateEvents GetEvents
    //на вход подаеются две даты (дата с и дато по) - 2 class

    public  Events executeEvents(Calendar service,DateTime dateFrom, DateTime dateMax) throws IOException {
        Events events = service.events().list("primary")
                .setTimeMin(dateFrom)
                .setTimeMax(dateMax)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events;
    }
}
