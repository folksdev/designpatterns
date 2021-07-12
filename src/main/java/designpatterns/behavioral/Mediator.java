package designpatterns.behavioral;

import java.util.HashMap;
import java.util.Map;

public class Mediator {

    interface Dispatcher{
        void dispatch(String topic, String message);
    }

    interface Actor{
        void receiveMessage(String message);

        void sendMessage(String topic, String message);
    }

    class MessageDispatcher implements Dispatcher{
        Map<String, Actor> registeredActors = new HashMap<>();

        void register(String topic, Actor actor){
            registeredActors.put(topic, actor);
        }

        @Override
        public void dispatch(String topic, String message) {
            Actor actor = registeredActors.get(topic);
            if(actor == null){
                System.out.println("No actor registered for this topic");
            }else{
                actor.receiveMessage(message);
            }
        }
    }

    class MessageActor implements Actor{
        String name;
        Dispatcher dispatcher;

        public MessageActor(String name, Dispatcher dispatcher) {
            this.name = name;
            this.dispatcher = dispatcher;
        }

        @Override
        public void receiveMessage(String message) {
            System.out.println(name + " received message: " + message);
        }

        @Override
        public void sendMessage(String topic, String message) {
            dispatcher.dispatch(topic, message);
        }
    }

    void mediatorDemo(){
        MessageDispatcher dispatcher = new MessageDispatcher();

        Actor actor1 = new MessageActor("Actor 1", dispatcher);
        Actor actor2 = new MessageActor("Actor 2", dispatcher);
        Actor actor3 = new MessageActor("Actor 3", dispatcher);
        Actor actor4 = new MessageActor("Actor 4", dispatcher);

        dispatcher.register("topic1", actor1);
        dispatcher.register("topic2", actor2);
        dispatcher.register("topic3", actor3);
        dispatcher.register("topic4", actor4);

        actor1.sendMessage("topic2", "Message for topic 2");
        actor1.sendMessage("topic3", "Message for topic 3");
        actor1.sendMessage("topic4", "Message for topic 4");

        actor2.sendMessage("topic1", "Message for topic 1");
        actor2.sendMessage("topic5", "Message for topic 5");
    }

    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        mediator.mediatorDemo();
    }
}
