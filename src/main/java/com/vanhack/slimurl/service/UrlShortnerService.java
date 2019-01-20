package com.vanhack.slimurl.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
class UrlShortnerService {

    private static final String SLASH = "/";

    public String shortUrl(final String url) {

        if(url.contains(SLASH)) {
            return encryptUrl(url, SLASH);
        } else {
            return encryptUrl(url, "\\.");
        }
    }

    private String encryptUrl(String urlToWork, String patternToSplit) {
        return Stream.of(urlToWork.split(patternToSplit))
                     .map(String::chars)
                     .map(this::reducePortion)
                     .map(String::valueOf)
                     .collect(Collectors.joining());
    }

    private String reducePortion(IntStream intStream) {
        List<Character> characters = intStream.mapToObj(chr -> (char) chr)
                                              .collect(Collectors.toList());
        if(characters.size() == 0) {
            return "";
        } else {
            int sum = characters.stream()
                    .map(character -> (int) character)
                    .reduce(0, (a,b)-> a + b);
            Character value = (char) ((char) sum / characters.size());
            return value.toString();
        }
    }
}