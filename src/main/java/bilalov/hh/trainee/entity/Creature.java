package bilalov.hh.trainee.entity;

import bilalov.hh.trainee.service.Dice;
import bilalov.hh.trainee.service.StoryTeller;

import java.util.HashMap;
import java.util.Map;

public abstract class Creature {
    private boolean isAlive = true;
    private int attack;
    private int defense;
    private int currentHealthPoints;
    private int maxHealthPoints;
    private Map<String, Integer> damage = new HashMap<String, Integer>();

    public Creature() {
        Dice dice = new Dice();
        this.attack = dice.getRandomNumber(1, 30);
        this.defense = dice.getRandomNumber(1, 30);
        int hp = dice.getRandomNumber(20, 40);
        this.currentHealthPoints = hp;
        this.maxHealthPoints = hp;

        int maxDamage = dice.getRandomNumber(1, 6);
        int minDamage = dice.getRandomNumber(1, 6);
        if (maxDamage < minDamage){
            int transit = maxDamage;
            maxDamage = minDamage;
            minDamage = transit;
        }
        damage.put("max", maxDamage);
        damage.put("min", minDamage);
    }

    public void hit(Creature creature){
        Dice dice = new Dice();
        int maxDamage = this.damage.get("max");
        int minDamage = this.damage.get("min");

        int attackModification = this.attack - creature.getDefense();
        attackModification = Math.max(attackModification, 1);
        StoryTeller.getBrokeIntoPiecesText("Модификатор атаки: " + attackModification + ", кидаем кости:");

        for (int i = 0; i < attackModification; i++) {
            int randomNumberFromDice = dice.roll();
            StoryTeller.getBrokeIntoPiecesText(String.format("#%s - цифра на кубике - %s", i+1, randomNumberFromDice));
            if (randomNumberFromDice == 5 || randomNumberFromDice == 6){
                int damage = dice.getRandomNumber(minDamage, maxDamage);
                StoryTeller.getBrokeIntoPiecesText("\n--------------------\n" + "Повезло! Атака проходит на... " + damage +
                        "\n--------------------\n");
                creature.getHit(damage);
            }
            if(!creature.isAlive)
                return;
        }
    };

    public void getHit(int damage){
        this.currentHealthPoints -= damage;
        if (this.currentHealthPoints < 1){
            currentHealthPoints = 0;
            this.isAlive = false;
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public int getAttack() {
        return attack;
    }

    public void setCurrentHealthPoints(int currentHealthPoints) {
        this.currentHealthPoints = currentHealthPoints;
    }

    public int getDefense() {
        return defense;
    }

    public int getCurrentHealthPoints() {
        return currentHealthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public Map<String, Integer> getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return  "\nНавыки атаки: " + attack +
                "\nЗащита: " + defense +
                "\nЗдоровье: " + currentHealthPoints +
                "\nСреднее значение атаки: " + (damage.get("min") + damage.get("max"))/2 ;
    }
}
