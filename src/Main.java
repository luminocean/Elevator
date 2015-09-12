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
                .setCurrentFloor(3)
                .setTargetFloor(8).go();

        // 暂停
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Human()
                .setName("Rachel")
                .setElevator(elevator)
                .setCurrentFloor(5)
                .setTargetFloor(1).go();
    }
}