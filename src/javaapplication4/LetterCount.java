/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
import java.util.Iterator;
import javaapplication4.NewHashMap;
import javaapplication4.NewHashSet;
public class LetterCount {
    public static void main(String[] args) {
        NewHashMap hashmap=new NewHashMap();
        NewHashSet hashset=new NewHashSet();
        String paragraph="Hello World. This is Letter Counting program using HashMap";
        for(char c:paragraph.toCharArray())
        {
            if(Character.isLetter(c))
            {
                c=Character.toLowerCase(c);
                hashset.add(c);
            }
        }
             Iterator<Object> iterator = hashset.iterator();
        while (iterator.hasNext()) {
            char letter = (char) iterator.next();
            System.out.print(letter + " ");
        }
    }
}
