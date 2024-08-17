package com.sagar.quiz_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuizDto {

    String categoryName;
    Integer numQuestions;
    String title;
}
