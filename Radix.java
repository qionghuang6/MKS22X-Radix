public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList[] buckets = new MyLinkedList[10];
    for (MyLinkedList<Integer> buc: buckets) {
      buc = new MyLinkedList<Integer>();
    }
    boolean completed = false;
    int place = 1;
    while(!completed){
      completed = true;
      for(int num: data){
        int index = (num % (10 * place)) / place;
        if(num < 0){
          buckets[index].addFront(num);
        } else{
          buckets[index].add(num);
        }
        if(num / place > 10){
          completed = false;
        }
        MyLinkedList<Integer> newData= new MyLinkedList();
        for (MyLinkedList<Integer> buc: buckets ) {
          newData.extend(buc);
        }
        
      }
    }
  }
}
