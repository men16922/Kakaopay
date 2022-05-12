package com.bm.kakaopay.algorithm;


import org.hibernate.annotations.Entity;

import java.util.*;

class Node<E> {

    E data;
    Node<E> next;	// 다음 노드객체를 가리키는 래퍼런스 변수
    Node<E> prev;	// 이전 노드객체를 가리키는 래퍼런스 변수

    Node(E data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public Node<E> getNext() {
        return next;
    }

    public Node<E> getPrev() {
        return prev;
    }


}

class DLinkedList<E> implements List<E>, Cloneable {

    private Node<E> head;	// 노드의 첫 부분
    private Node<E> tail;	// 노드의 마지막 부분
    private int size;	// 요소 개수

    public DLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // 특정 위치의 노드를 반환하는 메소드
    public Node<E> search(int index) {

        // 범위 밖(잘못된 위치)일 경우 예외 던지기
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // 뒤에서부터 검색
        if (index > size / 2) {
            Node<E> x = tail;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }
        // 앞에서부터 검색
        else {
            Node<E> x = head;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;
        }
    }


    public void link() {
        tail.next = head;
        head.prev = tail;
    }

    public Object link(Node<E> node) {
        Node<E> prevNode = node.prev;
        prevNode.next = node.next;
        Node<E> nextNode = node.next;
        nextNode.prev = node.prev;

        return node.prev.data;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<E>(value);	// 새 노드 생성
        newNode.next = head;	// 새 노드의 다음 노드로 head 노드를 연결

        /**
         * head가 null이 아닐 경우에만 기존 head노드의 prev 변수가
         * 새 노드를 가리키도록 한다.
         * 이유는 기존 head노드가 없는 경우(null)는 데이터가
         * 아무 것도 없던 상태였으므로 head.prev를 하면 잘못된 참조가 된다.
         */
        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
        size++;

        /**
         * 다음에 가리킬 노드가 없는 경우(=데이터가 새 노드밖에 없는 경우)
         * 데이터가 한 개(새 노드)밖에 없으므로 새 노드는 처음 시작노드이자
         * 마지막 노드다. 즉 tail = head 다.
         */
        if (head.next == null) {
            tail = head;
        }

    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }


    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        size++;
    }


    public void add(int index, E value) {

        // 잘못된 인덱스를 참조할 경우 예외 발생
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        // 추가하려는 index가 가장 앞에 추가하려는 경우 addFirst 호출
        if (index == 0) {
            addFirst(value);
            return;
        }
        // 추가하려는 index가 마지막 위치일 경우 addLast 호출
        if (index == size) {
            addLast(value);
            return;
        }

        // 추가하려는 위치의 이전 노드
        Node<E> prev_Node = search(index - 1);

        // 추가하려는 위치의 노드
        Node<E> next_Node = prev_Node.next;

        // 추가하려는 노드
        Node<E> newNode = new Node<E>(value);

        // 링크 끊기
        prev_Node.next = null;
        next_Node.prev = null;

        // 링크 연결하기
        prev_Node.next = newNode;

        newNode.prev = prev_Node;
        newNode.next = next_Node;

        next_Node.prev = newNode;
        size++;
    }




