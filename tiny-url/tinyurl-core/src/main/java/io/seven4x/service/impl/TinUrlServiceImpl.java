package io.seven4x.service.impl;

import io.seven4x.service.*;

import javax.annotation.Resource;

public class TinUrlServiceImpl implements TinyUrlService {

    UrlStore urlStore;


    UrlTransformStrategy urlTransformStrategy;


    TinyUrlConfig config;

    /**
     * 转换成功时返回结果为短链部分
     * 不成功返回 null
     *
     * @param longUrl 长链
     * @return
     */
    @Override
    public String toShortUrl(String longUrl) {
        for (int j = 1; j <= config.getMaxTry(); j++) {
            String shortUrl = trySave(longUrl, j);
            if (shortUrl != null) {
                return shortUrl;
            }
        }
        return null;
    }

    /**
     * 解决hash冲突的方法：
     * urlStore只存储长短链一对一关系，保存失败时改变seed重新hash
     */
    private String trySave(String longUrl, int seed) {
        int hash = urlTransformStrategy.hash(longUrl, seed);
        String shortUrl = urlTransformStrategy.toBase64(hash);

        int i = urlStore.storeMapping(longUrl, shortUrl);
        if (i > 0) {
            return shortUrl;
        }
        return null;
    }

    @Override
    public UrlInfo getLongUrl(String shortUrl) {
        return urlStore.getUrlInfoByShortUrl(shortUrl);
    }




    public void setUrlStore(UrlStore urlStore) {
        this.urlStore = urlStore;
    }


    public void setUrlTransformStrategy(UrlTransformStrategy urlTransformStrategy) {
        this.urlTransformStrategy = urlTransformStrategy;
    }


    public void setConfig(TinyUrlConfig config) {
        this.config = config;
    }
}
