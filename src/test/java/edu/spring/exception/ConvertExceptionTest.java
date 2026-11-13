package edu.spring.exception;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
public class ConvertExceptionTest {

    @Test
    public void basic() {
        ConvertException ex = new ConvertException("some-error");
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("some-error"));
    }
}
