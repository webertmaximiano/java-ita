package storage;

import java.util.List;

import classes.Achievement;
import observer.AchievementObserver;

public interface AchievementStorage {
    void addAchievement(String user, Achievement a);
    List<Achievement> getAchievements(String user);
    Achievement getAchievement(String user, String achievementName);

    // Método para registrar os observadores
    void addObserver(AchievementObserver observer);

    // Método para remover os observadores (se necessário)
    void removeObserver(AchievementObserver observer);
}
