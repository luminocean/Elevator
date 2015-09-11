package core;

import event.Callback;
import event.EventEmitter;
import util.Log;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 电梯类
 */
public class Elevator extends EventEmitter{
    // 外部电梯请求列表
    private List<OuterRequest> outerRequests = new LinkedList<>();
    // 电梯初始状态设置
    int currentFloor = 0;
    Direction direction = Direction.UP;
    int targetFloor = 0; // 要移动到的目标楼层

    // 覆盖父类方法
    public <T> void emit(ElevatorEvent type, T... data) {
        super.emit(type, data);
    }
    // 覆盖父类方法
    public void on(ElevatorEvent type, Callback callback) {
        super.on(type, callback);
    }

    /**
     * 电梯启动
     */
    public void launch() {
        // 启动电梯主线程
        new Thread(new ElevatorThread(this)).start();
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
        // 构造外部按钮请求
        OuterRequest req = new OuterRequest()
                .setDirection(direction)
                .setFloorLevel(floorLevel)
                .setPressers(pressers);
        outerRequests.add(req);

        // 触发事件
        this.emit(ElevatorEvent.OUTER_PRESSED, req);

        move();

        return this;
    }

    /**
     * 电梯启动
     */
    private void move(){
        // 首先要处理的请求,之后给出具体的值
        OuterRequest first;
        // 将请求列表排序,如果电梯正在向上,则根据楼层高度倒序排列,优先处理最高楼层
        // 反之亦然
        sort(outerRequests, direction == Direction.DOWN);
        first = outerRequests.get(0);
        if(first == null) return;

        // 设定目标楼层
        targetFloor = first.getFloorLevel();
    }

    private void sort(List<OuterRequest> collection, boolean lowToHigh){
        if(lowToHigh)
            Collections.sort(collection,
                (lhs, rhs) -> new Integer(lhs.getFloorLevel())
                        .compareTo(rhs.getFloorLevel()));
        else
            Collections.sort(collection,
                    (lhs, rhs) -> new Integer(lhs.getFloorLevel())
                            .compareTo(rhs.getFloorLevel())*(-1));
    }
}
