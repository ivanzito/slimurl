package com.vanhack.slimurl.service;

import com.vanhack.slimurl.endpoint.exception.ResourceNotFoundException;
import com.vanhack.slimurl.model.Shortner;
import com.vanhack.slimurl.repository.ShortnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ShortnerService {

    private final UrlShortnerService urlShortService;
    private final ShortnerRepository shortnerRepository;

    @Autowired
    public ShortnerService(UrlShortnerService urlShortnerService, ShortnerRepository shortnerRepository) {
        this.urlShortService = urlShortnerService;
        this.shortnerRepository = shortnerRepository;
    }

    public String shortUrl(@PathVariable(value = "url") Shortner shortner) {
        String encodedUrl = urlShortService.shortUrl(shortner.getUrl());
        shortner.setEncoded(encodedUrl);
        this.shortnerRepository.save(shortner);
        return encodedUrl;
    }


    public String forwardURL(@PathVariable(value = "url") String url) {
        Shortner shortner = shortnerRepository.findById(url)
                                              .orElseThrow(ResourceNotFoundException::new);
        return shortner.getUrl();
    }
}