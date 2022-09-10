// Queue implementation in Java

public class Queue {
    int SIZE = 5;
    int items[] = new int[SIZE];
    int front, rear;
  
    Queue() {
      front = -1;
      rear = -1;
    }
  
    boolean isFull() {
      if (front == 0 && rear == SIZE - 1) {
        return true;
      }
      return false;
    }
  
    boolean isEmpty() {
      if (front == -1)
        return true;
      else
        return false;
    }
  
    void enQueue(int element) {
      if (isFull()) {
        System.out.println("Queue is full");
      } else {
        if (front == -1)
          front = 0;
        rear++;
        items[rear] = element;
        System.out.println("Inserted " + element);
      }
    }
  
    int deQueue() {
      int element;
      if (isEmpty()) {
        System.out.println("Queue is empty");
        return (-1);
      } else {
        element = items[front];
        if (front >= rear) {
          front = -1;
          rear = -1;
        } /* Q has only one element, so we reset the queue after deleting it. */
        else {
          front++;
        }
        System.out.println("Deleted -> " + element);
        return (element);
      }
    }
  
    void display() {
      /* Function to display elements of Queue */
      int i;
      if (isEmpty()) {
        System.out.println("Empty Queue");
      } else {
        System.out.println("\nFront index-> " + front);
        System.out.println("Items -> ");
        for (i = front; i <= rear; i++)
          System.out.print(items[i] + "  ");
  
        System.out.println("\nRear index-> " + rear);
      }
    }
  
    public static void main(String[] args) {
      Queue q = new Queue();
  
      // deQueue is not possible on empty queue
      q.deQueue();
  
      // enQueue 5 elements
      q.enQueue(1);
      q.enQueue(2);
      q.enQueue(3);
      q.enQueue(4);
      q.enQueue(5);
  
      // 6th element can't be added to because the queue is full
      q.enQueue(6);
  
      q.display();
  
      // deQueue removes element entered first i.e. 1
      q.deQueue();
  
      // Now we have just 4 elements
      q.display();
  
    }
  }

// Circular Queue implementation in Java

public class CQueue {
  int SIZE = 5; // Size of Circular Queue
  int front, rear;
  int items[] = new int[SIZE];

  CQueue() {
    front = -1;
    rear = -1;
  }

  // Check if the queue is full
  boolean isFull() {
    if (front == 0 && rear == SIZE - 1) {
      return true;
    }
    if (front == rear + 1) {
      return true;
    }
    return false;
  }

  // Check if the queue is empty
  boolean isEmpty() {
    if (front == -1)
      return true;
    else
      return false;
  }

  // Adding an element
  void enQueue(int element) {
    if (isFull()) {
      System.out.println("Queue is full");
    } else {
      if (front == -1)
        front = 0;
      rear = (rear + 1) % SIZE;
      items[rear] = element;
      System.out.println("Inserted " + element);
    }
  }

  // Removing an element
  int deQueue() {
    int element;
    if (isEmpty()) {
      System.out.println("Queue is empty");
      return (-1);
    } else {
      element = items[front];
      if (front == rear) {
        front = -1;
        rear = -1;
      } /* Q has only one element, so we reset the queue after deleting it. */
      else {
        front = (front + 1) % SIZE;
      }
      return (element);
    }
  }

  void display() {
    /* Function to display status of Circular Queue */
    int i;
    if (isEmpty()) {
      System.out.println("Empty Queue");
    } else {
      System.out.println("Front -> " + front);
      System.out.println("Items -> ");
      for (i = front; i != rear; i = (i + 1) % SIZE)
        System.out.print(items[i] + " ");
      System.out.println(items[i]);
      System.out.println("Rear -> " + rear);
    }
  }

  public static void main(String[] args) {

    CQueue q = new CQueue();

    // Fails because front = -1
    q.deQueue();

    q.enQueue(1);
    q.enQueue(2);
    q.enQueue(3);
    q.enQueue(4);
    q.enQueue(5);

    // Fails to enqueue because front == 0 && rear == SIZE - 1
    q.enQueue(6);

    q.display();

    int elem = q.deQueue();

    if (elem != -1) {
      System.out.println("Deleted Element is " + elem);
    }
    q.display();

    q.enQueue(7);

    q.display();

    // Fails to enqueue because front == rear + 1
    q.enQueue(8);
  }

}
// Priority Queue implementation in Java

import java.util.ArrayList;

