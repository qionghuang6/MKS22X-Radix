public class MyLinkedList<E>{
 private int size;
 private Node start,end;

 public MyLinkedList(){
   //sets up instance variables, start and end dont exist yet
   size = 0;
   start = null;
   end = null;
 }

 public int size(){
   //returns siz eof the linked link
   return size;
 }

 public boolean add(Integer value){
   //temporarily holds old end
   Node oldEnd = end;
   end = new Node(value, null, oldEnd);
   //makes new end and sets it
   if(prevNode(size) != null){
     prevNode(size).setNext(end);
   } else{
     //if tis the first value, set it as the start too
     start = end;
   }
   size++;
   return true;
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

 public Integer get(int index){
   if(index < 0 || index >= size()){
     throw new IndexOutOfBoundsException();
   }
   //uses getNthNode to find the node and then returns the value
   return getNthNode(index).get();
 }

 public Integer set(int index, Integer value){
   if(index < 0 || index >= size()){
     throw new IndexOutOfBoundsException();
   }
   //calls the node and changes its value, returns old value
   Integer ret = getNthNode(index).get();
   getNthNode(index).set(value);
   return ret;
 }

 public boolean contains(Integer value){
   Node current = start;
   while (current != null){
     if(current.get().equals(value)){
       return true;
     }
     current = current.next();
   }
   return false;
 }
 public void add(int index, Integer value){
   if(index < 0 || index > size()){
     throw new IndexOutOfBoundsException();
   }
   Node nodeThere = getNthNode(index);
   Node newNode = new Node(value,nodeThere,prevNode(index));
   if(prevNode(index) != null){
     prevNode(index).setNext(newNode);
   } else{
     start = newNode;
   }
   if(nodeThere != null){
     nodeThere.setPrev(newNode);
   }
   size++;
 }
 public int indexOf(Integer value){
   //loops through to find the value, if it snot found, return -1
   int x = 0;
   Node current = start;
   while (x < size() && current != null){
     if(current.get().equals(value)){
       return x;
     }
     current = current.next();
     x++;
   }
   return -1;
 }
 private Node prevNode(int index){
   //helper to look for the previous node
   if(index <= 0){
     return null;
   }
   return getNthNode(index - 1);
 }
 private Node nextNode(int index){
   //helper to look for the next node
   if(index >= size()){
     return null;
   }
   return getNthNode(index + 1);
 }
 public Integer remove(int index){
   if(index < 0 || index >= size()){
     throw new IndexOutOfBoundsException();
   }
   //finds the node and its neighbors, changes the next and prev values of neighbors
   Node remv = getNthNode(index);
   if(remv.prev() != null){
     remv.prev().setNext(remv.next());
   } else{
     start = remv.next();
   }
   if(remv.next() != null){
     remv.next().setPrev(remv.prev());
   } else{
     end = remv.prev();
   }
   size--;
   return remv.get();
 }
 public boolean remove(Integer value){
   //finds the index of integer value, then uses remove by index
   if(!contains(value)){
     return false;
   }
   System.out.println(indexOf(value));
   remove(indexOf(value));
   return true;
 }
 public Node end(){
   //getter for end
   return end;
 }
 public void clear(){
   //clears the linkedlist
   size = 0;
   start = null;
   end = null;
 }
 public void extend(MyLinkedList other){
        //in O(1) runtime, move the elements from other onto the end of this
        //The size of other is reduced to 0
        //The size of this is now the combined sizes of both original lists
        if(other.start != null){
          end.setNext(other.start);
          other.start.setPrev(end);
          end = other.end();
          size = size() + other.size();
        }
        other.clear(); //uses helper to clear other list
    }
  public static void main(String[] args) {
    MyLinkedList<Integer> a = new MyLinkedList<Integer>();
    a.add(0);
    a.add(1);
    a.add(2);
    a.add(3);
    System.out.println(a);
    System.out.println(a.size());
    MyLinkedList<Integer> b = new MyLinkedList<Integer>();
    b.add(4);
    b.add(5);
    b.add(6);
    b.add(7);
    System.out.println(b);
    System.out.println(b.size());
    a.extend(b);
    System.out.println(a);
    System.out.println(a.size());
    System.out.println(b);
    System.out.println(b.size());
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
  public void set(Integer value){ //setter for data
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
