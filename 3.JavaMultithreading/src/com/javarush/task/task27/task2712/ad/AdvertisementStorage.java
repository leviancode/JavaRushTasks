package com.javarush.task.task27.task2712.ad;

import java.util.ArrayList;
import java.util.List;

public class AdvertisementStorage {
    private final static AdvertisementStorage instance = new AdvertisementStorage();
    private final List<Advertisement> videos = new ArrayList<>();

    private AdvertisementStorage(){
        Object someContent = new Object();
        add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        add(new Advertisement(someContent, "Second Video", 100, 10, 1 * 60)); //1 min
        add(new Advertisement(someContent, "Third Video", 400, 2, 4 * 60)); //4 min
        add(new Advertisement(someContent, "Fourth Video", 500, 2, 5 * 60)); //5 min
        add(new Advertisement(someContent, "Fifth Video", 600, 4, 2 * 60)); //2 min
        add(new Advertisement(someContent, "Sixth Video", 10, 0, 1 * 60)); //1 min
        add(new Advertisement(someContent, "Seventh Video", 1500, 3, 10 * 60)); //10 min
        add(new Advertisement(someContent, "Eighth Video", 10000, 0, 15 * 60)); //15 min
    }

    public static AdvertisementStorage getInstance(){
        return instance;
    }

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement advertisement){
        videos.add(advertisement);
    }
}
