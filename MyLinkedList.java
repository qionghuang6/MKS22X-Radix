public class MyLinkedList<E>{
 private Node start,end;

 public MyLinkedList(){
   //sets up instance variables, start and end dont exist yet
   clear();
 }
 public boolean add(E value){
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
   Node temp = start;
   start = start.next();
   return (E) temp.get();
 }
 public void clear(){
   //clears the linkedlist
   start = null;
   end = null;
 }
 public Node end(){
   return end;
 }
 public void extend(MyLinkedList<E> other){
        //in O(1) runtime, move the elements from other onto the end of this
        //The size of other is reduced to 0
        //The size of this is now the combined sizes of both original lists
        if(other.start != null){
          end.setNext(other.start);
          other.start.setPrev(end);
          end = other.end();
        }
        other.clear(); //uses helper to clear other list
    }
  public static void main(String[] args) {
    MyLinkedList<String> a = new MyLinkedList<String>();
    a.add("a");
    a.add("b");
    a.add("c");
    a.add("d");
    System.out.println(a);
    System.out.println(a.removeFront());
    System.out.println(a);
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
