package ru.naumen.collection.task2;

import java.util.Objects;

/**
 * Билет
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 */
public class Ticket {
    private long id;
    private String client;

    /**
     * Сравнивает содержимое этого объекта с любым другим
     * @param o - объект, с которым сравнивается данный
     * @return true, если класс Ticket и их id идентичны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return id == ticket.id;
    }

    /**
     * Вычисляет хэш-код объекта
     * Необходимо для корректной работы в хэш-коллекциях
     * Берется только id, т.к. он уникален
     * @return хэш-код объекта
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
