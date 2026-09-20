public class Bird {
    public String species;
    private boolean canFly;

    public Bird() {
        this.species = "Unknown Bird";
        this.canFly = true;
    }

    public Bird(String species, boolean canFly) {
        this.species = species;
        this.canFly = canFly;
    }

    public void fly() {
        System.out.println("The bird is flying.");
    }

    private void sleep() {
        System.out.println("The bird is sleeping.");
    }
}
