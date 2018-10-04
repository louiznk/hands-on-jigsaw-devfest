package me.xdrop.fuzzywuzzy.algorithms;

import java.util.Arrays;

final class PrimitiveUtils {

    static double max(double... elems) {

        return Arrays.stream(elems).max().orElse(0);
    }

    static int max(int... elems) {

        return Arrays.stream(elems).max().orElse(0);
    }


}
