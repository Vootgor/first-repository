package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

/*        // проверка MyArrayList
        MyArrayList<String> arrayList = new MyArrayList<>();
        MyArrayList sublist = new MyArrayList<>();

        System.out.println("Заполняем 6 ячеек списка");
        arrayList.add("Один");
        arrayList.add("Два");
        arrayList.add("Три");
        arrayList.add("Четыре");
        arrayList.add("Пять");
        arrayList.add("Шесть");

        System.out.println("Вместимость списка = " + arrayList.length());
        System.out.println("Заполненность списка = " + arrayList.size());
        System.out.println("Добавляем еще 5 значений в список");
        arrayList.add("Семь");
        arrayList.add("Восемь");
        arrayList.add("Девять");
        arrayList.add("Десять");
        arrayList.add("Одиннадцать");
        System.out.println("Вместимость стала = " + arrayList.length());
        System.out.println("Заполненность стала = " + arrayList.size());
        System.out.println("Получаем пятый индекс = " + arrayList.get(5));
        System.out.println("Получаем подсписок с первый по четвёртый индекс");
        sublist = arrayList.subList(1,4);
        for (int i = 0; i < sublist.size(); i++) {
            System.out.println(sublist.get(i));
        }
        System.out.println("Удалили число " + arrayList.remove(2));
        System.out.println("Заполненность списка стала = " + arrayList.size());
        System.out.println("Заменяем число один на число девять");
        arrayList.set(0,"Девять");
        System.out.println("Получаем первый элемент списка = " + arrayList.get(0));*/




        // проверка MyLinkedList
        MyLinkedList<String> myLinkedList = new MyLinkedList<>();
        MyLinkedList<String> sublist;

        System.out.println("Добавляем 6 элементов в список");
        myLinkedList.add("Один");
        myLinkedList.add("Два");
        myLinkedList.add("Три");
        myLinkedList.add("Четыре");
        myLinkedList.add("Пять");
        myLinkedList.add("Шесть");


        System.out.println("Заполненность списка = " + myLinkedList.size());
        System.out.println("Получаем пятый индекс = " + myLinkedList.get(5));
        System.out.println("Получаем подсписок с первый по четвёртый индекс");
        sublist = myLinkedList.sublist(1,3);
        for (int i = 0; i < sublist.size(); i++) {
            System.out.println(sublist.get(i));
        }
        System.out.println("Удалили число " + myLinkedList.remove(3));
        System.out.println("Заполненность списка стала = " + myLinkedList.size());
        System.out.println("Заменяем число один на число девять");
        myLinkedList.set(0,"Девять");
        System.out.println("Получаем первый элемент массива = " + myLinkedList.get(0));


    }
}