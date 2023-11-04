import java.util.Comparator;

public class ActivityDurationComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return Double.compare(act1.getDuration(),act2.getDuration());
    }
}
