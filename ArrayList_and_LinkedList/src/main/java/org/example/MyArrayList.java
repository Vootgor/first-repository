package org.example;


/**
 * Класс MyArrayList представляет собой простую реализацию динамического списка на основе массива.
 * @param <E> Тип элементов которые будут храниться в данном списке.
 */
public class MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;

    private int size = 0;


    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }


    /**
     * Добавляет элемент в конец списка
     * @param element Элемент который нужно добавить в список.
     */
    public void add(E element) {
        checkingCapacity();
        elements[size++] = element;
    }


    /**
     * Возвращает элемент по указанному индексу
     * @param index Индекс элемента, который нужно получить.
     * @return Элемент, находящийся по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс находится вне
     * допустимого диапазона.
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }


    /**
     * Заменяет элемент в указанной позиции на переданный элемент.
     * @param index Индекс элемента, который нужно заменить.
     * @param element Элемент, который вставляем в указанную позицию.
     * @throws IndexOutOfBoundsException если индекс находится вне
     * допустимого диапазона.
     */
    public void set(int index, E element) {
        checkIndex(index);
        elements[index] = element;
    }


    /**
     * Удаляет элемент по указанному индексу и сдвигает все последующие
     * элементы влево.
     * @param index Индекс элемента, который нужно удалить.
     * @return Удалённый элемент.
     * @throws IndexOutOfBoundsException если индекс находится вне
     * допустимого диапазона.
     */
    @SuppressWarnings("unchecked")
    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;

        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }

        elements[--size] = null;
        return oldValue;
    }


    /**
     * Возвращает подсписок от fromIndex (включительно) до toIndex (не включительно).
     * @param fromIndex Индекс, с которого начинается подсписок (включительно).
     * @param toIndex Индекс, на котором подсписок заканчивается (не включительно).
     * @return Новый список, содержащий элементы от fromIndex до toIndex.
     * @throws IndexOutOfBoundsException если fromIndex или toIndex
     * находятся вне допустимого диапазона.
     */
    @SuppressWarnings("unchecked")
    public MyArrayList<E> subList(int fromIndex, int toIndex) {

        checkIndex(fromIndex);
        checkIndex(toIndex);

        MyArrayList<E> subList = new MyArrayList<>();

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add((E) elements[i]);
        }

        return subList;
    }


    /**
     * Возвращает текущее количество элементов в списке.
     * @return Количество элементов в списке.
     */
    public int size() {
        return size;
    }


    /**
     * Возвращает длину списка.
     * @return Длина списка.
     */
    public int length() {
        return elements.length;
    }


    /**
     * Проверяет заполнен ли полностью массив и при необходимости
     * увеличивает размер на 2, чтобы вместить новые элементы.
     */
    private void checkingCapacity() {
        if (size == elements.length) {
            Object[] newElements = new Object[elements.length * 2];

            for (int i = 0; i < size; i++) {
                newElements[i] = elements[i];
            }
            elements = newElements;
        }
    }


    /**
     * Проверяет, что индекс находится в пределах допустимого диапазона.
     * @param index Индекс который нужно проверить.
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

}
