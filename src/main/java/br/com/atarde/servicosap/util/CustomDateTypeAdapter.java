package br.com.atarde.servicosap.util;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CustomDateTypeAdapter implements JsonSerializer<Date>, JsonDeserializer<Date> {
	
	private final SimpleDateFormat dateFormat;

	public CustomDateTypeAdapter() {
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
		this.dateFormat.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo")); // -03:00
		
	}

	@Override
	public JsonElement serialize(Date date, Type type, JsonSerializationContext context) {
		return new JsonPrimitive(dateFormat.format(date));
	}

	@Override
	public Date deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
		try {
			return dateFormat.parse(element.getAsString());
		} catch (ParseException e) {
			throw new JsonParseException(e);
		}
	}
}