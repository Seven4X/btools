package io.seven4x.service;

/**
 * 短链服务层
 * 两个简单的接口： 长链转化成短链，短链找到对应的长链信息
 */
public interface TinyUrlService {

    /**
     * 将长链接转换成短链接
     *
     * @param longUrl 长链
     * @return 短链
     */
    String toShortUrl(String longUrl);

    /**
     * 找到短链接对应的长连接
     *
     * @param shortUrl 短链
     * @return 返回对象 留给可能的扩展
     */
    UrlInfo getLongUrl(String shortUrl);
}
