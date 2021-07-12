import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Age {//this is the final version. The rest are simply kept for authenticity
    public ArrayList<String> states = new ArrayList<>();
    public void listOfStates(String directory) throws Exception {
        //int count=0;
        final File folder = new File(directory);// we are reading the folder
        for(final File fileEntry : folder.listFiles()){
            String s = fileEntry.getName();
            states.add(s);
            //count++;
        }
    }
    private boolean checkGender(String genderInput){//this is to check if the gender added
        //by the user is valid or not.
        if (genderInput.equalsIgnoreCase("M") ||
                genderInput.equalsIgnoreCase("F")) {
            return true;
        }
        else {
            return false;
        }

    }

    private boolean checkState(String stateInput) throws Exception {//this is to check if the state added
        //by the user is valid or not. the state input is in form of txt which is seen in the main
        for(int i=0;i<this.states.size;i++) {
            if (states.get(i).equalsIgnoreCase(stateInput)) {
                return true;
            }
        }
        return false;
    }
    private String findAge(String state, String gender, String name, String directory) throws Exception {
        File file = new File(directory+"/"+state);
        Scanner sc = new Scanner(file);
        // I used an index based system and hence i have created four arrays.
        ArrayList<String> nameList = new ArrayList();
        ArrayList<String> genderList = new ArrayList<>();
        ArrayList<Integer> birthyearList = new ArrayList<>();
        ArrayList<Integer> numberofPeopleList = new ArrayList<>();


        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String array[] = line.split(",");// split the lines and add accordingly in each Arraylist.
            nameList.add(array[3]);
            genderList.add(array[1]);
            birthyearList.add(Integer.valueOf(array[2]));
            numberofPeopleList.add(Integer.valueOf(array[4]));

        }

        int maximum=0;
        ArrayList<Integer> storeIndex = new ArrayList<Integer>();// store index stores all the index that match
        //the criteria of the name and gender being the same,
        //then it checks if the number of people that year is greater than previous and i add that index to the list.
        for (int i=0;i<nameList.size();i++){
            if(nameList.get(i).equalsIgnoreCase(name)
                    && genderList.get(i).equalsIgnoreCase(gender)){
                int temp = numberofPeopleList.get(i);
                if(temp>=maximum){
                    maximum=temp;
                    storeIndex.add(i);
                }
            }
        }
        /*
        for(int j=0;j<storeIndex.size;j++){
            System.out.println(storeIndex.get(j));
        }

         */
        int checkMax = numberofPeopleList.get(storeIndex.get(storeIndex.size-1)); // we know the
        //max will be the last element of the list store index.
        ArrayList<Integer> maxList = new ArrayList<>();//new ArrayList
        maxList.add(birthyearList.get(storeIndex.get(storeIndex.size-1)));
        for(int i=0;i<storeIndex.size()-1;i++){ // it is storeIndex.size-1 as we don't need the
            //last element as we have already added that to maxList
            int temp = numberofPeopleList.get(storeIndex.get(i));
            if(checkMax==temp){
                maxList.add(birthyearList.get(storeIndex.get(i)));//max list checks and adds all the years which have
                //the same number of people // the maximum number is equated
                //all the years are then added.
            }
        }
        //finding the min and max of the list.
        int MinInList = maxList.get(0);
        int MaxInList = maxList.get(0);

        for(int i=0;i<maxList.size;i++){
            int temp = maxList.get(i);

            if(temp<MinInList){//minimum
                MinInList = temp;

            }
            else if(temp>MaxInList){//Maximum
                MaxInList=temp;
            }
        }

        /*
        for(int k=0;k<maxList.size;k++){
            System.out.println(maxList.get(k));
        }

         */


        if(maxList.size==1){// to print a single value
            return "around " + (2021-MaxInList);
        }
        else{// if multiple values, print the range.
            return "around "+  (2021-MaxInList) + "-" + (2021-MinInList);
        }

        /*
        for(int i=0;i<storeIndex.size();i++){
            System.out.println(birthyearList.get(storeIndex.get(i)));
            System.out.println(numberofPeopleList.get(storeIndex.get(i)));
            System.out.println("---------------");
        }
         */
    }
    public static void main(String[] args) throws Exception {
        String Properties1 = args[0];
        Properties p = new Properties();
        InputStream inputStream = new FileInputStream(new File(Properties1));
        p.load(inputStream);
        String directory = p.getProperty("Directory");
        //System.out.println(directory);

        Age ageObject = new Age();
        ageObject.listOfStates(directory);
        System.out.println("Welcome to the program.");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the name of the person: ");
        String nameInput = scanner.next();// taking input of the name.

        while (!nameInput.equalsIgnoreCase("exit")){

            System.out.println("Please enter a gender: ");
            String genderInput= scanner.next();
            boolean g = ageObject.checkGender(genderInput);

            while(g==false){// while loop to ensure user cannot move ahead without valid gender input.
                System.out.println("Please enter either M or F.");
                String genderInput1 = scanner.next();
                g= ageObject.checkGender(genderInput1);
                genderInput=genderInput1;
            }

            System.out.println("Please enter the state of birth as a 2 letter code: ");
            String stateInput = scanner.next();
            String stateInputTXT = stateInput+".TXT";
            boolean s = ageObject.checkState(stateInputTXT);

            while(s==false){ // while loop to ensure user cannot move ahead without valid state input.
                System.out.println("Please enter a valid 2 letter code for state of birth: ");
                String stateInput1 = scanner.next();
                String stateInputTXT1 = stateInput1+".TXT";// all the state list has .txt so we manually add it and
                //check it accordingly.
                s = ageObject.checkState(stateInputTXT1);
                stateInputTXT=stateInputTXT1;
                stateInput=stateInput1;
            }

            String age= ageObject.findAge(stateInputTXT,genderInput,nameInput,directory);

                System.out.println(nameInput+ ", born in " +stateInput+" " +
                        "is most likely " + age+ " years old.");


            System.out.println("---------------------------------------------------");
            System.out.println("Name of the person: ");
            nameInput = scanner.next();

        }

        // I was just checking how the list is being printed for the file of states

        /*
        System.out.println("Thank you for using the program.");

        for(int i=0;i<trial.states.length;i++){
            System.out.println(trial.states[i]);
        }

         */
        System.out.println("Thank you for using the program");
    }
}
