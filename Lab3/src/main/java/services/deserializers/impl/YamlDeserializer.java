package services.deserializers.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import models.UserProfiles;
import services.deserializers.UserProfileDeserializer;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YamlDeserializer implements UserProfileDeserializer {

    @Override
    public List<UserProfiles> readList(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        List<UserProfiles> userProfiles = null;
        try {
            userProfiles = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return userProfiles;
    }

    @Override
    public UserProfiles read(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        UserProfiles userProfiles = null;
        try {
            userProfiles = objectMapper.readValue(new File(filePath), UserProfiles.class);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return userProfiles;
    }
}

//static class MessageSerializer extends StdSerializer<Message> {
//    public MessageSerializer() {
//        this(null);
//    }
//
//    public MessageSerializer(Class<Message> t) {
//        super(t);
//    }
//
//    @Override
//    public void serialize(Message message, JsonGenerator gen, SerializerProvider provider) throws IOException {
//        gen.writeStartObject();
//        gen.writeStringField("subject", message.getSubject());
//        gen.writeStringField("text", message.getText());
//        // Skip the "date" field
//        gen.writeEndObject();
//    }
//}
