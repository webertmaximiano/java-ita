package observer;

import classes.Achievement;
import classes.Badge;
import classes.Points;
import storage.AchievementStorageFactory;

public class PartOfTheCommunityBadgeObserver implements AchievementObserver {

    @Override
    public void achievementUpdate(String user, Achievement a) {
        if (a instanceof Points) {
            Points points = (Points) a;
            if (points.getType().equals("PARTICIPATION") && points.getValue() >= 100) {
                // Adicionar o badge "PART OF THE COMMUNITY" para o usuário
                AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Badge("PART OF THE COMMUNITY"));
            }
        }
    }
}
