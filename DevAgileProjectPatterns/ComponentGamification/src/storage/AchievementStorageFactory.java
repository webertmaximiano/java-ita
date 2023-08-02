package storage;

public class AchievementStorageFactory {
    private static AchievementStorage instance;

    public static void setAchievementStorage(AchievementStorage a) {
        instance = a;
    }

    public static AchievementStorage getAchievementStorage() {
        if (instance == null) {
            instance = new MemoryAchievementStorage();
        }
        return instance;
    }
}
