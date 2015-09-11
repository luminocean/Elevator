import java.text.MessageFormat;

public class Main {
    public static void main(String[] argv) {
        // 创建电梯和乘客对象
        Elevator elevator = new Elevator();
        Human human = new Human();

        // 配置系统的监视器
        Watcher watcher = new Watcher();
        watcher.watch(elevator);

        // 启动电梯
        elevator.launch();

        // 按下电梯按钮
        elevator.buttonPressed(Direction.DOWN, 8, human);
    }
}

// 系统监视器
class Watcher{
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
                    req.getDirection()==Direction.UP?"上":"下");
            Log.info(msg);
        });
    }
}