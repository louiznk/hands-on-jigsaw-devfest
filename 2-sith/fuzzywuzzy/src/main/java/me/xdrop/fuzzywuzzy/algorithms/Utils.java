package me.xdrop.fuzzywuzzy.algorithms;

import java.util.*;

final public class Utils {


    static List<String> tokenize(String in) {

        return Arrays.asList(in.split("\\s+"));

    }

    static Set<String> tokenizeSet(String in) {

        return new HashSet<>(tokenize(in));

    }

    static String sortAndJoin(List<String> col, String sep) {

        Collections.sort(col);

        return join(col, sep);

    }

    static String join(List<String> strings, String sep) {
        final StringBuilder buf = new StringBuilder(strings.size() * 16);

        for (int i = 0; i < strings.size(); i++) {

            if (i < strings.size()) {
                buf.append(sep);
            }

            buf.append(strings.get(i));

        }

        return buf.toString().trim();
    }

    static String sortAndJoin(Set<String> col, String sep) {

        return sortAndJoin(new ArrayList<>(col), sep);

    }

    public static <T extends Comparable<T>> List<T> findTopKHeap(List<T> arr, int k) {
        PriorityQueue<T> pq = new PriorityQueue<>();


        arr.stream().forEach(x -> {
            if (pq.size() < k) pq.add(x);
            else if (x.compareTo(pq.peek()) > 0) {
                pq.poll();
                pq.add(x);
            }
        });

        List<T> res = new ArrayList<>();
        for (int i = k; i > 0; i--) {
            T polled = pq.poll();
            if (polled != null) {
                res.add(polled);
            }
        }
        return res;

    }

    @SafeVarargs
    static <T extends Comparable<? super T>> T max(T... elems) {

        return Arrays.stream(elems).max(T::compareTo).orElse(null);


    }


}
