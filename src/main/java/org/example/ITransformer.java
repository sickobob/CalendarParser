package org.example;

import com.google.api.services.calendar.model.Event;

import java.util.List;

public interface ITransformer {
    public List<CalendarEvent> transformGoogleEvents(List<Event> items);

}
