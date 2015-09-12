# Elevator simulator

This is a simulator which simulates typical behaviors of a simple elevator.

### What's new in it?

In this simple Java program, I introduced an **event-driven** programming flavor which is widely used in Node.js but not quite common in Java except GUI programming (at least to me).

In this program, The Elevator class is loosely connected with other parts of the system based on publish/subscribe of events.
Here is some sample Java Code:

```java
// In Driver class
Elevator elevator = new Elevator();

elevator.on(ElevatorEvents.ARRIVE, data -> {
    int floor = (Integer)data;
    System.out.println("The elevator is now on floor " + floor);
});

// In Elevator class
this.emit(ElevatorEvents.ARRIVE, 5);
```

Which looks exactly like what we do in Node.js(implicitly using events module):
```javascript
// In driver script
Elevator elevator = new Elevator();

elevator.on('arrive', function(data){
    console.log("The elevator is now on floor " + data);
});

// In Elevator class
this.emit('arrive', 5);
```

Back to the Java version.
The Elevator class extends from EventEmitter class, thus it has event-related functionalities.

Once the elevator emits an event (with optional data), all the callbacks of its listeners will be executed.
And the best news is, any type of the event data can be passed into the emit method.
All you need is a little type convert when you use the data.

With the event-driven style, writing asynchronized programs like elevator simulator seems a little bit easier.

### Why did you do this?

To be frankly... I have no idea...


