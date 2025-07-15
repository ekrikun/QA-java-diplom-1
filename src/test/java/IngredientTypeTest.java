import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class IngredientTypeTest {

    private String enumName;
    private IngredientType expectedType;

    public IngredientTypeTest(String enumName, IngredientType expectedType) {
        this.enumName = enumName;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters(name = "{index}: enumName={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"SAUCE", IngredientType.SAUCE},
                {"FILLING", IngredientType.FILLING}
        });
    }

    @Test
    public void testEnumValueOf() {
        IngredientType type = IngredientType.valueOf(enumName);
        assertEquals(expectedType, type);
    }

    @Test
    public void testEnumValuesContains() {
        IngredientType[] values = IngredientType.values();
        assertTrue(Arrays.asList(values).contains(expectedType));
    }
}