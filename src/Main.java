import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> surnames = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    surnames.get(new Random().nextInt(surnames.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long underAge = persons.stream()
                .mapToInt(person -> person.getAge())
                .filter(integer -> integer < 18)
                .count();


        List<String> militaryAgeMen = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MALE))
                .filter(person -> person.getAge() <= 27 && person.getAge() >= 18)
                .map(person -> person.getSurname())
                .collect(Collectors.toList());

        List<String> workable = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getAge() >= 18)
                .filter(person -> (person.getSex()==Sex.MALE && person.getAge() < 65) ||
                        (person.getSex() == Sex.FEMALE && person.getAge() <60))
                .sorted(Comparator.comparing(Person::getSurname))
                .map(Person::getSurname)
                .collect(Collectors.toList());
    }
}
