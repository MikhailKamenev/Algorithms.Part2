package com.example.algorithms2;

public class Main {
    public static void main(String[] args) {
        IntegerListImpl integerList=new IntegerListImpl();
        IntegerListImpl integerList1=new IntegerListImpl();
        IntegerListImpl integerList2=new IntegerListImpl();

        //сравнивание значений затраченного на обработку списка времени

        long start = System.currentTimeMillis();
        integerList.sortFromLeastToMax();
        System.out.println(System.currentTimeMillis() - start);
        long start1 = System.currentTimeMillis();
        integerList1.sortSelection();
        System.out.println(System.currentTimeMillis() - start1);
        long start2 = System.currentTimeMillis();
        integerList2.sortInsertion();
        System.out.println(System.currentTimeMillis() - start2);
//         метод sortInsertion() выполняется быстрее остальных


    }
}
