package com.vanhack.slimurl.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UrlShortnerServiceTest {

    private UrlShortnerService urlShortnerService = new UrlShortnerService();

    @Test
    public void shortUrlWith6Slashes() {
        assertEquals(urlShortnerService.shortUrl("https://docs.docker.com/engine/reference/commandline/image_rm/"),"gaihih");
        assertEquals(urlShortnerService.shortUrl("https://www.google.com/search?q=ShortenUrlRequest&oq=ShortenUrlRequest&aqs=chrome..69i57j69i60.452j0j7&sourceid=chrome&ie=UTF-8"), "gdZ");
    }

    @Test
    public void shortUrlFromASimpleSite() {
        assertEquals(urlShortnerService.shortUrl("http://www.asciitable.com/"),"ed");
    }

}
