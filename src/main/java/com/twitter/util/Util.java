package com.twitter.util;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;

@Service
public class Util {

    Logger logger = LoggerFactory.getLogger(Util.class);

    public <Req, SftpResponse> SftpResponse hitRequest(String url, Object requestObj,
                                                       Class<SftpResponse> classObj, Map<String, String> headers, HttpMethod method)  {
        RestTemplate restTemplate = new RestTemplate();

        logger.info("Entering hitRequest");

        Object resultObj = null;
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<?> entity = null;

        if (headers != null && !headers.isEmpty()) {
            httpHeaders = new HttpHeaders();
            Set<String> headerKeySet = headers.keySet();
            for (String key : headerKeySet)
                httpHeaders.add(key, headers.get(key));
        }

        if (requestObj != null)
            entity = new HttpEntity<>(requestObj, httpHeaders);
        else
            entity = new HttpEntity<>(httpHeaders);

        try {
            // Send request with GET method, and Headers.
            HttpsURLConnection.setDefaultHostnameVerifier((host, session) -> false);
            ResponseEntity<?> response = restTemplate.exchange(url, method, entity, classObj);
            resultObj = response.getBody();
            logger.info(resultObj.toString());
            System.out.println(resultObj.toString());


        } catch (Exception ex) {
            logger.error("Error occured while hitting communication API , message :{}", ex.getMessage());
        }

        logger.info("Exiting hitRequest ");
        return (SftpResponse) resultObj;
    }
}
