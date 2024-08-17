package com.sagar.quiz_service.service;


import com.sagar.quiz_service.dao.QuizDao;
import com.sagar.quiz_service.feign.QuizInterface;
import com.sagar.quiz_service.model.QuestionWrapper;
import com.sagar.quiz_service.model.Quiz;
import com.sagar.quiz_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
         ResponseEntity<Integer> score = quizInterface.getScore(responses);
         return score;
    }

    public ResponseEntity<String> createQuiz(String categoryName, int numQ, String title) {
        List <Integer> questions = quizInterface.getQuestionsForQuiz(categoryName, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz is created.", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionIds = quiz.getQuestions();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }
}
