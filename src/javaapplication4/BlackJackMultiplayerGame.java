package javaapplication4;

import java.util.Scanner;
import java.util.Random;

class GameRecord {
    int card1;
    int card2;
    int card3;
    int betPoint;
    int result;
    int balance;
}


    interface Conditionss {
        int evaluate(int card1, int card2, int card3);
    }

    interface SumValue {
        int calculate(int card1, int card2, int card3);
    }

abstract class GameType implements Conditionss, SumValue{

}
class  GameTypeSumIsLessThan21 extends GameType{
    GameTypeSumIsLessThan21()
    {
        System.out.println("You have selected the Win if sum is less than 21 condition");
    }
    @Override
    public int calculate (int card1,int card2, int card3)
    {
        return BlackJackMultiplayerGame.normalsum(card1, card2, card3);
    }
    @Override
    public int evaluate(int card1,int card2, int card3)
    {
        return BlackJackMultiplayerGame.sumIsLessThan21(card1,card2,card3)?1:0;
    }
}
class  GameTypeSumIsLessThan17 extends GameType{
    GameTypeSumIsLessThan17(){
       System.out.println("You have selected the Win if sum is less than 17 condition");
    }

    @Override
    public int calculate (int card1,int card2, int card3)
    {
        return BlackJackMultiplayerGame.normalsum(card1, card2, card3);
    }
    @Override
    public int evaluate(int card1,int card2, int card3)
    {
        return BlackJackMultiplayerGame.sumIsLessThan17(card1,card2,card3)?1:0;
    }
}
class  GameTypeAJQKare10 extends GameType{
    GameTypeAJQKare10()
    {
        System.out.println("You have selected the Win if A, J, K, Q are equal to 10 condition");
    }
    @Override
    public int calculate (int card1,int card2, int card3)
    {
        return BlackJackMultiplayerGame.sumvalueAJQKare10(card1, card2, card3);
    }
    @Override
    public int evaluate(int card1,int card2, int card3)
    {
        return BlackJackMultiplayerGame.JQKare10(card1,card2,card3)?1:0;
    }
}
class  GameTypeJQKare10 extends GameType{
    GameTypeJQKare10()
    {
        System.out.println("You have selected the Win if J, K, Q are equal to 10, A is equal to 1 condition");
    }
    @Override
    public int evaluate (int card1,int card2, int card3)
    {
        return BlackJackMultiplayerGame.sumvalueAJQKare10(card1, card2, card3);
    }
    @Override
    public int calculate(int card1,int card2, int card3)
    {
        return BlackJackMultiplayerGame.JQKare10(card1,card2,card3)?1:0;
    }
}
class Player {
    String name;

    Player(String name) {
        this.name = name;
    }
}

public class BlackJackMultiplayerGame {
    public static Random random = new Random();

    public static String display(int card) {
        char[] temp_card = new char[3];
        temp_card[2] = '\0';
        if (card == 11) {
            temp_card[0] = 'J';
        } else if (card == 12) {
            temp_card[0] = 'Q';
        } else if (card == 13) {
            temp_card[0] = 'K';
        } else if (card == 1) {
            temp_card[0] = 'A';
        } else if (card >= 2 && card < 10) {
            temp_card[0] = (char) (card + 48);
        } else if (card == 10) {
            temp_card[0] = '1';
            temp_card[1] = '0';
        }
        return new String(temp_card);
    }

    public static boolean sumIsLessThan21(int card1, int card2, int card3) {
        return card1 + card2 + card3 < 21;
    }

    public static boolean sumIsLessThan17(int card1, int card2, int card3) {
        return (card1 + card2 + card3) < 17;
    }

    public static boolean AJQKare10(int card1, int card2, int card3) {
        if (card1 > 10 || card1 == 10) {
            card1 = 10;
        }
        if (card2 > 10 || card2 == 10) {
            card2 = 10;
        }
        if (card3 > 10 || card3 == 10) {
            card3 = 10;
        }
        return (card1 + card2 + card3) < 21;
    }

    public static boolean JQKare10(int card1, int card2, int card3) {
        if (card1 > 10) {
            card1 = 10;
        }
        if (card2 > 10) {
            card2 = 10;
        }
        if (card3 > 10) {
            card3 = 10;
        }
        return (card1 + card2 + card3) < 21;
    }

