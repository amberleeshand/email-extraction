package org.amber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractionWork {

    public static void main(String[] args) {
        String fileName = "/Users/acshand/IdeaProjects/email-extraction/EmailExtraction/sample.txt";
        if (args.length > 1) {
            fileName = args[1];
        }
        try {
            searchFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchFile(String fileName) throws IOException {
        Path filePath = Paths.get(fileName);

//        int counter = 0;
        String contents = Files.readString(filePath);
        String regex = "[a-zA-Z0-9_\\-.]+@(?<domain>[a-z]+(\\.[a-z]+)+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contents);

        Map<String, Integer> domains = new HashMap<>();

        while (matcher.find()) {
//            counter++;

            String key = matcher.group("domain");
            domains.put(key, domains.getOrDefault(key, 0) + 1);

        }
        // Amount of email occurrences per email
        domains.forEach(
                (email, emailOccurrence) -> System.out.printf("%s: %d%n", email, emailOccurrence));

        System.out.println(domains);
        System.out.println("-----------");
        // Sorted to find the order from highest to lowest

        domains.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(e -> System.out.println(e));
//        System.out.println(sorted);
//        System.out.printf("Found %s %d times", regex, counter);
        System.out.println(domains);
        }

//    Switch it to a Linked Hashmap (reg ones don't get sorted) - use streams and lambdas



}
