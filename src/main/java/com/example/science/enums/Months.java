package com.example.science.enums;

import java.util.ArrayList;
import java.util.List;

public class Months {

    private List<String> months = new ArrayList<>();

    public Months() {
        init();
    }

    private void init(){
        months.add("Январь");
        months.add("Февраль");
        months.add("Март");
        months.add("Апрель");
        months.add("Май");
        months.add("Июнь");
        months.add("Июль");
        months.add("Август");
        months.add("Сентябрь");
        months.add("Октябрь");
        months.add("Ноябрь");
        months.add("Декабрь");
    }

    public List<String> getMonths() {
        return months;
    }

}
