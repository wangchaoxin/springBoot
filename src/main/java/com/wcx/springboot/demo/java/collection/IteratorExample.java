package com.wcx.springboot.demo.java.collection;

import java.util.*;

public class IteratorExample {
    public static void display(Iterator<Pet> it) {
        while (it.hasNext()) {
            Pet p = it.next();
            System.out.print(p.id() + ":" + p + " ");
        }
        System.out.println();
    }

    public static void display(Collection<Pet> pets) {
        for (Pet p : pets)
            System.out.print(p.id() + ":" + p + " ");
        System.out.println();
    }

    public static class Pet {
        private int id;

        public int id() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    public static void main(String[] args) {
        List<Pet> petList = Arrays.asList(new Pet(), new Pet());
        Set<Pet> petSet = new HashSet<Pet>(petList);
        Map<String, Pet> petMap =
                new LinkedHashMap<String, Pet>();
        String[] names = ("Ralph, Eric, Robin, Lacey, " +
                "Britney, Sam, Spot, Fluffy").split(", ");
        for (int i = 0; i < names.length; i++)
            petMap.put(names[i], petList.get(i));
        display(petList);
        display(petSet);
        display(petList.iterator());
        display(petSet.iterator());
        System.out.println(petMap);
        System.out.println(petMap.keySet());
        display(petMap.values());
        display(petMap.values().iterator());

        display(new CollectionSequence());
    }

    /**
     * 需要遍历一个对象，可以继承AbstractCollection，但是需要实现一些不必要的方法，而且不能多继承
     */
    public static class CollectionSequence
            extends AbstractCollection<Pet> {
        private Pet[] pets = new Pet[]{new Pet(), new Pet()};

        public int size() {
            return pets.length;
        }

        public Iterator<Pet> iterator() {
            return new Iterator<Pet>() {
                private int index = 0;

                public boolean hasNext() {
                    return index < pets.length;
                }

                public Pet next() {
                    return pets[index++];
                }

                public void remove() { // Not implemented
                    throw new UnsupportedOperationException();
                }
            };
        }
    }
    static class  PetSequence {
        protected Pet[] pets = new Pet[]{new Pet(), new Pet()};
    }

    /**
     * 可以实现iterator接口，比较简便的遍历对象，不需要耦合
     */
    public static class NonCollectionSequence extends PetSequence {
        public Iterator<Pet> iterator() {
            return new Iterator<Pet>() {
                private int index = 0;
                public boolean hasNext() {
                    return index < pets.length;
                }
                public Pet next() { return pets[index++]; }
                public void remove() { // Not implemented
                    throw new UnsupportedOperationException();
                }
            };
        }
        public static void main(String[] args) {
            NonCollectionSequence nc = new NonCollectionSequence();
            IteratorExample.display(nc.iterator());
        }
    }

}
