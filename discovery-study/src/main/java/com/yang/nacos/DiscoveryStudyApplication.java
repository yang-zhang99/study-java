package com.yang.nacos;

import com.alibaba.nacos.api.remote.response.Response;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@RestController
public class DiscoveryStudyApplication {
    
    private static final Log LOG = LogFactory.getLog(DiscoveryStudyApplication.class);
    
    public static void main(String[] args) {
        SpringApplication.run(DiscoveryStudyApplication.class, args);
    }
    
    @GetMapping("/")
    public ResponseEntity<String> getDiscoveryStudy() throws InterruptedException {
        int minSleepTime = 10000;
        LOG.info("start: ");
        Thread.sleep(minSleepTime);
        LOG.info("end: ");
        return ResponseEntity.ok("OK");
    }
}
