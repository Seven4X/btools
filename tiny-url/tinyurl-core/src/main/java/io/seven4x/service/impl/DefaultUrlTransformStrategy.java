package io.seven4x.service.impl;


import io.seven4x.service.UrlTransformStrategy;


public class DefaultUrlTransformStrategy implements UrlTransformStrategy {
    @Override
    public int hash(String longUrl, int seed) {
        return MurmurHash3.murmurhash3_x86_32(longUrl, 0, longUrl.length(), seed);
    }

    @Override
    public String toBase64(int hash) {
        return Encode64.CompressNumber(hash);
    }
}
