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

    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        next = null;
    }

    public Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

public class LinkedList {

    public Node head, tail;

    public LinkedList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public void insertToHead(int data) {
        head = new Node(data, head);
        if (tail == null) {
            tail = head;
        }
    }

    public void insertToTail(int data) {
        if (!isEmpty()) {
            Node newNode = new Node(data);
            tail.next = newNode;
            tail = tail.next;
        } else {
            head = tail = new Node(data);
        }
    }

    public void DeleteHead() {
        if (!isEmpty()) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
        } else {
            System.out.println("List is empty");
        }
    }

    public void DeleteTail() {
        if (!isEmpty()) {
            if (head == tail) {
                head = tail = null;
            } else {
                Node temp = head;
                while (temp.next != tail) {
                    temp = temp.next;
                }
                temp.next = null;
                tail = temp;
            }
        }
    }

    public boolean searchData(int data) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.data == data) {
                return true;
            }
        }
        return false;
    }

    public void DeleteByData(int data) {
        Node temp = head;
       if(searchData(data)){
        while(temp.next!=null)
        {
            if(temp.data==data)
            {
                temp=temp.next;
            }
        }
       }
    }

    public void PrintAll() {
        Node temp = head;
        while (temp != null) {
            System.out.println("Element:" + temp.data);
            temp = temp.next;
        }
    }

    public void PrintAllReverse() {
        Node temp = tail;
        temp.next = head;
        while (temp.next != tail) {
            System.out.println("Reversed Element:" + temp.data);
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.insertToHead(0);
        list.insertToHead(1);
        list.insertToHead(2);
        list.insertToHead(3);
        list.PrintAll();
        list.DeleteByData(0);
        list.PrintAll();
//        list.DeleteHead();
//        System.out.println("Deleted by Head");
//        list.PrintAll();
//        list.DeleteTail();
//        System.out.println("Deleted by Tail");
//        list.PrintAll();
//        list.PrintAllReverse();
    }
}
