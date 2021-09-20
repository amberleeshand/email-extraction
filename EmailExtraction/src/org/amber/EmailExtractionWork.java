package org.amber;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailExtractionWork {

    public static void main(String[] args) {
        String fileName = "/Users/acshand/IdeaProjects/email-extraction/EmailExtraction/sample.txt";
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

        int counter = 0;
        String contents = Files.readString(filePath);
        //regex to find all of the Softwire email addresses
//        String regex = "[a-zA-Z0-9_\\-\\.]+[@]+softwire\\.com\\s";
        String regex = "(?<name>.[\\w_]+)@(?<domain>(?:[\\w\\-.]+)\\.)+([\\w\\-.]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(contents);

        Set<String> domainSet = new HashSet<String>();
        while (matcher.find()) {
            String domain = matcher.group("domain");
            domainSet.add(domain);
//            System.out.println(matcher.group("domain"));
//            counter++;
        }

        for (String oneDomain: domainSet) {
//            System.out.println(oneDomain);
        }

//        if (matcher.find()) {
//            counter++;
//            System.out.println("Found here: "+ matcher.group());
//        }
        System.out.printf("Found %s %d times", regex, counter);
    }


}
