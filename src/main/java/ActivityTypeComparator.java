import java.util.Comparator;

public class ActivityTypeComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return act1.getType().compareTo(act2.getType());
    }
}
