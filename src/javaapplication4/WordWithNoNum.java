/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
public class WordWithNoNum {

    public static void main(String[] args) {
        String paragraph = " This is3 the prog3ram that prints word with no numbers33";
        String[] words = paragraph.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            boolean Digit = false;
            for (int j = 0; j < words[i].length(); j++) {
                if (Character.isDigit(words[i].charAt(j))) {
                    Digit = true;
                    break;
                }
            }
            if (!Digit) {
                System.out.println(words[i]);
            }
        }
    }
}
