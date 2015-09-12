import core.Elevator;
import core.Human;
import util.Watcher;

public class Main {
    public static void main(String[] argv) {
        // 创建电梯和乘客对象
        Elevator elevator = new Elevator();

        // 配置系统的监视器
        Watcher watcher = new Watcher();
        watcher.watch(elevator);

        // 启动电梯
        elevator.launch();

        // 乘客开始行动
        new Human()
                .setName("Tom")
                .setElevator(elevator)
                .setCurrentFloor(10)
                .setTargetFloor(1).go();

        // 3秒后
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Human()
                .setName("Rachel")
                .setElevator(elevator)
                .setCurrentFloor(8)
                .setTargetFloor(4).go();
    }
}