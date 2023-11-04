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

        for (Activity a : list)
        {
            System.out.println(a.toString());
        }
        System.out.println("");

        for (Activity a : sortByCalories(list))
        {
            System.out.println(a.toString());
        }
    }

    public static ArrayList<Activity> sortByCalories (ArrayList<Activity> list)
    {
        ActivityCaloriesComparator comp = new ActivityCaloriesComparator();
        Collections.sort(list, comp);
        return list;
    }
}