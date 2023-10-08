package bilalov.hh.trainee;

import bilalov.hh.trainee.entity.Monster;
import bilalov.hh.trainee.service.Dungeon;
import bilalov.hh.trainee.service.StoryTeller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Game {

    private static final Logger logger = LogManager.getLogger(Game.class);

    public static void main(String[] args){
        logger.info("Старт игры");

        Dungeon dungeon = new Dungeon();
        StoryTeller.getBrokeIntoPiecesText(dungeon.startAdventure(initGame()));

        logger.info("Окончание игры");
    }

    //От этого зависит, сколько монстров будет на пути
    private static int initGame(){
        StoryTeller.getBrokeIntoPiecesText("Инициализация игры");

        int difficultyLvl = 1;
        try (Scanner scanner = new Scanner(System.in)){
            while (true){
                StoryTeller.getBrokeIntoPiecesText("Выберете сложность игры: 1 -- Легко, 2 -- Средне, 3 -- Сложно");
                if (!scanner.hasNextInt() ){
                    StoryTeller.getBrokeIntoPiecesText("Вы ввели не число");
                    scanner.nextLine();
                    continue;
                }
                difficultyLvl = scanner.nextInt();
                if (difficultyLvl > 3 || difficultyLvl < 0){
                    StoryTeller.getBrokeIntoPiecesText("Вы ввели неподходящую настройку");
                    continue;
                }
                return difficultyLvl;
            }
        }
    }
}
