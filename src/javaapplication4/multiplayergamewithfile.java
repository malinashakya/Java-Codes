/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

interface GameCondition {
    int sum(int card1, int card2, int card3);
    boolean IsWIn(int sum);
}

abstract class GameType implements GameCondition {
    abstract public int sum(int card1, int card2, int card3);
    abstract public boolean IsWIn(int sum);

    boolean sumIsLessThan21(int sum){
        return sum < 21;
    }

    boolean sumIsLessThan17(int sum){
        return sum < 17;
    }

    int normalSum(int card1, int card2, int card3){
        return card1 + card2 + card3;
    }

    int JQKAre10(int card1, int card2, int card3) {
        if (card1 > 10 ) {
            card1 = 10;
        }
        if (card2 > 10 ) {
            card2 = 10;
        }
        if (card3 > 10 ) {
            card3 = 10;
        }
        return (card1 + card2 + card3);
    }

    int AJQKare10(int card1, int card2, int card3) {
        if (card1 > 10 || card1 == 10) {
            card1 = 10;
        }
        if (card2 > 10 || card2 == 10) {
            card2 = 10;
        }
        if (card3 > 10 || card3 == 10) {
            card3 = 10;
        }
        return (card1 + card2 + card3);
    }
}

class SumIsLessThan21 extends GameType {
    @Override
    public int sum(int card1, int card2, int card3) {
        return super.normalSum(card1 , card2 , card3);
    }

    @Override
    public boolean IsWIn(int sum) {
        return super.sumIsLessThan21(sum);
    }
}

class SumIsLessThan17 extends GameType {
    @Override
    public int sum(int card1, int card2, int card3){
        return super.normalSum(card1 , card2 , card3);
    }
    @Override
    public boolean IsWIn(int sum){
        return super.sumIsLessThan17(sum);
    }
}

class AJQKare10 extends GameType {
    @Override
    public int sum(int card1, int card2, int card3){
        return super.AJQKare10(card1, card2, card3);
    }

    @Override
    public boolean IsWIn(int sum){
        return super.sumIsLessThan21(sum);
    }
}

class AJQKare10AndSumIsLessThan17 extends GameType {
    @Override
    public int sum(int card1, int card2, int card3){
        return super.AJQKare10(card1, card2, card3);
    }

    @Override
    public boolean IsWIn(int sum){
        return super.sumIsLessThan17(sum);
    }
}

class JQKAre10 extends GameType {
    @Override
    public int sum(int card1, int card2, int card3){
        return super.JQKAre10(card1, card2, card3);
    }

    @Override
    public boolean IsWIn(int sum){
        return super.sumIsLessThan21(sum);
    }
}

class JQKare10AndSumIsLessThan17 extends GameType {
    @Override
    public int sum(int card1, int card2, int card3){
        return super.AJQKare10(card1, card2, card3);
    }

    @Override
    public boolean IsWIn(int sum){
        return super.sumIsLessThan17(sum);
    }
}

class Round{
    int bet;
    int card1, card2, card3;
    int roundNo;
    int finalAmount;
    Round(int bet, int card1, int card2, int card3, int roundNo, int finalAmount){
        this.bet = bet;
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.roundNo = roundNo;
        this.finalAmount=finalAmount;
    }
    Round(){}
}

