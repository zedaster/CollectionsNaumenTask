package ru.naumen.collection.task2;

import java.util.HashMap;
import java.util.Map;

/**
 * Дано:
 * <pre>
 * public class Ticket {
 *     private long id;
 *     private String client;
 *     …
 * }</pre>
 * <p>Разработать программу для бармена в холле огромного концертного зала.
 * Зрители в кассе покупают билет (класс Ticket), на котором указан идентификатор билета (id) и имя зрителя.
 * При этом, есть возможность докупить наборы разных товаров (комбо-обед): нет товаров, напитки, еда и напитки.
 * Доп. услуги оформляются через интернет и привязываются к билету, но хранятся отдельно от билета
 * (нельзя добавить товары в класс Ticket).</p>
 * <p>Бармен сканирует билет и получает объект Ticket. По этому объекту нужно уметь
 * находить необходимые товары по номеру билета. И делать это нужно очень быстро,
 * ведь нужно как можно быстрее всех накормить.</p>
 * <p>
 * См. {@link Ticket}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task2 {

    // Выбран HashMap для добавления по хэшу за O(1) и получения элемента за O(1)
    private Map<Ticket, LunchType> lunchTypeMap = new HashMap<>();

    /**
     * Устанавливает ланч-набор для зрителя с билетом ticket
     * Выполняется за O(1)
     * @param ticket билет зрителя
     * @param lunchType тип ланч-набора
     */
    public void setLunchType(Ticket ticket, LunchType lunchType) {
        lunchTypeMap.put(ticket, lunchType);
    }

    /**
     * Получает ланч-набор зрителя с билетом ticket
     * Выполняется за O(1)
     * @param ticket билет зрителя
     * @return тип ланч-набора. Если зритель не покупал набор, вернется {@link LunchType#NO_LUNCH}
     */
    public LunchType getLunchType(Ticket ticket) {
        LunchType type = lunchTypeMap.get(ticket);
        if (type == null) {
            return LunchType.NO_LUNCH;
        }
        return type;
    }
}
