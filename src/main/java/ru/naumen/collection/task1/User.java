package ru.naumen.collection.task1;

import java.util.Arrays;
import java.util.Objects;

/**
 * Пользователь
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class User {
    private String username;
    private String email;
    private byte[] passwordHash;

    /**
     * Сравнивает содержимое этого объекта с любым другим
     * @param o - объект, с которым сравнивается данный
     * @return true, если класс User и поля объектов идентичны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Arrays.equals(passwordHash, user.passwordHash);
    }

    /**
     * Вычисляет хэш-код объекта
     * Необходимо для корректной работы в хэш-коллекциях
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(username, email);
        result = 31 * result + Arrays.hashCode(passwordHash);
        return result;
    }
}
