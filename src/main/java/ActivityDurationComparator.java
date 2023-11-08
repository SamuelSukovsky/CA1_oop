import java.util.Comparator;

//compares the duration of 2 activities for sort method
public class ActivityDurationComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return Double.compare(act1.getDuration(),act2.getDuration());
    }
}
