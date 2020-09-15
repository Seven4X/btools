package io.seven4x.repository;

import io.seven4x.service.UrlInfo;

public class UrlInfoData implements UrlInfo {
    String longUrl;

    public UrlInfoData() {
    }

    public UrlInfoData(String longUrl) {
        this.longUrl = longUrl;
    }

    @Override
    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
