package de.startat.aoc2021.secondDay;

public class ExtendedMovementStrategy implements MovementStrategy{
    public void processOrder(Order order, Position p) {
        switch(order.getDirection()){
            case "up":
                p.decreaseAim(order.getDistance());
                break;
            case "down":
                p.increaseAim(order.getDistance());
                break;
            case "forward":
                p.moveVertically(order.getDistance());
                p.changeDepth(p.getAim()* order.getDistance());
                break;
        }
    }
}
