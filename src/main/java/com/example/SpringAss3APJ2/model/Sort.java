package com.example.SpringAss3APJ2.model;

import java.util.Comparator;

public class Sort implements Comparator<History> {
    @Override
    public int compare(History o1, History o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
