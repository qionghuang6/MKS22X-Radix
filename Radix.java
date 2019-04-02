import java.util.*;
public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets = new MyLinkedList[10];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }
    boolean completed = false;
    int place = 1;
    while(!completed){
      completed = true;
      for(int num: data){
        int index = (Math.abs(num) % (10 * place)) / place;
        System.out.println(index);
        //System.out.println("INDEX" + index);
        if(num < 0){
          buckets[index].addFront(num);
        } else{
          buckets[index].add(num);
        }
        if(num / place > 10){
          completed = false;
        }
      }
      System.out.println(Arrays.toString(buckets));
      MyLinkedList<Integer> newData= new MyLinkedList();
      for (MyLinkedList<Integer> buc: buckets ) {
        newData.extend(buc);
        System.out.println(newData);
      }
      //System.out.println(newData);
      for (int x = 0;x < data.length ;x++ ) {
        data[x] = newData.removeFront();
      }
      place *= 10;
    }
  }
  public static void main(String[] args) {
    /*
    int[] a= new int[25];
    for (int x = 0;x < 25 ;x++ ) {
      a[x] = (int) (Math.random() * 1000);
    } */
    int[] a = {5,54,32,56,12,-12, 56, -3, 34 ,-56};
    System.out.println(Arrays.toString(a));
    radixsort(a);
    System.out.println(Arrays.toString(a));
  }
}
