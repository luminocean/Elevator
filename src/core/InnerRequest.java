package core;

/**
 * 电梯内部请求
 */
public class InnerRequest extends Request{
    private int targetFloor;
    private Human presser;

    public int getTargetFloor() {
        return targetFloor;
    }

    public InnerRequest setTargetFloor(int targetFloor) {
        this.targetFloor = targetFloor;
        this.stopFloor = targetFloor;
        return this;
    }

    public Human getPresser() {
        return presser;
    }

    public InnerRequest setPresser(Human presser) {
        this.presser = presser;
        return this;
    }
}
