package com.swd.springlearn;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author RDKJ_PC
 */
@RestController
@SpringBootApplication
public class SpringlearnApplicationOld {

    public static void main(String[] args) {
        SpringApplication.run(SpringlearnApplicationOld.class, args);
    }
    @GetMapping
    public String demo1(){
        return "hello word";
    }
    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
        return args -> {
            System.out.printf("来看看springBoot默认为我们提供的Bean:");
            String[] beanNames =  ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            Arrays.stream(beanNames).forEach(System.out::println);
        };
    }
}

