import java.util.Comparator;

//compares the distance of 2 activities for the sort method
public class ActivityDistanceComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return Double.compare(act1.getDistance(),act2.getDistance());
    }
}
