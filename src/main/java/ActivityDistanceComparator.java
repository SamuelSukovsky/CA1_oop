import java.util.Comparator;
public class ActivityDistanceComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return Double.compare(act1.getDistance(),act2.getDistance());
    }
}
