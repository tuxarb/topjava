package ru.javawebinar.topjava.web.json;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.javawebinar.topjava.util.TimeUtil;

import java.io.IOException;
import java.time.LocalDateTime;

public class JsonLocalDateTimeConverter {
    public static class JsonSerializer extends com.fasterxml.jackson.databind.JsonSerializer<LocalDateTime>
    {
        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeString(TimeUtil.toString(localDateTime));
        }
    }

    public static class JsonDeserializer extends com.fasterxml.jackson.databind.JsonDeserializer<LocalDateTime>
    {
        @Override
        public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            return TimeUtil.parseLocalDateTime(jsonParser.getValueAsString());
        }
    }
}
