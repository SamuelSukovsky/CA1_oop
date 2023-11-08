import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

public class MainApp
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in); //creating a new scanner for all inputs
        ArrayList<Activity> list = new ArrayList<>(); //the list of activity objects that stores all activities from the file

        for (final File fileEntry : new File("Data").listFiles()) //loop that goes through all the files in the folder
        {
            System.out.println(fileEntry.getName()); //displays all files
        }

        System.out.println();
        System.out.println("Activity tracker:");
        System.out.print("Input the name of the file to upload the activities from: ");


        boolean noFile = true;
        Scanner file = null;

        while (noFile)
        {
            String fileName = keyboard.nextLine(); //user has to specify the name of the file
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
        while (file.hasNextLine()) //goes through the whole file
        {
            String line = file.nextLine();
            String[] tokens = line.split(", "); //splits the whole lane into correct fields
            //creates a new activity from the data in the file
            Activity newActivity = new Activity(tokens[0], tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4]));
            list.add(newActivity); //adds it to the list
        }
        int input = 0, num;
        while(input == 0)//works until the user enters the correct number to quit
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
            System.out.println();

            while (input > 0) //works until the input if bigger than 0
            {
                switch (input)//what happens is specified by the input
                {
                    case 1 -> //when input is 1
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
                        switch (num) //depends on what the user entered above
                        {
                            //prints sorted lists based on various specifications
                            case 1 -> PrintList(sortByCalories(list));
                            case 2 -> PrintList(sortByDate(list));
                            case 3 -> PrintList(sortByDuration(list));
                            case 4 -> PrintList(sortByType(list));
                            case 5 -> PrintList(sortByDistance(list));
                            case 6 -> input = 0; //goes back to the main menu
                        }
                    }
                    case 2 -> //when input is 2
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
                        switch (num) //depends on what the user entered above
                        {
                            //prints the subset of data based on the chosen category
                            case 1 -> PrintByType(list);
                            case 2 -> PrintAboveMinimumDistance(list);
                            case 3 -> PrintByEnergyType(list);
                            case 4 -> PrintAboveMinimumDuration(list);
                            case 5 -> input = 0;
                        }
                    }
                    case 3 ->
                    {
                        //allows the user to search for an activity or activities based on different fields
                        System.out.println("Find By:");
                        FindBy(list);
                        input = 0; //goes back to the main menu after displaying the activity / list of activities
                    }
                    case 4 ->
                    {   //allows the user to view the statistics based on the type of the activity
                        System.out.println("View statistics:");
                        printStatistics(list);
                        input = 0; //goes back to the main menu
                    }
                    case 5 -> input = -1; //exits the application

                }

            }
        }
    }

    public static void PrintList (ArrayList<Activity> list) //prints all activities not sorted in any way
    {
        for (Activity a : list)
        {
            System.out.println(a);
        }
        System.out.println();
    }

    public static ArrayList<Activity> sortByCalories (ArrayList<Activity> list) //print activity list sorted by calories in descending order
    {
        ActivityCaloriesComparator comp = new ActivityCaloriesComparator(); //creates new comparator using a separate comparator class
        Collections.sort(list, comp); //sorts the list using collections
        Collections.reverse(list); //reverse the list
        return list;
    }

    //print the list sorted by the duration of the activity
    //user can specify if the order is going to be ascending or descending
    public static ArrayList<Activity> sortByDuration (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 - ascending order \n2 - descending order");
        int input = keyboard.nextInt();
        ActivityDurationComparator comp = new ActivityDurationComparator(); //creates new comparator using a separate comparator class
        Collections.sort(list, comp); //sorts the list using collections and chosen comparator
        if(input == 2)
        {
            Collections.reverse(list); //reverse the list
        }
        return list;
    }

    //print the list sorted by the date of the activity
    //user can specify if the order is going to be ascending or descending
    public static ArrayList<Activity> sortByDate (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 - ascending order \n2 - descending order");
        int input = keyboard.nextInt();
        ActivityDateComparator comp = new ActivityDateComparator(); //creates new comparator using a separate comparator class
        Collections.sort(list, comp); //sorts the list using collections and chosen comparator
        if(input == 2)
        {
            Collections.reverse(list); //reverse the list
        }
        return list;
    }

    //print the list sorted by the type of the activity
    public static ArrayList<Activity> sortByType (ArrayList<Activity> list)
    {
        ActivityTypeComparator comp = new ActivityTypeComparator(); //creates new comparator using a separate comparator class
        Collections.sort(list, comp); //sorts the list using collections and chosen comparator
        return list;
    }

    //print the list sorted by the distance of the activity
    //user can specify if the order is going to be ascending or descending
    public static ArrayList<Activity> sortByDistance (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1 - ascending order \n2 - descending order");
        int input = keyboard.nextInt();
        ActivityDistanceComparator comp = new ActivityDistanceComparator(); //creates new comparator using a separate comparator class
        Collections.sort(list, comp); //sorts the list using collections and chosen comparator
        if(input == 2)
        {
            Collections.reverse(list); //reverse the list
        }
        return list;
    }

    //prints the subset of data with the chosen type of activity
    public static void PrintByType (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the activity type: ");
        String input = keyboard.next(); //user specifies the type of activity
        for (Activity a : list) //goes through the whole list
        {
            if(input.equalsIgnoreCase(a.getType())) //checks if the chosen type is the same as the activity type in the list and prints the entries that match
            {
                System.out.println(a);
            }
        }
        System.out.println();
    }

    //prints the subset of data with the distance above the chosen minimum one
    public static void PrintAboveMinimumDistance (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the minimum distance: ");
        double input = keyboard.nextDouble(); //user specifies the distance
        for (Activity a : list)
        {
            if(input < a.getDistance()) //checks if the chosen distance is below the one in the list and prints the entries that match
            {
                System.out.println(a);
            }
        }
        System.out.println();
    }

    //prints the subset of data with the chosen energy type spent
    public static void PrintByEnergyType (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the energy type: ");
        String input = keyboard.nextLine(); //user specifies the type of energy
        Energy energy = switch (input.toLowerCase()) //translates string into energy value
        {
            //matches the value based on the input
            case "very light" -> Energy.Very_Light;
            case "light" -> Energy.Light;
            case "moderate" -> Energy.Moderate;
            case "vigorous" -> Energy.Vigorous;
            default -> Energy.Very_Vigorous;
        };
        for (Activity a : list)
        {
            if(energy == a.getEnergy()) //gets matching entries from the list and prints them
            {
                System.out.println(a);
            }
        }
        System.out.println();
    }

    //prints the subset of data with the duration above the one specified
    public static void PrintAboveMinimumDuration (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Enter the minimum duration: ");
        double input = keyboard.nextDouble(); //user specifies the minimum duration
        for (Activity a : list)
        {
            if(input < a.getDuration()) //checks if the chosen duration is below the one in the list and prints the entries that match
            {
                System.out.println(a);
            }
        }
        System.out.println();
    }

    //searches for matching entries based on the chosen field
    public static void FindBy (ArrayList<Activity> list)
    {
        Scanner keyboard = new Scanner(System.in);
        Activity key = new Activity();
        Comparator comp;
        System.out.print("Enter what you want to search by: ");
        String input = keyboard.nextLine(); //user specify the field that they want to search by
        double inputValue;
        int index;

        if (input.equalsIgnoreCase("type")) //searching by the type of the activity
        {
            comp = new ActivityTypeComparator(); //creates comparator
            System.out.print("Specify the type: ");
            input = keyboard.next(); //user specify the type of the activity
            for (Activity a : list)
            {
                if(a.getType().equalsIgnoreCase((input))) //checks if the chosen type is below the one in the list and prints the entries that match
                    System.out.println(a);
            }
        }
        else if (input.equalsIgnoreCase("date"))//searching by the date of the activity
        {
            comp = new ActivityDateComparator(); //creates comparator
            Collections.sort(list, comp);
            System.out.print("Specify the date: ");
            input = keyboard.next(); //user specify the date of the activity
            key.setDate(input);
            index = Collections.binarySearch(list, key, comp); //searches the list for the matching entry using the specified field and assigns it to the index value
            if (index >= 0) //checks if the index value is o or above
                System.out.println("Found " + list.get(index) + " at index " + index); //returns the entry as it found a match in the list and its index
            else
                System.out.println("Not found in list"); //the entry was not found
        }
        else if (input.equalsIgnoreCase("duration"))//searching by the duration of the activity
        {
            comp = new ActivityDurationComparator(); //creates comparator
            Collections.sort(list, comp);
            System.out.print("Specify the duration: ");
            inputValue = keyboard.nextDouble(); //user specify the duration of the activity
            key.setDuration(inputValue);
            index = Collections.binarySearch(list, key, comp); //searches the list for the matching entry using the specified field and assigns it to the index value
            if (index >= 0) //checks if the index value is o or above
                System.out.println("Found " + list.get(index) + " at index " + index); //returns the entry as it found a match in the list and its index
            else
                System.out.println("Not found in list"); //the entry was not found
        }
        else if (input.equalsIgnoreCase("distance"))//searching by the distance of the activity
        {
            comp = new ActivityDistanceComparator(); //creates comparator
            Collections.sort(list, comp);
            System.out.print("Specify the distance: ");
            inputValue = keyboard.nextDouble(); //user specify the distance of the activity
            key.setDistance(inputValue);
            index = Collections.binarySearch(list, key, comp); //searches the list for the matching entry using the specified field and assigns it to the index value
            if (index >= 0) //checks if the index value is o or above
                System.out.println("Found " + list.get(index) + " at index " + index); //returns the entry as it found a match in the list and its index
            else
                System.out.println("Not found in list"); //the entry was not found
        }
        else if (input.equalsIgnoreCase("heart rate"))//searching by the heart rate
        {
            comp = new ActivityHeartRateComparator(); //creates comparator
            Collections.sort(list, comp);
            System.out.print("Specify the heart rate: ");
            inputValue = keyboard.nextInt(); //user specify the heart rate
            key.setHeartRate((int) inputValue);
            index = Collections.binarySearch(list, key, comp); //searches the list for the matching entry using the specified field and assigns it to the index value
            if (index >= 0) //checks if the index value is o or above
                System.out.println("Found " + list.get(index) + " at index " + index); //returns the entry as it found a match in the list and its index
            else
                System.out.println("Not found in list"); //the entry was not found
        }

    }

    //prints statistics based on the activity type
    public static void printStatistics(ArrayList<Activity> list)
    {
        double calories = 0;
        double distanceSwimming = 0;
        int swim = 0;
        double distanceRunning = 0;
        int run = 0;
        double distanceCycling = 0;
        int cycle = 0;
        for (Activity a : list) //goes through the whole list
        {
            switch (a.getType()) //depends on the type of the activity
            {
                case "Swimming" -> {distanceSwimming += a.getDistance(); swim++;} //calculates the full distance based on the activity and counts the entries of the type
                case "Running" -> {distanceRunning += a.getDistance(); run++;} //calculates the full distance based on the activity and counts the entries of the type
                case "Cycling" -> {distanceCycling += a.getDistance(); cycle++;} //calculates the full distance based on the activity and counts the entries of the type
            }

            calories += a.CalculateCalories(); //calls the function that calculates calories from the Activity class
        }

        //prints the result and calculates the average values
        System.out.printf("Average Swimming distance: %3.2f%nAverage Running distance: %3.2f%nAverage Cycling distance: %3.2f%nAverage calories burned: %3.2f%n", distanceSwimming / swim, distanceRunning / run, distanceCycling / cycle, calories / list.size());
    }
}