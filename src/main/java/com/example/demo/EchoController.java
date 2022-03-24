package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.UUID;

@RestController
public class EchoController {

    @GetMapping("/echo/{input}")
    public String echo(@PathVariable String input) {
        return "Response:" + input;
    }

    @GetMapping("/stress")
    public String stress() {
        var start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            encryptThisString(UUID.randomUUID().toString());
        }
        return "" + (System.currentTimeMillis() - start);
    }

    @GetMapping("/health")
    public String health() {
        return "OK2";
    }

    public static void encryptThisString(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            StringBuilder hash = new StringBuilder(no.toString(16));
            while (hash.length() < 32) {
                hash.insert(0, "0");
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
