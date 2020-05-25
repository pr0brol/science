package com.example.science.services;

import com.example.science.entities.Calendar;
import com.example.science.repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    public Calendar addStatus(Calendar calendar) {
        return calendarRepository.save(calendar);
    }
}
