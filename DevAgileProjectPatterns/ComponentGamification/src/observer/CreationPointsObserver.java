package observer;

import classes.Achievement;
import classes.Badge;
import classes.Points;
import storage.AchievementStorage;
import storage.AchievementStorageFactory;

public class CreationPointsObserver implements AchievementObserver {
    private static final int CREATION_POINTS_THRESHOLD = 100;
    private static final String INVENTOR_BADGE_NAME = "INVENTOR";

    @Override
    public void achievementUpdate(String user, Achievement a) {
        if (a instanceof Points && ((Points) a).getType().equals("CREATION")) {
            int totalPoints = ((Points) a).getValue();
            if (totalPoints >= CREATION_POINTS_THRESHOLD) {
                // Concede o badge "INVENTOR" ao usuário
                AchievementStorage achievementStorage = AchievementStorageFactory.getAchievementStorage();
                Achievement inventorBadge = new Badge(INVENTOR_BADGE_NAME);
                achievementStorage.addAchievement(user, inventorBadge);
            }
        }
    }
}
