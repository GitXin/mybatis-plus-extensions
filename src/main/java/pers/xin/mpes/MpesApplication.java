package pers.xin.mpes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import pers.xin.mpes.handler.EncryptTypeHandler;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class MpesApplication {

    @Autowired
    private Environment environment;

    @PostConstruct
    void started() {
        EncryptTypeHandler.desKey = environment.getProperty("mpes.des-key");
    }

    public static void main(String[] args) {
        SpringApplication.run(MpesApplication.class, args);
    }

}
