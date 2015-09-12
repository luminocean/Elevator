package core;

import event.Callback;
import event.EventEmitter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 电梯类
 */
public class Elevator extends EventEmitter{
    // 外部电梯请求列表
    private List<OuterRequest> outerRequests = new LinkedList<>();
    // 电梯状态值
    ElevatorStatus status = new ElevatorStatus();

    /**
     * 电梯启动
     */
    public void launch() {
        // 启动电梯主线程
        new Thread(new ElevatorThread(this)).start();
        this.emit(ElevatorEvent.LAUNCH, status.currentFloor);

        // 当前外部请求已经完成,移除
        this.on(ElevatorEvent.OPEN, data -> outerRequests.remove(0));

        // 关门事件,执行下一个请求
        this.on(ElevatorEvent.CLOSE, data -> updateTarget());
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
        updateTarget();

        return this;
    }

    /**
     * 更新电梯的移动目标
     * 间接触发电梯移动
     */
    private void updateTarget(){
        // 首先要处理的请求,之后给出具体的值
        OuterRequest first;
        // 将请求列表排序,如果电梯正在向上,则根据楼层高度倒序排列,优先处理最高楼层
        // 反之亦然
        sort(outerRequests, status.direction == Direction.DOWN);
        if(outerRequests.size() == 0) return;

        first = outerRequests.get(0);
        // 设定目标楼层
        status.targetFloor = first.getFloorLevel();
    }

    /**
     * 将外部请求按照请求楼层高低排序
     * @param collection 要排序的集合
     * @param lowToHigh 是否从低到高(false为从高到低)
     */
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

    // 覆盖父类方法
    public <T> void emit(ElevatorEvent type, T... data) {
        super.emit(type, data);
    }
    // 覆盖父类方法
    public void on(ElevatorEvent type, Callback callback) {
        super.on(type, callback);
    }
}
