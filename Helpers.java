import java.io.*;
import java.util.*;
public class Helpers {
    
    public static void writeOneRow(String pFileName, String pInputString)
    {
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter(pFileName,true));
            pw.println(pInputString);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file" + e.getMessage());
        }
    }

    public static int saveList(String pFileName, DSALinkedList list)
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
                /*if(label.length() > 2)
                {
                    if(label.substring(label.length()-2).equals("(-u)"))
                    {
                        label = label.substring(0,label.length()-2);
                    }
                }/* */
                if(count == 0)
                {
                    pw.print(label + " ");
                    /*if(label.equals("CAPS"))
                    {
                        capCheck = true;
                    }*/
                }
                else
                {
                    String printString = (String)label;
                    /*if(capCheck)
                    {
                        printString = printString.toUpperCase();
                    }*/
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

    public static int printList(DSALinkedList list)
    {
        int count = 0;
        boolean capCheck = false;
        Iterator ill2 = list.iterator();
        while(ill2.hasNext())
        {
            DSAGraphVertex printV = (DSAGraphVertex)ill2.next();
            String label = (String)printV.getLabel();
            /*if(label.length() > 2)
            {
                if(label.substring(label.length()-2).equals("(-u)"))
                {
                    label = label.substring(0,label.length()-2);
                }
            }*/
            if(count == 0)
            {
                System.out.print(label + " ");
                /*if(label.equals("CAPS"))
                {
                    capCheck = true;
                }*/
            }
            else
            {
                String printString = (String)label;
                /*if(capCheck)
                {
                    printString = printString.toUpperCase();
                }*/
                System.out.print(" -> " + printString);
            }
                
                //count++;
            //System.out.print(label + " -> ");
            count ++;
        }
        System.out.println();
        return count-1;
    }

    public static String[] stringFix(String string, DSAGraph graph)
    {
        int count = 0;
        boolean capsCheck = false; /* placed outside of loop so that loop can keep track of if CAPS LOCK is on */
        String[] strArr = string.split("");
        for(int i = 0; i < strArr.length; i++)
        {
            if((Character.isUpperCase(strArr[i].charAt(0)) || (strArr[i].length() > 1 && (graph.hasVertex(strArr[i]+"(-u)")) && (!graph.hasVertex(strArr[i])))) && !capsCheck)
            {
                String[] strArr2 = new String[strArr.length + 1];
                for(int j = 0; j < strArr2.length; j++)
                {
                    if(j<i)
                    {
                        strArr2[j] = strArr[j];
                    }
                    else if(j==i)
                    {
                        strArr2[j] = "CAPS";
                    }
                    else if(j==i+1)
                    {
                        strArr2[j] = strArr[i];
                    }
                    else
                    {
                        strArr2[j] = strArr[j-1];
                    }
                }
                strArr = strArr2;
                i +=1;
                count ++;
                capsCheck = true;
            }
            else if((Character.isLowerCase(strArr[i].charAt(0)) || (strArr[i].length() > 1 && (graph.hasVertex(strArr[i])) && !(graph.hasVertex(strArr[i]+"(-u)")))) && capsCheck)
            {
                String[] strArr2 = new String[strArr.length + 1];
                for(int j = 0; j < strArr2.length; j++)
                {
                    if(j<i)
                    {
                        strArr2[j] = strArr[j];
                    }
                    else if(j==i)
                    {
                        strArr2[j] = "CAPS";
                    }
                    else if(j==i+1)
                    {
                        strArr2[j] = strArr[i];
                    }
                    else
                    {
                        strArr2[j] = strArr[j-1];
                    }
                }
                strArr = strArr2;
                i +=1;
                count ++;
                capsCheck = false;
            }
            if(strArr[i].equals(" "))
            {
                strArr[i] = "SPACE";
            }
            if(graph.hasVertex(strArr[i]+"(-u)") && capsCheck)
            {
                strArr[i] += "(-u)";
            }
            
            

            // if(strArr[i].length() < 2)
            // {
            //     strArr[i] = strArr[i].toLowerCase();

            // }
            
            /*System.out.println(strArr[i]);
            System.out.println(i);
            System.out.println(strArr.length);*/
            if(i == strArr.length - 1)
            {
                break;
            }
            
        }
        for(int i = 0; i < strArr.length; i++)
        {
            System.out.print(strArr[i] + " ");
        }
        return strArr;
    }





    public static String[] stringFix2(String string, DSAGraph graph)
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
                strArr3[k] = "1";
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
                strArr[i+1] = "SPACE";
            }
            System.out.print(capCheck);
            if(graph.hasVertex(strArr[i+1]+"(-u)") && capCheck)
            {
                strArr[i+1] += "(-u)";
            }
            if(graph.hasVertex(strArr[i+1]+"(-p)") && numCheck)
            {
                strArr[i+1] += "(-p)";
            }
            int check = capsCrosser(strArr[i],strArr[i+1], graph, capCheck, numCheck);
            if(check != 0)
            {
                String[] strArr2 = new String[strArr.length+1];
                if(check == 6)
                {
                    strArr2 = new String[strArr.length+2];
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
                            strArr2[j] = "CAPS";
                            capCheck = !capCheck;
                        }
                        else if(check == 2)
                        {
                            strArr2[j] = "CAPS(-u)"; 
                            capCheck = !capCheck;
                        }
                        else if(check == 3)
                        {
                            strArr2[j] = "#+=";
                            numCheck = true;
                            capCheck = false;
                        }
                        else if(check == 4)
                        {
                            strArr2[j] = "#+=(-u)";
                            numCheck = true; 
                            capCheck = false;
                        }
                        else if(check == 5 || check == 6)
                        {
                            strArr2[j] = "ABC";
                            numCheck = false;
                            /*if(check == 6)
                            {
                                capCheck = true;
                            }*/
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
                                strArr2[j] = "CAPS";
                            }
                            else if(j>i+2)
                            {
                                strArr2[j] = strArr[j-2];
                            }
                        }
                    }
                }
                strArr = strArr2;
                i++;
                
            }
            
            if(i == strArr.length - 1)
            {
                break;
            }
        }
        for(int i = 0; i < strArr.length; i++)
        {
            System.out.print(strArr[i] + ".");
        }
        return strArr;
    }



    public static int capsCrosser(String source, String dest, DSAGraph graph, boolean capCheck, boolean numCheck)
    {
        //System.out.println(graph.hasVertex("1"));
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
}
