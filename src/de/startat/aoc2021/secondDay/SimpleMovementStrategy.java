package de.startat.aoc2021.secondDay;

public class SimpleMovementStrategy implements MovementStrategy{
    public void processOrder(Order order, Position p) {
        switch(order.getDirection()){
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
}
