import Library00.FnList.*;
import Library00.LnList.*;
import Library00.FnTuple.*;
import Library00.MyMap00.*;

public class Assign08_02<V>
    implements MyMap00<String, V> {
    // HX-2026-04-01:
    // Please give an implementation of hash table
    // based on open addressing. The probing strategy
    // chosen for handling collisions is quadratic probing.
    private FnTupl2<String, V>[] table;
    private byte[] state; // 0 = empty, 1 = occupied, 2 = deleted
    private int size;

    private static final int CAPACITY = 97;

    public Assign08_02() {
        table = (FnTupl2<String, V>[]) new FnTupl2[CAPACITY];
        state = new byte[CAPACITY];
        size = 0;
    }

    private int hash(String key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private int findIndex(String key) {
        int h = hash(key);
        for (int j = 0; j < table.length; j += 1) {
            int i = (h + j * j) % table.length;

            if (state[i] == 0) {
                return -1;
            }
            if (state[i] == 1 && table[i].sub0.equals(key)) {
                return i;
            }
        }
        return -1;
    }

    private int findInsertIndex(String key) {
        int h = hash(key);
        int firstDeleted = -1;

        for (int j = 0; j < table.length; j += 1) {
            int i = (h + j * j) % table.length;

            if (state[i] == 1) {
                if (table[i].sub0.equals(key)) {
                    return i;
                }
            } else if (state[i] == 2) {
                if (firstDeleted < 0) firstDeleted = i;
            } else { // state[i] == 0
                return (firstDeleted >= 0 ? firstDeleted : i);
            }
        }

        return firstDeleted;
    }

    private LnStrm<FnTupl2<String, V>> table_to_strm(int i) {
        if (i >= table.length) {
            return LnStrmSUtil.nil0();
        }
        if (state[i] == 1) {
            return LnStrmSUtil.cons0(table[i], table_to_strm(i + 1));
        }
        return table_to_strm(i + 1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return size == table.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public LnStrm<FnTupl2<String, V>> keyval_strmize() {
        return table_to_strm(0);
    }

    @Override
    public V search$old(String key) {
        return search$exn(key);
    }

    @Override
    public V search$exn(String key) {
        V res = search$opt(key);
        if (res == null) throw new MyMap00NoKeyExn();
        return res;
    }

    @Override
    public V search$opt(String key) {
        int i = findIndex(key);
        if (i < 0) return null;
        return table[i].sub1;
    }

    @Override
    public V insert$opt(String key, V val) {
        int i = findInsertIndex(key);

        if (i < 0) {
            throw new MyMap00FullExn();
        }

        if (state[i] == 1) {
            V old = table[i].sub1;
            table[i].sub1 = val;
            return old;
        }

        table[i] = new FnTupl2<String, V>(key, val);
        state[i] = 1;
        size += 1;
        return null;
    }

    @Override
    public void insert$new(String key, V val) {
        if (search$opt(key) != null) {
            throw new RuntimeException("key already exists");
        }
        insert$opt(key, val);
    }

    @Override
    public V remove$old(String key) {
        return remove$exn(key);
    }

    @Override
    public V remove$exn(String key) {
        V res = remove$opt(key);
        if (res == null) throw new MyMap00NoKeyExn();
        return res;
    }

    @Override
    public V remove$opt(String key) {
        int i = findIndex(key);
        if (i < 0) return null;

        V old = table[i].sub1;
        table[i] = null;
        state[i] = 2; // tombstone
        size -= 1;
        return old;
    }

    @Override
    public void foritm(BiConsumer<? super String, ? super V> work) {
        for (int i = 0; i < table.length; i += 1) {
            if (state[i] == 1) {
                work.accept(table[i].sub0, table[i].sub1);
            }
        }
    }
}
