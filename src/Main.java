import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        // HANGMAN GAME

        String filePath = "5000-more-common.txt";
        ArrayList<String> words = new ArrayList<>();
        boolean playAgain = true;

        // Ensure file is properly read
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null){
                words.add(line.trim().toUpperCase());
            }
        }
        catch(FileNotFoundException e){
            System.out.println("COULD NOT FIND FILE");
        }
        catch(IOException e){
            System.out.println("SOMETHING WENT WRONG");
        }

        while(playAgain){
            // Chooses random word from word list
            Random random = new Random();
            String word = words.get(random.nextInt(words.size()));


            Scanner scanner = new Scanner(System.in);

            // Underscores
            ArrayList<Character> wordState = new ArrayList<>();
            ArrayList<Character> incorrectGuesses = new ArrayList<>();
            int wrongGuesses = 0;

            // Adds appropriate number of underscores
            for(int i = 0; i < word.length(); i++){
                wordState.add('_');
            }

            System.out.println("************************");
            System.out.println("Welcome to JAVA Hangman!");
            System.out.println("************************");

            while(wrongGuesses < 6){

                System.out.println(getHangmanArt(wrongGuesses));
                System.out.println("Incorrect Guesses: " + incorrectGuesses);

                System.out.print("Word: ");
                for(char c : wordState){
                    System.out.print(c + " ");
                }
                System.out.println();

                System.out.print("Guess a letter: ");
                char guess = scanner.next().toUpperCase().charAt(0);
                incorrectGuesses.add(guess);

                if(word.indexOf(guess) >= 0){
                    System.out.println("Correct guess!\n");

                    for(int i = 0; i < word.length(); i++){
                        if(word.charAt(i) == guess){
                            wordState.set(i, guess);
                        }
                    }

                    if(!wordState.contains('_')){
                        System.out.print(getHangmanArt(wrongGuesses));
                        System.out.println("YOU WIN!");
                        System.out.println("The word was: " + word);
                        break;
                    }
                }else{
                    wrongGuesses++;
                    System.out.println("Wrong guess!");
                }
            }

            if(wrongGuesses >= 6){
                System.out.print(getHangmanArt(wrongGuesses));
                System.out.println("GAME OVER!");
                System.out.println("The word was: " + word);
            }

            System.out.println("Play again (Y/N)?: ");
            String answer = scanner.next().toUpperCase();
            if(answer.equals("Y")){
                continue;
            }else{
                System.out.println("Thank you for playing! Goodbye!");
                playAgain = false;
            }

            scanner.close();
        }
    }

    static String getHangmanArt(int wrongGuesses){
        return switch(wrongGuesses){
            case 0 -> """
                      
                      
                      
                      """;
            case 1 -> """
                       O
                      
                      
                      """;
            case 2 -> """
                       O
                       |
                      
                      """;
            case 3 -> """
                       O
                      /|
                      
                      """;
            case 4 -> """
                       O
                      /|\\
                      
                      """;
            case 5 -> """
                       O
                      /|\\
                      /
                      """;
            case 6 -> """
                       O
                      /|\\
                      / \\
                      """;
            default -> "";
        };
    }
}
