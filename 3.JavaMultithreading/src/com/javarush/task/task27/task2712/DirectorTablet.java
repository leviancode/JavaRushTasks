package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import java.util.Map;

public class DirectorTablet {

    public void printAdvertisementProfit(){
        long totalProfit = StatisticManager.getInstance().getProfitStat().values()
                .stream().mapToLong(l -> l).sum();

        StatisticManager.getInstance().getProfitStat()
                .forEach((key, value) -> ConsoleHelper.writeMessage(key + " - " + (double)value/100));

        ConsoleHelper.writeMessage("Total - " + (double)totalProfit/100);
    }

    public void printCookWorkloading(){
        Map<String, Map<String, Integer>> cooksStat = StatisticManager.getInstance().getCooksStat();
        cooksStat.forEach((date, cooks) ->{
            ConsoleHelper.writeMessage(date);
            cooks.forEach((name, time) -> ConsoleHelper.writeMessage(name + " - " + time + " min"));
            ConsoleHelper.writeMessage("");
        });
    }

    public void printActiveVideoSet(){
        StatisticAdvertisementManager.getInstance().getAvailableAdsStat()
                .forEach((name, hits) -> ConsoleHelper.writeMessage(name + " - " + hits));
    }

    public void printArchivedVideoSet(){
        StatisticAdvertisementManager.getInstance().getArchivedAdsSet().forEach(ConsoleHelper::writeMessage);
    }
}
