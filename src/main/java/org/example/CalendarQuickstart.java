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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.example.CalendarCore.*;

/* class to demonstrate use of Calendar events list API */
public class CalendarQuickstart {


    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        CalendarCore calendarCore = new CalendarCore();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, calendarCore.JSON_FACTORY, calendarCore.getCredentials(HTTP_TRANSPORT))
                        .setApplicationName(calendarCore.APPLICATION_NAME)
                        .build();
        Events events = executeEvents(service);
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Events:");
           getItems(items);
        }
    }
}
// [END calendar_quickstart]