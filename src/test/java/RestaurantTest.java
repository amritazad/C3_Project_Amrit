import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    void setRestaurant() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =Mockito.spy(new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime));
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("11:30:00"));
        Assertions.assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("09:30:00"));
        Assertions.assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<Total calculation>>>>>>>>>>>>>>>>
    @Test
    public void calculate_total_of_existing_menu_item() throws itemNotFoundException{
        List<String> selectedMenu = new ArrayList<>();
        selectedMenu.add("Sweet corn soup");
        selectedMenu.add("Vegetable lasagne");
        assertEquals(restaurant.calculateTotal(selectedMenu), 388);
    }

    @Test
    public void calculate_total_of_non_existing_menu_item_should_throw_exception() throws itemNotFoundException{
        List<String> selectedMenu = new ArrayList<>();
        selectedMenu.add("Sweet corn soup");
        selectedMenu.add("Vegetable lasagne");
        selectedMenu.add("French fries");
        assertThrows(itemNotFoundException.class, () -> {
            restaurant.calculateTotal(selectedMenu);
        });
    }
    //<<<<<<<<<<<<<<<<<<<<<<<Total calculation>>>>>>>>>>>>>>>>
}