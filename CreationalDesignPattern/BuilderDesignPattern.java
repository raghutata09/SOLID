package CreationalDesignPattern;

public class BuilderDesignPattern {
   // https://gitlab.com/shrayansh8/interviewcodingpractise/-/tree/main/src/LowLevelDesign/DesignPatterns/BuilderDesignPattern
   public static void main(String[] args) {
       ComputerBuilder desktopBuilder = new DesktopComputerBuilder();
       ComputerDirector director = new ComputerDirector(desktopBuilder);
       Computer desktop = director.constructComputer();

       // Access the constructed Computer object
       System.out.println(desktop);
   }
}
public class Computer {
    private String processor;
    private int memory;
    private int storage;
    private String graphicsCard;

    // Constructor and getters
}

public interface ComputerBuilder {
    ComputerBuilder buildProcessor(String processor);
    ComputerBuilder buildMemory(int memory);
    ComputerBuilder buildStorage(int storage);
    ComputerBuilder buildGraphicsCard(String graphicsCard);
    Computer build();
}

public class DesktopComputerBuilder implements ComputerBuilder {
    private Computer computer;

    public DesktopComputerBuilder() {
        this.computer = new Computer();
    }

    @Override
    public ComputerBuilder buildProcessor(String processor) {
        computer.setProcessor(processor);
        return this;
    }

    @Override
    public ComputerBuilder buildMemory(int memory) {
        computer.setMemory(memory);
        return this;
    }

    @Override
    public ComputerBuilder buildStorage(int storage) {
        computer.setStorage(storage);
        return this;
    }

    @Override
    public ComputerBuilder buildGraphicsCard(String graphicsCard) {
        computer.setGraphicsCard(graphicsCard);
        return this;
    }

    @Override
    public Computer build() {
        return computer;
    }
}

public class ComputerDirector {
    private ComputerBuilder computerBuilder;

    public ComputerDirector(ComputerBuilder computerBuilder) {
        this.computerBuilder = computerBuilder;
    }

    public Computer constructComputer() {
        return computerBuilder
                .buildProcessor("Intel i7")
                .buildMemory(16)
                .buildStorage(512)
                .buildGraphicsCard("NVIDIA GTX 1660")
                .build();
    }
}