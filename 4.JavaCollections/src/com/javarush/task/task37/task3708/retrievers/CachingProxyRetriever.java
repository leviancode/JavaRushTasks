package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

public class CachingProxyRetriever implements Retriever{
    private LRUCache lruCache;
    private OriginalRetriever originalRetriever;

    public CachingProxyRetriever(Storage storage) {
        originalRetriever = new OriginalRetriever(storage);
        lruCache = new LRUCache(10);
    }

    @Override
    public Object retrieve(long id) {
        Object result = lruCache.find(id);
        if (result == null){
            result = originalRetriever.retrieve(id);
            lruCache.set(id, result);
        }

        return result;
    }
}

