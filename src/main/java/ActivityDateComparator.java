import java.util.Comparator;

public class ActivityDateComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return act1.getDate().compareTo(act2.getDate());
    }
}
