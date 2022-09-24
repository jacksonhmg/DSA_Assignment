import java.util.*;
import java.io.*;
import java.lang.*;

public class keyMeUp
{
    public static void main(String[] args)
    {
        DSAGraph graph = new DSAGraph();

        if(args.length < 0)
        {
            System.out.println("Please enter in the format of 'java keyMeUp -i' for interative or 'java keyMeUp -s keyFile strFile pathFile' for silent mode");
            return;
        }
        if(args[0].equals("-i"))
        {
            
        }
        else if(args[0].equals("-s"))
        {
            readInGraph(args,graph);

            File f= new File(args[args.length - 1]);//file to be deleted
            f.delete();
            System.out.println();
            graph.displayRankedPaths(readInString(args), args[args.length - 1]);
        }
        else
        {
            System.out.println("Please enter in the format of 'java keyMeUp -i' for interative or 'java keyMeUp -s keyFile strFile pathFile' for silent mode");
            return;
        }


    }

    public static String[] processLine(String csvRow)
    {  //reading one row of a file at a time, separated by string.split method
        String[] splitLine;
        splitLine = csvRow.split(" "); 
        return splitLine;
    }

    public static String[] processLine2(String csvRow)
    {  //reading one row of a file at a time, separated by string.split method
        String[] splitLine;
        splitLine = csvRow.split(""); 
        return splitLine;
    }

    public static String readInString(String[] args)
    {
        String[] sArray = null;
        String string = "";
        try{
            File myObj = new File(args[2]);
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            sArray = processLine2(data);
            for(int i = 0; i < sArray.length; i++)
            {
                string += sArray[i];
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return string;
    }

    public static void readInGraph(String[] args, DSAGraph graph)
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
                    boolean directed = false;
                    if(sArray[i].length() > 2)
                    {
                        if(sArray[i].charAt(0) == '-' && sArray[i].charAt(1) == 'd')
                        {
                            directed = true;
                            sArray[i] = sArray[i].substring(2,sArray[i].length());
                        }
                    }
                    graph.addVertex(sArray[i], sArray[i]);
                    if(i != 0)
                    {
                        graph.addEdge(sArray[0], sArray[i], directed);
                    }
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    

}