package model;

public class Employee extends Villain {

    public Employee() {
        super("Employee", 100, null, null);
    }

    @Override
    public void attack() {
        if (!isAlive()) return;
        System.out.println(getName() + " versucht, die Sicherheitskräfte zu alarmieren!");
    }

    @Override
    public void presentPuzzle() {
        System.out.println(getName() + " hat kein Rätsel. Besiege ihn, um weiterzukommen!");
    }
}
