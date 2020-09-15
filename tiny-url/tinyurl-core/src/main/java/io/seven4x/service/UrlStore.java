package io.seven4x.service;

public interface UrlStore {
    /**
     * 仅支持 长链接和短链一对一关系
     * 数据库实现：
     * 实现依赖数据库的SQL影响行返回
     * 库建立了longUrl+shortUrl唯一性约束
     * <p>
     *
     * <p>
     * 为了先尝试保存避免每次都进行重复校验
     *
     * @param longUrl
     * @param shortUrl
     * @return
     */
    int storeMapping(String longUrl, String shortUrl);

    UrlInfo getUrlInfoByShortUrl(String shortUrl);

}
