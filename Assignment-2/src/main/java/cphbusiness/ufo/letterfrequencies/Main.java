package cphbusiness.ufo.letterfrequencies;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String fileName = "/home/tjalfe/Documents/github/UFO/letterfrequencies/src/main/resources/FoundationSeries.txt";
        int runs = 100;
        int[] time = new int[runs];
        Reader reader;

        for(int i = 0; i < runs; i++) {
            reader = new FileReader(fileName);
            long startTime = System.nanoTime();
            Map<Integer, Long> freq = new HashMap();
            tallyChars(reader, freq);
            long duration = System.nanoTime() - startTime;
            time[i] = (int) duration / 1000000;
        }
        printMean(time);

        for(int i = 0; i < runs; i++) {
            long startTime = System.nanoTime();
            reader = new BufferedReader(new FileReader(fileName));
            Map<Integer, Integer> freq = new HashMap();
            improvedTallyChars(reader, freq);
            long duration = System.nanoTime() - startTime;
            time[i] = (int) duration / 1000000;
        }
        printMean(time);

        for(int i = 0; i < runs; i++) {
            long startTime = System.nanoTime();
            reader = new BufferedReader(new FileReader(fileName));
            Map<Integer, CharObj> freq = new HashMap();
            improvedTallyChars2(reader, freq);
            long duration = System.nanoTime() - startTime;
            time[i] = (int) duration / 1000000;
        }
        printMean(time);
    }

    private static void printMean(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        System.out.println("Mean: " +  (sum/arr.length));
    }

    private static void tallyChars(Reader reader, Map<Integer, Long> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            try {
                freq.put(b, freq.get(b) + 1);
            } catch (NullPointerException np) {
                freq.put(b, 1L);
            };
        }
    }

    private static void improvedTallyChars(Reader reader, Map<Integer, Integer> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            try {
                freq.put(b, (freq.get(b) + 1));
            } catch (NullPointerException np) {
                freq.put(b, 1);
            };
        }
    }

    private static void improvedTallyChars2(Reader reader, Map<Integer, CharObj> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            try {
                freq.get(b).increment();
            } catch (NullPointerException np) {
                freq.put(b, new CharObj());
            };
        }
    }

//    private static void print_tally(Map<Integer, Long> freq) {
//        int dist = 'a' - 'A';
//        Map<Character, Long> upperAndlower = new LinkedHashMap();
//        for (Character c = 'A'; c <= 'Z'; c++) {
//            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
//        }
//        Map<Character, Long> sorted = upperAndlower
//                .entrySet()
//                .stream()
//                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
//                .collect(
//                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
//                                LinkedHashMap::new));
//        for (Character c : sorted.keySet()) {
//            System.out.println("" + c + ": " + sorted.get(c));;
//        }
//    }
}
