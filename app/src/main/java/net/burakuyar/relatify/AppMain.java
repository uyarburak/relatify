package net.burakuyar.relatify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans(value = { @ComponentScan("net.burakuyar.relatify.dao"), @ComponentScan("net.burakuyar.relatify.service") })
public class AppMain{
    public static void main(String args[]){
        SpringApplication.run(AppMain.class, args);
    }
}