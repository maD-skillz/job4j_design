package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import ru.job4j.serialization.xml.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public boolean isEmpty() {
        return isEmpty;
    }

    public int getCount() {
        return count;
    }

    public JsonContact getJsonContact() {
        return jsonContact;
    }

    public String[] getCounter() {
        return counter;
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

        JSONObject jsonContact = new JSONObject("{\"phone\":\"+7(999)999-999-99-99\"}");

        List<String> list = new ArrayList<>();
        list.add("Student");
        list.add("Free");
        JSONArray jsonStatuses = new JSONArray(list);


        final JsonExample json = new JsonExample(
                false, 25, new JsonContact("111333"), "One", "Two");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isEmpty", json.isEmpty());
        jsonObject.put("count", json.getCount());
        jsonObject.put("jsonContact", jsonContact);
        jsonObject.put("jsonCounter", jsonStatuses);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(json).toString());

   /*     final Gson gson = new GsonBuilder().create();
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
        System.out.println(jsonEx); */
    }
}
