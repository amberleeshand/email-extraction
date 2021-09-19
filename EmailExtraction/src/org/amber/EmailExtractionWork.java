package org.amber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractionWork {

    public static void main(String[] args) {
        String fileName = "EmailExtraction/sample.txt";
        if (args.length > 1){
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
        String regex = "[a-zA-Z0-9_\\-\\.]+@(?<domain>[a-z]+(\\.[a-z]+)+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contents);

        Map<String, Integer> domains = new HashMap<String, Integer>();

        while (matcher.find()) {
//            counter++;

            String key = matcher.group("domain");
            domains.put(key, domains.getOrDefault(key, 0) + 1);

        }

        domains.forEach((k, v) -> {
            System.out.format("%s: %d%n", k, v);
        });

//        System.out.printf("Found %s %d times", regex, counter);
    }


}
