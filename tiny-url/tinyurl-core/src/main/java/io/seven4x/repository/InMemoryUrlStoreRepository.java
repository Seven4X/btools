package io.seven4x.repository;

import io.seven4x.service.UrlInfo;
import io.seven4x.service.UrlStore;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUrlStoreRepository implements UrlStore {
    private Map<String, String> shortUrlMap = new ConcurrentHashMap<>();
    private Map<String, String> longUrlMap = new ConcurrentHashMap<>();
    private final int SUCCESS = 1;
    private final int FAILED = 0;


    @Override
    public int storeMapping(String longUrl, String shortUrl) {
        String url = shortUrlMap.get(shortUrl);
        if (url == null) {
            shortUrlMap.put(shortUrl, longUrl);
            longUrlMap.put(longUrl, shortUrl);
            return SUCCESS;
        }
        // 确保长短URL一对一
        if (Objects.equals(url, longUrl) && Objects.equals(shortUrl, longUrlMap.get(longUrl))) {
            return SUCCESS;
        }

        return FAILED;
    }


    @Override
    public UrlInfo getUrlInfoByShortUrl(String shortUrl) {
        String s = shortUrlMap.get(shortUrl);
        UrlInfoData urlInfoData = new UrlInfoData();
        urlInfoData.setLongUrl(s);
        return urlInfoData;
    }
}
