package de.startat.aoc2021.solutions.secondDay;

import java.util.List;

public class ExtendedMovementStrategy implements MovementStrategy{
    public Position processOrders(List<Order> orders) {
        ExtendedPosition p = new ExtendedPosition();

        for(Order order : orders) {
            switch (order.getDirection()) {
                case "up":
                    p.decreaseAim(order.getDistance());
                    break;
                case "down":
                    p.increaseAim(order.getDistance());
                    break;
                case "forward":
                    p.moveVertically(order.getDistance());
                    p.changeDepth(p.getAim() * order.getDistance());
                    break;
            }
        }
        return p;
    }
}
