package de.startat.aoc2021;

import de.startat.aoc2021.solutions.SecondDay;
import de.startat.aoc2021.solutions.secondDay.ExtendedMovementStrategy;
import de.startat.aoc2021.solutions.secondDay.SimpleMovementStrategy;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Aoc2021Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Aoc2021Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new SecondDay(new SimpleMovementStrategy()).run();
		new SecondDay(new ExtendedMovementStrategy()).run();
	}
}
