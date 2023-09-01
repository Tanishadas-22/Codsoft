import java.util.Random;
import java.util.Scanner;

public class numbergame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int num, guess, tries, level, points, i;
        points = 0;
        i = 0;
        System.out.println("Number guessing game:");
        System.out.println("There are 5 levels of this game, and 10 tries for each level to guess the number. If the number is guessed");
        System.out.println("correctly, in any of the tries,one obtaines 10 points, else 0");
        for (level = 1; level <= 4; level++) {
            System.out.println(" ");
            System.out.println("Round no:" + level);
            num = rand.nextInt(100);
            {
                tries = 0;
                while (tries <= 8) {
                    System.out.println("Guess a number between 0 and 100:");
                    guess = sc.nextInt();
                    tries++;
                    if (guess == num) {
                        System.out.println("Your guess is correct");
                        System.out.println("It took " + tries + " tries to guess the correct number");
                        i = 10;
                        points = points + i;
                        break;

                    } else if (guess > num) {
                        System.out.println("The number guessed is too high");
                    } else {
                        System.out.println("The number guessed is too low");
                    }

                }
                System.out.println("The random number was: " + num);
                System.out.println("The points obtained in Round " + level + " is " + i);
            }
            System.out.println("The total points obtained upto this round is=" + points);
        }
        System.out.println("The total points obtained in this game is=" + points);
    }

}