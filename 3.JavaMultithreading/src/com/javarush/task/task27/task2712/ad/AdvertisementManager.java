package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvertisementManager{
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds; // время приготовления заказа = макс. время показа рекламы
    private List<Advertisement> selectedAds;
    private int [] combo;
    private Advertisement[] bestCombo;
    private long maxProfit;
    private long maxDuration;
    private int adsCount;
    private int extent;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();

        selectAds();
        showAds();
    }

    private void selectAds(){
        selectedAds = storage.list().stream()
                .filter(o -> o.getHits() > 0)
                .filter(o -> o.getDuration() <= timeSeconds)
                .collect(Collectors.toList());

        for (extent = selectedAds.size(); extent > 0; extent--){
            combo = new int[extent];
            generateCombo(0,0);
        }
    }

    // рекурсивный алгоритм сочетания без повторений
    private void generateCombo(int pos, int maxUsed){
        if (pos == extent)
            checkCombo();
        else{
            for (int i = maxUsed; i < selectedAds.size(); i++){
                combo[pos] = i;
                generateCombo(pos+1, i+1);
            }
        }
    }

    private void checkCombo(){
        Advertisement[] tempCombo = new Advertisement[combo.length];
        for (int i = 0; i < combo.length; i++){
            tempCombo[i] = selectedAds.get(combo[i]);
        }

        int duration = Arrays.stream(tempCombo).mapToInt(Advertisement::getDuration).sum();
        long profit = Arrays.stream(tempCombo).mapToLong(Advertisement::getAmountPerOneDisplaying).sum();

        if (timeSeconds - duration >= 0){
            if (profit > maxProfit) {
                setBestCombo(tempCombo, profit, duration);
            }else if (profit == maxProfit){
                if (duration > maxDuration){
                    setBestCombo(tempCombo, profit, duration);
                }else if (duration == maxDuration && combo.length < adsCount){
                    setBestCombo(tempCombo, profit, duration);
                }
            }
        }
    }

    private void setBestCombo(Advertisement[] ads, long profit, int duration){
        bestCombo = ads;
        maxProfit = profit;
        maxDuration = duration;
        adsCount = combo.length;
    }

    private void showAds(){
        StatisticManager.getInstance().register(new VideoSelectedEventDataRow(Arrays.asList(bestCombo), maxProfit, (int)maxDuration));

        Arrays.stream(bestCombo).sorted(Comparator.comparingLong(Advertisement::getAmountPerOneDisplaying).reversed()
                                    .thenComparing(Advertisement::getAmountPerSecond))
                    .forEach(ad -> {
                            ConsoleHelper.writeMessage(ad.toString());
                            revalidate(ad);
                });
    }
    private void revalidate(Advertisement ad){
        int index = storage.list().indexOf(ad);
        storage.list().get(index).revalidate();
    }


}
