import core.Direction;
import core.Elevator;
import core.Human;
import util.Watcher;

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

        // 按下电梯按钮
        elevator.buttonPressed(Direction.DOWN, 4, human);

        // 按下电梯按钮
        elevator.buttonPressed(Direction.DOWN, 2, human);
    }
}