package ru.ilyai.lab2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
@Getter
@Setter
public class Person implements Serializable, Comparable<Person> {
    private Long id;

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Integer weight; //Значение поля должно быть больше 0

    private Country nationality; //Поле не может быть null

    @XmlElement(name = "location")
    private Location location; //Поле может быть null

    private Color hairColor; //Поле может быть null

    @XmlTransient
    private StudyGroupDto studyGroup;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", nationality=" + nationality +
                ", location=" + location +
                ", hairColor=" + hairColor +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.weight - o.getWeight();
    }
}
