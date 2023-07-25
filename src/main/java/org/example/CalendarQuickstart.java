package org.example;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.SslUtils;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import java.io.*;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class CalendarQuickstart {
final static SimpleDateFormat format1= new SimpleDateFormat("dd.MM.yyyy");
  static   java.util.Calendar start = new GregorianCalendar();
   static java.util.Calendar end = new GregorianCalendar();
    static   Events executeEvents(Calendar service, java.util.Calendar dateFrom, java.util.Calendar dateEnd) throws IOException {
        DateTime dateTimeStart= new DateTime(dateFrom.getTimeInMillis());
        DateTime dateTimeEnd= new DateTime(dateEnd.getTimeInMillis());
        Events events = service.events().list("primary")
                .setTimeMin(dateTimeStart)
                .setTimeMax(dateTimeEnd)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        return events;
    }
    public static void main(String... args) throws IOException, GeneralSecurityException, ParseException {
        boolean flg = false;
        start.setTime(format1.parse(args[0]));
        end.setTime(format1.parse(args[1]));
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        CalendarCore calendarCore = new CalendarCore();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, calendarCore.JSON_FACTORY, calendarCore.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(calendarCore.APPLICATION_NAME)
                        .build();
       Events events = executeEvents(service,start,end);
       List<SingleEvent> singleEvents = new ArrayList<>();
        List<MultyEvent> multyEvents = new ArrayList<>();
         List<Event> items = events.getItems();

        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            if(flg){
                System.out.println("Events:");
                SingleEvent singleEvent = new SingleEvent();
              singleEvents =  singleEvent.setArrayOfSingleEvents(items);
               for (SingleEvent s : singleEvents) System.out.println(s.toString());
            }
            else {
                MultyEvent multyEvent = new MultyEvent();
               multyEvents= multyEvent.setArrayOfMultyEvents(items);
                for (MultyEvent m: multyEvents) System.out.println(m.toString());
            }
        }
    }

}
// [END calendar_quickstart]
//"2023-01-01T00:00:00.000+03:00" "2023-12-31T00:00:00.000+03:00"