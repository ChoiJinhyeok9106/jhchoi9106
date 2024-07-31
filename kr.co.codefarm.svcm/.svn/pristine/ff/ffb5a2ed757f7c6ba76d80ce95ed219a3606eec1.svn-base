package kr.co.codefarm.svcm.commons.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;

import java.io.IOException;
import java.util.*;

@SuppressWarnings({ "deprecation" })
public class XmlUntypedObjectDeserializer extends UntypedObjectDeserializer {
	
	private static final long serialVersionUID                  = 1L;
	private static final XmlUntypedObjectDeserializer _INSTANCE = new XmlUntypedObjectDeserializer();

	public static XmlUntypedObjectDeserializer getInstance() {
		return _INSTANCE;
	}

    @Override
    @SuppressWarnings({ "unchecked" })
    protected Object mapObject(JsonParser parser, DeserializationContext context) throws IOException {
        String firstKey = "";
        JsonToken token = parser.getCurrentToken();
        
        if (token == JsonToken.START_OBJECT) {
            firstKey = parser.nextFieldName();
        } else if (token == JsonToken.FIELD_NAME) {
            firstKey = parser.getCurrentName();
        } else {
            if (token == JsonToken.END_OBJECT) {
                return Collections.emptyMap();
            }
        }

        Map<String, Object> valueByKey = new LinkedHashMap<>();
        String nextKey = firstKey;
        do {
            parser.nextToken();
            Object nextValue = deserialize(parser, context);

            if (valueByKey.containsKey(nextKey)) {
                Object existingValue = valueByKey.get(nextKey);
                if (existingValue instanceof List) {
                    List<Object> values = (List<Object>) existingValue;
                    values.add(nextValue);
                } else {
                    List<Object> values = new ArrayList<>();
                    values.add(existingValue);
                    values.add(nextValue);
                    valueByKey.put(nextKey, values);
                }
            }

            else {
                valueByKey.put(nextKey, nextValue);
            }

        } while ((nextKey = parser.nextFieldName()) != null);

        return valueByKey;
    }

}
