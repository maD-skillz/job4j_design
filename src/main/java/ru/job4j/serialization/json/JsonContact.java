package ru.job4j.serialization.json;

public class JsonContact {
    private final String numbers;

    public JsonContact(String numbers) {
        this.numbers = numbers;
    }

    public String getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return "JsonContact{"
                + "numbers="
                + numbers
                + '}';
    }
}
