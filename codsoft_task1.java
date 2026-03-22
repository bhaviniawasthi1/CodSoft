//TASK 1- NUMBER GAME

import java.util.Random;
import java.util.Scanner;

public class codsoft_task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalRounds = 0;
        int roundsWon = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Try to guess the number between 1 and 100.\n");

        boolean playAgain = true;

        while (playAgain) {

            totalRounds++;

            int numberToGuess = random.nextInt(100) + 1; // range: 1–100
            int maxAttempts = 7;
            int attemptsUsed = 0;
            boolean guessedCorrectly = false;

            System.out.println("Round " + totalRounds + " begins!");
            System.out.println("You have " + maxAttempts + " attempts.\n");

            while (attemptsUsed < maxAttempts) {

                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attemptsUsed++;

                if (userGuess == numberToGuess) {
                    guessedCorrectly = true;
                    roundsWon++;

                    System.out.println("Correct! You guessed it in " 
                            + attemptsUsed + " attempts.");
                    break;

                } else if (userGuess < numberToGuess) {
                    System.out.println("Too low! Try a higher number.");

                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                System.out.println("Attempts left: " + (maxAttempts - attemptsUsed));
                System.out.println();
            }

            if (!guessedCorrectly) {
                System.out.println("You've used all attempts!");
                System.out.println("The correct number was: " + numberToGuess);
            }

            // Score display after each round
            System.out.println("\nScore: " + roundsWon + " wins out of " + totalRounds + " rounds.");

            // Ask user if they want another round
            System.out.print("\nDo you want to play another round? (yes/no): ");
            String choice = scanner.next().toLowerCase();

            if (!choice.equals("yes")) {
                playAgain = false;
            }

            System.out.println();
        }

        System.out.println("Thanks for playing!");
        System.out.println("Final Score: " + roundsWon + "/" + totalRounds);

        scanner.close();
    }
}