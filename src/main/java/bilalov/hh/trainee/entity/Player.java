package bilalov.hh.trainee.entity;

import bilalov.hh.trainee.service.StoryTeller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Player extends Creature{
    private static final Logger logger = LogManager.getLogger(Monster.class);
    private int healStamina = 3;

    public Player() {
        super();
        logger.info("Создан новый Игрок с характеристиками: " + this.toString(), 2000);
        StoryTeller.getBrokeIntoPiecesText("\nВаши характеристики: " + this, 5000);
    }

    @Override
    public void hit(Creature creature){
        StoryTeller.getBrokeIntoPiecesText("\nХод игрока:");
        super.hit(creature);
    }

    @Override
    public void getHit(int damage){
        super.getHit(damage);
        StoryTeller.getBrokeIntoPiecesText("Игрок получает -- " + damage +" урона, остаток здоровья -- " + this.getCurrentHealthPoints());

        //Если есть стамина + хп < 50%
        if (this.getCurrentHealthPoints() < (int) (Math.floor(this.getMaxHealthPoints()*0.5)) && healStamina > 0){
            this.heal(this);
        }
    }

    //Сделал через параметр внутри метода, чтобы иметь возможность хилить кого то другого
    public void heal(Creature creature) {
        healStamina--;
        int healPoints = (int) (Math.floor(creature.getMaxHealthPoints()*0.3));
        int updatedHP = healPoints + creature.getCurrentHealthPoints();
        StoryTeller.getBrokeIntoPiecesText(String.format("Игрок лечится на %s, теперь у него -- %s ОЗ. " +
                "Осталось применений -- %s", healPoints, updatedHP, healStamina));
        creature.setCurrentHealthPoints(updatedHP);
    }
}
