package MyLibrary.MyHashMap;

import java.util.LinkedList;

public class MyHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V val;

        Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    public MyHashMap() {
        this(16);
    }

    public MyHashMap(int capacity) {
        buckets = new LinkedList[capacity];
        size = 0;

        for (int i = 0; i < capacity; i += 1) {
            buckets[i] = new LinkedList<Entry<K, V>>();
        }
    }

    private int index(K key) {
        if (key == null) return 0;
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public int size() {
        return size;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        int i = index(key);

        for (Entry<K, V> e : buckets[i]) {
            if ((key == null && e.key == null) ||
                (key != null && key.equals(e.key))) {
                return e.val;
            }
        }

        return null;
    }

    public V put(K key, V val) {
        int i = index(key);

        for (Entry<K, V> e : buckets[i]) {
            if ((key == null && e.key == null) ||
                (key != null && key.equals(e.key))) {
                V old = e.val;
                e.val = val;
                return old;
            }
        }

        buckets[i].add(new Entry<K, V>(key, val));
        size += 1;
        return null;
    }

    public V remove(K key) {
        int i = index(key);

        for (Entry<K, V> e : buckets[i]) {
            if ((key == null && e.key == null) ||
                (key != null && key.equals(e.key))) {
                V old = e.val;
                buckets[i].remove(e);
                size -= 1;
                return old;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<String, Integer>();

        map.put("a", 1);
        map.put("b", 2);
        map.put("a", 10);

        System.out.println(map.get("a")); // 10
        System.out.println(map.get("b")); // 2
        System.out.println(map.remove("a")); // 10
        System.out.println(map.get("a")); // null
        System.out.println(map.size()); // 1
    }
}