    public E remove() {

        Node<E> headNode = head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = headNode.data;

        // head의 다음 노드
        Node<E> nextNode = head.next;

        // head 노드의 데이터들을 모두 삭제
        head.data = null;
        head.next = null;

        /**
         * head의 다음노드(=nextNode)가 null이 아닐 경우에만
         * prev 변수를 null로 업데이트 해주어야 한다.
         * 이유는 nextNode가 없는 경우(null)는 데이터가
         * 아무 것도 없던 상태였으므로 nextNode.prev를 하면 잘못된 참조가 된다.
         */
        if(nextNode != null) {
            nextNode.prev = null;
        }

        head = nextNode;
        size--;

        /**
         * 삭제된 요소가 리스트의 유일한 요소였을 경우
         * 그 요소는 head 이자 tail이었으므로
         * 삭제되면서 tail도 가리킬 요소가 없기 때문에
         * size가 0일경우 tail도 null로 변환
         */
        if(size == 0) {
            tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 삭제하려는 노드가 첫 번째 노드일 경우
        if (index == 0) {
            E element = head.data;
            remove();
            return element;
        }

        Node<E> prevNode = search(index - 1);	// 삭제할 노드의 이전 노드
        Node<E> removedNode = prevNode.next;	// 삭제할 노드
        Node<E> nextNode = removedNode.next;	// 삭제할 노드의 다음 노드

        E element = removedNode.data;	// 삭제되는 노드의 데이터를 반환하기 위한 임시변수

        /**
         * index == 0 일 때의 조건에서 이미 head노드의 삭제에 대한 분기가 있기 때문에
         * prevNode는 항상 존재한다.
         *
         * 그러나 nextNode의 경우는 null일 수 있기 때문에 (= 마지막 노드를 삭제하려는 경우)
         * 이전처럼 반드시 검사를 해준 뒤, nextNode.prev에 접근해야 한다.
         */

        prevNode.next = null;
        removedNode.next = null;
        removedNode.prev = null;
        removedNode.data = null;

        if(nextNode != null) {
            nextNode.prev = null;

            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }
        /**
         *  nextNode가 null이라는 것은 마지막 노드를 삭제했다는 의미이므로
         *  prevNode가 tail이 된다. (연결 해줄 것이 없음)
         */
        else {
            tail = prevNode;
        }

        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {

        Node<E> prevNode = head;
        Node<E> x = head;		// removedNode

        // value 와 일치하는 노드를 찾는다.
        for (; x != null; x = x.next) {
            if (value.equals(x.data)) {
                break;
            }
            prevNode = x;
        }

        // 일치하는 요소가 없을 경우 false 반환
        if (x == null) {
            return false;
        }

        // 삭제하려는 노드가 head일 경우 remove()로 삭제
        if (x.equals(head)) {
            remove();
            return true;
        }
        // remove(int index)와 같은 메커니즘으로 삭제
        else {
            Node<E> nextNode = x.next;

            prevNode.next = null;
            x.data = null;
            x.next = null;
            x.prev = null;

            if(nextNode != null) {
                nextNode.prev = null;

                nextNode.prev = prevNode;
                prevNode.next = nextNode;
            }
            else {
                tail = prevNode;
            }

            size--;
            return true;
        }

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public E set(int index, E value) {

        Node<E> prev = search(index);
        prev.data = null;
        prev.data = value;
        return value;
    }


    @Override
    public int indexOf(Object o) {	// 정방향 탐색
        int index = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            if (o.equals(x.data)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {	// 역방향 탐색
        int index = size;

        for (Node<E> x = tail; x != null; x = x.prev) {
            index--;
            if (o.equals(x.data)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean contains(Object item) {
        return indexOf(item) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null;) {
            Node<E> nextNode = x.next;
            x.data = null;
            x.next = null;
            x.prev = null;
            x = nextNode;
        }
        head = tail = null;
        size = 0;
    }


    public Object clone() throws CloneNotSupportedException {

        @SuppressWarnings("unchecked")
        DLinkedList<? super E> clone = (DLinkedList<? super E>) super.clone();

        clone.head = null;
        clone.tail = null;
        clone.size = 0;

        for (Node<E> x = head; x != null; x = x.next) {
            clone.addLast(x.data);
        }

        return clone;
    }



    public Object[] toArray() {
        Object[] array = new Object[size];
        int idx = 0;
        for (Node<E> x = head; x != null; x = x.next) {
            array[idx++] = (E) x.data;
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            // Arrya.newInstance(컴포넌트 타입, 생성할 크기)
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        Object[] result = a;
        for (Node<E> x = head; x != null; x = x.next) {
            result[i++] = x.data;
        }

        return a;
    }

    public void sort() {
        /**
         *  Comparator를 넘겨주지 않는 경우 해당 객체의 Comparable에 구현된
         *  정렬 방식을 사용한다.
         *  만약 구현되어있지 않으면 cannot be cast to class java.lang.Comparable
         *  에러가 발생한다.
         *  만약 구현되어있을 경우 null로 파라미터를 넘기면
         *  Arrays.sort()가 객체의 compareTo 메소드에 정의된 방식대로 정렬한다.
         */
        sort(null);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);

        int i = 0;
        for (Node<E> x = head; x != null; x = x.next, i++) {
            x.data = (E) a[i];
        }
    }
}

public class p3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pNum =  sc.nextInt();
        int move = sc.nextInt();

        DLinkedList dLinkedList = new DLinkedList();

        for(int i = 1; i <= pNum; i++){
            dLinkedList.add(i);
        }

        if(pNum == 0) {
            System.out.println(0);
            return;
        } else {

            if(pNum >= 1) {

                dLinkedList.link();

                int cur = pNum - 1;
                Node node = dLinkedList.search(cur);
                Node prevNode;
                while(dLinkedList.size() != 2) {
                    for(int i = 0; i < move; i++){
                        node = node.next;
                    }

                    prevNode = node.prev;
                    System.out.println(node.data);
                    dLinkedList.remove(node.data);
                    System.out.println();
                    System.out.println("hey");
                    node = prevNode;
                }

                dLinkedList.remove(node);

                System.out.println("정답" + dLinkedList.search(0).data);
            }
        }




    }
}

