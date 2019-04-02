import java.util.*;
public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList<Integer>[] buckets = new MyLinkedList[10];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }
    boolean completed = false;
    int place = 1;
    MyLinkedList<Integer> newData= new MyLinkedList();
    for (int num: data) {
      newData.add(num);
    }
    while(!completed){
      completed = true;
      for(int a = 0; a < data.length; a++){
        int num = newData.removeFront();
        int index = (Math.abs(num) % (10 * place)) / place;
          buckets[index].add(num);
        if(Math.abs(num) / place > 10){
          completed = false;
        }
      }
      newData.clear();
      for (MyLinkedList<Integer> buc: buckets ) {
        if(!completed){
          newData.extend(buc);
        } else{
          while(buc.size() > 0){
            int temp = buc.removeFront();
            if(temp < 0){
              newData.addFront(temp);
            } else{
              newData.add(temp);
            }
          }
        }
      }
      place *= 10;
    }
    for (int x = 0;x < data.length ;x++ ) {
      data[x] = newData.removeFront();
    }
  }
  public static void main(String[] args) {
    /*
    int[] a= new int[25];
    for (int x = 0;x < 25 ;x++ ) {
      a[x] = (int) (Math.random() * 1000);
    } */
    System.out.println((-312 % 10000) / 1000);
    int[] a = {5,524,312,12332,5126,0,-312, 536, -3, 3234 ,-56, 100};
    System.out.println(Arrays.toString(a));
    radixsort(a);
    System.out.println(Arrays.toString(a));
  }
}
