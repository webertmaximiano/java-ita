package observer;

import classes.Achievement;
import classes.Badge;
import classes.Points;
import storage.AchievementStorage;
import storage.AchievementStorageFactory;

public class ParticipationPointsObserver implements AchievementObserver {
    private static final int PARTICIPATION_POINTS_THRESHOLD = 100;
    private static final String PART_OF_COMMUNITY_BADGE_NAME = "PART OF THE COMMUNITY";

    @Override
    public void achievementUpdate(String user, Achievement a) {
        if (a instanceof Points && ((Points) a).getType().equals("PARTICIPATION")) {
            int totalPoints = ((Points) a).getValue();
            if (totalPoints >= PARTICIPATION_POINTS_THRESHOLD) {
                // Concede o badge "PART OF THE COMMUNITY" ao usuário
                AchievementStorage achievementStorage = AchievementStorageFactory.getAchievementStorage();
                Achievement partOfCommunityBadge = new Badge(PART_OF_COMMUNITY_BADGE_NAME);
                achievementStorage.addAchievement(user, partOfCommunityBadge);
            }
        }
    }
}
