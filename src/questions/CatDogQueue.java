package questions;

import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 *猫狗队列
 */
public class CatDogQueue {

    private Queue<Pair<Integer, Dog>> dogs = new LinkedList<>();
    private Queue<Pair<Integer, Cat>> cats = new LinkedList<>();
    private Integer count = 0;

    public class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getPetType() {
            return this.type;
        }
    }

    public class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public Pet pollAll() {
        if (isAllEmpty()) throw new RuntimeException("Queue Empty.");
        else if (isDogEmpty() && !isCatEmpty()) return cats.poll().getValue();
        else if (!isDogEmpty() && isCatEmpty()) return dogs.poll().getValue();
        return dogs.peek().getKey() < cats.peek().getKey() ?
                dogs.poll().getValue() : cats.poll().getValue();
    }

    public Dog pollDog() {
        return dogs.poll().getValue();
    }

    public Cat pollCat() {
        return cats.poll().getValue();
    }

    public boolean isAllEmpty() {
        return dogs.isEmpty() && cats.isEmpty();
    }

    public boolean isDogEmpty() {
        return dogs.isEmpty();
    }

    public boolean isCatEmpty() {
        return cats.isEmpty();
    }

    public void add(Pet pet) {
        if (pet instanceof Dog) dogs.add(new Pair<>(count++, (Dog) pet));
        else if (pet instanceof Cat) cats.add(new Pair<>(count++, (Cat) pet));
    }
}
