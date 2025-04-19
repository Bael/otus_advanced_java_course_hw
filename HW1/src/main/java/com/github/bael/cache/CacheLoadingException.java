package com.github.bael.cache;

public class CacheLoadingException extends RuntimeException {

    public CacheLoadingException(String message) {
        super(message);
    }

    public CacheLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

}
