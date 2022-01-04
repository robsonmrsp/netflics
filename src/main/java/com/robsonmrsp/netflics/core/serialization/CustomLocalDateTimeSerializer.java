package com.robsonmrsp.netflics.core.serialization;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/** generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20  */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

	@Override
	public Class<LocalDateTime> handledType() {
		return LocalDateTime.class;
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider arg2) throws IOException, JsonProcessingException {

		gen.writeString(value.format(formatter));

	}
}