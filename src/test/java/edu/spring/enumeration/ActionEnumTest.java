package edu.spring.enumeration;

import edu.spring.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import java.util.Arrays;

import static edu.spring.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
public class ActionEnumTest {

    @Test
    public void constructor() {
        ActionEnum actionEnumTrimJson = ActionEnum.valueOf("TRIM_JSON");
        assertNotNull(actionEnumTrimJson);
        assertEquals(Constants.ACTION_TRIM_JSON, actionEnumTrimJson.getValue());
    }

    @Test
    public void getArray() {
        String[] actionArray = ActionEnum.getArray();
        assertNotNull(actionArray);
        assertTrue(Arrays.stream(actionArray).anyMatch(ACTION_DISPLAY_JSON::equalsIgnoreCase));
        assertTrue(Arrays.stream(actionArray).anyMatch(ACTION_DISPLAY_XML::equalsIgnoreCase));
        assertTrue(Arrays.stream(actionArray).anyMatch(ACTION_JSON_TO_XML::equalsIgnoreCase));
        assertTrue(Arrays.stream(actionArray).anyMatch(ACTION_XML_TO_JSON::equalsIgnoreCase));
        assertTrue(Arrays.stream(actionArray).anyMatch(ACTION_TRIM_FILE::equalsIgnoreCase));
        assertTrue(Arrays.stream(actionArray).anyMatch(ACTION_TRIM_XML::equalsIgnoreCase));
        assertTrue(Arrays.stream(actionArray).anyMatch(ACTION_TRIM_JSON::equalsIgnoreCase));
    }

    @Test
    public void find() {
        assertNotEquals(ActionEnum.UNDEFINED, ActionEnum.find(ACTION_DISPLAY_JSON));
        assertNotEquals(ActionEnum.UNDEFINED, ActionEnum.find(ACTION_DISPLAY_XML));
        assertNotEquals(ActionEnum.UNDEFINED, ActionEnum.find(ACTION_JSON_TO_XML));
        assertNotEquals(ActionEnum.UNDEFINED, ActionEnum.find(ACTION_XML_TO_JSON));
        assertNotEquals(ActionEnum.UNDEFINED, ActionEnum.find(ACTION_TRIM_FILE));
        assertNotEquals(ActionEnum.UNDEFINED, ActionEnum.find(ACTION_TRIM_JSON));
        assertNotEquals(ActionEnum.UNDEFINED, ActionEnum.find(ACTION_TRIM_XML));
        assertEquals(ActionEnum.UNDEFINED, ActionEnum.find("dummy"));
        assertEquals(ActionEnum.UNDEFINED, ActionEnum.find(null));
    }
}
