/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
import java.util.Scanner;
import java.util.Random;

public class BlackJackGame {

   public static String display(int card, Random random) {
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
        temp_card[0] = (char)(card + 48);
    } else if (card == 10) {
        temp_card[0] = '1';
        temp_card[1] = '0';
    }

    return new String(temp_card); 
}
    public static int playgame(int card1, int card2, int betPoint, int Points) {
        Random random = new Random();
        int card3, sum;
        card3 = random.nextInt(13) + 1;
        sum = card1 + card2 + card3;
        System.out.println("So your sum is:" + sum);
        if (sum > 21) {
            Points = Points - betPoint;
            System.out.println("You lose" + betPoint + "points");
            System.out.println("Your remaining points:" + Points);
        } else {
            Points = Points + betPoint;
            System.out.println("You win" + betPoint + "points");
            System.out.println("Your remaining points:" + Points);
        }
        return Points;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int points = 100;
        int betPoint, card1, card2;
        System.out.println("You have 100 ppoints in youur account");
        while (points > 0) {
            card1 = random.nextInt(13) + 1;
            card2 = random.nextInt(13) + 1;
            String tempcard1 = display(card1, random);
            String tempcard2 = display(card2, random);
            System.out.println("Your cards are:" + tempcard1 + "and" + tempcard2);
            System.out.println("How many points you want to bet?");
            betPoint = sc.nextInt();
            if (betPoint <= points && betPoint > 0) {
                points = playgame(card1, card2, betPoint, points);
            } else if (betPoint <= 0) {
                System.out.println("Betpoint cannot be negative or zero");
            } else {
                System.out.println("You don't have enough points");
            }
        }
        System.out.println("Game over!!!");
    }

}
