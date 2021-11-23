package ast.adrs.vo.MainAuxilaries;

public class DModel_AnimalPopulation {
    private String name;
    private int totalAnimals;
    private int sickAnimals;
    private int deadAnimals;

    public DModel_AnimalPopulation() {

    }

    public DModel_AnimalPopulation(String name, int totalAnimals, int sickAnimals, int deadAnimals) {
        this.name = name;
        this.totalAnimals = totalAnimals;
        this.sickAnimals = sickAnimals;
        this.deadAnimals = deadAnimals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalAnimals() {
        return totalAnimals;
    }

    public void setTotalAnimals(int totalAnimals) {
        this.totalAnimals = totalAnimals;
    }

    public int getSickAnimals() {
        return sickAnimals;
    }

    public void setSickAnimals(int sickAnimals) {
        this.sickAnimals = sickAnimals;
    }

    public int getDeadAnimals() {
        return deadAnimals;
    }

    public void setDeadAnimals(int deadAnimals) {
        this.deadAnimals = deadAnimals;
    }
}
