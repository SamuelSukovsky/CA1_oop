import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;
public class TestApp
{
    public static void main(String[] args) {
        Activity act1 = new Activity("Swimming", "05/01/2020", 92, 4.01, 145);
        Activity act2 = new Activity("Swimming", "06/01/2020", 57, 1.63, 104);
        Activity act3 = new Activity("Swimming", "07/01/2020", 120, 7.37, 148);

        ArrayList<Activity> activities = new ArrayList<>();
        activities.add((act1));
        activities.add((act2));
        activities.add((act3));

    }
}
