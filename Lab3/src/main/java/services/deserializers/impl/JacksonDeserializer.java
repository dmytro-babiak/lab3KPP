package services.deserializers.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.UserProfiles;
import services.deserializers.UserProfileDeserializer;


import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonDeserializer implements UserProfileDeserializer {

    @Override
    public List<UserProfiles> readList(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<UserProfiles> UsersProfiles = null;
        try {
            UsersProfiles = objectMapper.readValue(new File(filePath), new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return UsersProfiles;
    }

    @Override
    public UserProfiles read(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        UserProfiles userProfiles = null;
        try {
            userProfiles = objectMapper.readValue(new File(filePath), UserProfiles.class);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return userProfiles;
    }
}
