package com.twitter.controller;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.util.Util;


@RestController
@RequestMapping("/api/v1")
public class Controller {

    Logger LOG = Logger.getLogger(Controller.class.getName());

    @Autowired
    Util util;

    @GetMapping("/heartbeat")
    public String heartbeat()
    {
       System.out.println("heartbeat");
        return "TWITTER API RUNNING : " +  new Date();
    }

    @GetMapping("get-tweets")
    public String getTweets()
    {

        Map<String,String> headers = new HashMap<>();
        headers.put("Authorization","OAuth oauth_consumer_key=\"ohXPT7JdMS4j4PKjvwjCHygZa\",oauth_token=\"2955186811-fkFwQTi5JPiWPIYu4KDzyr0pjSVrE6P2wnQbY00\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1559989570\",oauth_nonce=\"HfGzTzmxVyD\",oauth_version=\"1.0\",oauth_signature=\"IxDXsl1BvC8uRbBjY2Kg0N8yyTo%3D\"");
        LOG.info("returning response");

        String res = util.hitRequest("https://api.twitter.com/1.1/tweets/search/fullarchive/ironThroneProd.json?query=gameOfThrone&fromDate=201903010000&toDate=201905010000&maxResults=100",
           null,String.class,headers, HttpMethod.GET);

        return res;
    }

}
