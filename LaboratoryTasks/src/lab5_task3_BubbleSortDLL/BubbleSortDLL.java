package lab5_task3_BubbleSortDLL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Node {
    int data;
    Node prev, next;

    public Node(int data) {
        this.data = data;
        this.prev = this.next = null;
    }
}

public class BubbleSortDLL {
    Node head;
    public void addToTail(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        newNode.prev = temp;
    }
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    public void bubbleSort() {
        if (head == null || head.next == null) {
            return;
        }
        boolean swapped;
        Node last = null;
        do {
            swapped = false;
            Node current = head;
            while (current.next != last) {
                if (current.data > current.next.data) {
                    swapNodes(current, current.next);
                    swapped = true;
                }
                current = current.next;
            }
            last = current;
        } while (swapped);
    }
    private void swapNodes(Node node1, Node node2) {
        int temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] nodesData = br.readLine().split("\\s+");
        BubbleSortDLL list = new BubbleSortDLL();
        for (String data : nodesData) {
            list.addToTail(Integer.parseInt(data));
        }
        list.bubbleSort();
        list.display();
    }
}
