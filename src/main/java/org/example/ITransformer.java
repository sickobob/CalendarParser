package org.example;

import com.google.api.services.calendar.model.Event;

import java.util.List;
import java.util.Map;

public interface ITransformer {
    public List<CalendarEvent> transformGoogleEvents(List<Event> items, Map<String,String> surnames);

}
