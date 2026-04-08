import Library00.FnList.*;
import Library00.LnList.*;
import Library00.FnTuple.*;
import Library00.MyMap00.*;

public class Assign08_01<V>
    implements MyMap00<String, V> {
    // HX-2026-04-01:
    // Please give an implementation of hash table
    // that uses separate chaining for handling collisions.
    private LnList<FnTupl2<String, V>>[] table;
    private int size;
    private static final int CAPACITY = 97;

    public Assign08_01() {
        table = (LnList<FnTupl2<String, V>>[]) new LnList[CAPACITY];
        size = 0;
    }

    private int hash(String key) {
        return Math.floorMod(key.hashCode(), table.length);
    }

    private LnStrm<FnTupl2<String, V>> bucket_to_strm(LnList<FnTupl2<String, V>> xs) {
        if (xs == null || xs.nilq1()) {
            return LnStrmSUtil.nil0();
        }
        return LnStrmSUtil.cons0(xs.hd1(), bucket_to_strm(xs.tl1()));
    }

    private LnStrm<FnTupl2<String, V>> table_to_strm(int i) {
        if (i >= table.length) {
            return LnStrmSUtil.nil0();
        }
        LnStrm<FnTupl2<String, V>> rest = table_to_strm(i + 1);
        if (table[i] == null || table[i].nilq1()) {
            return rest;
        }
        return bucket_to_strm(table[i]).append0(rest);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isFull() {
        return false;
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
        LnList<FnTupl2<String, V>> bucket = table[hash(key)];
        if (bucket == null) return null;

        final Object[] ans = new Object[1];
        bucket.foritm1(kv -> {
            if (kv.sub0.equals(key)) ans[0] = kv.sub1;
        });
        return (V) ans[0];
    }

    @Override
    public V insert$opt(String key, V val) {
        int i = hash(key);

        if (table[i] == null) {
            table[i] = new LnList<FnTupl2<String, V>>();
        }

        final Object[] old = new Object[1];
        final boolean[] found = new boolean[1];

        table[i].foritm1(kv -> {
            if (kv.sub0.equals(key)) {
                old[0] = kv.sub1;
                kv.sub1 = val;
                found[0] = true;
            }
        });

        if (found[0]) {
            return (V) old[0];
        }

        table[i] = new LnList<FnTupl2<String, V>>(new FnTupl2<String, V>(key, val), table[i]);
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
        int i = hash(key);
        LnList<FnTupl2<String, V>> bucket = table[i];
        if (bucket == null) return null;

        LnList<FnTupl2<String, V>> kept = new LnList<FnTupl2<String, V>>();
        final Object[] removed = new Object[1];
        final boolean[] found = new boolean[1];

        bucket.foritm1(kv -> {
            if (!found[0] && kv.sub0.equals(key)) {
                removed[0] = kv.sub1;
                found[0] = true;
            } else {
                kept.append1(new LnList<FnTupl2<String, V>>(kv, new LnList<FnTupl2<String, V>>()));
            }
        });

        table[i] = kept;
        if (found[0]) {
            size -= 1;
            return (V) removed[0];
        }
        return null;
    }

    @Override
    public void foritm(BiConsumer<? super String, ? super V> work) {
        for (int i = 0; i < table.length; i += 1) {
            if (table[i] != null) {
                table[i].foritm1(kv -> work.accept(kv.sub0, kv.sub1));
            }
        }
    }
}