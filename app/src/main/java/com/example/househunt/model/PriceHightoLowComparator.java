package com.example.househunt.model;

import java.util.Comparator;

public class PriceHightoLowComparator implements Comparator<House> {

    @Override
    public int compare(House sp1, House sp2) {
        return (Float.valueOf(sp1.getPrice()) > Float.valueOf(sp2.getPrice()) ) ? -1: (Float.valueOf(sp1.getPrice()) <  Float.valueOf(sp2.getPrice())) ? 1:0 ;
    }


}


