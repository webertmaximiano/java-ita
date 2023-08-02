package observer;

import classes.Achievement;

public interface AchievementObserver {
    void achievementUpdate(String user, Achievement a);
}