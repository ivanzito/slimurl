package com.vanhack.slimurl.endpoint;

import com.vanhack.slimurl.model.Shortner;
import com.vanhack.slimurl.service.ShortnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


@RestController
@RequestMapping
public class ShortnerEndpoint {

    private final ShortnerService shortnerService;
    private HttpServletRequest request;

    @Autowired
    public ShortnerEndpoint(ShortnerService shortnerService, HttpServletRequest request) {
        this.shortnerService = shortnerService;
        this.request = request;
    }

    @GetMapping
    @RequestMapping
    public String shortUrl(@RequestParam(value = "url") String url) {

        String server = request.getRequestURL().toString();
        final Shortner shortner = Shortner.builder()
                .localDateTime(LocalDateTime.now())
                .url(url.toLowerCase())
                .build();
        return server + shortnerService.shortUrl(shortner);
    }


    @GetMapping
    @RequestMapping("/{token}")
    public void forwardURL(@PathVariable(value = "token") String token, HttpServletResponse response) throws ServletException, IOException {
        //request.getRequestDispatcher(shortnerService.forwardURL(token.toLowerCase())).forward(request, response);
        response.sendRedirect(shortnerService.forwardURL(token.toLowerCase()));
    }
}
