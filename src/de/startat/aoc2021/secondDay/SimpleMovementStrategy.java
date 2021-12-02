package de.startat.aoc2021.secondDay;

import java.util.List;

public class SimpleMovementStrategy implements MovementStrategy{
    public Position processOrders(List<Order> orders) {
        Position p = new Position();

        for(Order order : orders) {
            switch (order.getDirection()) {
                case "up":
                    p.changeDepth(-order.getDistance());
                    break;
                case "down":
                    p.changeDepth(order.getDistance());
                    break;
                case "forward":
                    p.moveVertically(order.getDistance());
                    break;
            }
        }
        return p;
    }
}
