package org.example;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class CalendarEvent {
    final SimpleDateFormat format1= new SimpleDateFormat("dd.MM.yyyy");
    java.util.Calendar dateStart =  new GregorianCalendar();
    java.util.Calendar dateEnd = new GregorianCalendar();
    private String name;
    public String getName() { return name; }
    public void setName(String name) {this.name=name;}

    @Override
    public String toString() {
        return String.format("Name: %s, date %s - %s", getName(),format1.format(dateStart.getTime()),format1.format(dateEnd.getTime()));
    }
}
