package com.javarush.task.task27.task2712.ad;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticAdvertisementManager {
    private static final StatisticAdvertisementManager instance = new StatisticAdvertisementManager();
    private AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();

    private StatisticAdvertisementManager(){}

    public static StatisticAdvertisementManager getInstance() {
        return instance;
    }

    public Map<String, Integer> getAvailableAdsStat(){
        return advertisementStorage.list().stream()
                .filter((ad) -> ad.getHits() > 0)
                .collect(Collectors.toMap(Advertisement::getName, Advertisement::getHits, (a, b) -> b, TreeMap::new));

    }

    public List<String> getArchivedAdsSet(){
        return advertisementStorage.list().stream()
                .filter((ad) -> ad.getHits() == 0)
                .map(Advertisement::getName)
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
