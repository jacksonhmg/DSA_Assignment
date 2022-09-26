import java.util.*;
import java.io.*;

public class DSAGraph{
    public DSALinkedList vertices = new DSALinkedList();
    public DSALinkedList edges = new DSALinkedList();

    public void addVertex(Object label, Object value)
    {
        if(!(hasVertex(label)))
        {
            DSAGraphVertex vertex = new DSAGraphVertex(label, value);
            vertices.insertLast(vertex);
        }
    }

    public void addEdge(Object label1, Object label2, boolean directed)
    {
        String edgeLabel = label1 + "to" + label2;
        if(!(hasEdge(label1, label2)))
        {
            DSAGraphEdge edge = new DSAGraphEdge(getVertex(label1), getVertex(label2), edgeLabel, null, directed);
            DSAGraphVertex vert1 = getVertex(label1);
            DSAGraphVertex vert2 = getVertex(label2);
            vert1.addEdge(vert2);
            if(!directed)
            {
                vert2.addEdge(vert1);
            }
            edges.insertLast(edge);
        }
    }

    public boolean hasVertex(Object label)
    {
        Iterator ill = vertices.iterator();
        boolean check = false;
        while(ill.hasNext())
        {
            DSAGraphVertex testV = (DSAGraphVertex)ill.next();
            Object sTest = testV.getLabel();
            if(sTest.equals(label))
            {
                check = true;
            }
        }
        return check;
    }

    public boolean hasEdge(Object label1, Object label2)
    {
        DSAGraphVertex vert = getVertex(label1);
        boolean check = false;
        if(isAdjacent(label1, label2))
        {
            check = true;
        }
        
        return check;
    }


    public int getVertexCount()
    {
        Iterator ill = vertices.iterator();
        int count = 0;
        while(ill.hasNext())
        {
            ill.next();
            count++;
        }
        return count;
    }

    public int getEdgeCount()
    {
        Iterator ill = edges.iterator();
        int count = 0;
        while(ill.hasNext())
        {
            ill.next();
            count++;
        }
        return count;
    }

    public DSAGraphVertex getVertex(Object label)
    {
        Iterator ill = vertices.iterator();
        DSAGraphVertex vertex = null;
        while(ill.hasNext())
        {
            DSAGraphVertex testV = (DSAGraphVertex)ill.next();
            Object sTest = testV.getLabel();
            if(sTest.equals(label))
            {
                vertex = testV;
            }
        }
        return vertex;
    }

    public DSALinkedList getAdjacent(Object label)
    {
        DSAGraphVertex vert = getVertex(label);
        DSALinkedList list = vert.getAdjacent();
    
        return list;
    }

    public boolean isAdjacent(Object label1, Object label2)
    {
        Iterator ill = edges.iterator();
        DSAGraphVertex vert1 = getVertex(label1);
        DSAGraphVertex vert2 = getVertex(label2);
        boolean checker = false;
        while(ill.hasNext())
        {
            DSAGraphEdge edge = (DSAGraphEdge)ill.next();
            if(!(edge.isDirected()))
            {
                if((edge.getFrom().getLabel().equals(label1) && edge.getTo().getLabel().equals(label2)) || (edge.getFrom().getLabel().equals(label2) && edge.getTo().getLabel().equals(label1)))
                {
                    checker = true;
                }
            }
            if(edge.isDirected())
            {
                if((edge.getFrom().getLabel().equals(label1) && edge.getTo().getLabel().equals(label2)))
                {
                    checker = true;
                }
            }
        }
        return checker;
    }

    public void displayAsList()
    {
        Iterator ill = vertices.iterator();
        while(ill.hasNext())
        {
            DSAGraphVertex vert = ((DSAGraphVertex)(ill.next()));
            Object label = vert.getLabel();
            DSALinkedList list = vert.getAdjacent();
            System.out.print(label + ": ");
            Iterator ill2 = list.iterator();
            while(ill2.hasNext())
            {
                DSAGraphVertex vert2 = ((DSAGraphVertex)(ill2.next()));
                Object label2 = vert2.getLabel();
                System.out.print(label2 + " | ");
            }
            System.out.println();
        }
    }

