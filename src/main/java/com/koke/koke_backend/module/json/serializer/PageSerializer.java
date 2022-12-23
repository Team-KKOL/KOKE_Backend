package com.koke.koke_backend.module.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;

public class PageSerializer extends StdSerializer<PageImpl> {

	private static final long serialVersionUID = -2740002483620849536L;

	public PageSerializer() {
		super(PageImpl.class);
	}

	@Override
	public void serialize(PageImpl value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeFieldName("content");
        provider.defaultSerializeValue(value.getContent(), gen);
        gen.writeObjectField("pageable", value.getPageable());
        gen.writeNumberField("totalElements", value.getTotalElements());
        gen.writeNumberField("totalPages", value.getTotalPages());
        gen.writeNumberField("size", value.getSize());
        gen.writeNumberField("number", value.getNumber());
        gen.writeObjectField("sort", value.getSort());
        gen.writeBooleanField("first", value.isFirst());
        gen.writeBooleanField("last", value.isLast());
        gen.writeNumberField("numberOfElements", value.getNumberOfElements());
        gen.writeBooleanField("empty", value.isEmpty());
        gen.writeBooleanField("hasPrevious", value.hasPrevious());
        gen.writeBooleanField("hasNext", value.hasNext());
		gen.writeEndObject();
	}

}