class HistoryDisplay {
    static void displayHistory(Round[] gameHistory, String playerName, int rounds, BufferedWriter writer) throws IOException {
        writer.write("\nGameplay History for Player " + playerName + ":\n");
        for (int i = 0; i < rounds; i++) {
            int roundNumber = i + 1;
            int bet = gameHistory[i].bet;
            int card1 = gameHistory[i].card1;
            int card2 = gameHistory[i].card2;
            int card3 = gameHistory[i].card3;
            int finalAmount = gameHistory[i].finalAmount;

            writer.write("Round " + roundNumber + ": Bet - " + bet +
                    ", Cards - " + card1 + ", " + card2 + ", " + card3 +
                    ", Final Amount - " + finalAmount + "\n");
        }
    }
}

 class Main {
    static int MAX_BET = 100;
    static int PLAYER_NAME_LENGTH = 100;

    public static int sum(int a, int b, int c) {
        return a + b + c;
    }

    public static void display(int random) {
        String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        System.out.println(" Card: " + cards[random - 1]);
    }

    public static int getNumberOfPlayers(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the total number of players who will be playing this game with a maximum of 17 players: ");
        int MAX_PLAYERS = scan.nextInt();
        if (MAX_PLAYERS > 17) {
            System.out.println("Maximum players can only be 17. Exiting...");
            System.exit(0);
        }
        return MAX_PLAYERS;
    }

    static class PlayerNamesAndBalances {
        String[] playerNames;
        int[][] balances;
        PlayerNamesAndBalances(String[] playerNames, int[][] balances){
            this.playerNames = playerNames;
            this.balances = balances;
        }
    }

    public static PlayerNamesAndBalances getPlayerNames(int numberOfPlayers){
        Scanner scan = new Scanner(System.in);
        String[] playerNames = new String[numberOfPlayers];
        int[][] balances = new int[numberOfPlayers][10]; // Assuming a maximum of 10 rounds for history
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.print("Name of player " + (i + 1) + ": ");
            playerNames[i] = scan.next();
            System.out.print("Enter the initial balance for player " + playerNames[i] + ": ");
            balances[i][0] = scan.nextInt(); // Initial balance for the first round
        }
        return new PlayerNamesAndBalances(playerNames, balances);
    }

    public static GameType getGameVariants(Scanner scan){
        System.out.println("Welcome to the game of Blackjack! We have 4 variants of the game. Please choose one of them.\n1.\tTotal sum is less than 21\n2.\tTotal sum is less than 17\n3.\tIf K,Q,J and A are equal to 10\n4.\tIf K,Q,J equal to 10 and A equal to 1");
        System.out.print("Enter the game you would like to play: ");
        int selectVariant = scan.nextInt();
        GameType gameType = new SumIsLessThan21();;
        switch (selectVariant) {
            case 1:
                gameType = new SumIsLessThan21();
                break;
            case 2:
                gameType = new SumIsLessThan17();
                break;
            case 3:
                gameType= new AJQKare10();
                break;
            case 4:
                gameType= new JQKAre10();
                break;
            default:
                System.out.println("Invalid game variant. Exiting...");
                System.exit(0);
        }
        return gameType;
    }

    // Method to execute the code and record history
    private static void executeCodeAndRecordHistory(BufferedWriter writer, int MAX_PLAYERS, Random random, PlayerNamesAndBalances playerNamesAndBalances, GameType gameType) throws IOException {
        Round[][] gameHistory = new Round[MAX_PLAYERS][10];
        String[] playerNames = playerNamesAndBalances.playerNames;
        int[][] balances = playerNamesAndBalances.balances;
        Scanner scan = new Scanner(System.in);
        for (int playerIndex = 0; playerIndex < MAX_PLAYERS; playerIndex++) {
            int round = 0;
            char response;

            writer.write("\nPlayer " + playerNames[playerIndex] + ", your turn!\n");
            while (true) {
                playGame(random, balances, playerIndex, gameType, gameHistory, round, scan);
                writer.write("Do you want to continue? (y/n): ");
                response = scan.next().charAt(0);

                if (response != 'y') {
                    writer.write("Thank you for playing! Exiting the game...\n");
                    break;
                }

                round++;
            }

            HistoryDisplay.displayHistory(gameHistory[playerIndex], playerNames[playerIndex], round + 1, writer);
        }
    }

    public static void playGame(Random random, int[][] balances, int playerIndex, GameType gameType, Round[][] gameHistory, int round, Scanner scan) {
        int random1 = random.nextInt(13) + 1;
        System.out.print("Your first card is: ");
        display(random1);

        int random2 = random.nextInt(13) + 1;
        System.out.print("Your second card is: ");
        display(random2);

        int random3 = random.nextInt(13) + 1;

        System.out.print("\nEnter the amount you bet : ");
        int bet = scan.nextInt();
        if (bet > balances[playerIndex][round] || bet < 0) {
            System.out.println("Your bet is invalid. Please enter a valid bet.");
            return;
        }

        System.out.print("\nYour third card is: ");
        display(random3);
        int totalSum = gameType.sum(random1, random2, random3);
        System.out.println("\nYour total sum is: " + totalSum);

        boolean isWin = gameType.IsWIn(totalSum);

        gameHistory[playerIndex][round] = new Round();
        gameHistory[playerIndex][round].bet = bet;
        gameHistory[playerIndex][round].card1 = random1;
        gameHistory[playerIndex][round].card2 = random2;
        gameHistory[playerIndex][round].card3 = random3;

        if (isWin) {
            balances[playerIndex][round + 1] = balances[playerIndex][round] + 2 * bet;
            gameHistory[playerIndex][round].finalAmount = balances[playerIndex][round + 1];
            System.out.println("Congratulations! You win!! Your remaining balance is: " + balances[playerIndex][round + 1]);
        } else {
            balances[playerIndex][round + 1] = balances[playerIndex][round] - bet;
            gameHistory[playerIndex][round].finalAmount = balances[playerIndex][round + 1];
            System.out.println("Sorry, you lose. Your remaining balance is: " + balances[playerIndex][round + 1]);
        }
    }

    public static void main(String[] args) {
        File file = new File("history.txt");
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            int MAX_PLAYERS = getNumberOfPlayers();
            Random random = new Random();
            Scanner scan = new Scanner(System.in);
            PlayerNamesAndBalances playerNamesAndBalances = getPlayerNames(MAX_PLAYERS);
            GameType gameType = getGameVariants(scan);

            executeCodeAndRecordHistory(bufferedWriter, MAX_PLAYERS, random, playerNamesAndBalances, gameType);

            bufferedWriter.close();
            System.out.println("History recorded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
