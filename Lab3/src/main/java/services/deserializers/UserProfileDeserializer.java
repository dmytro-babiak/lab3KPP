package services.deserializers;

import models.UserProfiles; // Змінено для роботи з UserProfiles
import java.util.List;

public interface UserProfileDeserializer {

    List<UserProfiles> readList(String filePath);

    UserProfiles read(String filePath);
}
