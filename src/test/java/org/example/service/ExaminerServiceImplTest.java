package org.example.service;

import org.example.model.Question;
import org.example.service.impl.ExaminerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ExaminerServiceImplTest {
    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    void testGetQuestions() {
        when(questionService.getAll()).thenReturn(Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2"),
                new Question("Q3", "A3")
        ));

        when(questionService.getRandomQuestion())
                .thenReturn(new Question("Q1", "A1"))
                .thenReturn(new Question("Q2", "A2"));

        Collection<Question> result = examinerService.getQuestions(2);

        assertEquals(2, result.size());
        verify(questionService, times(2)).getRandomQuestion();
    }

    @Test
    void testGetQuestionsExceedsAvailable() {
        when(questionService.getAll()).thenReturn(Set.of(
                new Question("Q1", "A1"),
                new Question("Q2", "A2")
        ));

        assertThrows(ResponseStatusException.class, () -> {
            examinerService.getQuestions(5);
        });
    }
}
