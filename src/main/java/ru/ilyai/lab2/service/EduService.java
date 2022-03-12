package ru.ilyai.lab2.service;

import ru.ilyai.lab2.model.FormOfEducation;

public interface EduService {
    void changeEducationForm(long groupId, FormOfEducation formOfEducation);
}
