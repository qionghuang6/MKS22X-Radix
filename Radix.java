public class Radix{
  public static void radixsort(int[] data){
    MyLinkedList[] buckets = new MyLinkedList[10];
    for (int i = 0; i < buckets.length; i++) {
      buckets[i] = new MyLinkedList<Integer>();
    }
    boolean completed = false;
    int place = 1;
    while(!completed){
      completed = true;
      for(int num: data){
        int index = (num % (10 * place)) / place;
        System.out.println("INDEX" + index);
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
        for (int x = 0;x < data.length ;x++ ) {
          data[x] = newData.removeFront();
          }
      }
    }
  }
  public static void main(String[] args) {
    int[] a= {20,23,45,12,56,87};
    radixsort(a);
    System.out.println(a);
  }
}
