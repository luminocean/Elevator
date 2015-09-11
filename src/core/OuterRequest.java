package core;

import java.util.List;

/**
 * 外部电梯请求
 * 每次在电梯外部按下电梯按钮就是一个请求
 */
public class OuterRequest {
    private Direction direction;
    private int floorLevel;
    private List<Human> pressers;

    public Direction getDirection() {
        return direction;
    }

    public OuterRequest setDirection(Direction direction) {
        this.direction = direction;
        return this;
    }

    public int getFloorLevel() {
        return floorLevel;
    }

    public OuterRequest setFloorLevel(int floorLevel) {
        this.floorLevel = floorLevel;
        return this;
    }

    public List<Human> getPressers() {
        return pressers;
    }

    public OuterRequest setPressers(List<Human> pressers) {
        this.pressers = pressers;
        return this;
    }
}
