import java.util.Comparator;
public class ActivityHeartRateComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return Integer.compare(act1.getHeartRate(),act2.getHeartRate());
    }
}
