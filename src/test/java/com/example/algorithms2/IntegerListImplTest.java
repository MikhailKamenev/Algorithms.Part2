package com.example.algorithms2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IntegerListImplTest {
    IntegerListImpl integerList;

    @BeforeEach
    public void setUp() {
        this.integerList=new IntegerListImpl();
    }

    @Test//в этом методе проверка выполняется при помощи бинарного поиска
    //заодно проверяется метод add()
    public void whenContainsReturnTrue() {
        this.integerList.add(6);
        this.integerList.add(7);
        this.integerList.add(8);
        this.integerList.add(3);
        Assertions.assertTrue(this.integerList.contains(8));
        Assertions.assertTrue(this.integerList.contains(7));
        Assertions.assertTrue(this.integerList.contains(6));
    }

    @Test//проверка метода на заполняемость значениями
    public void whenSortThenLength100_000() {
        this.integerList.sortInsertion();
        Assertions.assertEquals(100_000,this.integerList.size());
    }
}
