package tests;

import org.junit.jupiter.api.*;

public class Tests {

    @BeforeAll
    public static void setupAll() {
        System.out.println("beforeAll()");
    }

    @BeforeEach
    public void setupEach() {
        System.out.println("beforeEach()");
    }

    @Test
    public void simpleTest() {
        //AAA pattern
        // Arrange
        
        // Act
        // Assert
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
