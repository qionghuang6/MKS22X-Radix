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
        if(Math.abs(num) / place > 1){
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
  public static void main(String[]args){
    System.out.println("Size\t\tMax Value\tradix/builtin ratio ");
    int[]MAX_LIST = {1000000000,500,10};
    for(int MAX : MAX_LIST){
      for(int size = 31250; size < 2000001; size*=2){
        long qtime=0;
        long btime=0;
        //average of 5 sorts.
        for(int trial = 0 ; trial <=5; trial++){
          int []data1 = new int[size];
          int []data2 = new int[size];
          for(int i = 0; i < data1.length; i++){
            data1[i] = (int)(Math.random()*MAX);
            data2[i] = data1[i];
          }
          long t1,t2;
          t1 = System.currentTimeMillis();
          Radix.radixsort(data2);
          t2 = System.currentTimeMillis();
          qtime += t2 - t1;
          t1 = System.currentTimeMillis();
          Arrays.sort(data1);
          t2 = System.currentTimeMillis();
          btime+= t2 - t1;
          if(!Arrays.equals(data1,data2)){
            System.out.println("FAIL TO SORT!");
            System.exit(0);
          }
        }
        System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
      }
      System.out.println();
    }
  }
}
