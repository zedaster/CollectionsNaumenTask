package ru.naumen.collection.task1;

import ru.naumen.collection.task2.Ticket;

import java.util.*;

/**
 * Дано:
 * <pre>
 * public class User {
 *     private String username;
 *     private String email;
 *     private byte[] passwordHash;
 *     …
 * }
 * </pre>
 * Нужно написать утилитный метод
 * <pre>
 * public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB);
 * </pre>
 * <p>который возвращает дубликаты пользователей, которые есть в обеих коллекциях.</p>
 * <p>Одинаковыми считаем пользователей, у которых совпадают все 3 поля: username,
 * email, passwordHash. Дубликаты внутри коллекций collA, collB можно не учитывать.</p>
 * <p>Метод должен быть оптимален по производительности.</p>
 * <p>Пользоваться можно только стандартными классами Java SE.
 * Коллекции collA, collB изменять запрещено.</p>
 * <p>
 * См. {@link User}
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Task1 {

    /**
     * Возвращает дубликаты пользователей, которые есть в обеих коллекциях
     * Работает за O(N)
     */
    public static List<User> findDuplicates(Collection<User> collA, Collection<User> collB) {
        // Выбрана, чтобы contains работал за O(1)
        Set<User> allUsers = new HashSet<>(collA);
        // Выбрано, т.к. возвращается List и добавление за O(1) в среднем
        List<User> duplicateUsers = new ArrayList<>();
        for (User user : collB) {
            if (allUsers.contains(user)) {
                duplicateUsers.add(user);
            }
        }
        return duplicateUsers;
    }
}
