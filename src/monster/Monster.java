package monster;

import entity.Entity;
import main.GamePanel;

public abstract class Monster extends Entity {

    GamePanel gp;
    public Monster(GamePanel gp) {
        super(gp);
        this.gp = gp;
        type = 2;
        defaultSpeed = 1;
        speed = defaultSpeed;
        maxLife = 5;
        life = maxLife;
        attack = 1;
        defence = 0;
        exp = 2;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public Monster(GamePanel gp, Monster monster) {
        super(gp);
        this.gp = gp;
        type = monster.type;
        defaultSpeed = monster.defaultSpeed;
        speed = defaultSpeed;
        maxLife = monster.maxLife;
        life = maxLife;
        attack = monster.attack;
        defence = monster.defence;
        exp = monster.exp;

        solidArea.x = monster.solidArea.x;
        solidArea.y = monster.solidArea.y;
        solidArea.width = monster.solidArea.width;
        solidArea.height = monster.solidArea.height;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

    }

    public void setAction() {}

    public void damageReaction() {}

    public void checkDrop() {}

    public abstract Monster clone();
}
