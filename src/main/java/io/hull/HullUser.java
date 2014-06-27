package io.hull;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class HullUser {

    public JsonNode rootNode;
    private static ObjectMapper mapper = new ObjectMapper();

    public HullUser(String userSource) {
        try {
            this.rootNode = mapper.readValue(userSource, JsonNode.class);
        } catch (Exception e) {
            this.rootNode = mapper.createObjectNode();
        }
    }

    public String getId() {
        return this.rootNode.path("id").asText();
    }

    public String get(String key) {
        return this.rootNode.path(key).asText();
    }

    public JsonNode getPath(String key) {
        return this.rootNode.path(key);
    }
}
