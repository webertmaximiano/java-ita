package functions;

import java.util.ArrayList;
import java.util.List;

import classes.Achievement;
import classes.Badge;
import classes.Points;
import observer.AchievementObserver;
import storage.AchievementStorage;
import storage.AchievementStorageFactory;

public class ForumServiceGamificationProxy implements ForumService {
    private ForumService forumService;
    private AchievementStorage storage;
    private List<AchievementObserver> observers = new ArrayList<>();

    public ForumServiceGamificationProxy(ForumService forumService, AchievementStorage storage) {
        this.forumService = forumService;
        this.storage = storage;
    }

    @Override
    public void addTopic(String user, String topic) {
        forumService.addTopic(user, topic);
        // Adicionar conquistas apropriadas com base nas regras definidas
        AchievementStorage achievementStorage = AchievementStorageFactory.getAchievementStorage();
        Achievement pointsAchievement = new Points("CREATION", 5);
        Achievement badgeAchievement = new Badge("I CAN TALK");
        achievementStorage.addAchievement(user, pointsAchievement);
        achievementStorage.addAchievement(user, badgeAchievement);

        // Notificar os observadores sobre a nova conquista
        notifyObservers(user, pointsAchievement);
    }

    @Override
    public void addComment(String user, String topic, String comment) {
        forumService.addComment(user, topic, comment);
        // Adicionar conquistas apropriadas com base nas regras definidas
        AchievementStorage achievementStorage = AchievementStorageFactory.getAchievementStorage();
        Achievement pointsAchievement = new Points("PARTICIPATION", 3);
        Achievement badgeAchievement = new Badge("LET ME ADD");
        achievementStorage.addAchievement(user, pointsAchievement);
        achievementStorage.addAchievement(user, badgeAchievement);

        // Notificar os observadores sobre a nova conquista
        notifyObservers(user, pointsAchievement);
    }

    @Override
    public void likeTopic(String user, String topic, String topicUser) {
        forumService.likeTopic(user, topic, topicUser);
        // Adicionar conquista apropriada com base nas regras definidas
        AchievementStorage achievementStorage = AchievementStorageFactory.getAchievementStorage();
        Achievement pointsAchievement = new Points("CREATION", 1);
        achievementStorage.addAchievement(topicUser, pointsAchievement);

        // Notificar os observadores sobre a nova conquista
        notifyObservers(topicUser, pointsAchievement);
    }

    @Override
    public void likeComment(String user, String topic, String comment, String commentUser) {
        forumService.likeComment(user, topic, comment, commentUser);
        // Adicionar conquista apropriada com base nas regras definidas
        AchievementStorage achievementStorage = AchievementStorageFactory.getAchievementStorage();
        Achievement pointsAchievement = new Points("PARTICIPATION", 1);
        achievementStorage.addAchievement(commentUser, pointsAchievement);

        // Notificar os observadores sobre a nova conquista
        notifyObservers(commentUser, pointsAchievement);
    }

    // Método para registrar observadores
    public void registerObserver(AchievementObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(String user, Achievement a) {
        for (AchievementObserver observer : observers) {
            observer.achievementUpdate(user, a);
        }
    }
}