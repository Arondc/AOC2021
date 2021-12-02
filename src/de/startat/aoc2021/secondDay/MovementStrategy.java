package de.startat.aoc2021.secondDay;

import java.util.List;

public interface MovementStrategy {
    Position processOrders(List<Order> orders);
}
