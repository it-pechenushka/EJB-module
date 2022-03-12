package ru.ilyai.lab2.model;

import ru.ilyai.lab2.helper.LocalDateAdapter;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@XmlRootElement(name = "StudyGroup")
@XmlAccessorType(XmlAccessType.FIELD)
public class StudyGroupDto implements Serializable {
    private Long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    private String name; //Поле не может быть null, Строка не может быть пустой

    private Float xCoordinate; //Значение поля должно быть больше -406, Поле не может быть null

    private Float yCoordinate;

    @XmlJavaTypeAdapter(value = LocalDateAdapter.class)
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    private Long studentsCount; //Значение поля должно быть больше 0, Поле не может быть null

    private Long expelledStudents; //Значение поля должно быть больше 0

    private Integer shouldBeExpelled; //Значение поля должно быть больше 0, Поле не может быть null

    private FormOfEducation formOfEducation; //Поле не может быть null

    @XmlElement(name = "GroupAdmin")
    private Person groupAdmin; //Поле может быть null

    public StudyGroupDto(){
        creationDate = LocalDate.now();
    }

    @Override
    public String toString() {
        return "StudyGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", creationDate=" + creationDate +
                ", studentsCount=" + studentsCount +
                ", expelledStudents=" + expelledStudents +
                ", shouldBeExpelled=" + shouldBeExpelled +
                ", formOfEducation=" + formOfEducation +
                ", groupAdmin=" + groupAdmin +
                '}';
    }
}
