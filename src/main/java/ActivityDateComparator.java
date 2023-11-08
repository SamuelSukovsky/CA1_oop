import java.util.Comparator;

public class ActivityDateComparator implements Comparator<Activity>
{
    //compares the date of 2 activities / used in the sort method
    public int compare(Activity act1, Activity act2)
    {
        String[] date1 = act1.getDate().split("/");
        String[] date2 = act2.getDate().split("/");
        for (int i = 2; i >= 0; i--)
        {
            int j = date1[i].compareTo(date2[i]);
            if (j != 0)
            {
                return j;
            }
        }

        return 0;
    }
}
