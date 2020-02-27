package code.string;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("String Test");

        String s1 = "hello!";
        String s2 = "Hello!".toLowerCase();
        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

        System.out.println(String.valueOf(new Object()));

        int[] scores = new int[] { 88, 77, 51, 66 };
        Score s = new Score(scores);
        s.printScores();
        scores[2] = 99;
        s.printScores();
    }
}

class Score {
    private int[] scores;
    public Score(int[] scores) {
        this.scores = scores.clone();
    }
    public void printScores() {
        System.out.println(Arrays.toString(scores));
    }
}