/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
import java.util.HashSet;
import java.util.Iterator;

public class HashSetWithObject {

    static class Person {

        int age;
        String name;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
        //First character same vayeko lai eutai consider gareko
        public int hashCode() {
            return this.name.charAt(0);
        }

        public boolean equals(Object obj) {
            return this.hashCode()==obj.hashCode();
        }

        public static void main(String[] args) {
            HashSet<Person> person = new HashSet<>();
            person.add(new Person("Malina", 21));
            person.add(new Person("Lijala", 21));
            person.add(new Person("Bipassi", 20));
            System.out.println("Person' details:" + person.size());
            person.add(new Person("Lijala", 21));
            System.out.println("Person' details:" + person.size());
            //For Person type
            Iterator<Person> abc=person.iterator();
            while(abc.hasNext())
            {
                Person people=abc.next();
            }
        }

    }
}
