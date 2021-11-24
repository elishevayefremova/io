package com.luxoft.fileanalyzer;

import java.io.IOException;
import java.util.List;

public class Starter {
    public static void main(String[] args) throws IOException {

        String searchedWord = "cat";
        FileAnalyzer fileAnalyzer = new FileAnalyzer();
        FileStatistics result = fileAnalyzer.analyze("D:/Luxcampus/io/src/main/resources/text.txt", searchedWord);
        List<String> filteredSentences = result.getSentences();
        int searchedWordEntries = result.getWordEntries();

        printSentencesWithWord(filteredSentences);
        printWordEntries(searchedWordEntries, searchedWord);
    }

    public static void printSentencesWithWord(List<String> sentences){
        for (String sentence : sentences) {
            System.out.println(sentence);
        }
    }

    public static void printWordEntries(int searchedWordEntries, String searchedWord){
        System.out.println("There are " + searchedWordEntries + " '" + searchedWord + "' word entries in this text.");
    }
}
