package sprint_8;

public class Test implements  Rabbit {


    @Override
    public void eat(Plant plant) {

    }
}

interface Animal {

}
interface Herbivore {
    void eat(Plant plant);
}
interface Carnivore {
    void eat(Herbivore herbivore);
    void eat(Animal animal);
}
interface Plant {

}

interface Wolf extends Animal, Carnivore {

}

interface Rabbit extends Animal, Herbivore {


}

interface Ape extends Animal, Herbivore, Carnivore {

}
