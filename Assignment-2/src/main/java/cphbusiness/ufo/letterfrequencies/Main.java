package cphbusiness.ufo.letterfrequencies;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.toMap;

/**
 * Frequency analysis Inspired by
 * https://en.wikipedia.org/wiki/Frequency_analysis
 *
 * @author kasper
 */
public class Main {

    static long totalTime = 0;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        int benchmarkRuns = 100;
        Map<Integer, Long> freq = new HashMap<>();
        for(int i = 0; i < benchmarkRuns; i++){
            freq = newMethod(freq, i);
        }
        //print_tally(freq);
        System.out.println("Execution time on average in nanoseconds: " + (totalTime/benchmarkRuns));
    }

    private static Map<Integer, Long> stockMethod(Map<Integer, Long> freq, int i) throws IOException {
        String fileName = "C:\\Users\\Admin\\Documents\\Github\\davidcarl\\UFO-Assignments\\Assignment-2\\src\\main\\resources\\FoundationSeries.txt";
        Reader reader = new FileReader(fileName);
        return sameCode(freq, i, reader);
    }

    private static Map<Integer, Long> newMethod(Map<Integer, Long> freq, int i) throws IOException {
        String fileName = "C:\\Users\\Admin\\Documents\\Github\\davidcarl\\UFO-Assignments\\Assignment-2\\src\\main\\resources\\FoundationSeries.txt";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        return sameCode(freq, i, reader);
    }

    private static Map<Integer, Long> sameCode(Map<Integer, Long> freq, int i, Reader reader) throws IOException {
        freq = new HashMap<>();
        Long startTime = System.nanoTime();
        tallyChars(reader, freq);
        Long endTime = System.nanoTime();
        totalTime += (endTime-startTime);
        System.out.println("Run: " + (i + 1) + ", time: " + (endTime-startTime));
        return freq;
    }

    private static void tallyChars(Reader reader, Map<Integer, Long> freq) throws IOException {
        int b;
        while ((b = reader.read()) != -1) {
            try {
                freq.put(b, freq.get(b) + 1L);
            } catch (NullPointerException np) {
                freq.put(b, 1L);
            }
        }
    }

    private static void print_tally(Map<Integer, Long> freq) {
        int dist = 'a' - 'A';
        Map<Character, Long> upperAndlower = new LinkedHashMap();
        for (Character c = 'A'; c <= 'Z'; c++) {
            upperAndlower.put(c, freq.getOrDefault(c, 0L) + freq.getOrDefault(c + dist, 0L));
        }
        Map<Character, Long> sorted = upperAndlower
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));
        for (Character c : sorted.keySet()) {
            System.out.println("" + c + ": " + sorted.get(c));;
        }
    }
}
