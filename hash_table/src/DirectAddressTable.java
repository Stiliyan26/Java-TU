public class DirectAddressTable {
    private int[] table;

    public DirectAddressTable(int size) {
        table = new int[size];
    }

    public void insert(int key, int value) {
        table[key] = value;
    }

    public int search(int key) {
        return table[key];
    }

    public void delete(int key) {
        table[key] = 0; // Пример: Задаване на стойност 0 за изтриване на записа
    }

    public static void main(String[] args) {
        int tableSize = 1000; // Големина на таблицата
        DirectAddressTable dat = new DirectAddressTable(tableSize);

        // Вмъкване на стойности
        dat.insert(42, 100);
        dat.insert(17, 50);

        // Търсене на стойности
        int value1 = dat.search(42);
        int value2 = dat.search(17);

        // Изтриване на стойност
        dat.delete(42);

        System.out.println("Value for key 42: " + value1);
        System.out.println("Value for key 17: " + value2);
    }
}





