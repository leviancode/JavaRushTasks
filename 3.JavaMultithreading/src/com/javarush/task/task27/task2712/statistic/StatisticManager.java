package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.ad.Advertisement;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;

public class StatisticManager {
    private static final StatisticManager instance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    private StatisticManager(){}

    public static StatisticManager getInstance(){
        return instance;
    }

    public void register(EventDataRow data){
        statisticStorage.put(data);
    }

    public Map<String, Long> getProfitStat(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Map<String, Long> profitMap = new HashMap<>();

        if(statisticStorage.storage.containsKey(EventType.SELECTED_VIDEOS)){
            List<EventDataRow> list = statisticStorage.storage.get(EventType.SELECTED_VIDEOS);

            for (int i = list.size()-1; i >=0; i--){
                VideoSelectedEventDataRow videoEvent = (VideoSelectedEventDataRow) list.get(i);
                String date = formatter.format(videoEvent.getDate());
                if (profitMap.containsKey(date))
                    profitMap.merge(date, videoEvent.getAmount(), Long::sum);
                else
                    profitMap.put(date, videoEvent.getAmount());
            }
        }
        return profitMap;
    }

    public Map<String, Map<String, Integer>> getCooksStat(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Map<String, Map<String, Integer>> cooksStatMap = new HashMap<>();

        if(statisticStorage.storage.containsKey(EventType.COOKED_ORDER)){
            List<EventDataRow> list = statisticStorage.storage.get(EventType.COOKED_ORDER);

            for (int i = list.size()-1; i >=0; i--){
                CookedOrderEventDataRow orders = (CookedOrderEventDataRow) list.get(i);
                String date = formatter.format(orders.getDate());
                String name = orders.getCookName();
                int time = orders.getTime() / 60;

                if (cooksStatMap.containsKey(date)){
                    if (cooksStatMap.get(date).containsKey(name)){
                        cooksStatMap.get(date).merge(name, time, Integer::sum);
                    }else{
                        cooksStatMap.get(date).put(name, time);
                    }
                }else{
                    Map<String, Integer> cook = new TreeMap<>();
                    cook.put(name, time);
                    cooksStatMap.put(date, cook);
                }
            }
        }
        return cooksStatMap;
    }

    private class StatisticStorage{
        private Map<EventType, List<EventDataRow>> storage;

        public StatisticStorage() {
           storage = new HashMap<>();

           for (EventType et : EventType.values()){
               storage.put(et, new ArrayList<>());
           }
        }

        private void put(EventDataRow data){
            storage.get(data.getType()).add(data);
        }
    }
}
