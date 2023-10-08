package bilalov.hh.trainee.service;

import bilalov.hh.trainee.entity.Monster;
import bilalov.hh.trainee.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Dungeon {
    private List<Monster> monsters = new ArrayList<Monster>();
    private Player player;

    public String startAdventure(int difficultyLvl){
        init(difficultyLvl);

        String turn = "player";
        while (monsters.size() > 0){
            StoryTeller.getBrokeIntoPiecesText("\nВы долго брели по туннелям, а там... какое то чудовище");
            Monster currentMonster = monsters.get(0);
            StoryTeller.getBrokeIntoPiecesText("Внимательно осмотрев соперника вы выяснили его характеристики: " +
                    currentMonster.toString(), 5000);

            while (player.isAlive() && currentMonster.isAlive()){
                if (turn.equals("player")){
                    player.hit(currentMonster);
                    turn = "monster";
                } else {
                    currentMonster.hit(player);
                    turn = "player";
                }
            }

            if (!player.isAlive()){
                return "Вам пришлось отступить, чтобы восстановить силы, награду вы оставили там";
            }

            //Я так понимаю, что это очень ресурсоемко, тк они все сдвигаются влево
            //Решение: переделать на последний элемент
            monsters.remove(0);
            StoryTeller.getBrokeIntoPiecesText("Этот монстр мертв! Собрав с него вещи, вы продолжили путь...");
        }

        return "Вы прошли этот данж! Все золото ваше!";
    }

    private void init(int difficultyLvl){
        StoryTeller.getBrokeIntoPiecesText("Генерация данжа");
        player = new Player();

        for (int i = 0; i < difficultyLvl; i++) {
            monsters.add(new Monster());
        }
    }
}
