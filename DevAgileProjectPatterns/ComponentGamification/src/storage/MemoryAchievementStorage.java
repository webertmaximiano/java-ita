package storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classes.Achievement;
import observer.AchievementObserver;

public class MemoryAchievementStorage implements AchievementStorage {
    private Map<String, List<Achievement>> achievementsMap;
    private List<AchievementObserver> observers;

    public MemoryAchievementStorage() {
        achievementsMap = new HashMap<>();
        observers = new ArrayList<>();
    }

    @Override
    public void addAchievement(String user, Achievement a) {
        List<Achievement> userAchievements = achievementsMap.getOrDefault(user, new ArrayList<>());
        userAchievements.add(a);
        achievementsMap.put(user, userAchievements);
        
        // Notificar os observadores quando um novo achievement é adicionado
        for (AchievementObserver observer : observers) {
            observer.achievementUpdate(user, a);
        }
    }

    @Override
    public List<Achievement> getAchievements(String user) {
        return achievementsMap.getOrDefault(user, new ArrayList<>());
    }

    @Override
    public Achievement getAchievement(String user, String achievementName) {
        List<Achievement> userAchievements = achievementsMap.getOrDefault(user, new ArrayList<>());
        for (Achievement achievement : userAchievements) {
            if (achievement.getName().equals(achievementName)) {
                return achievement;
            }
        }
        return null;
    }

    @Override
    public void addObserver(AchievementObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(AchievementObserver observer) {
        observers.remove(observer);
    }
}
