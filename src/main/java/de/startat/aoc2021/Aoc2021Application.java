package de.startat.aoc2021;

import de.startat.aoc2021.solutions.FifthDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aoc2021Application implements CommandLineRunner {

    @Autowired
    FifthDay day;

    public static void main(String[] args) {
        SpringApplication.run(Aoc2021Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        day.run();
    }
}