    public static int sumvalueAJQKare10(int card1, int card2, int card3) {
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

    public static int sumvalueJQKare10(int card1, int card2, int card3) {
        if (card1 > 10) {
            card1 = 10;
        }
        if (card2 > 10) {
            card2 = 10;
        }
        if (card3 > 10) {
            card3 = 10;
        }
        return (card1 + card2 + card3);
    }

    public static int normalsum(int card1, int card2, int card3) {
        return (card1 + card2 + card3);
    }

    static int playgame(int betPoint, int card1, int card2, int[] Points, GameRecord[] records, int round,
                    GameType gametype) {
    int card3, sum;
    card3 = random.nextInt(13) + 1;
    sum = gametype.calculate(card1, card2, card3);
    records[round]= new GameRecord();
    records[round].card1 = card1;
    records[round].card2 = card2;
    records[round].card3 = card3;
    records[round].betPoint = betPoint;

        System.out.println("Your third card is :" + display(card3));
        System.out.println("Your sum is:" + sum);
        if (gametype.evaluate(card1, card2, card3) == 0) {
            Points[0] -= betPoint;
            records[round].result = -betPoint;
            System.out.println("You lose " + betPoint + " points");
            System.out.println("You remaining points " + Points[0]);
        } else {
            Points[0] += betPoint;
            records[round].result = betPoint;
            System.out.println("You win " + betPoint + " points");
            System.out.println("You remaining points " + Points[0]);
        }
        records[round].balance = Points[0];
        return Points[0];
    }

    static void displayHistory(GameRecord[] records, int rounds, Player[] players, int numPlayers) {
    System.out.println("Game History:");
    int count = 0;
    for (int i = 0; i < rounds; i++) {
        if (i % numPlayers == 0) {
            count++;
        }
        if (records[i].balance == 0) {
            System.out.println("Player " + players[i / numPlayers].name + " is out of the game");
        } else {
            System.out.println("Player " + players[i / numPlayers].name + " - Round " + count + ": Cards "
                    + display(records[i].card1) + ", " + display(records[i].card2) + ", "
                    + display(records[i].card3) + " | Bet: " + records[i].betPoint + " | Result: "
                    + records[i].result + " | Balance: " + records[i].balance);
        }
    }
}


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the game of Blackjack!");

        int condition;
        System.out.println("Select the game condition:");
        System.out.println("1. Win if sum is less than 21");
        System.out.println("2. Win if sum is less than 17");
        System.out.println("3. Win if J, K, Q, A and sum is less than 21");
        System.out.println("4. Win if J, K, Q are equal to 10, A is equal to 1 and sum is less than 21");
        condition = scanner.nextInt();

        int numPlayers;
        System.out.print("Enter the number of players (1-17): ");
        numPlayers = scanner.nextInt();

        if (numPlayers < 1 || numPlayers > 17) {
            System.out.println("Invalid number of players. Exiting...");
            return;
        }

        Player[] players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name of Player " + (i + 1) + ": ");
            String playerName = scanner.next();
            players[i] = new Player(playerName);
        }

        System.out.println("Names of players:");
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Player " + (i + 1) + ": " + players[i].name);
        }

        int startingPoints = 100;
        int[] points = { startingPoints };
        int betPoint, card1, card2, rounds = 0;
        GameRecord[] records = new GameRecord[100];

        // Array to store player balances
        int[] playerBalances = new int[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerBalances[i] = startingPoints;
        }

        System.out.println("\nYou each have 100 points in your accounts.");

        GameType gametype = null;

        // Main game loop
        while (true) {
            // Loop through each player
            for (int player = 0; player < numPlayers; player++) {
                if (playerBalances[player] <= 0) {
                    System.out.println("Player " + players[player].name + " is out of the game.");
                    continue;
                }

                card1 = random.nextInt(13) + 1;
                card2 = random.nextInt(13) + 1;
                switch (condition) {
                    case 1:
                        gametype = new GameTypeSumIsLessThan21();
                        break;
                    case 2:
                        gametype = new GameTypeSumIsLessThan17();
                        break;
                    case 3:
                        gametype = new GameTypeAJQKare10();
                        break;
                    case 4:
                        gametype = new GameTypeJQKare10();
                        break;
                    default:
                        System.out.println("Wrong selection of game");
                        break;
                }
                System.out.println("\nPlayer " + players[player].name + ", your cards are " + display(card1)
                        + " and " + display(card2));
                System.out.println("How many points do you want to bet?");

                betPoint = scanner.nextInt();
                if (betPoint > 0 && betPoint <= playerBalances[player]) {
                    playerBalances[player] = playgame(betPoint, card1, card2, points, records, rounds, gametype);
                    rounds++;
                } else if (betPoint <= 0) {
                    System.out.println("Bet point cannot be negative or zero");
                } else {
                    System.out.println("Player " + players[player].name + ", you don't have enough points");
                }
            }

            System.out.print("\nDo you want to continue to the next round? (y/n): ");
            char response = scanner.next().charAt(0);
            if (response == 'n') {
                break; // Exit the game if the user does not want to continue
            } else if (response != 'y') {
                System.out.println("Wrong input");
                System.out.print("\nDo you want to continue to the next round? (y/n): ");
                char reResponse = scanner.next().charAt(0);
                
            }
        }

        displayHistory(records, rounds, players, numPlayers);

        System.out.println("Game over!!!");
    }
}
