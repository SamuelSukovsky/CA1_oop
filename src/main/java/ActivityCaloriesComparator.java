import java.util.Comparator;
public class ActivityCaloriesComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2) //compares the number of calories burned during 2 activities
    {
        return Double.compare(act1.CalculateCalories(),act2.CalculateCalories());
    }
}
