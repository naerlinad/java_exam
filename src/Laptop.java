import java.util.Objects;

public class Laptop {

    private String brand;
    private int ram;
    private int storage;
    private String cpu;
    private String gpu;
    private double displaySize;
    private String displayMatrix;
    private String gaming;
    private String os;
    private double price;

    public Laptop(String brand, int ram, int storage, String cpu, String gpu,
                  double displaySize, String displayMatrix, String gaming, String os, double price) {

        this.brand = brand;
        this.ram = ram;
        this.storage = storage;
        this.cpu = cpu;
        this.gpu = gpu;
        this.displaySize = displaySize;
        this.displayMatrix = displayMatrix;
        this.gaming = gaming;
        this.os = os;
        this.price = price;
    }

    public Object get(int k) {
        return switch (k) {
            case 1 -> brand;
            case 2 -> ram;
            case 3 -> storage;
            case 4 -> cpu;
            case 5 -> gpu;
            case 6 -> displaySize;
            case 7 -> displayMatrix;
            case 8 -> gaming;
            case 9 -> os;
            case 10 -> price;
            default -> null;
        };
    }

    public void set(int k, Object o) {
        switch (k) {
            case 1 -> this.brand = (String) o;
            case 2 -> this.ram = (int) o;
            case 3 -> this.storage = (int) o;
            case 4 -> this.cpu = (String) o;
            case 5 -> this.gpu = (String) o;
            case 6 -> this.displaySize = (double) o;
            case 7 -> this.displayMatrix = (String) o;
            case 8 -> this.gaming = (String) o;
            case 9 -> this.os = (String) o;
            case 10 -> this.price = (double) o;
        }
    }

    @Override
    public String toString() {
        return String.format("Brand: %s%nRAM: %d GB%nStorage: %d GB%nCPU: %s%nGPU: %s%n" +
                        "Display Size: %.1f%nDisplayMatrix: %s%nGaming: %s%nOperating System: %s%nPrice: %.2f RUB",
                brand, ram, storage, cpu, gpu, displaySize, displayMatrix, gaming, os, price);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Laptop lp = (Laptop) obj;
        return brand.equals(lp.brand) && ram == lp.ram && storage == lp.storage
                && cpu.equals(lp.cpu) && gpu.equals(lp.gpu) && displaySize == lp.displaySize
                && displayMatrix.equals(lp.displayMatrix) && gaming.equals(lp.gaming) && os.equals(lp.os)
                && price == lp.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, ram, storage, cpu, gpu, displaySize, displayMatrix,
                displayMatrix, gaming, os, price);
    }
}
