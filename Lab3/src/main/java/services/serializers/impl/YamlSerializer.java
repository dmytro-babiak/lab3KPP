package services.serializers.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import models.Message;
import models.UserProfiles;
import services.serializers.helpers.MessageSerializer;
import services.serializers.UserProfileSerializer;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class YamlSerializer implements UserProfileSerializer {
    private final ObjectMapper objectMapper;

    public YamlSerializer() {
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        SimpleModule module = new SimpleModule();
        module.addSerializer(Message.class, new MessageSerializer());
        this.objectMapper.registerModule(module);
    }

    @Override
    public String serialize(UserProfiles userProfile) {
        try {
            return objectMapper.writeValueAsString(userProfile);
        } catch (IOException e) {
            System.out.println("Error serializing user profile: " + e.getMessage());
            return null;
        }
    }

    @Override
    public void dump(UserProfiles userProfile, Writer writer) {
        try {
            objectMapper.writeValue(writer, userProfile);
        } catch (IOException e) {
            System.out.println("Error dumping user profile: " + e.getMessage());
        }
    }

    @Override
    public void dumpAll(List<UserProfiles> userProfiles, Writer writer) {
        try {
            objectMapper.writeValue(writer, userProfiles);
        } catch (IOException e) {
            System.out.println("Error dumping user profiles: " + e.getMessage());
        }
    }

}