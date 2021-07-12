import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Trial2 {
    public void name(){

    }
    public void gender(){

    }

    public static void main(String[] args) {
        try {
            String s = "CA";
            File file = new File("/Users/pranay/Desktop/CS 245/Project/Project 2/WhatsMyAge/namesbystate/"+s+".TXT");
            Scanner sc = new Scanner(file);
            ArrayList<ArrayList<String>> a1 = new ArrayList<ArrayList<String>>();
            //int count=0;
            ArrayList <String> name = new ArrayList(345000);
            ArrayList<String> gender = new ArrayList<>();
            ArrayList<Integer> birthyear = new ArrayList<>();
            ArrayList<Integer> numberofPeople = new ArrayList<>();
            //a1.add(new ArrayList<String>());
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                //System.out.println(line);
               String array[] =line.split(",");
               name.add(array[3]);
               gender.add(array[1]);
               birthyear.add(Integer.valueOf(array[2]));
               numberofPeople.add(Integer.valueOf(array[4]));

               //a1.get(0).add(array[1]);
            }
            String name2 = "David";
            String gender2 = "M";
            int maximum=0;
            int store=0;
            ArrayList<Integer> minmax = new ArrayList<>();
            for(int i=0;i<name.size();i++){
                if(name.get(i).equals(name2) && gender.get(i).equals(gender2)){
                    int temp = numberofPeople.get(i);
                    if(temp>=maximum){
                        maximum=temp;
                        store = i;

                    }
                }
            }
            System.out.println(2021-birthyear.get(store));
            //System.out.println(birthyear);
            //System.out.println(a1);
        }
        catch(Exception e){
            System.out.println("File does not exist in given directory");
        }
    }
}
