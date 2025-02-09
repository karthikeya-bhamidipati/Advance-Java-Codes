// the Bird class defines a fly() method before it is known that not all birds can fly
// penguin extends Bird but penguins can't fly, making the fly() method meaningless
// this means Penguin cannot be substituted for Bird without unexpected behavior
// this violates the Liskov Substitution Principle
// to fix this, the fly() method should be moved to a separate interface
// "GENERAL" Bird class without fly() method
abstract class Bird {
    private String name;

    public Bird(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public abstract void display();
}

// created a separate interface for flying birds
interface canFly {
    void fly();
}

// Sparrow is a flying bird so it extends Bird and IMPLEMENTS canFly
class Sparrow extends Bird implements canFly {
    public Sparrow(String name) {
        super(name);
    }

    public void fly() {
        System.out.println(getName() + " is flying!");
    }

    public void display() {
        System.out.println(getName() + " is a sparrow.");
    }
}

// Penguin is a non-flying bird so it only extends Bird
// it does not implement canFly
// now Penguin doesn't inherit an irrelevant behavior
// making it a valid Bird following LSP.
class Penguin extends Bird {
    public Penguin(String name) {
        super(name);
    }

    public void display() {
        System.out.println(getName() + " is a penguin and cannot fly.");
    }
}

public class BirdLiskovViolation {
    public static void main(String[] args) {
        // creating objects of Sparrow and Penguin
        Bird Kiku = new Sparrow("Kiku");
        Bird Piku = new Penguin("Piku");

        // since kiku is a sparrow, a flying bird, it can fly
        Kiku.display();
        ((canFly) Kiku).fly();
        
        // since piku is a penguin, a non-flying bird, it cannot fly
        Piku.display();
    }
}
