/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
public class SwitchingWord {

    public static void main(String[] args) {
        String paragraph = "This is word switching program";
        String[] words = paragraph.split("\\s+");
        System.out.println(words[0]);
        System.out.println(words[1]);
        System.out.println(words[2]);
        System.out.println(words[3]);
        System.out.println(words[4]);
        for (int i = 0; i < words.length; i++) {
            int length = words[i].length() - 1;
            char firstLetter = words[i].charAt(0);
            char lastLetter = words[i].charAt(length);
            StringBuilder switchedWord = new StringBuilder(words[i]);
            switchedWord.setCharAt(0, lastLetter);
            switchedWord.setCharAt(length, firstLetter);
            words[i] = switchedWord.toString();
        }
        for (String word : words) {
            System.out.print(word + " ");
        }
    }
}
