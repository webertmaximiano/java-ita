package test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import classes.Achievement;
import classes.Points;
import functions.ForumService;
import functions.ForumServiceGamificationProxy;
import observer.CreationPointsObserver;
import observer.InventorBadgeObserver;
import observer.PartOfTheCommunityBadgeObserver;
import observer.ParticipationPointsObserver;
import storage.AchievementStorage;
import storage.MemoryAchievementStorage;

public class TesteComponentGamification {
    private AchievementStorage achievementStorage;
    private ForumService forumService;
    private ForumService forumServiceMock;

    @BeforeEach
    public void setUp() {
        // Configurar o ambiente de testes
        achievementStorage = new MemoryAchievementStorage();

        // Adicionar os observadores
        achievementStorage.addObserver(new CreationPointsObserver());
        achievementStorage.addObserver(new ParticipationPointsObserver());
        achievementStorage.addObserver(new InventorBadgeObserver());
        achievementStorage.addObserver(new PartOfTheCommunityBadgeObserver());

        // Criar uma instância do ForumServiceGamificationProxy usando o mock do ForumService
        ForumService forumServiceMock = mock(ForumService.class);
        forumService = new ForumServiceGamificationProxy(forumServiceMock, achievementStorage);
    }

    @Test
    public void testAddTopic() {
        // Testar o método addTopic() do proxy
        forumService.addTopic("user1", "topic1");

        // Verificar se os achievements foram adicionados corretamente
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        assertEquals(2, userAchievements.size());

        boolean hasCreationPoints = false;
        boolean hasParticipationPoints = false;

        for (Achievement achievement : userAchievements) {
            if (achievement instanceof Points) {
                Points points = (Points) achievement;
                if (points.getType().equals("CREATION")) {
                    hasCreationPoints = true;
                } else if (points.getType().equals("PARTICIPATION")) {
                    hasParticipationPoints = true;
                }
            }
        }

        assertEquals(true, hasCreationPoints);
        assertEquals(true, hasParticipationPoints);

        // Assert that the badge "INVENTOR" is present
        boolean hasInventorBadge = userAchievements.stream().anyMatch(a -> a.getName().equals("INVENTOR"));
        assertEquals(true, hasInventorBadge);

        // Assert that the badge "PART OF THE COMMUNITY" is present
        boolean hasPartOfCommunityBadge = userAchievements.stream()
                .anyMatch(a -> a.getName().equals("PART OF THE COMMUNITY"));
        assertEquals(true, hasPartOfCommunityBadge);
    }
    
    @Test
    public void testAddComment() {
        // Testar o método addComment() do proxy
        forumService.addComment("user1", "comment1");

        // Verificar se os achievements foram adicionados corretamente
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        // Faça as verificações necessárias para garantir que os achievements foram adicionados corretamente
    }
    
    @Test
    public void testLikeTopic() {
        // Testar o método likeTopic() do proxy
        forumService.likeTopic("user1", "topic1", "topicuser");

        // Verificar se os achievements foram adicionados corretamente
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        // Faça as verificações necessárias para garantir que os achievements foram adicionados corretamente
    }
    
    @Test
    public void testLikeComment() {
        // Testar o método likeComment() do proxy
        forumService.likeComment("user1", "topic1", "comment1", "user2");

        // Verificar se os achievements foram adicionados corretamente
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        
        // Verificar se o Points para "PARTICIPATION" foi adicionado corretamente
        boolean hasParticipationPoints = false;
        for (Achievement achievement : userAchievements) {
            if (achievement instanceof Points) {
                Points points = (Points) achievement;
                if (points.getType().equals("PARTICIPATION")) {
                    assertEquals("user1", points.getName());
                    assertEquals(5, points.getValue());
                    assertEquals("PARTICIPATION", points.getType());
                    hasParticipationPoints = true;
                    break;
                }
            }
        }
        assertEquals(true, hasParticipationPoints);

        // Verificar se o badge "PART OF THE COMMUNITY" foi adicionado corretamente
        boolean hasPartOfCommunityBadge = userAchievements.stream()
                .anyMatch(a -> a.getName().equals("PART OF THE COMMUNITY"));
        assertEquals(true, hasPartOfCommunityBadge);
    }
    
    @Test
    public void testAddTopicTwice() {
        // Testar o método addTopic() do proxy duas vezes
        forumService.addTopic("user1", "topic1");
        forumService.addTopic("user1", "topic2");

        // Verificar se os achievements foram adicionados corretamente
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        
        // Verificar se os Points para "CREATION" foram adicionados corretamente e somados
        boolean hasCreationPoints = false;
        int totalPoints = 0;
        for (Achievement achievement : userAchievements) {
            if (achievement instanceof Points) {
                Points points = (Points) achievement;
                if (points.getType().equals("CREATION")) {
                    hasCreationPoints = true;
                    totalPoints += points.getValue();
                }
            }
        }
        assertEquals(true, hasCreationPoints);
        assertEquals(10, totalPoints); // Somou 5 pontos para cada chamada do método addTopic()

        // Verificar se o badge "INVENTOR" está presente apenas uma vez
        long inventorBadgeCount = userAchievements.stream()
                .filter(a -> a.getName().equals("INVENTOR"))
                .count();
        assertEquals(1, inventorBadgeCount);
    }
    