    public void displayAsMatrix()
    {
        int tick;
        tick = 0;
    
        Object[] headers = new Object[getVertexCount()];

        System.out.print(" | ");
        Iterator ill = vertices.iterator();
        while(ill.hasNext())
        {
            DSAGraphVertex vert = ((DSAGraphVertex)(ill.next()));
            Object label = vert.getLabel();
            headers[tick] = label;
            tick++;
            System.out.print(label + " | ");
        }
        System.out.println();

        Iterator ill2 = vertices.iterator();
        while(ill2.hasNext())
        {
            
            DSAGraphVertex vert = ((DSAGraphVertex)(ill2.next()));
            Object label = vert.getLabel();
            DSALinkedList list = vert.getAdjacent();
            System.out.print(label + "| ");
            for(int i = 0; i < headers.length; i++)
            {
                boolean check = false;
                Iterator ill3 = list.iterator();
                while(ill3.hasNext())
                {
                    DSAGraphVertex vert2 = ((DSAGraphVertex)(ill3.next()));
                    Object label2 = vert2.getLabel();
                    if(label2.equals(headers[i]))
                    {
                        check = true;
                    }
                }
                if(check)
                {
                    System.out.print("1 | ");
                }
                else
                {
                    System.out.print("0 | ");
                }
            }
            System.out.println();
        }
    }


    public void breadthFirstSearch()
    {
        DSAQueue T = new DSAQueue();
        DSAQueue Q = new DSAQueue();
        Iterator clearILL = vertices.iterator();
        while(clearILL.hasNext())
        {
            DSAGraphVertex v = (DSAGraphVertex)clearILL.next();
            v.clearVisited();
        }
        DSAGraphVertex v = (DSAGraphVertex)vertices.head.getValue();
        v.setVisited();
        Q.enqueue(v);
        while(!(Q.isEmpty()))
        {
            v = (DSAGraphVertex)Q.dequeue();
            Iterator ill = (v.getAdjacent()).iterator();
            while(ill.hasNext())
            {
                DSAGraphVertex w = (DSAGraphVertex)ill.next();
                if(!(w.getVisited()))
                {
                    T.enqueue(v);
                    T.enqueue(w);
                    w.setVisited();
                    Q.enqueue(w);
                }
            }
        }
    }


    public int breadthFirstSearchFind(Object source, Object dest, String pFileName, int option)
    {

        DSAQueue T = new DSAQueue();
        DSAQueue Q = new DSAQueue();
        Iterator clearILL = vertices.iterator();
        while(clearILL.hasNext())
        {
            DSAGraphVertex v = (DSAGraphVertex)clearILL.next();
            v.clearVisited();
        }
        DSAGraphVertex v = getVertex(source);
        v.setVisited();
        Q.enqueue(v);

        while(!(Q.isEmpty()))
        {
            v = (DSAGraphVertex)Q.dequeue();
            Iterator ill = (v.getAdjacent()).iterator();
            while(ill.hasNext())
            {
                DSAGraphVertex w = (DSAGraphVertex)ill.next();
                if(!(w.getVisited()))
                {
                    T.enqueue(v);
                    T.enqueue(w);
                    w.setVisited();
                    Q.enqueue(w);
                }
                if(w.getLabel().equals(dest))
                {
                    return shortestPathBreadth(T, getVertex(dest), getVertex(source), pFileName, option);
                }
            }
        }
        return 0;
    }



    public int shortestPathBreadth(DSAQueue queue, DSAGraphVertex dest, DSAGraphVertex source, String pFileName, int option)
    {
        DSALinkedList list = new DSALinkedList();

        DSAGraphVertex w = new DSAGraphVertex(null, null);
        list.insertLast(dest);
        DSAGraphVertex v = dest;
        do{
            boolean stop = false;
            Iterator ill = queue.iterator();
            while(ill.hasNext() && !stop)
            {
                w = (DSAGraphVertex)ill.next();
                if(isAdjacent(w.getLabel(), v.getLabel()))
                {
                    list.insertFirst(w);
                    stop = true;
                }
            }
            v = w;
        } while(!((v.getLabel()).equals(source.getLabel())));
        if(option == 0)
        {
            saveList(pFileName, list);
        }
        else if(option == 1)
        {
            printList(list);
        }
        else if(option == 2)
        {
            saveList(pFileName, list);
            printList(list);
        }
        return list.length() - 1;
    }


