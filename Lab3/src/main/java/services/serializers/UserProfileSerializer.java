package services.serializers;

import models.UserProfiles;

import java.io.Writer;
import java.util.List;

public interface UserProfileSerializer {
    String serialize(UserProfiles userProfile);

    void dump(UserProfiles userProfile, Writer writer);

    void dumpAll(List<UserProfiles> userProfiles, Writer writer);
}
