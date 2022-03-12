package ru.ilyai.lab2.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class Location implements Serializable {
    private Long id;

    private Long xLocation;

    private Float yLocation; //Поле не может быть null

    private Integer zLocation; //Поле не может быть null

    @XmlTransient
    private Person person;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", xLocation=" + xLocation +
                ", yLocation=" + yLocation +
                ", zLocation=" + zLocation +
                '}';
    }
}
