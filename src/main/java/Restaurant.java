import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private List<String> selectedMenu = new ArrayList<>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        if (getCurrentTime().isAfter(openingTime) && getCurrentTime().isBefore(closingTime))
            return true;
        else
            return false;
    }

    public LocalTime getCurrentTime() {
        return LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName) throws itemNotFoundException {
        for (Item item : menu) {
            if (item.getName().equals(itemName))
                return item;
        }
        throw new itemNotFoundException(itemName);
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name, price);
        menu.add(newItem);
    }

    private int calculateTotal() throws itemNotFoundException {
        Iterator<String> sMenu = selectedMenu.iterator();
        int total = 0;
        while (sMenu.hasNext()){
            Item temp =  findItemByName(sMenu.next());
            total += temp.getPrice();
        }
        return total;
    }

    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }


    public String getName() {
        return name;
    }

    public int setSelectedMenu(String item) throws itemNotFoundException {
        selectedMenu.add(item);
        return calculateTotal();
    }

}
