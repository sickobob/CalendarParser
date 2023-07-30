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

    public static void main(String... args) throws IOException, GeneralSecurityException, ParseException {
        start.setTime(format1.parse(args[0]));
        end.setTime(format1.parse(args[1]));
        Map<String, String> map1 = new HashMap<>();
        map1.put("123","отпуск");
        map1.put("3333", "отдых");
        CalendarCore calendarCore = new CalendarCore(map1);
         List<Event> items = calendarCore.getEvents(start,end);
         List<CalendarEvent> calendarEvents;
         calendarEvents = calendarCore.getMultyEvents(items);
        if (calendarEvents.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
               for (CalendarEvent calendarEvent: calendarEvents)
                   System.out.println(calendarEvent.toString());
        }
    }
}
//java util map hashmap
//на вход <String1, string2> если string1 содержится
//беру ивент называю
// [END calendar_quickstart]
//"2023-01-01T00:00:00.000+03:00" "2023-12-31T00:00:00.000+03:00"