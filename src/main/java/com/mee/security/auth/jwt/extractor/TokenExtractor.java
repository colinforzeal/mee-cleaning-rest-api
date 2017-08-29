package com.mee.security.auth.jwt.extractor;


public interface TokenExtractor {
    public String extract(String payload);
}
