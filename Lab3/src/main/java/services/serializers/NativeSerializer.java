package services.serializers;

import models.UserProfiles;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class NativeSerializer {


    public String serialize(UserProfiles userProfile) {
        try (
                ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteStream)
        ) {
            objectOutputStream.writeObject(userProfile);
            objectOutputStream.flush();
            return byteStream.toString(StandardCharsets.ISO_8859_1);
        } catch (IOException e) {
            System.out.println("Error serializing user profile: " + e.getMessage());
            return null;
        }
    }


    public void dump(UserProfiles userProfile, OutputStream stream) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream)) {
            objectOutputStream.writeObject(userProfile);
        } catch (IOException e) {
            System.out.println("Error writing user profile: " + e.getMessage());
        }
    }


    public void dumpAll(List<UserProfiles> userProfiles, OutputStream stream) {
        if (userProfiles == null) {
            System.out.println("Error: userProfiles list is null.");
            return;
        }

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream)) {
            for (UserProfiles userProfile : userProfiles) {
                objectOutputStream.writeObject(userProfile);
            }
        } catch (IOException e) {
            System.out.println("Error writing user profiles: " + e.getMessage());
        }
    }


    public UserProfiles load(InputStream stream) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(stream)) {
            return (UserProfiles) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading user profile: " + e.getMessage());
            return null;
        }
    }


    public List<UserProfiles> loadAll(InputStream stream) {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(stream)) {
            return (List<UserProfiles>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading user profiles: " + e.getMessage());
            return null;
        }
    }
}
