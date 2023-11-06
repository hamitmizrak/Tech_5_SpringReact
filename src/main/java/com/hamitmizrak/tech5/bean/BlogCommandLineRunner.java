package com.hamitmizrak.tech5.bean;

import com.hamitmizrak.tech5.business.services.IRegisterServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// LOMBOK
@RequiredArgsConstructor

@Configuration
@Log4j2
public class BlogCommandLineRunner {

    // INJECTION
    // 1.YOL (Field Injection)
    // @AutoWired
    // private  IRegisterServices iRegisterServices;

    // 2.YOL (Constructor Injection)
    /*
    private  IRegisterServices iRegisterServices;
    @Autowired
    public BlogCommandLineRunner(IRegisterServices iRegisterServices) {
        this.iRegisterServices = iRegisterServices;
    }
    */

    // 3.YOL (Lombok Constructor Injection)
    // private final  IRegisterServices iRegisterServices;

    // FIRST
    public void blogCommandLineRunnerAfterBeanMethod(){
        log.info("blog CommandLineRunner After Bean Method başladı");
        System.out.println("blog CommandLineRunner After Bean Method başladı");
    }

    // Injection
    @Bean
    public CommandLineRunner blogCommandLineRunnerMethod(IRegisterServices iRegisterServices) { // 4.YOL (Parameter Injecion
        // Lambda Expression
        return args -> {
            System.out.println("CommandLineRunner Çalıştı");
            log.info("CommandLineRunner Çalıştı");
           // iRegisterServices.registerServiceSpeedData(5L);
        };
    }

    //LAST
    public void blogCommandLineRunnerBeforeBeanMethod(){
        log.info("blog CommandLineRunner Before Bean Method bitti");
        System.out.println("blog Command Line Runner Befdre Bean Method bitti");
    }

} //end class
