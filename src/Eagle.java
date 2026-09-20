public class Eagle extends Bird {
    private String eagleName;
    public int wingspan;

    public Eagle() {
        super("Eagle", true);
        this.eagleName = "Default Eagle";
        this.wingspan = 200;
    }

    public Eagle(String eagleName, int wingspan) {
        super("Eagle", true);
        this.eagleName = eagleName;
        this.wingspan = wingspan;
    }

    @Override
    public void fly() {
        System.out.println(eagleName + " soars high in the sky with a wingspan of " + wingspan + "cm.");
    }

    public void hunt(String prey) {
        System.out.println(eagleName + " is hunting " + prey + ".");
    }

    private void nest() {
        System.out.println(eagleName + " is resting in its high mountain nest.");
    }
}
