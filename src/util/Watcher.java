package util;

import core.Direction;
import core.Elevator;
import core.ElevatorEvent;
import core.OuterRequest;

import java.text.MessageFormat;

/**
 * 系统监视器
 */
public class Watcher{
    public void watch(Elevator elevator){
        // 启动
        elevator.on(ElevatorEvent.LAUNCH, data -> {
            Log.info("电梯电源启动");
        });

        // 外部按电梯按钮
        elevator.on(ElevatorEvent.OUTER_PRESSED, data -> {
            OuterRequest req = (OuterRequest)data;
            String msg = MessageFormat.format("{0}层有{1}人要{2}楼",
                    req.getFloorLevel(), req.getPressers().size(),
                    req.getDirection() == Direction.UP ? "上" : "下");
            Log.info(msg);
        });
    }
}
