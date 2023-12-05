public class CustomHashTable<K, V> {
    private final int SIZE = 5;
    private int capacity;
    private Entry<K, V>[] table;

    public CustomHashTable() {
        table = new Entry[SIZE];
    }

    public void put(K key, V value) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> entry = table[hash];

        if (entry == null) {
            table[hash] = new Entry<K, V>(key, value);
        }  else {
            while (entry.next != null) {
                if (entry.getKey() == key) {
                    entry.setValue(value);
                    return;
                }

                entry = entry.next;
            }

            if (entry.getKey() == key) {
                entry.setValue(value);
                return;
            }

            entry.next = new Entry<K, V>(key, value);
        }
    }

    public V get(K key) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> entry = table[hash];

        if (entry == null) {
            return null;
        }

         while (entry != null) {
             if (entry.getKey() == key) {
                 return  entry.getValue();
             }

             entry = entry.next;
         }

         return null;
    }

    public Entry<K, V> remove(K key, V value) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> entry = table[hash];

        if (entry == null) {
            return  null;
        }

        if (entry.getKey() == key) {
            table[hash] = entry.next;
            entry.next = null;
            return entry;
        }

        Entry<K, V> prev = entry;
        entry = entry.next;

        while (entry != null) {
            if (entry.getKey() == key) {
                prev.next = entry.next;
                entry.next = null;
                return entry;
            }
        }

        return null;
    }
}



