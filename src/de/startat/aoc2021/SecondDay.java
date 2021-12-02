package de.startat.aoc2021;

import de.startat.aoc2021.secondDay.*;

import java.util.List;
import java.util.stream.Collectors;

public class SecondDay {

    public static void main(String[] args) throws Exception{
        List<Order> orders = AOCUtil.getFileLines("SecondDay_Data1.txt").stream().map(Order::new).collect(Collectors.toList());
        new SecondDay(new SimpleMovementStrategy()).processOrders(orders);
        new SecondDay(new ExtendedMovementStrategy()).processOrders(orders);
    }

    MovementStrategy movementStrategy;
    public SecondDay(MovementStrategy movementStrategy){
        this.movementStrategy = movementStrategy;
    }

    public void processOrders(List<Order> orders){
        Position p = movementStrategy.processOrders(orders);
        System.out.println(p);
        System.out.println(p.getVerticalPosition() * p.getDepth());
    }
}
