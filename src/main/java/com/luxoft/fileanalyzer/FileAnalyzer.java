package com.luxoft.fileanalyzer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileAnalyzer {

    private String path;
    private String word;

    public FileAnalyzer() {
        System.out.println("File Analyzer start:");
    }

    public FileStatistics analyze(String path, String word) throws IOException {

        String fileContent = readContent(path);


        List<String> sentences = splitIntoSentences(fileContent);
        List<String> filteredSentences = filterSentences(sentences, word);

        int count = getEntries(filteredSentences, word);

        return new FileStatistics(filteredSentences, count);
    }

    /*
    * принимает путь к файлу
    * возвращает строку с содержимым файла
    * */
    private String readContent(String path) throws IOException {
        String fileContent;
        byte[] fileBytesContent;

        File file = new File(path);
        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(file);
            fileBytesContent = inputStream.readAllBytes();
            inputStream.close();

            fileContent = new String(fileBytesContent);
            return fileContent;
        } else {
          throw new FileNotFoundException("File not found!");
        }
    }

    /*
    *  принимает строку с содержимым файла
    *  возвращает список строк
    * */
    private List<String> splitIntoSentences(String fileContent) {
        String myRegex = "(?<=[a-z])\\.\\s+";
        String[] fileSentences;
        List<String> fileSentencesList = new ArrayList();

        fileSentences = fileContent.split(myRegex);

        for (String fileSentence : fileSentences) {
            fileSentencesList.add(fileSentence);
        }

        return fileSentencesList;
    }

    /*
    * принимает список строк и искомое слово
    * возвращает список строк, которые сожержат слово
    * */
    private List<String> filterSentences(List<String> sentences, String word) {

        List<String> filteredSentences = new ArrayList<>();

        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                filteredSentences.add(sentence);
            }
        }

        return filteredSentences;
    }

    /*
    * принимает список строк и искомое слово
    * возвращает колличество вхождений слова в строку
    * */
    public int getEntries(List<String> sentences, String word) {

        int entriesCount = 0;

        for (String sentence : sentences) {
            String[] sentenceWords = sentence.split("\\P{L}+");
            for (String sentenceWord : sentenceWords) {
                if (word.equals(sentenceWord)) {
                    entriesCount++;
                }
            }
        }

        return entriesCount;
    }

}
