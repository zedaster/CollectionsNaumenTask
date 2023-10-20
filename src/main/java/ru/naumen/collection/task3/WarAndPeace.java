package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    // Такая реализация НЕ работает
    // Если, к примеру, слово "декабристов" встречается 2 раз, оно уже есть в bottom ("декабристов" - 1),
    // то из bottom его придется удалить. Значений в bottom останется 9, десятое брать не откуда.
    // Эта проблема может сделать итоговый списки неполными и неточными
    // Следовательно при сортировке необходимо учитывать ВСЕ ранние значения, т.к. слово "декабристов" из примера
    // могло встретиться и в самом начале.
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        SortedSet<WordCounter> topCounterSet = new TreeSet<>(Comparator.reverseOrder());
        SortedSet<WordCounter> bottomCounterSet = new TreeSet<>();

        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        wordParser.forEachWord((word) -> {
            int newCount;
            if (map.containsKey(word)) {
                newCount = map.get(word) + 1;
                map.put(word, newCount);
            } else {
                newCount = 1;
                map.put(word, 1);
            }

            WordCounter newCounter = new WordCounter(word, newCount);
            WordCounter oldCounter = new WordCounter(word, newCount - 1);
            if (topCounterSet.size() < 10) {
                topCounterSet.remove(oldCounter);
                bottomCounterSet.remove(oldCounter);
                topCounterSet.add(newCounter);
                bottomCounterSet.add(newCounter);
            } else {
                if (topCounterSet.last().compareTo(newCounter) < 0) {
                    topCounterSet.add(newCounter);
                    topCounterSet.remove(oldCounter);
                    if (topCounterSet.size() > 10) {
                        topCounterSet.remove(topCounterSet.last());
                    }
                }
                if (bottomCounterSet.last().compareTo(newCounter) > 0) {
                    bottomCounterSet.add(newCounter);
                    bottomCounterSet.remove(oldCounter);
                    if (bottomCounterSet.size() > 10) {
                        bottomCounterSet.remove(bottomCounterSet.last());
                    }
                }
            }
        });

        System.out.println("Топ самых наиболее используемых слов:");
        for (WordCounter counter : topCounterSet) {
            System.out.println(counter.getWord() + " " + counter.getCount());
        }

        System.out.println();
        System.out.println("Топ самых наименее используемых слов:");
        for (WordCounter counter : bottomCounterSet) {
            System.out.println(counter.getWord() + " " + counter.getCount());
        }
    }
}
