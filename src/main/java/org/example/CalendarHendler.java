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
//event гугл ---->в ивент календрарьИвент
//абстрактный либо с интерфейсом
    //календарьИвент новый класс атрибут Name start finish Date
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
