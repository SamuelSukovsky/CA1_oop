import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class MainApp
{
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner keyboard = new Scanner(System.in);
        ArrayList<Activity> list = new ArrayList<>();

        /*Set<String> filesFound = (Set<String>) Stream.of(new File("Data").listFiles()).map(File::getName);
        for (String name : filesFound)
        {
            System.out.println(name);
        }*/

        String fileName = keyboard.nextLine();
        File input = new File(fileName);
        Scanner file = new Scanner(input);
    }
}