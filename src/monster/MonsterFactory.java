package monster;

import entity.Entity;
import main.GamePanel;

public class MonsterFactory implements AbstractFactory<Entity, GamePanel, MonsterClass>{
    @Override
    public Monster create(GamePanel gp,MonsterClass monsterClass) {
        switch (monsterClass){
            case Wolf -> {
                return new Wolf(gp);
            }
            case GreenSlime -> {
                return new GreenSlime(gp);
            }
            case RedSlime -> {
                return new RedSlime(gp);
            }
            case Sunflower -> {
                return new Sunflower(gp);
            }
            case SandSlime -> {
                return new SandSlime(gp);
            }

        }
        return null;
    }

}
