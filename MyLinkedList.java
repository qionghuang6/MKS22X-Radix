import java.util.*;
public class MyLinkedList<E>{
 private Node start,end;
 private int size;
 public MyLinkedList(){
   //sets up instance variables, start and end dont exist yet
   int size = 0;
   clear();
 }
 public boolean add(E value){
   size++;
   //temporarily holds old end
   Node oldEnd = end;
   end = new Node(value, null, oldEnd);
   //makes new end and sets it
   if(oldEnd != null){
     oldEnd.setNext(end);
   } else{
     //if tis the first value, set it as the start too
     start = end;
   }
   return true;
 }
 public boolean addFront(E value){
   size++;
   Node oldStart = start;
   start = new Node(value,oldStart,null);
   if(oldStart != null){
     oldStart.setPrev(start);
   }else{
     start = end;
   }
   return true;
 }
 public E[] toArray(){
  // System.out.println(size);
   E[] arr = (E[]) new Object[size];
   int i = 0;
   Node pointer = start;
   while(pointer != null){
     arr[i] = (E) pointer.get();
     pointer = pointer.next();
     i++;
   }
   return arr;
 }
 public int size()
{
  return size;
}
private Node prevNode(int index){
   //helper to look for the previous node
   if(index <= 0){
     return null;
   }
   return getNthNode(index - 1);
 }
 private Node getNthNode(int index){
  int x = 0;
  Node current = start;
  //loops through the list until it reaches index
  while (x != index && current != null){
    current = current.next();
    x++;
  }
  return current;
}
 public String toString(){
   //System.out.println(size);
   //creates string with bracket
   String ret = "[";
   Node current = start;
   //loops through linked link, adding to the string
   while (current != null){
     ret += current.toString();
     if(current.hasNext()){
       ret +=  ", ";
     }
     current = current.next();
   }
   ret += "]";
   //finishes the string and returns it
   return ret;
 }

 public E removeFront(){
   //System.out.println(this);
   if(start == null){
     throw new NoSuchElementException();
   }
   size--;
   Node temp = start;
   start = start.next();
   return (E) temp.get();
 }
 public void clear(){
   //clears the linkedlist
   start = null;
   end = null;
   size = 0;
 }
 public Node end(){
   return end;
 }
 public void extend(MyLinkedList<E> other){
        //in O(1) runtime, move the elements from other onto the end of this
        //The size of other is reduced to 0
        //The size of this is now the combined sizes of both original lists
        if(other.start != null && size >= 1){
          end.setNext(other.start);
          other.start.setPrev(end);
          end = other.end();
        } else if(start == null){
          start = other.start;
          end = other.end();
        } else if(end == null){
          start.setNext(other.start);
          end = other.end();
        }
        size += other.size();
        other.clear(); //uses helper to clear other list
    }
  public static void main(String[] args) {
    MyLinkedList<String> a = new MyLinkedList<String>();
    MyLinkedList<String> b = new MyLinkedList<String>();
    b.add("1");
    b.add("2");
    a.add("a");
    a.add("b");
    a.add("c");
    a.add("d");
    System.out.println(a);
    System.out.println(a.removeFront());
    System.out.println(a);
    a.addFront("z");
    System.out.println(a);
    /*
    for(int x = 0; x < 5; x++){
        a.removeFront();
    }
    */
    System.out.println(a);
    System.out.println(Arrays.toString(a.toArray()));
    a.extend(b);
    System.out.println(a);
    System.out.println(Arrays.toString(a.toArray()));
  }
 private class Node<E>{
  private E data;
  private Node next,prev;

  public Node(E data, Node next, Node prev){
    this.data = data;
    this.next = next;
    this.prev = prev;
  }
  public Node prev(){ //getter for prev
    return prev;
  }
  public Node next(){ //getter for next
    return next;
  }
  public void setNext(Node other){ //setter for next
    next = other;
  }
  public void setPrev(Node other){ //setter for prev
    prev = other;
  }
  public String toString(){ //turns data into a string
    return "" + data;
  }
  public void set(E value){ //setter for data
    data = value;
  }
  public E get(){ //getter for data
    return data;
  }
  public boolean hasNext(){
    //checks to see if there is another value in the linked list after this node
    return next != null;
  }
 }
}
