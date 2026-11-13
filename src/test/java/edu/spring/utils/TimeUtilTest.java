package edu.spring.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
public class TimeUtilTest {

    private static final Logger LOG = LoggerFactory.getLogger(TimeUtilTest.class);

    @Test
    public void currentSdfDateTimeTest() {
        LOG.info(TimeUtil.getSdfCurrentDateTime());
        assertNotNull(TimeUtil.getSdfCurrentDateTime());

        LOG.info(TimeUtil.getSdfCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
        assertNotNull(TimeUtil.getSdfCurrentDateTime("yyyy-MM-dd HH:mm:ss"));
    }
}
