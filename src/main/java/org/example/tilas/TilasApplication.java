package org.example.tilas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
//开启了对Servlet组件的支持
@SpringBootApplication
@ServletComponentScan
public class TilasApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasApplication.class, args);
    }

}
