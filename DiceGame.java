// Student Name: Rekell Caraan
// Student Number: A00133304
// Program Description: The following program: "Die.java" simulates a die roll and displays the current side up.
// It allows the user to specify the number of sides OR numnber of sides & type OR the default of D6 die.
// It allows the user to set the desired side up and rolls the given die and displays the number of rolls it took to get the desired side up
// BONUS: It allows the user to play the YAHTZEE game

import java.util.*;

public class DiceGame {

    // Main Method
    public static void main(String[] args){
    
        var DiceGame = new DiceGame();
        var die = DiceGame.new Die();
        var die2 = DiceGame.new Die(20);
        var die3 = DiceGame.new Die(100, "Percentile");
        die.desiredSideUp = 2;
        die.Roll();
        die2.desiredSideUp = 2;
        die2.Roll();
        die3.desiredSideUp = 2;
        die3.Roll();
        die.SetDesiredSideUpThenRoll();
        die2.SetDesiredSideUpThenRoll();
        die3.SetDesiredSideUpThenRoll();
        
        DiceGame.playYahtzee(5, 2);
    }

    // Play YAHTZEE Game
    // This game will roll all the number of dies and wait for all of current side ups of each die
    // to equal the desired side up. This will indicate how many roll counts it was attained.
    public static void playYahtzee(int numberOfD6Dice, int desiredSideUp){
        
        System.out.println("Creating " + numberOfD6Dice + " d6...");
        
        Die dies[] = new Die[numberOfD6Dice];

        var DiceGame = new DiceGame();

        // Iterate on creating the desired number of dies
        for(int diecounter = 0; diecounter < numberOfD6Dice; diecounter++){
            var die = DiceGame.new Die();
            die.desiredSideUp = desiredSideUp;
            dies[diecounter] = die;
        }

        var rollscounter = 1;

        var yahtzee = false;
        while(!yahtzee){
            var hasPreviousNotDesiredSideUp = true;

            // Roll the dies
            for(int diecounter = 0; diecounter < dies.length; diecounter++){
                dies[diecounter].Roll();
            }
            
            // Check the current side up of each die
            for(int diecounter = 0; diecounter < dies.length; diecounter++){
                
                if(dies[diecounter].currentSideUp != desiredSideUp){
                    hasPreviousNotDesiredSideUp = true;
                    break;
                }
                else{
                    hasPreviousNotDesiredSideUp = false;
                }
            }

            // If there are no previous current side up of each die that does not equal to desired side up
            // then there is no yahtzee. Proceed to next roll
            if(!hasPreviousNotDesiredSideUp){
                yahtzee = true;
            }

            rollscounter++;
        }

        if(yahtzee){
            System.out.println("YAHTZEE! It took " + rollscounter + " rolls");
        }
    }

    ///
    /// Die Class
    ///
    public class Die{

        private String type;
        private int numberOfSides;
        private int currentSideUp;
        public int desiredSideUp;

        // Constructor for D6 default die
        public Die(){
            type = "d6";
            numberOfSides = 6;
            currentSideUp = generateRandomNumber();
            System.out.println("Creating a default d6...");
        }

        // Constructor for variable number of sides die
        public Die(int numberOfSides){
            this.numberOfSides = numberOfSides;
            type = "d" + numberOfSides;            
            currentSideUp = generateRandomNumber();
            System.out.println("Creating a " + type + "...");
        }

        // Constructor for variable number of sides and type die
        public Die(int numberOfSides, String type){
            this.numberOfSides = numberOfSides;
            this.type = type;
            currentSideUp = generateRandomNumber();
            System.out.println("Creating a " + type + " die (a special " + numberOfSides + ")...");
        }

        // Generates a random number (int) based on the maximum limit
        private int generateRandomNumber(){
            return new Random().nextInt(1, numberOfSides + 1);
        }

        // Simulate a roll of die
        public void Roll(){

            System.out.println("The current side up for " + type + " is " + currentSideUp);

            if(desiredSideUp > 0 && desiredSideUp <= numberOfSides){
                System.out.println("Rolling the " + type + "...");

                currentSideUp = generateRandomNumber();

                System.out.println("The new value is " + currentSideUp);
            }
            else{
                System.out.println("Desired side up is not given in the dice.");
            }
        }

        // Set the desired side up and then roll the die
        public void SetDesiredSideUpThenRoll(){
            System.out.println("Setting the " + type + " to show " + desiredSideUp);

            var rollscounter = 1;

            while(currentSideUp != desiredSideUp){

                Roll();

                if(currentSideUp == desiredSideUp){
                    System.out.println("The side up is now "+desiredSideUp+". Finally after " + rollscounter + " roll(s)");
                    break;
                }

                rollscounter++;
            }
        }
    }
}