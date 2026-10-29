public class HashTable {
    private static class Entry {
        String key;
        Vehicle value;
        Entry next;
        Entry(String key, Vehicle value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    private Entry[] buckets;
    private int size;
    public HashTable(int capacity) {
        buckets = new Entry[capacity];
        size = 0;
    }
    private int hash(String key) {
        int total = 0;
        for (int i = 0; i < key.length(); i++) {
            total = total + key.charAt(i);
        }
        return total % buckets.length;
    }
    public void put(Vehicle vehicle) {
        String key = vehicle.getPlateNumber().toUpperCase();
        int index = hash(key);
        Entry head = buckets[index];
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = vehicle;
                return;
            }
            head = head.next;
        }
        Entry entry = new Entry(key, vehicle);
        entry.next = buckets[index];
        buckets[index] = entry;
        size++;
    }
    public Vehicle get(String plateNumber) {
        String key = plateNumber.toUpperCase();
        int index = hash(key);
        Entry head = buckets[index];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    public boolean contains(String plateNumber) {
        return get(plateNumber) != null;
    }
    public boolean remove(String plateNumber) {
        String key = plateNumber.toUpperCase();
        int index = hash(key);
        Entry head = buckets[index];
        Entry previous = null;
        while (head != null) {
            if (head.key.equals(key)) {
                if (previous == null) {
                    buckets[index] = head.next;
                } else {
                    previous.next = head.next;
                }
                size--;
                return true;
            }
            previous = head;
            head = head.next;
        }
        return false;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void rebuild(Vehicle[] parkedSlots) {
        buckets = new Entry[buckets.length];
        size = 0;
        for (int i = 0; i < parkedSlots.length; i++) {
            if (parkedSlots[i] != null) {
                put(parkedSlots[i]);
            }
        }
    }
}