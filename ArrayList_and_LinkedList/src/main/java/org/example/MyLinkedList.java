package org.example;

/**
 * MyLinkedList - реализация двусвязного списка.
 *
 * @param <E> тип элементов, хранящихся в списке.
 */
public class MyLinkedList<E> {

    // Узлы списка и размер
    private Node<E> head;
    private Node<E> tail;
    private int size;


    /**
     * Вложенный класс, представляющий узел двусвязного списка.
     *
     * @param <E> тип данных, хранящихся в узле.
     */
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
        }
    }


    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент для добавления.
     */
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }



    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно удалить.
     * @return удаленный элемент.
     * @throws IndexOutOfBoundsException если индекс вне диапазона.
     */
    public E remove(int index) {
        checkElementIndex(index);
        Node<E> currentNode = getNode(index);

        if (currentNode.prev != null) {
            currentNode.prev.next = currentNode.next;
        } else {
            head = currentNode.next;
        }

        if (currentNode.next != null) {
            currentNode.next.prev = currentNode.prev;
        } else {
            tail = currentNode.prev;
        }

        size--;
        return currentNode.data;
    }



    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно получить.
     * @return элемент, находящийся по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс вне диапазона.
     */
    public E get(int index) {
        checkElementIndex(index);
        return getNode(index).data;
    }


    /**
     * Заменяет элемент по указанному индексу на новый.
     *
     * @param index индекс элемента, который нужно заменить.
     * @param element новый элемент, который заменит старый.
     * @throws IndexOutOfBoundsException если индекс вне диапазона.
     */
    public void set(int index, E element) {
        checkElementIndex(index);
        Node<E> currentNode = getNode(index);
        currentNode.data = element;
    }



    /**
     * Возвращает подсписок от указанного начального индекса (включительно)
     * до конечного индекса (исключая его).
     *
     * @param fromIndex начальный индекс (включительно)
     * @param toIndex конечный индекс (исключая его)
     * @return новый список, содержащий элементы в диапазоне [fromIndex, toIndex)
     * @throws IndexOutOfBoundsException если начальный или конечный индекс вне диапазона
     */
    public MyLinkedList<E> sublist(int fromIndex, int toIndex) {
        checkPositionIndex(fromIndex);
        checkPositionIndex(toIndex);
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("fromIndex > toIndex");
        }

        MyLinkedList<E> sublist = new MyLinkedList<>();
        Node<E> currentNode = getNode(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            sublist.add(currentNode.data);
            currentNode = currentNode.next;
        }

        return sublist;
    }



    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов в списке.
     */
    public int size() {
        return size;
    }



    /**
     * Возвращает узел по указанному индексу.
     * Если индекс меньше чем половина размера списка ищет с головы, иначе с хвоста.
     *
     * @param index индекс узла.
     * @return узел, находящийся по указанному индексу.
     * @throws IndexOutOfBoundsException если индекс вне диапазона.
     */
    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> currentNode;

        if (index < size / 2) {
            currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
        } else {
            currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.prev;
            }
        }

        return currentNode;
    }


    /**
     * Проверяет, что индекс элемента находится в пределах допустимого диапазона.
     * Используется для методов, работающих с существующими элементами.
     *
     * @param index индекс для проверки.
     * @throws IndexOutOfBoundsException если индекс вне диапазона.
     */
    private void checkElementIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }


    /**
     * Проверяет, что индекс позиции находится в пределах допустимого диапазона.
     * Используется для методов, которые могут добавлять элементы по индексу.
     *
     * @param index индекс для проверки.
     * @throws IndexOutOfBoundsException если индекс вне диапазона.
     */
    private void checkPositionIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
