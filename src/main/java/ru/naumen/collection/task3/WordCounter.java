package ru.naumen.collection.task3;

import java.util.Objects;

public class WordCounter implements Comparable<WordCounter> {
    private String word;
    private Integer count;

    public WordCounter(String word, Integer count) {
        this.word = word;
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }

    public Integer getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }

    @Override
    public int compareTo(WordCounter o) {
        int compareResult = this.count.compareTo(o.count);
        if (compareResult == 0) {
            return this.word.compareTo(o.word);
        } else {
            return compareResult;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WordCounter that = (WordCounter) o;
        return Objects.equals(word, that.word) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(word, count);
    }
}
