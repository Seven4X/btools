package io.seven4x.service;

/**
 * URL信息
 * 依赖倒置原则：接口定义在这里的原因
 * repo层依赖service的接口，service层不依赖repo具体实现
 */
public interface UrlInfo {
    /**
     * 长链
     *
     * @return
     */
    String getLongUrl();

    /**
     * 访问次数
     *
     * @return
     */
    default long getVisitCount() {
        return 0L;
    }
}
