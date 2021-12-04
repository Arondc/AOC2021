package de.startat.aoc2021.solutions.secondDay;

import java.util.List;

public interface MovementStrategy {
    Position processOrders(List<Order> orders);
}
