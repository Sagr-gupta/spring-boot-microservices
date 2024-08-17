package com.sagar.question_service.controller;


import com.sagar.question_service.model.Question;
import com.sagar.question_service.model.QuestionWrapper;
import com.sagar.question_service.model.Response;
import com.sagar.question_service.service.QuestionService;
import org.springframework.core.env.Environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){
         return questionService.getAllQuestions();
    }
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@PathVariable String category){
         return questionService.getAllQuestionsByCategory(category);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName, @RequestParam Integer numOfQuestions){
        return questionService.getQuestionsForQuiz(categoryName, numOfQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
         System.out.println(environment.getProperty("local.server.port"));
         return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
         return questionService.getScore(responses);
    }
}