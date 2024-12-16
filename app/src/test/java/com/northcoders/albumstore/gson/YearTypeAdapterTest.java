package com.northcoders.albumstore.gson;


import static org.junit.Assert.assertEquals;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.time.Year;
import java.util.List;
import java.util.stream.Stream;

@RunWith(Parameterized.class)
public class YearTypeAdapterTest {

    private final YearTypeAdapter adapter;
    private final String string;
    private final Year year;
    private final JsonElement json;

    @Parameters
    public static List<Object[]> data() {
        return Stream.of("2024", "1970", "0")
                .map(string -> new Object[] { string, Year.parse(string), new JsonPrimitive(string) })
                .toList();
    }

    public YearTypeAdapterTest(String string, Year year, JsonElement json) {
        this.adapter = new YearTypeAdapter();
        this.string = string;
        this.year = year;
        this.json = json;
    }

    @Test
    public void canSerialize() {
        JsonElement result = adapter.serialize(year, Year.class, null);
        assertEquals(json, result);
    }

    @Test
    public void canDeserialize() {
        Year result = adapter.deserialize(json, Year.class, null);
        assertEquals(year, result);
    }
}