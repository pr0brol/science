package com.example.science.enums;

import java.util.ArrayList;
import java.util.List;

public class Days {
    private List<Integer> days = new ArrayList<>();

    public Days() {
        init();
    }

    private void init(){
        for (int i=1;i<=31;i++){
            days.add(i);
        }
    }

    public List<Integer> getDays() {
        return days;
    }
}
