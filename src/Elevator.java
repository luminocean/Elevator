import base.Callback;
import base.EventEmitter;

import java.util.LinkedList;
import java.util.List;

/**
 * 电梯类
 */
public class Elevator extends EventEmitter{
    // 覆盖父类方法
    public <T> void emit(ElevatorEvent type, T... data) {
        super.emit(type, data);
    }

    // 覆盖父类方法
    public void on(ElevatorEvent type, Callback callback) {
        super.on(type, callback);
    }

    private List<OuterRequest> outerRequests = new LinkedList<>();

    /**
     * 电梯启动
     */
    public void launch() {
        this.emit(ElevatorEvent.LAUNCH);
    }

    /**
     * 电梯按钮被按下执行方法
     * @param direction 按下的方向
     * @param floorLevel 当前所在的楼层
     * @param presser 按下按钮的人
     * @return 自身,实现链式调用
     */
    public Elevator buttonPressed(Direction direction, int floorLevel, Human presser) {
        // 构造成list转发请求
        List<Human> list = new LinkedList<>();
        list.add(presser);
        return buttonPressed(direction, floorLevel, list);
    }

    /**
     * 电梯按钮被按下执行方法
     * @param direction 按下的方向
     * @param floorLevel 当前所在的楼层
     * @param pressers 按下按钮的人的列表
     * @return 自身,实现链式调用
     */
    public Elevator buttonPressed(Direction direction, int floorLevel, List<Human> pressers) {
        OuterRequest req = new OuterRequest()
                .setDirection(direction)
                .setFloorLevel(floorLevel)
                .setPressers(pressers);

        outerRequests.add(req);

        this.emit(ElevatorEvent.OUTER_PRESSED, req);
        return this;
    }
}
