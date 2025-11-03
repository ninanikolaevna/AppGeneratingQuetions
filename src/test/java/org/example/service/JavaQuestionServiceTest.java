package org.example.service;

import org.example.model.Question;
import org.example.service.impl.JavaQuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @BeforeEach
    void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    void testAddQuestion() {
        Question question = javaQuestionService.add("What is Java?", "Programming language");

        assertNotNull(question);
        assertEquals("What is Java?", question.getQuestion());
        assertEquals("Programming language", question.getAnswer());

        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertTrue(allQuestions.contains(question));
    }

    @Test
    void testRemoveQuestion() {
        Question question = javaQuestionService.add("Test Q", "Test A");
        Question removed = javaQuestionService.remove(question);

        assertEquals(question, removed);
        assertFalse(javaQuestionService.getAll().contains(question));
    }

    @Test
    void testGetAllQuestions() {
        javaQuestionService.add("Q1", "A1");
        javaQuestionService.add("Q2", "A2");

        Collection<Question> allQuestions = javaQuestionService.getAll();
        assertEquals(2, allQuestions.size());
    }

    @Test
    void testGetRandomQuestion() {
        javaQuestionService.add("Q1", "A1");
        javaQuestionService.add("Q2", "A2");

        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertNotNull(randomQuestion);
    }
}