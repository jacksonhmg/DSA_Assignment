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
                    if(label.substring(label.length()-2).equals("-a"))
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
                if(label.substring(label.length()-2).equals("-a"))
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
        boolean capsCheck = false;
        String[] strArr = string.split("");
        for(int i = 0; i < strArr.length; i++)
        {
            if(Character.isUpperCase(strArr[i].charAt(0)) && !capsCheck)
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
            if(Character.isLowerCase(strArr[i].charAt(0)) && capsCheck)
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
            if(graph.hasVertex(strArr[i]+"-a") && capsCheck)
            {
                strArr[i] += "-a";
            }
            if(strArr[i].equals(" "))
            {
                strArr[i] = "SPACE";
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
        return strArr;
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
