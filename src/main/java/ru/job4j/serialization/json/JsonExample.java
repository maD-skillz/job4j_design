package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class JsonExample {
    private final boolean isEmpty;
    private final int count;
    private final JsonContact jsonContact;
    private final String[] counter;


    public JsonExample(boolean isEmpty, int count, JsonContact jsonContact, String... counter) {
        this.isEmpty = isEmpty;
        this.count = count;
        this.jsonContact = jsonContact;
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "JsonExample{"
                + "isEmpty=" + isEmpty
                + ", count=" + count
                + ", jsonContact='" + jsonContact
                + '\''
                + ", counter=" + Arrays.toString(counter)
                + '}';
    }

    public static void main(String[] args) {
        final JsonExample json = new JsonExample(
                false, 25, new JsonContact("111333"), "One", "Two");

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(json));

        final String jsonToString =
                "{"
                + "\"isEmpty\":false,"
                + "\"count\":40,"
                        + "\"jsonContact\":"
                + "{"
                + "\"numbers\":\"222444\""
                        + "}, "
                + "\"counter\":"
                + "[\" Three\",\"Four\"]"
                + "}";
        final JsonExample jsonEx = gson.fromJson(jsonToString, JsonExample.class);
        System.out.println(jsonEx);
    }
}
