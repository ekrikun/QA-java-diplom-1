package praktikum;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BurgerTest {

    private Burger burger;
    private Bun mockBun;
    private Ingredient mockIngredient1;
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        burger = new Burger();

        mockBun = mock(Bun.class);
        when(mockBun.getName()).thenReturn("Classic Bun");
        when(mockBun.getPrice()).thenReturn(50.0f);

        mockIngredient1 = mock(Ingredient.class);
        when(mockIngredient1.getName()).thenReturn("Lettuce");
        when(mockIngredient1.getPrice()).thenReturn(10.0f);
        when(mockIngredient1.getType()).thenReturn(IngredientType.FILLING);

        mockIngredient2 = mock(Ingredient.class);
        when(mockIngredient2.getName()).thenReturn("Tomato");
        when(mockIngredient2.getPrice()).thenReturn(15.0f);
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void testSetBuns() {
        burger.setBuns(mockBun);
        assertEquals(mockBun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(mockIngredient1);
        assertTrue(burger.ingredients.contains(mockIngredient1));
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(mockIngredient1));
        assertEquals(1, burger.ingredients.size());
        assertTrue(burger.ingredients.contains(mockIngredient2));
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        burger.moveIngredient(0, 1);

        assertEquals(mockIngredient2, burger.ingredients.get(0));
        assertEquals(mockIngredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        float expectedPrice = 2 * 50.0f + 10.0f + 15.0f;
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("Classic Bun"));
        assertTrue(receipt.toLowerCase().contains("lettuce"));
        assertTrue(receipt.toLowerCase().contains("tomato"));
        assertTrue(receipt.contains("Price:"));
    }
}