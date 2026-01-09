package com.base.java;

import jakarta.annotation.Resource;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Base64;

@SpringBootApplication
public class JavaBaseApplication implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(JavaBaseApplication.class);
    
    @Resource
    private ClassI classI;
    
    public static void main(String[] args) {
        SpringApplication.run(JavaBaseApplication.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        try (FileInputStream inputStream = new FileInputStream("/Users/yang99/Desktop/Report.pdf")) {
            String a = JavaBaseApplication.inputStreamToBase64(inputStream);
            JavaBaseApplication.getTotalPagesFromInputStream(a);
        }
    }
    
    public static String inputStreamToBase64(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int bytesRead;
            
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] bytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            return null;
        } finally {
            outputStream.close();
            inputStream.close();
        }
        
    }
    
    public static int getTotalPagesFromInputStream(String base64PdfString) {
        try {
            
            byte[] pdfBytes = Base64.getDecoder().decode(base64PdfString);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(pdfBytes);
            
            PDDocument document = PDDocument.load(inputStream);
            int totalPages = document.getNumberOfPages();
            System.out.println("Total number of pages: " + totalPages);
            
            document.close();
            inputStream.close();
            return totalPages;
        } catch (Exception e) {
            logger.error("获取文件总页数失败，", e);
            return -1;
        }
    }
    
    
}
