import java.io.*;
import java.util.*;
public class Helpers {
    
    public boolean printSaveCheck = true;

    public void writeOneRow(String pFileName, String pInputString) /* method for writing one row to a file */
    {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter(pFileName,true)); /* appends to it. allows for multiple line printing */
            pw.println(pInputString);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file" + e.getMessage());
        }
    }

    public int saveList(String pFileName, DSALinkedList list)
    {
        boolean capCheck = false;
        PrintWriter pw;
        int count = 0;
        try {
            pw = new PrintWriter(new FileWriter(pFileName,true));
            Iterator ill2 = list.iterator();
            while(ill2.hasNext())
            {
                DSAGraphVertex printV = (DSAGraphVertex)ill2.next();
                String label = (String)printV.getLabel();
                if(!printSaveCheck) /* if doesn't want suffixes, then... */
                {
                    if(label.length() > 4)
                    {
                        if(label.substring(label.length()-4).equals("(-u)") || label.substring(label.length()-4).equals("(-p)")) /* if has suffix, then... */
                        {
                            label = label.substring(0,label.length()-4); /* remove suffix */
                        }
                    }
                }
                if(count == 0)
                {
                    pw.print(label + " ");
                }
                else
                {
                    String printString = (String)label;
                    pw.print(" -> " + printString);
                }
                
                count++;
            }
            pw.println();
            pw.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file" + e.getMessage());
        }
        return count;
    }

    public int printList(DSALinkedList list)
    {
        int count = 0;
        boolean capCheck = false;
        Iterator ill2 = list.iterator();
        while(ill2.hasNext())
        {
            DSAGraphVertex printV = (DSAGraphVertex)ill2.next();
            String label = (String)printV.getLabel();
            if(!printSaveCheck) /* if doesn't want suffixes, then... */
            {
                if(label.length() > 4)
                {
                    if(label.substring(label.length()-4).equals("(-u)") || label.substring(label.length()-4).equals("(-p)")) /* if has suffix, then... */
                    {
                        label = label.substring(0,label.length()-4); /* remove suffix */
                    }
                }
            }
            if(count == 0)
            {
                System.out.print(label + " ");
            }
            else
            {
                String printString = (String)label;
                System.out.print(" -> " + printString);
            }
            count ++;
        }
        System.out.println();
        return count-1;
    }

    public String[] stringFix2(String string, DSAGraph graph)
    {
        int count = 0;
        boolean capsCheck = false;
        String[] strArr = string.split("");
        boolean capCheck = false;
        boolean numCheck = false;
        String[] strArr3 = new String[strArr.length+1];
        for(int k = 0; k < strArr.length+1; k++)
        {
            
            if(k==0)
            {
                strArr3[k] = "1"; /* start from 1 on the path, as all keyboards will make you do */
            }
            else
            {
                strArr3[k] = strArr[k-1];
            }
        }
        strArr = strArr3;

        for(int i = 0; i < strArr.length-1; i++)
        {
            if(strArr[i+1].equals(" "))
            {
                strArr[i+1] = "SPACE"; /* change " " into "SPACE" to make it easier to understand and visualise */
            }
            if(graph.hasVertex(strArr[i+1]+"(-u)") && capCheck)
            {
                strArr[i+1] += "(-u)"; /* this is how my program creates optimal path with duplicate keys. in this example, if the keyboard is in uppercase, and the next key has a duplicate key on the uppercase keyboard (suffix "(-u)") then use that duplicate instead */
            }
            if(graph.hasVertex(strArr[i+1]+"(-p)") && numCheck)
            {
                strArr[i+1] += "(-p)"; /* see above comment for (-u) */
            }
            int check = capsCrosser(strArr[i],strArr[i+1], graph, capCheck, numCheck);
            if(check != 0) /* if there is a funnel that the path has crossed, then...   . A "funnel" is what i say to explain when a keyboard has travelled from one keyboard to another */
            {
                String[] strArr2 = new String[strArr.length+1];
                if(check == 6)
                {
                    strArr2 = new String[strArr.length+2]; /* if check == 6, then the single path has traversed over more than one funnel */
                }
                for(int j = 0; j < strArr2.length; j++)
                {
                    if(j<=i)
                    {
                        strArr2[j] = strArr[j];
                    }
                    if(j==i+1)
                    {
                        if(check == 1)
                        {
                            strArr2[j] = "CAPS"; /* press CAPS before you travel from the lowercase keyboard and press the key on the uppercase keyboard you were going to press. this is the best way to visualise that */
                            capCheck = !capCheck;
                        }
                        else if(check == 2)
                        {
                            strArr2[j] = "CAPS(-u)"; /* press CAPS(-u) before you travel from the uppercase keyboard and press the key on the lowercase keyboard you were going to press. this is the best way to visualise that */
                            capCheck = !capCheck;
                        }
                        else if(check == 3)
                        {
                            strArr2[j] = "#+="; /* press #+= before you travel from the lowercase keyboard and press the key on the punctuation keyboard you were going to press. this is the best way to visualise that */
                            numCheck = true;
                            capCheck = false;
                        }
                        else if(check == 4)
                        {
                            strArr2[j] = "#+=(-u)";  /* press #+= before you travel from the uppercase keyboard and press the key on the punctuation keyboard you were going to press. this is the best way to visualise that */
                            numCheck = true; 
                            capCheck = false;
                        }
                        else if(check == 5 || check == 6)
                        {
                            strArr2[j] = "ABC"; /* press ABC before you travel from the punctuation keyboard and press the key on the uppercase or lowercase keyboard you were going to press. this is the best way to visualise that */
                            numCheck = false;
                            if(check == 6)
                            {
                                capCheck = true; /* travelled to uppercase keyboard */
                            }
                        }
                    }
                    if(j>i+1)
                    {
                        if(check != 6)
                        {
                            strArr2[j] = strArr[j-1];
                        }
                        if(check == 6)
                        {
                            if(j==i+2)
                            {
                                strArr2[j] = "CAPS"; /* press ABC to go to lowercase, and then CAPS to go from lowercase to uppercase. 
                                I made it intentionally harder for myself by replicating exactly the behaviour of the switch (punctuation can only go to lowercase)*/
                            }
                            else if(j>i+2)
                            {
                                strArr2[j] = strArr[j-2];
                            }
                        }
                    }
                }
                strArr = strArr2;
                i++; /* so that it doesn't then iterate to the key we just addressed and infinitely loop */
                if(check == 6)
                {
                    i++; /* extra add because check == 6 added two places */
                }
                
            }
            
            if(i == strArr.length - 1)
            {
                break; /* because we are manually adding to i (the for variable), it will continue the for loop iteration outside of what we are wanting */
            }
        }
        return strArr;
    }



    public int capsCrosser(String source, String dest, DSAGraph graph, boolean capCheck, boolean numCheck)
    {
        /* for helping with stringFix */
        if(graph.breadthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 1 || graph.depthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 1)
        {
            return 1;
        }
        else if(graph.breadthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 2 || graph.depthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 2)
        {
            return 2;
        }
        else if(graph.breadthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 3 || graph.depthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 3)
        {
            return 3;
        }
        else if(graph.breadthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 4 || graph.depthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 4)
        {
            return 4;
        }
        else if(graph.breadthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 5 || graph.depthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 5)
        {
            return 5;
        }
        else if(graph.breadthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 6 || graph.depthFirstSearchFindCAPSCHECK(source, dest, capCheck, numCheck) == 6)
        {
            return 6;
        }
        else
        {
            return 0;
        }
    }



    public String[] processLine(String csvRow)
    {  //reading one row of a file at a time, separated by a space
        String[] splitLine;
        splitLine = csvRow.split(" "); 
        return splitLine;
    }

    public String[] processLine2(String csvRow)
    {  //reading one row of a file at a time, separated by each character
        String[] splitLine;
        splitLine = csvRow.split(""); 
        return splitLine;
    }


    public String readInString(String[] args)
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



    /* read in the graph files i've created into an actual graph */
    public void readInGraph(String file, DSAGraph graph)
    {
        try{
            graph.wipe(); /* make graph empty */
            File myObj = new File(file);
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
                        if(sArray[i].charAt(0) == '-' && sArray[i].charAt(1) == 'd') /* this prefix means that the connection is directed */
                        {
                            directed = true;
                            sArray[i] = sArray[i].substring(2,sArray[i].length()); /* get rid of the prefix for when actually in the graph */
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
            System.out.println("An error occured");
            e.printStackTrace();
            System.out.println("Due to this error, the program will terminate");
            System.exit(0);
        }
    }



}
