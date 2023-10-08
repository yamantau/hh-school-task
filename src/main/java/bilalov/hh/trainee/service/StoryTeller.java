package bilalov.hh.trainee.service;

public class StoryTeller {

    public static void getBrokeIntoPiecesText(String rawText, int time){
        char[] text = rawText.toCharArray();
        int timeForOneLetter = (int)(Math.floor(time / text.length));
        print(text, timeForOneLetter);
    }

    //Перегрузка
    public static void getBrokeIntoPiecesText(String rawText){
        char[] text = rawText.toCharArray();
        int timeForOneLetter = (int)(Math.floor(2000 / text.length));
        print(text, timeForOneLetter);
    }

    private static void print(char[] text, int time){
        for (int i = 0; i < text.length; i++) {
            System.out.print(text[i]);
            try {
                Thread.sleep(time);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println();
    }

}
