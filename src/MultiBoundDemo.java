// A superclass
class Animal {
    public void eat() {
        System.out.println("Animal is eating...");
    }
}

// Interface 1
interface Playable {
    void play();
}

// Interface n (another interface)
interface SoundMaker {
    void makeSound();
}

// A specific class that meets all requirements
class Dog extends Animal implements Playable, SoundMaker {
    public void play() {
        System.out.println("Dog is playing fetch.");
    }
    public void makeSound() {
        System.out.println("Dog says: Woof!");
    }
}

public class MultiBoundDemo {

    /**
     * This is a Multi-Bound Generic Method.
     * The type parameter T is restricted (bounded) to:
     * 1. Must extend Animal (Class - must come first)
     * 2. Must implement Playable (Interface)
     * 3. Must implement SoundMaker (Interface)
     */
    public <T extends Animal & Playable & SoundMaker> void trainAnimal(T creature) {
        System.out.println("Training begins...");
        
        // We can call methods from the Class
        creature.eat();
        
        // We can call methods from Interface 1
        creature.play();
        
        // We can call methods from Interface n
        creature.makeSound();
    }

    public static void main(String[] args) {
        MultiBoundDemo demo = new MultiBoundDemo();
        Dog myDog = new Dog();

        System.out.println("--- Running Multi-Bound Generic Method ---");
        // This works because Dog extends Animal and implements both interfaces
        demo.trainAnimal(myDog);

        /* 
           If we had a 'Cat' that only extended Animal but didn't implement Playable,
           the compiler would throw an error if we tried demo.trainAnimal(myCat).
        */
    }
}
