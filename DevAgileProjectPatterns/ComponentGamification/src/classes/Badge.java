package classes;

import java.util.List;

public class Badge extends Achievement {
    public Badge(String name) {
        super(name);
    }

    @Override
    public void addAchievement(List<Achievement> achievements) {
        if (!isPresentIn(achievements)) {
            achievements.add(this);
        }
    }
}
