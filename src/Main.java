import base.EventEmitter;
import base.EventType;

public class Main {
    public static void main(String[] argv) {
        EventEmitter eventEmitter = new EventEmitter();

        eventEmitter.on(EventType.BEGIN, System.out::println);
        eventEmitter.emit(EventType.BEGIN, "Hello");
    }
}