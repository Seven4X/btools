package io.seven4x.service;

/**
 * url 转换策略
 */
public interface UrlTransformStrategy {

    /**
     * Hash算法
     * @param longUrl
     * @return
     */
    default int hash(String longUrl){
        return hash(longUrl,0);
    }

    int hash(String longUrl,int seed);


    /**
     * hash值转换成64位
     * @param hash
     * @return
     */
    String toBase64(int hash);
}
