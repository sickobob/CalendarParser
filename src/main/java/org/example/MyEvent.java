package org.example;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

public abstract class MyEvent {
    final  SimpleDateFormat format1= new SimpleDateFormat("dd.MM.yyyy");
    java.util.Calendar dateStart =  new GregorianCalendar();
    java.util.Calendar dateEnd = new GregorianCalendar();
    private String name;
    public String getName() { return name; }
    public void setName(String name) {this.name=name;}

    // public abstract void printItems(List<Object> items);

}
