package classes;

import java.util.List;

public class Points extends Achievement {
    private int value;
    private String type;

    public Points(String name, int value) {
        super(name);
        this.value = value;
        this.type = "CREATION"; // Defina o tipo para "CREATION" ou qualquer outro valor adequado
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    @Override
    public void addAchievement(List<Achievement> achievements) {
        if (!isPresentIn(achievements)) {
            achievements.add(this);
        } else {
            for (Achievement achievement : achievements) {
                if (achievement.getName().equals(getName()) && achievement instanceof Points) {
                    Points existingPoints = (Points) achievement;
                    existingPoints.value += this.value;
                }
            }
        }
    }
}