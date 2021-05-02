import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RestaurantService {
    private static List<Restaurant> restaurants = new ArrayList<>();
    private static Restaurant selectedRestaurant;
    private static List<String> selectedMenu = new ArrayList<>();
    public Restaurant findRestaurantByName(String restaurantName) throws restaurantNotFoundException{
        Iterator<Restaurant> i = restaurants.iterator();
        while (i.hasNext()) {
            selectedRestaurant = i.next();
            if (selectedRestaurant.getName().equals(restaurantName))
                return selectedRestaurant;
        }
         throw new restaurantNotFoundException(restaurantName);
    }


    public Restaurant addRestaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        Restaurant newRestaurant = new Restaurant(name, location, openingTime, closingTime);
        restaurants.add(newRestaurant);
        return newRestaurant;
    }

    public Restaurant removeRestaurant(String restaurantName) throws restaurantNotFoundException {
        Restaurant restaurantToBeRemoved = findRestaurantByName(restaurantName);
        restaurants.remove(restaurantToBeRemoved);
        return restaurantToBeRemoved;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }


    public int selectMenu(String menuItem) throws itemNotFoundException{
        selectedMenu.add(menuItem);
        return selectedRestaurant.calculateTotal(selectedMenu);
    }

}
