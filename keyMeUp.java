import java.util.*;
import java.io.*;
public class keyMeUp
{
    public static void main(String[] args)
    {
        DSAGraph graph = new DSAGraph();
        int inpCheck;
        if(args.length < 0)
        {
            System.out.println("Please enter in the format of 'java keyMeUp -i' for interative or 'java keyMeUp -s keyFile strFile pathFile' for silent mode");
            return;
        }
        if(args[0].equals("-i"))
        {
            inpCheck = 0;
        }
        else if(args[0].equals("-s"))
        {
            inpCheck = 1;
        }
        else
        {
            System.out.println("Please enter in the format of 'java keyMeUp -i' for interative or 'java keyMeUp -s keyFile strFile pathFile' for silent mode");
            return;
        }

        if(inpCheck == 1)
        {
            
        
            try{
                File myObj = new File(args[1]);
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine())
                {
                    String data = myReader.nextLine();
                    String[] sArray = processLine(data);
                    for(int i = 0; i < sArray.length; i++)
                    {
                        graph.addVertex(sArray[i], sArray[i]);
                        if(i != 0)
                        {
                            graph.addEdge(sArray[0], sArray[i], false);
                        }
                    }
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        //graph.displayAsList();
        graph.displayAsList();
        System.out.println();
        int x = graph.breadthFirstSearchFind("/", "1");
        System.out.println();
        System.out.println(x + " steps");

        x = graph.depthFirstSearchFind("3", "1");
        System.out.println();
        System.out.println(x + " steps");
        // graph.displayAsMatrix();

    }

    public static String[] processLine(String csvRow)
    {  //reading one row of a file at a time, separated by string.split method
        String[] splitLine;
        splitLine = csvRow.split(" "); 
        return splitLine;
    }

}