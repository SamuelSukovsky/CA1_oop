import java.util.Comparator;
public class ActivityCaloriesComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return Double.compare(act1.CalculateCalories(),act2.CalculateCalories());
    }
}
