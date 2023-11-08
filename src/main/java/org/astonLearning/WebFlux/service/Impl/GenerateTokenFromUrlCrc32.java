package org.astonLearning.WebFlux.service.Impl;

import com.google.common.base.Charsets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.astonLearning.WebFlux.service.GenerateTokenFromUrl;
import org.springframework.stereotype.Service;

@Service
public class GenerateTokenFromUrlCrc32 implements GenerateTokenFromUrl {

    final private HashFunction hashFunction;

    public GenerateTokenFromUrlCrc32() {
        this.hashFunction = Hashing.crc32();
    }

    @Override
    public String getToken(String original) {
        return hashFunction.hashString(original, Charsets.UTF_8).toString();
    }
}