    @Test
    public void testMultipleMethodsInvocation() {
        // Simular a interação do usuário no fórum
        forumService.addTopic("user1", "topic1");
        forumService.addComment("user1", "topic1");
        forumService.likeTopic("user2", "topic1", "user1");
        forumService.addTopic("user1", "topic2");
        forumService.likeComment("user2", "topic1", "comment1", "user1");
        forumService.likeComment("user3", "topic1", "comment1", "user1");

        // Verificar se os achievements foram adicionados corretamente para o usuário "user1"
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        
        // Verificar se os Points para "CREATION" e "PARTICIPATION" foram adicionados corretamente
        boolean hasCreationPoints = false;
        boolean hasParticipationPoints = false;
        int totalCreationPoints = 0;
        int totalParticipationPoints = 0;
        for (Achievement achievement : userAchievements) {
            if (achievement instanceof Points) {
                Points points = (Points) achievement;
                if (points.getType().equals("CREATION")) {
                    hasCreationPoints = true;
                    totalCreationPoints += points.getValue();
                } else if (points.getType().equals("PARTICIPATION")) {
                    hasParticipationPoints = true;
                    totalParticipationPoints += points.getValue();
                }
            }
        }
        assertEquals(true, hasCreationPoints);
        assertEquals(true, hasParticipationPoints);
        assertEquals(10, totalCreationPoints); // Pontos para "CREATION": 5 + 5
        assertEquals(3, totalParticipationPoints); // Pontos para "PARTICIPATION": 1 + 1 + 1

        // Verificar se os badges "INVENTOR" e "PART OF THE COMMUNITY" estão presentes apenas uma vez
        long inventorBadgeCount = userAchievements.stream().filter(a -> a.getName().equals("INVENTOR")).count();
        long partOfCommunityBadgeCount = userAchievements.stream().filter(a -> a.getName().equals("PART OF THE COMMUNITY")).count();
        assertEquals(1, inventorBadgeCount);
        assertEquals(1, partOfCommunityBadgeCount);

        // Verificar se os métodos foram chamados corretamente no mock do ForumService
        verify(forumServiceMock).addTopic("user1", "topic1");
        verify(forumServiceMock).addComment("user1", "topic1");
        verify(forumServiceMock).likeTopic("user2", "topic1", "user1");
        verify(forumServiceMock).addTopic("user1", "topic2");
        verify(forumServiceMock).likeComment("user2", "topic1", "comment1", "user1");
        verify(forumServiceMock).likeComment("user3", "topic1", "comment1", "user1");
    }
    
    @Test
    public void testAddTopicWithException() {
        // Simular exceção sendo lançada pelo método addTopic do mock
        doThrow(new RuntimeException("Simulated exception")).when(forumServiceMock).addTopic("user1", "topic1");

        try {
            // Chamar o método addTopic do proxy, que deve lançar a exceção simulada
            forumService.addTopic("user1", "topic1");
        } catch (RuntimeException e) {
            // Capturou a exceção, o que é esperado
        }

        // Verificar se nenhum Achievement foi adicionado ao AchievementStorage
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        assertEquals(0, userAchievements.size());

        // Verificar se o método addTopic do mock foi chamado corretamente
        verify(forumServiceMock).addTopic("user1", "topic1");
    }
    
    @Test
    public void testReceiveInventorBadge() {
        // Adicionar 100 pontos de "CREATION" ao usuário
        for (int i = 0; i < 100; i++) {
            forumService.addTopic("user1", "topic" + i);
        }

        // Verificar se o badge "INVENTOR" foi adicionado ao usuário
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        boolean hasInventorBadge = userAchievements.stream().anyMatch(a -> a.getName().equals("INVENTOR"));
        assertEquals(true, hasInventorBadge);
    }
    
    @Test
    public void testReceivePartOfCommunityBadge() {
        // Adicionar 100 pontos de "PARTICIPATION" ao usuário
        for (int i = 0; i < 100; i++) {
            forumService.likeTopic("user1", "topic" + i, "user2");
        }

        // Verificar se o badge "PART OF THE COMMUNITY" foi adicionado ao usuário
        List<Achievement> userAchievements = achievementStorage.getAchievements("user1");
        boolean hasPartOfCommunityBadge = userAchievements.stream().anyMatch(a -> a.getName().equals("PART OF THE COMMUNITY"));
        assertEquals(true, hasPartOfCommunityBadge);
    }
}
