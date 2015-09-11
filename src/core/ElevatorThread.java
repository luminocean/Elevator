package core;

import util.Log;

/**
 * 电梯主线程行为
 */
public class ElevatorThread implements Runnable{
    public static int TIME_INTERVAL = 1000;

    private Elevator ele;
    public ElevatorThread(Elevator elevator){ this.ele = elevator; }

    @Override
    public void run() {
        while(true){
            // 如果没有达到目标楼层,则进行移动
            if(ele.targetFloor != 0 && ele.currentFloor != ele.targetFloor){
                if(ele.currentFloor < ele.targetFloor) ele.currentFloor++;
                else ele.currentFloor--;

                ele.emit(ElevatorEvent.MOVING, ele.currentFloor);
            }else{
                ele.emit(ElevatorEvent.PENDING);
            }

            try {
                Thread.sleep(TIME_INTERVAL);
            } catch (InterruptedException e) {
                Log.error("电梯主线程被中断", e);
                break;
            }
        }
    }
}
