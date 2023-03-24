package com.example.hellospring.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@RestController
public class HelloResource {
    private static final Logger logger = LoggerFactory.getLogger(HelloResource.class);

    @GetMapping("/")
    public String index() {
        return "Hello Spring!!!";
    }

    @GetMapping("/hello")
    public String hello(HttpSession session) {
        logger.info("call Hello API");
        return session.getId() + "\n" + "Hello";
    }

    @GetMapping("/world")
    public String world(HttpSession session) {
        logger.info("call World API");
        return session.getId() + "\n" + "World";
    }

    @GetMapping("/hello/{name}")
    public String hello(HttpSession session, @PathVariable String name) {
        logger.info("call Hello API 2");
        session.setAttribute("name", name);
        return "Hello " + name;

    }

    @GetMapping("/callApi")
    public ResponseEntity<String> callApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://127.0.0.1:8081/hello", String.class);
        logger.info("----------------------------------------");
        logger.info(response.getStatusCode().toString());
        logger.info(response.getBody());
        logger.info("----------------------------------------");
        return response;
    }

    @GetMapping("/callError")
    public String error() throws Exception {
        throw new Exception("Hello Exception");
    }
}
