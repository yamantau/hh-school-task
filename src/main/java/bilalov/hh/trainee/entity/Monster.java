package bilalov.hh.trainee.entity;

import bilalov.hh.trainee.service.StoryTeller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Monster extends Creature {
    private static final Logger logger = LogManager.getLogger(Monster.class);
    public Monster() {
        super();
        logger.info("Создан новый Монстр с характеристиками: " + this.toString());
    }

    @Override
    public void hit (Creature creature){
        StoryTeller.getBrokeIntoPiecesText("\nХод Монстра:");
        super.hit(creature);
    }

    @Override
    public void getHit (int damage){
        super.getHit(damage);
        StoryTeller.getBrokeIntoPiecesText("Монстр получает -- " + damage +" урона, остаток здоровья -- " + this.getCurrentHealthPoints());
    }
}
