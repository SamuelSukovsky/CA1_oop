import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class MainApp
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Activity> list = new ArrayList<>();

        for (final File fileEntry : new File("Data").listFiles())
        {
            System.out.println(fileEntry.getName());
        }

        boolean noFile = true;
        Scanner file = null;

        while (noFile)
        {
            String fileName = keyboard.nextLine();
            try
            {
                File inputFile = new File("Data/" + fileName);
                file = new Scanner(inputFile);
                noFile = false;
            }
            catch (Exception e)
            {
                System.out.println("Incorrect file name");
            }
        }

        if (file.hasNextLine())
            file.nextLine();
        while (file.hasNextLine())
        {
            String line = file.nextLine();
            String[] tokens = line.split(", ");

            list.add(new Activity(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4])));
        }

        PrintList(list);
        PrintList(sortByCalories(list));
        PrintList(sortByDuration(list));
        PrintList(sortByDate(list));
        PrintList(sortByType(list));
        PrintList(sortByDistance(list));
    }

    public static void PrintList (ArrayList<Activity> list)
    {
        for (Activity a : list)
        {
            System.out.println(a.toString());
        }
        System.out.println("");
    }

    public static ArrayList<Activity> sortByCalories (ArrayList<Activity> list)
    {
        ActivityCaloriesComparator comp = new ActivityCaloriesComparator();
        Collections.sort(list, comp);
        Collections.reverse(list);
        return list;
    }

    public static ArrayList<Activity> sortByDuration (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 - ascending order \n2 - descending order");
        int input = keyboard.nextInt();
        ActivityDurationComparator comp = new ActivityDurationComparator();
        Collections.sort(list, comp);
        if(input == 2)
        {
            Collections.reverse(list);
        }
        return list;
    }

    public static ArrayList<Activity> sortByDate (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 - ascending order \n2 - descending order");
        int input = keyboard.nextInt();
        ActivityDateComparator comp = new ActivityDateComparator();
        Collections.sort(list, comp);
        if(input == 2)
        {
            Collections.reverse(list);
        }
        return list;
    }

    public static ArrayList<Activity> sortByType (ArrayList<Activity> list)
    {
        ActivityTypeComparator comp = new ActivityTypeComparator();
        Collections.sort(list, comp);
        return list;
    }

    public static ArrayList<Activity> sortByDistance (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 - ascending order \n2 - descending order");
        int input = keyboard.nextInt();
        ActivityDistanceComparator comp = new ActivityDistanceComparator();
        Collections.sort(list, comp);
        if(input == 2)
        {
            Collections.reverse(list);
        }
        return list;
    }
}