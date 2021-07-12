package designpatterns.behavioral;

public class TemplateMethod {
    abstract class Game {
        abstract void initialize();
        abstract void startPlay();
        abstract void endPlay();

        public final void play(){
            initialize();
            startPlay();
            endPlay();
        }
    }

    class Cricket extends Game {

        @Override
        void endPlay() {
            System.out.println("Cricket Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Cricket Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Cricket Game Started. Enjoy the game!");
        }
    }

    class Football extends Game {

        @Override
        void endPlay() {
            System.out.println("Football Game Finished!");
        }

        @Override
        void initialize() {
            System.out.println("Football Game Initialized! Start playing.");
        }

        @Override
        void startPlay() {
            System.out.println("Football Game Started. Enjoy the game!");
        }
    }

    void templateDemo(){
        Game game = new Cricket();
        game.play();
        System.out.println();
        game = new Football();
        game.play();
    }

    public static void main(String[] args) {
        TemplateMethod templateMethod = new TemplateMethod();
        templateMethod.templateDemo();
    }
}