    public int breadthStringPath(String string, String pFileName, int option)
    { 
        int count = 0;
        String[] strArr = stringFix(string);
        for(int i = 0; i < strArr.length-1; i++)
        {
            count += breadthFirstSearchFind(strArr[i], strArr[i+1], pFileName, option);
        }
        return count;
    }


    public String[] stringFix(String string)
    {
        int count = 0;
        String[] strArr = string.split("");
        for(int i = 0; i < strArr.length; i++)
        {
            if(Character.isUpperCase(strArr[i].charAt(0)))
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
            }
            if(strArr[i].equals(" "))
            {
                strArr[i] = "SPACE";
            }
            if(strArr[i].length() < 2)
            {
                strArr[i] = strArr[i].toLowerCase();

            }
            
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


    public void depthFirstSearch()
    {
        DSAQueue T = new DSAQueue();
        DSAStack S = new DSAStack();
        Iterator clearILL = vertices.iterator();
        while(clearILL.hasNext())
        {
            DSAGraphVertex v = (DSAGraphVertex)clearILL.next();
            v.clearVisited();
        }
        DSAGraphVertex v = (DSAGraphVertex)vertices.head.getValue();
        v.setVisited();
        S.push(v);
        while(!(S.isEmpty()))
        {
            Iterator ill = (v.getAdjacent()).iterator();
            while(ill.hasNext())
            {
                DSAGraphVertex w = (DSAGraphVertex)ill.next();
                if(!(w.getVisited()))
                {
                    T.enqueue(v);
                    T.enqueue(w);
                    w.setVisited();
                    S.push(w);
                    v = w;
                }
            }
            v = (DSAGraphVertex)S.pop();
        }

    }


    public int depthFirstSearchFind(Object source, Object dest, String pFileName, int option)
    {
        int count = 0;
        DSAQueue T = new DSAQueue();
        DSAStack S = new DSAStack();
        Iterator clearILL = vertices.iterator();
        while(clearILL.hasNext())
        {
            DSAGraphVertex v = (DSAGraphVertex)clearILL.next();
            v.clearVisited();
        }
        DSAGraphVertex v = getVertex(source);
        //System.out.println("\n" + v.getLabel());
        v.setVisited();
        S.push(v);
        T.enqueue(v);
        while(!(S.isEmpty()))
        {
            Iterator ill = (v.getAdjacent()).iterator();
            while(ill.hasNext())
            {
                DSAGraphVertex w = (DSAGraphVertex)ill.next();
                if(!(w.getVisited()))
                {
                    T.enqueue(v);
                    T.enqueue(w);
                    w.setVisited();
                    S.push(w);
                }
                if(w.getLabel().equals(dest))
                {
                    return shortestPathDepth(T, getVertex(dest), getVertex(source), pFileName, option);
                }
            }
            v = (DSAGraphVertex)S.pop();
            
        }
        return 0;
    }


    public int shortestPathDepth(DSAQueue queue, DSAGraphVertex dest, DSAGraphVertex source, String pFileName, int option)
    {
        DSALinkedList list = new DSALinkedList();

        DSAGraphVertex w = new DSAGraphVertex(null, null);
        list.insertLast(dest);
        DSAGraphVertex v = dest;
        do{
            boolean stop = false;
            Iterator ill = queue.iterator();
            while(ill.hasNext() && !stop)
            {
                w = (DSAGraphVertex)ill.next();
                if(isAdjacent(w.getLabel(), v.getLabel()))
                {
                    list.insertFirst(w);
                    stop = true;
                }
            }
            v = w;
        } while(!((v.getLabel()).equals(source.getLabel())));
        if(option == 0)
        {
            saveList(pFileName, list);
        }
        else if(option == 1)
        {
            printList(list);
        }
        else if(option == 2)
        {
            saveList(pFileName, list);
            printList(list);
        }
        return list.length() -1;
    }


    public int printList(DSALinkedList list)
    {
        int count = 0;
        Iterator ill2 = list.iterator();
        while(ill2.hasNext())
        {
            DSAGraphVertex printV = (DSAGraphVertex)ill2.next();
            System.out.print(printV.getLabel() + " -> ");
            count ++;
        }
        System.out.println();
        return count-1;
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
                if(count == 0)
                {
                    pw.print(printV.getLabel() + " ");
                    if(printV.getLabel().equals("CAPS"))
                    {
                        capCheck = true;
                    }
                }
                else
                {
                    String printString = (String)printV.getLabel();
                    if(capCheck)
                    {
                        printString = printString.toUpperCase();
                    }
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


    public int depthStringPath(String string, String pFileName, int option)
    {
        int count = 0;
        String[] strArr = stringFix(string);
        for(int i = 0; i < strArr.length-1; i++)
        {
            count += depthFirstSearchFind(strArr[i], strArr[i+1], pFileName, option);
        }
        return count;
    }


    public static void writeOneRow(String pFileName, String pInputString){
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileWriter(pFileName,true));
            pw.println(pInputString);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file" + e.getMessage());
        }
    }

    public void displayRankedPaths(String inputString, String pFileName, int option)
    {
        if(depthStringPath(inputString, pFileName, 4) < breadthStringPath(inputString, pFileName, 4))
        {
            File f= new File(pFileName);           //file to be delete  
            f.delete();
            writeOneRow(pFileName, "Depth wins for " + inputString + "! \n");
            writeOneRow(pFileName, "Depth path:");
            int x = depthStringPath(inputString, pFileName, option);
            writeOneRow(pFileName, x + " steps\n");
            writeOneRow(pFileName, "Breadth path:");
            x = breadthStringPath(inputString, pFileName, option);
            writeOneRow(pFileName, x + " steps\n");
        }
        else if(breadthStringPath(inputString, pFileName, 4) < depthStringPath(inputString,pFileName, 4))
        {
            File f= new File(pFileName);           //file to be delete  
            f.delete();
            writeOneRow(pFileName, "Breadth wins for " + inputString + "! \n");
            writeOneRow(pFileName, "Breadth path:");
            int x = breadthStringPath(inputString, pFileName, option);
            writeOneRow(pFileName, x + " steps\n");
            writeOneRow(pFileName, "Depth path:");
            x = depthStringPath(inputString, pFileName, option);
            writeOneRow(pFileName, x + " steps\n");
        }
        else
        {
            File f= new File(pFileName);           //file to be delete  
            f.delete();
            writeOneRow(pFileName, "TIE for " + inputString + "! \n");
            writeOneRow(pFileName, "Breadth path:");
            int x = breadthStringPath(inputString, pFileName, option);
            writeOneRow(pFileName, x + " steps\n");
            writeOneRow(pFileName, "Depth path:");
            x = depthStringPath(inputString, pFileName, option);
            writeOneRow(pFileName, x + " steps\n");
        }
    }

    /*public int myDepthFunc(String source, String dest)
    {
        DSAQueue queue = new DSAQueue();
        int count = 0;
        Iterator clearIll = vertices.iterator();
        while(clearIll.hasNext())
        {
            DSAGraphVertex clearV = (DSAGraphVertex)clearIll.next();
            clearV.clearVisited();
        }
        myDepthFunc2(source, dest,queue, count);
    }

    public int myDepthFunc2(String source, String dest, DSAQueue queue, int count)
    {
        DSAGraphVertex v = getVertex(source);
        v.setVisited();
        queue.enqueue(v);
        Iterator ill = (v.getAdjacent()).iterator();
        while(ill.hasNext())
        {
            DSAGraphVertex w = (DSAGraphVertex)ill.next();
            if(!(w.getVisited()))
            {
                count++;
                //queue.enqueue(w);
                if(w != getVertex(dest))
                {
                    myDepthFunc2((String)w.getLabel(), dest, queue, count);
                }
            }
        }
    }*/




}