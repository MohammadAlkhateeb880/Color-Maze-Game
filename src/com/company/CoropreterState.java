package com.company;

import java.util.Comparator;

public class CoropreterState implements Comparator<State> {
    @Override
    public int compare(State o1, State o2) {
       /* if (o1.cost>o2.cost)
            return 1;
        if (o1.cost<o2.cost)
            return -1;
        return 0;*/
        if (o1.f>o2.f)
            return 1;
        if (o1.f<o2.f)
            return -1;
        return 0;


    }
}
