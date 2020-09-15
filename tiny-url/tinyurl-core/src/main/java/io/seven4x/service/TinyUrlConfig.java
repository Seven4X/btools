package io.seven4x.service;

public class TinyUrlConfig {
    private String baseUrl;


    /**
     * hash 冲突 最大尝试
     */
    private int maxTry = 3;


    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public int getMaxTry() {
        return maxTry;
    }

    public void setMaxTry(int maxTry) {
        this.maxTry = maxTry;
    }
}
