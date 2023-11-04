import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class MainApp
{
    public static void main(String[] args) throws FileNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Activity> list = new ArrayList<>();

        for (final File fileEntry : new File("Data").listFiles())
        {
            System.out.println(fileEntry.getName());
        }

        boolean noFile = true;
        File inputFile = null;

        while (noFile)
        {
            String fileName = keyboard.nextLine();
            try
            {
                inputFile = new File("Data/" + fileName);
                noFile = false;
            } catch (Exception e)
            {
                System.out.println("Incorrect file name");
            }
        }
        Scanner file = new Scanner(inputFile);

        if(file.hasNextLine())
            file.nextLine();
        while (file.hasNextLine())
        {
            String line = file.nextLine();
            String [] tokens = line.split(",");

            list.add(new Activity(tokens[0], Double.parseDouble(tokens[1]), tokens[2], Double.parseDouble(tokens[3]), Integer.parseInt(tokens[4])));
        }

        for(Activity a : list)
        {
            System.out.println(a.toString());
        }
    }
}