package de.startat.aoc2021.solutions;

import de.startat.aoc2021.solutions.secondDay.*;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Log
@Component
public class SecondDay {

    @Autowired
    FileReadService fileReadService;

    public void run() throws Exception{
        List<Order> orders = fileReadService.getFileLines("SecondDay_Data1.txt").stream().map(Order::new).collect(Collectors.toList());
        processOrders(orders);
    }

    private MovementStrategy movementStrategy;

    public void setMovementStrategy(MovementStrategy movementStrategy) {
        this.movementStrategy = movementStrategy;
    }

    public void processOrders(List<Order> orders){
        Position p = movementStrategy.processOrders(orders);
        log.info(p.toString());
        log.info(""+(p.getVerticalPosition() * p.getDepth()));
    }
}
