package com.obektevco.robosum.obektev_utils;

import java.util.Comparator;

public class RobotComparator implements Comparator<RobotInfo> {
    @Override
    public int compare(RobotInfo r1, RobotInfo r2) {
        // First, compare by wins in descending order
        int winsCompare = Integer.compare(r2.getWins(), r1.getWins());

        if (winsCompare != 0) {
            return winsCompare; // If wins are not equal, return comparison result
        } else {
            // If wins are equal, compare by weight in ascending order
            return Integer.compare(Integer.parseInt(r1.getWeight()), Integer.parseInt(r2.getWeight()));
        }
    }
}
