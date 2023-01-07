package com.example.algorithms2;

import java.util.Arrays;
import java.util.Random;

public class IntegerListImpl implements IntegerList{
    private Integer[] data;
    private int size=0;

    public IntegerListImpl() {
        this.data = new Integer[]{};
    }

    @Override//Добавление элемента в конец массива
    public Integer add(Integer item) {
        checkItemIsNull(item);
        grow();
        size++;
        this.data[this.data.length-1]=item;
        return item;
    }

    @Override//добавление элемента по индексу, со сдвигом вправо
    public Integer add(int index, Integer item) {
        checkItemIsNull(item);
        checkIndex(index);
        grow();
        size++;
        System.arraycopy(this.data,index,this.data,index+1,this.data.length-index-1);
        this.data[index] = item;
        return item;
    }


    @Override // добавление элемента по индексу с удалением предыдущего элемента
    public Integer set(int index, Integer item) {
        checkItemIsNull(item);
        checkIndex(index);
        this.data[index] = item;
        return item;
    }


    @Override//удаление всех элементов, соответствующих передаваемому значению
    //наверняка существует более изящное решение, но имеем что имеем
    public Integer removeByItem(Integer item) {
        checkItemIsNull(item);
        //если массив состоит из одного элемента и его надо удалить
        if (this.data.length == 1&&contains(item)) {
            decrease();}
        for (int i = 0; i < this.data.length; i++) {//итерируемся по массиву
            do {
                //если элемент, который надо удалить находится в конце массива, то просто копируем массив,
                // уменьшая размер на 1. для остальных случаев (элемент в начале или в середине) - следующее if
                if
                (this.data[this.data.length - 1].equals(item)) {
                    decrease();
                }
                // найденный удаляем элемент при помощи копирования массива в тот массив же со сдвигом влево
                else if
                (this.data[i].equals(item)) {
                    System.arraycopy(this.data, i + 1, this.data, i, this.data.length - i - 1);
                    decrease();//чтобы не было копирования элемента в конце
                }
            } while (this.data[i].equals(item));//выполнять действие, пока в массиве есть заданный элемент
        }
        return item;
    }

    @Override//удаление элемента по индексу
    public Integer remove(int index) {
        checkIndex(index);
        Integer removedItem = this.data[index];//новая переменная чтобы вернуть удаленный элемент
        if (index == this.data.length - 1) {
            decrease();
        }//если индекс - последний в списке, то удаляем обычным
        // копированием массива с уменьшением количества ячеек
        else {System.arraycopy(this.data,index+1,this.data,index,this.data.length-index-1);
            decrease();}//в остальных случаях - копированием со смещением
        return removedItem;
    }

    @Override//проверка на наличие элемента в списке
    public boolean contains(Integer item) {
        checkItemIsNull(item);
        return binarySearch(item);
    }

    @Override//найти индекс элемента, если таковой есть в списке (итерация с начала списка)
    public int indexOf(Integer item) {
        checkItemIsNull(item);
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override//найти индекс элемента, если таковой есть в списке (итерация с конца списка)
    public int lastIndexOf(Integer item) {
        checkItemIsNull(item);
        for (int i = this.data.length-1; i > 0; i--) {
            if (this.data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override//получить элемент по индексу
    public Integer get(int index) {
        checkIndex(index);
        return this.data[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (this == otherList) return true;
        if (otherList == null || getClass() != otherList.getClass()) return false;
        IntegerListImpl list = (IntegerListImpl) otherList;
        return size == list.size && Arrays.equals(data, list.data);
    }

    @Override
    public int size() {//подробности в методе grow()
        return size;
    }

    @Override
    public boolean isEmpty() {
        return this.data.length == 0;
    }

    @Override//удаление всех элементов структуры данных
    public void clear() {
        this.data = Arrays.copyOf(this.data, 0);
        size = this.data.length;
    }

    @Override//объект класса StringListImpl в объект класса Array
    public Integer[] toArray() {
        return Arrays.copyOf(this.data,this.data.length);
    }

    @Override//распечатать массив
    public String toString() {
        return Arrays.toString(data);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.data.length) {
            throw new IllegalArgumentException("Указан неверный индекс элемента");
        }
    }

    private void checkItemIsNull(Integer item) {
        if (item == null) {
            throw new IllegalArgumentException("Объект списка не может быть null");
        }
    }
    private void grow() {
        this.data = Arrays.copyOf(this.data, this.data.length + 1);
    }

    private void decrease() {
        this.data = Arrays.copyOf(this.data, this.data.length - 1);
        size--;
    }
// метод увеличения длины (100000) и заполнения массива(от -50000 до 50000)
    private void fillInTheArray() {
        Random random = new Random();
        this.data = new Integer[100000];
        for (int i = 0; i < this.data.length; i++) {
            this.data[i]= random.nextInt(-50000,50000);
        }
        size=this.data.length;
    }

    // сортировка массива пузырьковая
    public void sortFromLeastToMax() {
        fillInTheArray();
        for (int i = 0; i < this.data.length; i++) {
            for (int j = 0; j < this.data.length-1-i; j++) {
                if (this.data[j] > this.data[j + 1]) {
                    swap(this.data,j,j+1);
                }
            }
        }
    }

    //сортировка массива выбором
    public void sortSelection() {
        fillInTheArray();
        for (int i = 0; i < this.data.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < this.data.length; j++) {
                if (this.data[j] < this.data[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swap(this.data, i, minElementIndex);
        }
    }

    //сортировка вставкой   -  самый быстрый вариант сортировки
    public void sortInsertion() {
        fillInTheArray();
        for (int i = 1; i < this.data.length; i++) {
            int temp = this.data[i];
            int j = i;
            while (j > 0 && this.data[j - 1] >= temp) {
                this.data[j] = this.data[j - 1];
                j--;
            }
            this.data[j] = temp;
        }
    }

    // приватный метод бинарного поиска в отсортированном массиве. вызывется в методе contains
    private boolean binarySearch(int element) {
        int min = 0;
        int max = this.data.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (element == this.data[mid]) {
                return true;
            }
            if (element < this.data[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    //метод для перестановки элементов внутри массива при сортировке
    private static void swap(Integer[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}
