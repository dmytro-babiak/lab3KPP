package services.IO;

import models.UserProfiles;
import models.Message;

import java.io.*;
import java.util.List;

public class UserProfileIO {

    public void writeProfilesToFile(List<UserProfiles> userProfiles, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (UserProfiles profile : userProfiles) {
                writer.write(profile.getId() + "," + profile.getFirstName() + "," + profile.getLastName() + "," + profile.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing user profiles to file: " + e.getMessage());
        }
    }


    public List<UserProfiles> readProfilesFromFile(String fileName) {
        List<UserProfiles> userProfiles = new java.util.ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    userProfiles.add(new UserProfiles(
                            Integer.parseInt(parts[0]),
                            parts[1],
                            parts[2],
                            parts.length > 3 ? parts[3] : null,
                            new java.util.ArrayList<>()
                    ));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading user profiles from file: " + e.getMessage());
        }

        return userProfiles;
    }


    public void writeMessagesToFile(List<Message> messages, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Message message : messages) {
                oos.writeObject(message);
            }
        } catch (IOException e) {
            System.out.println("Error writing messages to file: " + e.getMessage());
        }
    }


    public List<Message> readMessagesFromFile(String fileName) {
        List<Message> messages = new java.util.ArrayList<>();

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            while (fis.available() > 0) {
                messages.add((Message) ois.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading messages from file: " + e.getMessage());
        }

        return messages;
    }
}
