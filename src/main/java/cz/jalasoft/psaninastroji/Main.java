package cz.jalasoft.psaninastroji;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jan Lastovicka
 * @since 05/10/2019
 */
@SpringBootApplication
public class Main implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Ahoj");
    }
}
