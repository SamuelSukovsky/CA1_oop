import java.util.Comparator;

//compares the heart rate during 2 activities
public class ActivityHeartRateComparator implements Comparator<Activity>
{
    public int compare(Activity act1, Activity act2)
    {
        return Integer.compare(act1.getHeartRate(),act2.getHeartRate());
    }
}
