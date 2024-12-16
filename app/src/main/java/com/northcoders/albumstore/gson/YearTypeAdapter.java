package com.northcoders.albumstore.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.Year;
import java.time.format.DateTimeParseException;

public class YearTypeAdapter implements JsonSerializer<Year>, JsonDeserializer<Year> {

    @Override
    public Year deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return Year.parse(json.getAsString());
        } catch (DateTimeParseException e) {
            throw new JsonParseException(e);
        }
    }

    @Override
    public JsonElement serialize(Year src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
}
