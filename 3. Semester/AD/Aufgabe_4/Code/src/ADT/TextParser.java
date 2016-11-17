/**
 * Copyright (C) <2015>  @author Mesut, Igor and Anton
 */

package ADT;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;

public class TextParser {
    public static HashSet readSourceFile(String file) {
        final String DELIMITERS = "[ .,?!]+";
        HashSet<String> parsedWords = new HashSet<>();

        Path inputFile = Paths.get(file);
        try (BufferedReader reader = Files.newBufferedReader(inputFile, StandardCharsets.UTF_8)) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {

                String parts[] = inputLine.split(DELIMITERS);

                for (int i = 0; i < parts.length; i++) {
                    parsedWords.add(parts[i]);
                }
            }
        } catch (IOException e) {
            System.out.println("Import Error");
            parsedWords.clear();
            return parsedWords;
        }
        return parsedWords;
    }
}
