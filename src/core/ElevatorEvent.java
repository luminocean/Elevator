package core;

import base.EventType;

/**
 * 电梯事件类型
 */
public class ElevatorEvent extends EventType {
    public static ElevatorEvent stub = new ElevatorEvent();
    public static ElevatorEvent LAUNCH, STOP, OUTER_PRESSED = stub;
}
