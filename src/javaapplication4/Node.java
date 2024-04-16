/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication4;

/**
 *
 * @author malin
 */
class Node {
    int data;
    Node next;
    
}
 class LinkedList {
    private Node head;
    private int size;
    
    public void addAtEnd (int data) {
        Node newNode = new Node();
        newNode.data = data;
        newNode.next = null;
        if(head == null){
            head = newNode;
        }
        else{
            Node currentNode = head;
            while (currentNode.next != null){
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
    }    
//    public void addAtFront( int data){
//        Node newNode = new Node();
//        newNode.data = data;
//        newNode.next = null;
//        newNode = head;
//    }
    
     public void removeAtFront(int data){
         if(head == null){
             System.out.println("Empty list.");
         }
         head = head.next;
         size--;
     }
     public void removeAtEnd(int data) {
         if(head == null){
             System.out.println("Empty list.");
         }
         else{
            Node currentNode = head;
            while (currentNode.next != null){
                currentNode = currentNode.next;
            }
            size--;
        }
     }
     
     public void display (){
         Node currentNode = head;
         while (currentNode.next != null && currentNode!=null){
               
            currentNode = currentNode.next;
            System.out.println(currentNode.data + "");
          
         }
     }
     public int size(){
         return size;
     }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.addAtEnd(4);
        list.addAtEnd(3);
        list.addAtEnd(2);
        list.addAtEnd(1);
        list.size();
        list.display();
        
        
    }
    
}
