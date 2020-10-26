package com.xm.jy.linkedlist.LinkedLists;

/**
 * @author: albert.fang
 * @date: 2020/10/26 10:05
 * @description: 实现链表
 */
public class LinkedList<E> implements List<E> {


    // 虚拟头结点，不存储元素值，只是用来使添加和删除的逻辑一致
    private Node dummyHead = new Node();

    private int size;

    // 内部私有节点类（用户不能操作这个内部私有类）
    private class Node{

        E e;
        Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(){
            this(null,null);
        }

        public Node(E e){
            this(e,null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    @Override
    public void add(int index, E e) {
        if (size < index || index < 0)
            throw new IllegalArgumentException("add failed,index is illegal");
        Node prev = dummyHead;
        for (int i = 0; i < index ; i++)
            prev = prev.next;
        prev.next = new Node(e,prev.next);
        size ++;
    }

    public void addFirst(E e){
        add(0,e);
    }

    public void addLast(E e){
        add(size,e);
    }

    @Override
    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null){
            if (cur.e.equals(e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (isEmpty()){
            throw new IllegalArgumentException("failed，no value in");
        }
        if (size < index || index < 0)
            throw new IllegalArgumentException("delete failed,index is illegal");
        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        Node delNode = prev.next;
        prev.next = delNode.next;
        // 方便JVM回收
        delNode.next = null;
        size --;
        return delNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    @Override
    public void set(int index, E e) {
        if (size <= index || index < 0)
            throw new IllegalArgumentException("set failed,index is illegal");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    @Override
    public E get(int index) {
        if (size <= index || index < 0)
            throw new IllegalArgumentException("get failed,index is illegal");
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null){
            sb.append(cur.e.toString()).append(" -> ");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        // 添加到末尾
        for (int i = 0; i < 3; i++) {
            linkedList.addLast(i);
            System.out.println(linkedList + " 链表大小：" + linkedList.getSize());
        }
        // 添加到头部
        for (int i = 3; i < 6; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList + " 链表大小：" + linkedList.getSize());
        }
        // 按index来添加
        linkedList.add(2,2);
        System.out.println(linkedList + " 链表大小：" + linkedList.getSize());

        // 修改链表值
        for (int i = 0; i < linkedList.getSize(); i++) {
            linkedList.set(i,i);
            System.out.println(linkedList + " 链表大小：" + linkedList.getSize());
        }

        // 得到链表的值
        System.out.println(linkedList.get(linkedList.getSize() - 1));

        // 删除链表头结点
        for (int i = 0; i < 3; i++) {
            linkedList.removeFirst();
            System.out.println(linkedList + " 链表大小：" + linkedList.getSize());
        }

        // 删除链表尾节点
        for (int i = 0; i < 2; i++) {
            linkedList.removeLast();
            System.out.println(linkedList + " 链表大小：" + linkedList.getSize());
        }

        // 删除指定索引节点
        linkedList.remove(1);
        System.out.println(linkedList + " 链表大小：" + linkedList.getSize());
    }
}