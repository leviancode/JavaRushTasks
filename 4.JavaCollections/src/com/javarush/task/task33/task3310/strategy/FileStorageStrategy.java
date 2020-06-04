package com.javarush.task.task33.task3310.strategy;

public class FileStorageStrategy implements StorageStrategy{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    private FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    private int size;
    private long maxBucketSize;

    public FileStorageStrategy(){
        for (int i = 0; i < DEFAULT_INITIAL_CAPACITY; i++) {
            table[i] = new FileBucket();
        }
    }

    public int hash(Long k){
        int h;
        return (k == null) ? 0 : (h = k.hashCode()) ^ (h >>> 16);
    }
    int indexFor(int hash, int length){
        return hash & (length-1);
    }

    Entry getEntry(Long key){
        int hash = (key == null) ? 0 : hash(key);
        int index = indexFor(hash, table.length);
        FileBucket bucket = table[index];

        if (bucket != null) {
            for (Entry entry = bucket.getEntry(); entry != null; entry = entry.next){
                if (entry.key.equals(key))
                    return entry;
            }
        }
        return null;
    }
    void resize(int newCapacity){
        FileBucket[] newTable = new FileBucket[newCapacity];
        transfer(newTable);
        table = newTable;
    }
    void transfer(FileBucket[] newTable){
        for (FileBucket bucket : table) {
            Entry entry = bucket.getEntry();
            if (entry == null) continue;
            int newIndex = indexFor(entry.hash, newTable.length);
            newTable[newIndex] = new FileBucket();
            newTable[newIndex].putEntry(entry);
            bucket.remove();
        }
    }
    void addEntry(int hash, Long key, String value, int bucketIndex){
        FileBucket bucket = table[bucketIndex];
        bucket.putEntry(new Entry(hash, key, value, bucket.getEntry()));
        size++;
        if (bucket.getFileSize() >= bucketSizeLimit)
            resize(2 * table.length);
    }
    void createEntry(int hash, Long key, String value, int bucketIndex){
        table[bucketIndex] = new FileBucket();
        table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        size++;
    }

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key) {
       return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        return getValue(getKey(value)) != null;
    }

    @Override
    public void put(Long key, String value) {
        if (key != null) {
            int hash = hash(key);
            int i = indexFor(hash, table.length);
            for (FileBucket bucket : table){
                for (Entry e = bucket.getEntry(); e != null; e = e.next) {
                    Object k;
                    if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                        e.value = value;
                        return;
                    }
                }
            }
            addEntry(hash, key, value, i);
        }
    }

    @Override
    public Long getKey(String value) {
        if (value != null) {
            for (FileBucket bucket : table)
                for (Entry e = bucket.getEntry(); e != null; e = e.next)
                    if (value.equals(e.value))
                        return e.key;
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        Entry e = getEntry(key);
        return e == null ? null : e.value;
    }
}
