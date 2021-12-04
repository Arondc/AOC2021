package de.startat.aoc2021.solutions;

import de.startat.aoc2021.solutions.secondDay.*;
import lombok.extern.java.Log;

import java.util.List;
import java.util.stream.Collectors;

@Log
public class SecondDay {

    public void run() throws Exception{
        List<Order> orders = AOCUtil.getFileLines("SecondDay_Data1.txt").stream().map(Order::new).collect(Collectors.toList());
        processOrders(orders);
    }

    MovementStrategy movementStrategy;
    public SecondDay(MovementStrategy movementStrategy){
        this.movementStrategy = movementStrategy;
    }

    public void processOrders(List<Order> orders){
        Position p = movementStrategy.processOrders(orders);
        log.info(p.toString());
        log.info(""+(p.getVerticalPosition() * p.getDepth()));
    }
}
