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
            Helpers.saveList(pFileName, list);
        }
        else if(option == 1)
        {
            Helpers.printList(list);
        }
        else if(option == 2)
        {
            Helpers.saveList(pFileName, list);
            Helpers.printList(list);
        }
        return list.length() - 1;
    }

    public int breadthStringPath(String string, String pFileName, int option)
    { 
        int count = 0;
        String[] strArr = Helpers.stringFix2(string, this);
        for(int i = 0; i < strArr.length-1; i++)
        {
            count += breadthFirstSearchFind(strArr[i], strArr[i+1], pFileName, option);
        }
        return count;
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
        //System.out.println(v.getLabel());
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
            Helpers.saveList(pFileName, list);
        }
        else if(option == 1)
        {
            Helpers.printList(list);
        }
        else if(option == 2)
        {
            Helpers.saveList(pFileName, list);
            Helpers.printList(list);
        }
        else if(option == 4)
        {

        }
        return list.length() -1;
    }





    public int breadthFirstSearchFindCAPSCHECK(Object source, Object dest, boolean capCheck, boolean numCheck)
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
        System.out.print(v.getLabel());
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
                    return shortestPathBreadthCAPSCHECK(T, getVertex(dest), getVertex(source), capCheck, numCheck);
                }
            }
        }
        return 0;
    }

    public int shortestPathBreadthCAPSCHECK(DSAQueue queue, DSAGraphVertex dest, DSAGraphVertex source, boolean capCheck, boolean numCheck)
    {
        DSALinkedList list = new DSALinkedList();
        int crossCheck = 0;
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
                    if(!numCheck && !capCheck)
                    {
                        if(w.getLabel().equals("ABC"))
                        {
                            crossCheck = 3;
                        }
                    }
                    if(!numCheck && capCheck)
                    {
                        if(w.getLabel().equals("ABC"))
                        {
                            crossCheck = 4;
                        }
                    }
                    if(numCheck)
                    {
                        if(w.getLabel().equals("#+="))
                        {
                            crossCheck = 5;
                        }
                    }
                    if(!capCheck)
                    {
                        if(w.getLabel().equals("CAPS(-u)")) /* add boolean check to flip this between either CAPS(-u) or CAPS depening on CAPCHECK atm (which keyboard its on) */
                        {
                            crossCheck = 1;
                        }
                    }
                    else if(capCheck)
                    {
                        if(w.getLabel().equals("CAPS")) /* add boolean check to flip this between either CAPS(-u) or CAPS depening on CAPCHECK atm (which keyboard its on) */
                        {
                            crossCheck = 2;
                        }
                    }
                    stop = true;
                }
            }
            v = w;
        } while(!((v.getLabel()).equals(source.getLabel())));
        return crossCheck;
    }



    public int depthFirstSearchFindCAPSCHECK(Object source, Object dest, boolean capCheck, boolean numCheck)
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
        //System.out.println(v.getLabel());
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
                    return shortestPathDepthCAPSCHECK(T, getVertex(dest), getVertex(source), capCheck, numCheck);
                }
            }
            v = (DSAGraphVertex)S.pop();
            
        }
        return 0;
    }


    public int shortestPathDepthCAPSCHECK(DSAQueue queue, DSAGraphVertex dest, DSAGraphVertex source, boolean capCheck, boolean numCheck)
    {
        DSALinkedList list = new DSALinkedList();
        int crossCheck = 0;
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
                    if(!numCheck && !capCheck)
                    {
                        if(w.getLabel().equals("ABC"))
                        {
                            crossCheck = 3;
                        }
                    }
                    if(!numCheck && capCheck)
                    {
                        if(w.getLabel().equals("ABC"))
                        {
                            crossCheck = 4;
                        }
                    }
                    if(numCheck)
                    {
                        if(w.getLabel().equals("#+="))
                        {
                            crossCheck = 5;
                        }
                    }
                    if(!capCheck)
                    {
                        if(w.getLabel().equals("CAPS(-u)")) /* add boolean check to flip this between either CAPS(-u) or CAPS depening on CAPCHECK atm (which keyboard its on) */
                        {
                            crossCheck = 1;
                        }
                    }
                    else if(capCheck)
                    {
                        if(w.getLabel().equals("CAPS")) /* add boolean check to flip this between either CAPS(-u) or CAPS depening on CAPCHECK atm (which keyboard its on) */
                        {
                            crossCheck = 2;
                        }
                    }
                    stop = true;
                }
            }
            v = w;
        } while(!((v.getLabel()).equals(source.getLabel())));
        return crossCheck;
    }












    public int depthStringPath(String string, String pFileName, int option)
    {
        int count = 0;
        String[] strArr = Helpers.stringFix2(string, this);
        for(int i = 0; i < strArr.length-1; i++)
        {
            count += depthFirstSearchFind(strArr[i], strArr[i+1], pFileName, option);
        }
        return count;
    }

    public void displayRankedPaths(String inputString, String pFileName, int option)
    {
        if(depthStringPath(inputString, pFileName, 4) < breadthStringPath(inputString, pFileName, 4))
        {
            File f= new File(pFileName);           //file to be delete  
            f.delete();
            Helpers.writeOneRow(pFileName, "Depth wins for " + inputString + " ! \n");
            System.out.println("Depth wins for " + inputString + " ! \n");
            Helpers.writeOneRow(pFileName, "Depth path:");
            System.out.println("Depth path:");
            int x = depthStringPath(inputString, pFileName, option);
            Helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            Helpers.writeOneRow(pFileName, "Breadth path:");
            System.out.println("Breadth path:");
            x = breadthStringPath(inputString, pFileName, option);
            Helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            System.out.println("See " + pFileName + " for saved ranked paths");
        }
        else if(breadthStringPath(inputString, pFileName, 4) < depthStringPath(inputString,pFileName, 4))
        {
            File f= new File(pFileName);           //file to be delete  
            f.delete();
            Helpers.writeOneRow(pFileName, "Breadth wins for " + inputString + " ! \n");
            System.out.println("Breadth wins for " + inputString + " ! \n");
            Helpers.writeOneRow(pFileName, "Breadth path:");
            System.out.println( "Breadth path:");
            int x = breadthStringPath(inputString, pFileName, option);
            Helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            Helpers.writeOneRow(pFileName, "Depth path:");
            System.out.println( "Depth path:");
            x = depthStringPath(inputString, pFileName, option);
            Helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            System.out.println("See " + pFileName + " for saved ranked paths");
        }
        else
        {
            File f= new File(pFileName);           //file to be delete  
            f.delete();
            Helpers.writeOneRow(pFileName, "TIE for " + inputString + " ! \n");
            System.out.println("Breadth wins for " + inputString + " ! \n");
            Helpers.writeOneRow(pFileName, "Breadth path:");
            System.out.println( "Breadth path:");
            int x = breadthStringPath(inputString, pFileName, option);
            Helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            Helpers.writeOneRow(pFileName, "Depth path:");
            System.out.println( "Depth path:");
            x = depthStringPath(inputString, pFileName, option);
            Helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            System.out.println("See " + pFileName + " for saved ranked paths");
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