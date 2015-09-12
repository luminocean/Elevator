package core;

import event.EventEmitter;
import util.Log;

/**
 * 电梯乘客类
 */
public class Human extends EventEmitter{
    private int currentFloor = 0;
    private int targetFloor = 0;
    private int weight = 0;
    private Elevator elevator;

    /**
     * 让该乘客在外部按下电梯按钮
     */
    public Human go(){
        elevator.buttonPressed(currentFloor>targetFloor?Direction.DOWN:Direction.UP,
                currentFloor, this);

        return this;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public Human setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
        return this;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    public Human setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
        return this;
    }

    public int getWeight() {
        return weight;
    }

    public Human setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public Human setElevator(Elevator elevator) {
        this.elevator = elevator;

        elevator.on(ElevatorEvent.OPEN, data -> {
            int openFloor = (Integer)data;

            // 如果电梯停在了自己的楼层
            if( openFloor == targetFloor ){
                Log.info("Yeah!");
            }
        });

        return this;
    }
}
