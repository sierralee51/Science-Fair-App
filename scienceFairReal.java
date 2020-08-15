import java.util.Scanner;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;
public class scienceFairReal{
    public static void main(String[] args) throws FileNotFoundException{
        Scanner console = new Scanner(System.in);
        System.out.println("REACTION TIME VS. AGE: ");
        System.out.println("AVERAGE CALCULATOR");
        System.out.println();
        System.out.print("Display all current averages? (Y/N) ");
        String yOrN = console.next();
        String exit = "";
        boolean cont = true;
        if (yOrN.equalsIgnoreCase("y")){
            displayAll();
        } else{

            System.out.println();
        }
        System.out.print("Want to proceed with the program? (Y/N) ");
        exit = console.next();
        if (exit.equalsIgnoreCase("n")){
            cont = false;   
        }

        while (cont){
            cont = work(console);
            System.out.print("Display all updated averages? (Y/N) ");
            String yOrN3 = console.next();
            if (yOrN3.equalsIgnoreCase("y")){
                displayAll();
            } else{
                System.out.println();
            }
        } 

    }

    public static boolean work(Scanner console) throws FileNotFoundException{

        boolean repeatAge = true;
        File f = new File("68+.txt");
        Scanner fileScan = new Scanner(f);
        String ageRange = "";
        File all = new File("MasterList.txt");
        Scanner allFileScan = new Scanner(all);

        while (repeatAge){
            System.out.println("Age Range?");
            System.out.println("a. 0-12 yrs  b. 13-25 yrs  c. 26-46 yrs  d. 47-67 yrs  e. 68+ yrs");
            ageRange = console.next();
            ageRange = ageRange.toLowerCase();
            if (ageRange.equals("a") || ageRange.equals("b") || ageRange.equals("c") || ageRange.equals("d") || ageRange.equals("e")){
                repeatAge = false;
                if (ageRange.equals("a")){
                    f = new File("0-12.txt");

                } else if (ageRange.equals("b")){
                    f = new File("13-25.txt");

                } else if (ageRange.equals("c")){
                    f = new File("26-46.txt");
                } else if (ageRange.equals("d")){
                    f = new File("47-67.txt");

                } //else {
                //f = new File("68+.txt");   
                //}
                fileScan = new Scanner(f);
            } else {
                //repeatAge = true;
            }
        }
        System.out.print("Centimeters? ");
        double length = console.nextDouble();
        try {
            PrintWriter out = new PrintWriter(new FileWriter(f, true));
            PrintWriter outTwo = new PrintWriter(new FileWriter(all, true));
            out.append(length + " ");
            out.append(" ");
            out.close();
            outTwo.append(length + " ");
            outTwo.append(" ");
            outTwo.close();
        } catch (IOException e){
            System.out.println("no file");   
        }
        boolean repeatAll = false;

        System.out.println("Average Score in Age Range: " + localAve(f, fileScan));
        //System.out.println("Average Score Overall: " + localAve(all, allFileScan));
        System.out.println();
        System.out.print("Repeat (Y/N)? ");
        String response = console.next();
        if (response.equalsIgnoreCase("Y")){
            repeatAll = true;
        }
        System.out.println();
        return repeatAll;

    }

    public static double localAve(File f, Scanner fileScan){
        double sum = 0.0;
        int quan = 0;
        //sum += fileScan.nextDouble();
        while (fileScan.hasNextDouble()){
            quan++;
            sum += fileScan.nextDouble();
        }
        if (quan == 0){
            return 0;   
        }
        double calcAv = sigFigs(sum, quan);
        // return sum/quan;
        return calcAv;
    }

    public static void displayAll() throws FileNotFoundException{
        int redo = 0;
        File v = new File("0-12.txt");
        String name = "0-12 yrs";
        System.out.println("AVERAGES:");
        while (redo!=5){
            if (redo == 1){
                v = new File("13-25.txt");
                name = "13-25 yrs";
            } else if (redo == 2){
                v = new File("26-46.txt"); 
                name = "26-46 yrs";
            } else if (redo == 3){
                v = new File("47-67.txt");  
                name = "47-67 yrs";
            } else if (redo == 4){
                v = new File("68+.txt");
                name = "68+ yrs";
            } //else if (redo == 5){
                //v = new File("MasterList.txt");
                //name = "Overall";
            //}
            Scanner scanV = new Scanner(v);
            double average = localAve(v, scanV);
            System.out.println(name + " average: " + average);
            redo++;
        }
        v = new File("MasterList.txt");
        name = "Overall";
        Scanner scanV = new Scanner(v);
        double average = localAve(v, scanV);
        System.out.println(name + " average: " + average);
        System.out.println();

    }

    public static double sigFigs(double sum, int quan){
        String sumSt = Double.toString(sum);
        int dec = sumSt.length()-1;
        double d = sum/quan;
        BigDecimal bd = new BigDecimal(d);
        bd = bd.round(new MathContext(dec));
        double rounded = bd.doubleValue();
        return rounded;

    }
}