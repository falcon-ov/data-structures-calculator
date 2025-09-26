package org.example;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example")
@PWA(name = "Vaadin Calculator", shortName = "Calc")
public class Main implements AppShellConfigurator {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
