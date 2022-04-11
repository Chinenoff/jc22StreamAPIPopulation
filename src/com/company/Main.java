package com.company;

import java.util.*;
import java.util.stream.Collectors;

import static com.company.Sex.MAN;
import static com.company.Sex.WOMAN;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        //task 1
        System.out.print("КОЛИЧЕСТВО НЕСОВЕРШЕННОЛЕТНИХ ->");
        System.out.println(
                persons.stream()
                        .filter(x -> x.getAge() < 18)
                        .count()
        );

        //task 2
        System.out.print("СПИСОК ПРИЗЫВНИКОВ:");
        persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> x.getAge() < 27)
                .map(x -> x.getFamily() + " " + x.getAge())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        //task 3
        System.out.print("СПИСОК РАБОТОСПОСОБНЫХ:");
        persons.stream()
                .filter(x -> x.getAge() > 18)
                .filter(x -> {
                    if (x.getSex() == MAN && x.getAge() < 65)
                        return true;
                    if (x.getSex() == WOMAN && x.getAge() < 60)
                        return true;
                    return false;
                })
                .map(x -> x.getFamily() + " " + x.getAge() + " " + x.getSex())
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