class Heap {
  // Function to heapify the tree
  void heapify(ArrayList<Integer> hT, int i) {
    int size = hT.size();
    // Find the largest among root, left child and right child
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;
    if (l < size && hT.get(l) > hT.get(largest))
      largest = l;
    if (r < size && hT.get(r) > hT.get(largest))
      largest = r;

    // Swap and continue heapifying if root is not largest
    if (largest != i) {
      int temp = hT.get(largest);
      hT.set(largest, hT.get(i));
      hT.set(i, temp);

      heapify(hT, largest);
    }
  }

  // Function to insert an element into the tree
  void insert(ArrayList<Integer> hT, int newNum) {
    int size = hT.size();
    if (size == 0) {
      hT.add(newNum);
    } else {
      hT.add(newNum);
      for (int i = size / 2 - 1; i >= 0; i--) {
        heapify(hT, i);
      }
    }
  }

  // Function to delete an element from the tree
  void deleteNode(ArrayList<Integer> hT, int num) {
    int size = hT.size();
    int i;
    for (i = 0; i < size; i++) {
      if (num == hT.get(i))
        break;
    }

    int temp = hT.get(i);
    hT.set(i, hT.get(size - 1));
    hT.set(size - 1, temp);

    hT.remove(size - 1);
    for (int j = size / 2 - 1; j >= 0; j--) {
      heapify(hT, j);
    }
  }

  // Print the tree
  void printArray(ArrayList<Integer> array, int size) {
    for (Integer i : array) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  // Driver code
  public static void main(String args[]) {

    ArrayList<Integer> array = new ArrayList<Integer>();
    int size = array.size();

    Heap h = new Heap();
    h.insert(array, 3);
    h.insert(array, 4);
    h.insert(array, 9);
    h.insert(array, 5);
    h.insert(array, 2);

    System.out.println("Max-Heap array: ");
    h.printArray(array, size);

    h.deleteNode(array, 4);
    System.out.println("After deleting an element: ");
    h.printArray(array, size);
  }
}
// Deque implementation in Java

class Deque {
  static final int MAX = 100;
  int arr[];
  int front;
  int rear;
  int size;

  public Deque(int size) {
    arr = new int[MAX];
    front = -1;
    rear = 0;
    this.size = size;
  }

  boolean isFull() {
    return ((front == 0 && rear == size - 1) || front == rear + 1);
  }

  boolean isEmpty() {
    return (front == -1);
  }

  void insertfront(int key) {
    if (isFull()) {
      System.out.println("Overflow");
      return;
    }

    if (front == -1) {
      front = 0;
      rear = 0;
    }

    else if (front == 0)
      front = size - 1;

    else
      front = front - 1;

    arr[front] = key;
  }

  void insertrear(int key) {
    if (isFull()) {
      System.out.println(" Overflow ");
      return;
    }

    if (front == -1) {
      front = 0;
      rear = 0;
    }

    else if (rear == size - 1)
      rear = 0;

    else
      rear = rear + 1;

    arr[rear] = key;
  }

  void deletefront() {
    if (isEmpty()) {
      System.out.println("Queue Underflow\n");
      return;
    }

    // Deque has only one element
    if (front == rear) {
      front = -1;
      rear = -1;
    } else if (front == size - 1)
      front = 0;

    else
      front = front + 1;
  }

  void deleterear() {
    if (isEmpty()) {
      System.out.println(" Underflow");
      return;
    }

    if (front == rear) {
      front = -1;
      rear = -1;
    } else if (rear == 0)
      rear = size - 1;
    else
      rear = rear - 1;
  }

  int getFront() {
    if (isEmpty()) {
      System.out.println(" Underflow");
      return -1;
    }
    return arr[front];
  }

  int getRear() {
    if (isEmpty() || rear < 0) {
      System.out.println(" Underflow\n");
      return -1;
    }
    return arr[rear];
  }

  public static void main(String[] args) {

    Deque dq = new Deque(4);

    System.out.println("Insert element at rear end : 12 ");
    dq.insertrear(12);

    System.out.println("insert element at rear end : 14 ");
    dq.insertrear(14);

    System.out.println("get rear element : " + dq.getRear());

    dq.deleterear();
    System.out.println("After delete rear element new rear become : " + dq.getRear());

    System.out.println("inserting element at front end");
    dq.insertfront(13);

    System.out.println("get front element: " + dq.getFront());

    dq.deletefront();

    System.out.println("After delete front element new front become : " + +dq.getFront());

  }
}