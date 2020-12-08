package com.lwade.projects.shorturl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ShortUrlRestController {
    //This is where the short and long URL pairs will be stored
    private Map<String, ShortenUrl> shortenUrlList = new HashMap<>();

    @PostMapping("/shortenurl")
    public ResponseEntity<Object>getShortenUrl(@RequestBody ShortenUrl shortenUrl) throws MalformedURLException {
        String randomChar = getRandomChars();
        setShortUrl(randomChar, shortenUrl);
        return new ResponseEntity<Object>(shortenUrl, HttpStatus.OK);
    }

    private void setShortUrl(String randomChar, ShortenUrl shortenUrl)throws MalformedURLException {
        shortenUrl.setShort_url("http://localhost:8080/s/"+ randomChar);
        shortenUrlList.put(randomChar, shortenUrl);
    }

    @GetMapping("/s/{randomstring}")
    public void getFullUrl(HttpServletResponse response, @PathVariable("randomstring") String randomString)throws IOException{
        response.sendRedirect(shortenUrlList.get(randomString).getFull_url());
    }

    private String getRandomChars(){
        String randomStr = "";
        String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i=0;i<5;i++)
            randomStr += possibleChars.charAt((int)Math.floor(Math.random()*possibleChars.length()));
        return randomStr;
    }


}
