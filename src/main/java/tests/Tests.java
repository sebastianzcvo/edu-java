package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Tests {

    @Mock
    FasterCalculator fasterCalculator;
    @InjectMocks
    Calculator testedInstance;


    @BeforeAll
    public static void setupAll() {
        System.out.println("beforeAll()");
    }

    @BeforeEach
    public void setupEach() {
        System.out.println("beforeEach()");
    }

    @Test
    public void sum_shouldSumCorrectly_whenValidInput() {
        //AAA pattern
        // Arrange
        int a = 2;
        int b = 2;
        int sum = 4;
        String expected = "Total: 4";

        when(fasterCalculator.sum(anyInt(), anyInt())).thenReturn(sum);

        // Act
        String result = testedInstance.sumMessage(a, b);

        // Assert
        Assertions.assertEquals(expected, result);
    }

    @AfterEach
    public void afterEach() {
        System.out.println("afterEach()");
    }


    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll()");
    }
}
