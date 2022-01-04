package com.robsonmrsp.netflics.core.serialization;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/** generated by JSetup v0.95 :  at 3 de jan de 2022 23:41:20  */
public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

	public static final Logger LOGGER = LoggerFactory.getLogger(CustomLocalDateTimeDeserializer.class);
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	@Override
	public Class<LocalDateTime> handledType() {
		return LocalDateTime.class;
	}

	@Override
	public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		String date = jp.getText();
		try {
			return LocalDateTime.parse(date, formatter);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.warn("Erro ao deserializar data: " + date, e);
		}
		return null;
	}
}