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

        System.out.println();
        System.out.println("Activity tracker:");
        System.out.print("Input the name of the file to upload the activities from: ");


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

            Activity newActivity = new Activity(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]));
            list.add(newActivity);
        }
        int input = 0, num;
        while(input == 0)
        {
            System.out.println();
            System.out.println("Main Menu:");
            System.out.println("1 - sorting options");
            System.out.println("2 - view activities by...");
            System.out.println("3 - finding options");
            System.out.println("4 - view statistics");
            System.out.println("5 - quit");
            System.out.println();
            System.out.print("Enter a number: ");
            input = keyboard.nextInt();

            while (input > 0)
            {
                switch (input)
                {
                    case 1 ->
                    {
                        System.out.println("Sorting Menu:");
                        System.out.println("1 - sort by calories burned (descending)");
                        System.out.println("2 - sort by date");
                        System.out.println("3 - sort by activity duration");
                        System.out.println("4 - sort by type of activity");
                        System.out.println("5 - sort by distance");
                        System.out.println("6 - go back");
                        System.out.println();
                        System.out.print("Enter a number: ");
                        num = keyboard.nextInt();
                        switch (num)
                        {
                            case 1 -> {PrintList(sortByCalories(list));}
                            case 2 -> {PrintList(sortByDate(list));}
                            case 3 -> {PrintList(sortByDuration(list));}
                            case 4 -> {PrintList(sortByType(list));}
                            case 5 -> {PrintList(sortByDistance(list));}
                            case 6 -> {input = 0;}
                        }
                    }
                    case 2 ->
                    {
                        System.out.println("View Menu:");
                        System.out.println("1 - view by activity type");
                        System.out.println("2 - view above minimum distance");
                        System.out.println("3 - view type of energy expended");
                        System.out.println("4 - view above minimum duration");
                        System.out.println("5 - go back");
                        System.out.println();
                        System.out.print("Enter a number: ");
                        num = keyboard.nextInt();
                        switch (num)
                        {
                            case 1 -> {PrintByType(list);}
                            case 2 -> {PrintAboveMinimumDistance(list);}
                            case 3 -> {}
                            case 4 -> {}
                            case 5  -> {input = 0;}
                        }
                    }
                    case 3 ->
                    {
                        System.out.println("Find By:");
                    }
                    case 4 ->
                    {
                        System.out.println("View statistics:");
                    }
                    case 5 ->
                    {
                        input = -1;
                    }
                }

            }
        }
        //PrintList(list);
        //PrintList(sortByCalories(list));
        //PrintList(sortByDuration(list));
        //PrintList(sortByDate(list));
        //PrintList(sortByType(list));
        //PrintList(sortByDistance(list));


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
    public static void PrintByType (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the activity type: ");
        String input = keyboard.next();
        for (Activity a : list)
        {
            if(input.equalsIgnoreCase(a.getType()))
            {
                System.out.println(a.toString());
            }
        }
        System.out.println("");
    }

    public static void PrintAboveMinimumDistance (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the minimum distance: ");
        double input = keyboard.nextDouble();
        for (Activity a : list)
        {
            if(input < a.getDistance())
            {
                System.out.println(a.toString());
            }
        }
        System.out.println("");
    }
}