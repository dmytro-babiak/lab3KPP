package main;

import models.UserProfiles;
import models.Message;
import services.serializers.NativeSerializer;
import services.serializers.impl.JacksonSerializer;
import services.serializers.impl.YamlSerializer;
import services.deserializers.impl.JacksonDeserializer;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private final String path = "C:/Users/dimab/OneDrive/Desktop/nulp3/KPP/lab3v2/Lab3/src/main/resources/";
    private List<UserProfiles> userProfiles;

    public static void main(String[] args) {
        Main main = new Main();
        main.readUserProfiles();

        main.YamlSerialization();
        main.JacksonSerialization();
        main.NativeSerialization();
    }

    // Метод для серіалізації в YAML
    public void YamlSerialization() {
        var yamlSerializer = new YamlSerializer();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "userProfiles.yaml"))) {
            yamlSerializer.dumpAll(userProfiles, writer);
        } catch (IOException e) {
            System.out.println("Error during YAML serialization: " + e.getMessage());
        }
    }

    // Метод для серіалізації в JSON (Jackson)
    public void JacksonSerialization() {
        var jacksonSerializer = new JacksonSerializer();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path + "userProfiles.json"))) {
            jacksonSerializer.dumpAll(userProfiles, writer);
        } catch (IOException e) {
            System.out.println("Error during JSON serialization: " + e.getMessage());
        }
    }

    // Нативна серіалізація
    public void NativeSerialization() {
        var nativeSerializer = new NativeSerializer();
        try (FileOutputStream stream = new FileOutputStream(path + "userProfiles.dat")) {
            nativeSerializer.dumpAll(userProfiles, stream);
        } catch (IOException e) {
            System.out.println("Error during Native serialization: " + e.getMessage());
        }
    }

    // Читання даних з JSON
    public void readUserProfiles() {
        JacksonReading();
        // Можна додати обробку або генерацію даних для тестування
    }

    // Читання з Jackson
    public void JacksonReading() {
        var reader = new JacksonDeserializer();
        userProfiles = reader.readList(path + "userProfiles.json");
    }
}
