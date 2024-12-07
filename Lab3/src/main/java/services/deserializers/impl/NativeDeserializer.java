package services.deserializers.impl;

import models.UserProfiles;
import services.deserializers.UserProfileDeserializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class NativeDeserializer implements UserProfileDeserializer {

    @Override
    public List<UserProfiles> readList(String filePath) {
        List<UserProfiles> userProfiles = new ArrayList<>();
        try (InputStream inputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            while (true) {
                try {
                    UserProfiles profile = (UserProfiles) objectInputStream.readObject();
                    userProfiles.add(profile);
                } catch (IOException e) {
                    // Кінець файлу або інша помилка - виходимо з циклу
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading user profiles list from file: " + e.getMessage());
        }
        return userProfiles;
    }

    @Override
    public UserProfiles read(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            // Десеріалізація одного профілю користувача
            return (UserProfiles) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading user profile from file: " + e.getMessage());
            return null;
        }
    }
    public UserProfiles read(InputStream inputStream) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (UserProfiles) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading user profile: " + e.getMessage());
            return null;
        }
    }

    public List<UserProfiles> readList(InputStream inputStream) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
            return (List<UserProfiles>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading user profiles: " + e.getMessage());
            return null;
        }
    }

}
