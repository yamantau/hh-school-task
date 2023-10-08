package bilalov.hh.trainee.service;

public class Dice {

    public int getRandomNumber(int min, int max){
        return (int) (Math.random()*(max-min+1) + min);
    }

    public int roll(){
        return getRandomNumber(1 , 6);
    }

}
