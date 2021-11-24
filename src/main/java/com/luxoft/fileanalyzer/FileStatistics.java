package com.luxoft.fileanalyzer;

import java.util.List;

public class FileStatistics {

    List<String> sentences;
    int wordEntries;

    public FileStatistics(){}

    public FileStatistics(List<String> sentences, int wordEntries){
        this.sentences = sentences;
        this.wordEntries = wordEntries;
    }

    public List<String> getSentences(){
        return sentences;
    }

    public int getWordEntries(){
        return wordEntries;
    }

}
