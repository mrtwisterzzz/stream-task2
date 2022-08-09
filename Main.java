import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Алексей", "Сергей", "Иван", "Антон", "Дмитрий", "Василий");
        List<String> families = Arrays.asList("Сергеев", "Жидяев", "Попов", "Скворцов", "Соколов", "Смирнов", "Иванов");
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

        long underages = persons.stream().filter(person -> person.getAge() < 18).count();
        System.out.println(underages);

        List<String> recruit = persons.stream().filter(person -> (person.getAge() >= 18 && person.getAge() <= 27))
                .filter(person -> person.getSex() == Sex.MAN).map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(recruit);

        Collection<Person> ableBodied = persons.stream().
                filter(person -> person.getEducation() == Education.HIGHER)
                .filter(person -> person.getSex() == Sex.MAN ? person.getAge() >= 18 && person.getAge() <= 65
                        : person.getAge() >= 18 && person.getAge() <= 60)
                .sorted(Comparator.comparing(Person::getFamily)).collect(Collectors.toList());
        System.out.println(ableBodied);
    }
}