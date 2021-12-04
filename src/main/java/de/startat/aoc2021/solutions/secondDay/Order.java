package de.startat.aoc2021.solutions.secondDay;

public class Order{
        String direction;
        int distance;

    public String getDirection() {
        return direction;
    }

    public int getDistance() {
        return distance;
    }

    public Order(String orderLine){
            this.direction = orderLine.split(" ")[0];
            this.distance = Integer.parseInt(orderLine.split(" ")[1]);
        }


    }