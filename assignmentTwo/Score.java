package assignmentTwo;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;


public class Score extends JPanel {
    public static final String FILENAME = "1.txt";//sample file
    int score;//retrieve score from 'Base1'
    ArrayList<Integer> tempScores = new ArrayList<>();
    int[] topScores;
    public Score(int score){
        this.score = score;
        topScores = new int[5];
    }

    //The following method writes scores into the text file
    public void writeScores(){
        try {
            FileWriter file = new FileWriter(FILENAME,true);
            BufferedWriter b = new BufferedWriter(file);
            b.append(score+"\n");
            b.close();
        }
        catch (IOException e){
            System.out.println("Error occurred!");
            e.printStackTrace();
        }
    }
    //This method retrieves scores from text file
    public int[] readScores() {

        try {

            BufferedReader file = new BufferedReader(new FileReader(FILENAME));
            String read;
            while ((read = file.readLine()) != null) {
                if (read.isEmpty())
                    continue;
                read = read.trim();
                try
                {
                    //the append function inserts an empty space at the beginning of the file
                    int num = Integer.parseInt(read);
                    tempScores.add(num);//add to array list
                }
                catch (NumberFormatException nfe)
                {
                    System.out.println("'"+read+"' is not a valid number;\nreplacing with 0");
                    System.out.println(nfe);
                    tempScores.add(0);
                }
            }
            file.close();
            tempScores.sort(Collections.reverseOrder());

            for (int i = 0; i < 5; i++) {
                if (i + 1 <= tempScores.size())
                    topScores[i] = tempScores.get(i);
            }
        }
        catch (IOException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        return topScores;
    }

}
