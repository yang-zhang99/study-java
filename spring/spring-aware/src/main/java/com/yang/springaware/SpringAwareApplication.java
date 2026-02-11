package com.yang.springaware;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SpringAwareApplication {

    public static void main(String[] args) throws Exception {
        System.out.println(SpringAwareApplication.generateTokenWithRS256("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDRQo08EHWlXD5aTQ2COG4LIa3an8+ER2dXugq2QfUERQeZpXeOBaLqhk1/U8EcRcIowGmoTyeS4d27g+2ma3ukGXZtYG2bedrVjSSKmvFNLDk4PESkS51d18hAC5mmEVptge9tHIgFtxvEle2IRmnazFjvbqEbNH+48z9vfeym85kjQeHHlk1DI16orvuCAIp1NHzDXo9HXjsyfv3CqDucf1HHg3hMyONyr3liIYqtJv4pWV9YzaCarnNKazwghXOL3UkFxQ1s82EnxkuwtxOwUk1+WPY7MLCLCXpTPlU0/hJVN7GxPuTQnvLfjSuTyhi5izp6TWWAdMyxrbd5IrIZAgMBAAECggEAEtY5anYjPcICjUeXkLWA4/87H1pfAfRMT2K2D72UnB0upI93Jjfhq3Gj5kAYbdgY6AKYc25bDJpiYFY6FuhgyXibs+RucwSfROi4qMI70guW3WlBCinevfgGCAxDReZH/2JaUBy1lELtL/jWPsi/3J89qwfu+Ej1U/IpleEXyj6ROpoxlm2yPMBSIRNVwbDs9yMIHAxKeLxHErh3WyNKcTo2n1X5V0hcj0lTdneJHrvFZRiw4WJ7mz+N27MIFYPWFdxRsNX/IeyrymKFET8KcbCYRPQxCEG47KLa/8Y7M+2k+7wrHX9IegGStkua0gbvLrmXBcqukvNZ70Yowf6BhQKBgQD0jGPAcdJv6hXNpeKtrpeE/Ks2gy/8PaLHETuzMt329CIVIiCwpcaEWYYrRs/ZCFtEn498v1JMs8VMJWwXvj0C0A20/hsVIuYibENKz9dhbIV+2Eqsit4rbC+zANzYApjLuEMCqhBFcRPu9sRgqnJ53VizOVC8uBjjsXvAyKrafwKBgQDbDyEoS2PmVkuA/6UVOKMCz/nLLmGwBjPAMzsPdWMT2fPmSPcHVdcYzmOsLVPqkLe5HmSPg327b1pG7s1RTJ2HHLf7iSyzZCzHbAI5ctjsRML+Q1cGoA0tRC3msc2uObW3Gb4MVN3XSYOShp8aZBkN50gN3RSHEpVyfW+BA8y3ZwKBgQCqvnLPZSlm1mwOv+n7FlHYmon4b7RLS8WhdBjYz4VG7cH2O3PTootu0QrqoHByunDvAvEuSm7t+v6gYeiM01jJ4/YODvVd1pYUJr79NwzMivbST7Yjrnncm5baRpnrgDSq2KaA77iYo5ElYcpPK/Ltv2w1Bqmmemt2Xu9m9TTFhQKBgQCdKzzB+V1izPV9R259TFaA1RFG+y8xqTGGK/uq+rEsdAsvZZh/ldIvRR10Uk1MFasL0ceaxF/4lEs0a2+6bfr0DuHeQixlRXFvX4o1B7alxiwVW+Fbqofpu2ong9irsVI0Xe7iUB2esmsJPTd4Kuti81baQ43P38CEFYVkDoLZ7wKBgFFMK92rbzGFFGYY2OiVirO0gOGlO6FY0E21gHAy8q877tnoT5wXXPoyTeHMHYr9HfsWuJGLal8cWLDOK8rbBuhxGpXkCrxzpa0dOIQZs8DK0n0Zo8nVySudQm3lcWGKrHex2kwBzyihhBX0h+YvOYY65JsbpX650a3U5Rv+ve23", "S018"
                , "HC06", "阙飞"));
    }



    public static String generateTokenWithRS256(String privateKey, String appCode, String userId, String userName) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        // 封装 jwt 的 head 部信息
        HashMap<String, Object> header = new HashMap<>();
        // 指定使用 RS256 非对称加密算法
        header.put("alg", SignatureAlgorithm.RS256.getValue());
        // 指定令牌类型
        header.put("typ", "JWT");
        // 封装 jwt 的 body 部分,这个信息很重要，必须有值且传输正确
        HashMap<String, Object> body = new HashMap<>();
        body.put("sub", appCode);
        body.put("exp", System.currentTimeMillis() + 10000);
        Map<String, Object> addition = new HashMap<>();
        addition.put("userId", userId);
        addition.put("userName", userName);
        body.put("addition", addition);
        // 生成 JWT 令牌
        String token = Jwts.builder()
                .setHeader(header)
                .setClaims(body)
                .signWith(SignatureAlgorithm.RS256, keyFactory.generatePrivate(keySpec)) // 使用私钥加密
                .compact();
        return token;
    }

}
