package ru.naumen.collection.task3;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Stream;

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

    /**
     * Выводит на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов
     * из романа Льва Толстого “Война и мир”
     *
     * Сложность алгоритма O(N + N*logN)
     * @param args
     */
    public static void main(String[] args) {
        // Берем LinkedHashMap для обновления частоты слов за O(1)
        // и быстрой итерации по всем элементам в следующем шаге
        Map<String, Integer> map = new LinkedHashMap<>();

        // За O(N) считываем слова
        WordParser wordParser = new WordParser(WAR_AND_PEACE_FILE_PATH);
        wordParser.forEachWord((word) -> {
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        });

        // Используя Stream, сортируем пары в map и заливаем их в List (Immutable ArrayList) за O(N*logN)
        List<Map.Entry<String, Integer>> sortedEntries = map.entrySet()
                .stream()
                .sorted(Comparator
                        .comparing(Map.Entry<String, Integer>::getValue, Comparator.reverseOrder())
                        .thenComparing(Map.Entry::getKey))
                .toList();

        // Далее через subList за константное время получаем искомые топы
        System.out.println("Топ-10 наиболее используемых слов:");
        List<Map.Entry<String, Integer>> firstTenEntries = sortedEntries.subList(0, 10);
        for (Map.Entry<String, Integer> entry : firstTenEntries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println();
        System.out.println("Топ-10 наименее используемых слов:");
        int lastTenStartIndex = Math.max(sortedEntries.size() - 10, 0);
        int lastTenEndIndex = sortedEntries.size();
        List<Map.Entry<String, Integer>> lastTenEntries = sortedEntries.subList(lastTenStartIndex, lastTenEndIndex);
        for (Map.Entry<String, Integer> entry : lastTenEntries) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
