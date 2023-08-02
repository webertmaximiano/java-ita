package observer;

import classes.Achievement;
import classes.Badge;
import classes.Points;
import storage.AchievementStorageFactory;

public class InventorBadgeObserver implements AchievementObserver {

    @Override
    public void achievementUpdate(String user, Achievement a) {
        if (a instanceof Points) {
            Points points = (Points) a;
            if (points.getType().equals("CREATION") && points.getValue() >= 100) {
                // Adicionar o badge "INVENTOR" para o usuário
                AchievementStorageFactory.getAchievementStorage().addAchievement(user, new Badge("INVENTOR"));
            }
        }
    }
}
