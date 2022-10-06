/* Unless otherwise stated, every method up until shortestPathBreadth is included from my Practical 6 work in DSA class */



import java.util.*;
import java.io.*;

public class DSAGraph{
    public DSALinkedList vertices = new DSALinkedList();
    public DSALinkedList edges = new DSALinkedList();
    public Helpers helpers = new Helpers();


    /* not from Practical 6, this was created for this assignment */
    public void wipe()
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
    }

    /* used from my Practical 6 work */
    public void addVertex(Object label, Object value)
    {
        if(!(hasVertex(label)))
        {
            DSAGraphVertex vertex = new DSAGraphVertex(label, value);
            vertices.insertLast(vertex);
        }
    }

    /* used from my Practical 6 work */
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


    /* used from my Practical 6 work */
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

    /* used from my Practical 6 work */
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

    /* used from my Practical 6 work */
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

    /* used from my Practical 6 work */
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

    /* used from my Practical 6 work */
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

    /* used from my Practical 6 work */
    public DSALinkedList getAdjacent(Object label)
    {
        DSAGraphVertex vert = getVertex(label);
        DSALinkedList list = vert.getAdjacent();
    
        return list;
    }

    /* used from my Practical 6 work */
    public boolean isAdjacent(Object label1, Object label2)
    {
        Iterator ill = edges.iterator();
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

    /* created for this assignment */
    public void deleteVertice(Object label)
    {
        DSALinkedList newVerticeList = new DSALinkedList();
        Iterator ill = vertices.iterator();
        while(ill.hasNext())
        {
            DSAGraphVertex v = (DSAGraphVertex)ill.next();
            if(!(v.getLabel().equals(label)))
            {
                DSALinkedList newLinks = new DSALinkedList();
                Iterator intraILL = v.getAdjacent().iterator();
                while(intraILL.hasNext())
                {
                    DSAGraphVertex link = (DSAGraphVertex)intraILL.next(); /* checking each link to make sure its not connected to vertice to be deleted */
                    if(!(link.getLabel().equals(label)))
                    {
                        newLinks.insertLast(link); /* if a vertice isn't the one to be deleted, remove any of its connections to the vertice to be deleted */
                    }
                }
                v.links = newLinks; /* including every vertice except the one to be deleted */
                newVerticeList.insertLast(v);
            }
        }
        vertices = newVerticeList;
        
        DSALinkedList newEdgeList = new DSALinkedList();
        Iterator ill2 = edges.iterator();
        while(ill2.hasNext())
        {
            DSAGraphEdge e = (DSAGraphEdge)ill2.next();
            if(!(e.getFrom().getLabel().equals(label)) && !(e.getTo().getLabel().equals(label)))
            {
                newEdgeList.insertLast(e); /* dont include any edges that include the vertice to be deleted */
            }
        }
        edges = newEdgeList;
    }

    /* created for this assignment */
    public void deleteEdge(Object label1, Object label2)
    {
        DSAGraphVertex v1 = getVertex(label1);
        DSAGraphVertex v2 = getVertex(label2);

    }

    /* created for this assignment */
    public void updateNode(Object oldLabel, Object newLabel)
    {
        getVertex(oldLabel).setLabel(newLabel);
    }


    /* used from my Practical 6 work */
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

    /* used from my Practical 6 work */
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


    /* mostly taken from practical 6 but tweaked a bit to work for this assignment. see below comments */
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
                    return shortestPathBreadth(T, getVertex(dest), getVertex(source), pFileName, option); /* now for this assignment, it returns the shortestPath steps */
                }
            }
        }
        return 0;
    }

    public int shortestPathBreadth(DSAQueue queue, DSAGraphVertex dest, DSAGraphVertex source, String pFileName, int option)
    {
        DSALinkedList list = new DSALinkedList();

        DSAGraphVertex w = new DSAGraphVertex(null, null);
        list.insertLast(dest); /* start from destination and work your way back */
        DSAGraphVertex v = dest;
        do{
            boolean stop = false;
            Iterator ill = queue.iterator();
            while(ill.hasNext() && !stop)
            {
                w = (DSAGraphVertex)ill.next();
                if(isAdjacent(w.getLabel(), v.getLabel())) /* test that w is connected to v (and not other way round) so that you can account for directional edges */
                {
                    list.insertFirst(w);
                    stop = true; /* no need to continue checking for this specific key */
                }
            }
            v = w;
        } while(!((v.getLabel()).equals(source.getLabel()))); /* while you have not reached the source */

        if(option == 0)
        {
            helpers.saveList(pFileName, list);
        }
        else if(option == 1)
        {
            helpers.printList(list);
        }
        else if(option == 2)
        {
            helpers.saveList(pFileName, list);
            helpers.printList(list);
        }
        return list.length() - 1; /* return number of steps / keys crossed taken to complete */
    }

    /* this method repeats the process of finding the optimal path from one key to another, but for the entire string */
    public int breadthStringPath(String string, String pFileName, int option)
    { 
        int count = 0;
        String[] strArr = helpers.stringFix2(string, this);
        for(int i = 0; i < strArr.length-1; i++)
        {
            count += breadthFirstSearchFind(strArr[i], strArr[i+1], pFileName, option);
        }
        return count;
    }

    /* same as breadthFirstSearchFind. mostly from P6 but tweaked slightly */
    public int depthFirstSearchFind(Object source, Object dest, String pFileName, int option)
    {
        DSAQueue T = new DSAQueue();
        DSAStack S = new DSAStack();
        Iterator clearILL = vertices.iterator();
        while(clearILL.hasNext())
        {
            DSAGraphVertex v = (DSAGraphVertex)clearILL.next();
            v.clearVisited();
        }
        DSAGraphVertex v = getVertex(source);
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
            helpers.saveList(pFileName, list);
        }
        else if(option == 1)
        {
            helpers.printList(list);
        }
        else if(option == 2)
        {
            helpers.saveList(pFileName, list);
            helpers.printList(list);
        }
        else if(option == 4)
        {

        }
        return list.length() -1;
    }


    public int depthStringPath(String string, String pFileName, int option)
    {
        int count = 0;
        String[] strArr = helpers.stringFix2(string, this);
        for(int i = 0; i < strArr.length-1; i++)
        {
            count += depthFirstSearchFind(strArr[i], strArr[i+1], pFileName, option);
        }
        return count;
    }


    /* The next four methods are the CAPSCHECK methods. This methods help stringFix (Helpers.java) find if a path would've crossed over into another keyboard,
     * either uppercase version or punctuation version which then allows stringFix to backtrack and place a traversal key in the path to make the path make sense.
     * For example, if the path was crossing from lowercase keyboard to uppercase keyboard to output the path, stringFix would go back and ensure the path CLICKS onto
     * CAPS before it enters uppercase. See stringFix (Helpers.java) for more details
     */

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
                        if(w.getLabel().equals("ABC")) /* so if its in the lowercase keyboard and crosses over into the punctuation keyboard */
                        {
                            crossCheck = 3;
                        }
                    }
                    if(!numCheck && capCheck)
                    {
                        if(w.getLabel().equals("ABC")) /* if its in the uppercase keyboard and crosses over into the puncuation keyboard */
                        {
                            crossCheck = 4;
                        }
                    }
                    if(numCheck)
                    {
                        if(w.getLabel().equals("#+=") && crossCheck != 6)
                        {
                            crossCheck = 5;
                        }
                        if(w.getLabel().equals("CAPS(-u)")) /* have to address this scenario in case goes from punctuation to upper. therefore needs to pass through lower. therefore passing through 2 keyboards in one path*/
                        {
                            crossCheck = 6;
                        }
                    }
                    else if(!capCheck)
                    {
                        if(w.getLabel().equals("CAPS(-u)")) /* if its in lowercase and crosses over into uppercase */
                        {
                            crossCheck = 1;
                        }
                    }
                    else if(capCheck)
                    {
                        if(w.getLabel().equals("CAPS")) /* if its in uppercase and crosses over into lowercas */
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
        DSAQueue T = new DSAQueue();
        DSAStack S = new DSAStack();
        Iterator clearILL = vertices.iterator();
        while(clearILL.hasNext())
        {
            DSAGraphVertex v = (DSAGraphVertex)clearILL.next();
            v.clearVisited();
        }
        DSAGraphVertex v = getVertex(source);
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



    /* SEE COMMENTS FOR shortestPathBreadthCAPSCHECK */
    public int shortestPathDepthCAPSCHECK(DSAQueue queue, DSAGraphVertex dest, DSAGraphVertex source, boolean capCheck, boolean numCheck)
    {

        /* SEE COMMENTS FOR shortestPathBreadthCAPSCHECK */
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
                        if(w.getLabel().equals("#+=") && crossCheck != 6)
                        {
                            crossCheck = 5;
                        }
                        if(w.getLabel().equals("CAPS(-u)")) 
                        {
                            crossCheck = 6;
                        }
                    }
                    else if(!capCheck)
                    {
                        if(w.getLabel().equals("CAPS(-u)")) 
                        {
                            crossCheck = 1;
                        }
                    }
                    else if(capCheck)
                    {
                        if(w.getLabel().equals("CAPS"))
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


    public void displayRankedPaths(String inputString, String pFileName, int option)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nWould you like to keep the uppercase/punctuation suffixes on keys printed/saved. These suffixes help show which keyboard specific keys with duplicates are from. Enter true for yes, or false for no");
        helpers.printSaveCheck = sc.nextBoolean();
        if(depthStringPath(inputString, pFileName, 4) < breadthStringPath(inputString, pFileName, 4)) /* option 4 is used here so that nothing is double printed / double saved */
        {
            File f= new File(pFileName); 
            f.delete(); //file is deleted to be rewritten over
            /* format for outputting */
            helpers.writeOneRow(pFileName, "Depth wins for " + inputString + " ! \n");
            System.out.println("Depth wins for " + inputString + " ! \n");
            helpers.writeOneRow(pFileName, "Depth path:");
            System.out.println("Depth path:");
            int x = depthStringPath(inputString, pFileName, option);
            helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            helpers.writeOneRow(pFileName, "Breadth path:");
            System.out.println("Breadth path:");
            x = breadthStringPath(inputString, pFileName, option);
            helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            System.out.println("See " + pFileName + " for saved ranked paths");
        }
        else if(breadthStringPath(inputString, pFileName, 4) < depthStringPath(inputString,pFileName, 4))
        {
            File f= new File(pFileName); 
            f.delete(); //file is deleted to be rewritten over
            /* format for outputting */
            helpers.writeOneRow(pFileName, "Breadth wins for " + inputString + " ! \n");
            System.out.println("Breadth wins for " + inputString + " ! \n");
            helpers.writeOneRow(pFileName, "Breadth path:");
            System.out.println( "Breadth path:");
            int x = breadthStringPath(inputString, pFileName, option);
            helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            helpers.writeOneRow(pFileName, "Depth path:");
            System.out.println( "Depth path:");
            x = depthStringPath(inputString, pFileName, option);
            helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            System.out.println("See " + pFileName + " for saved ranked paths");
        }
        else
        {
            File f= new File(pFileName);  
            f.delete(); //file is deleted to be rewritten over
            /* format for outputting */
            helpers.writeOneRow(pFileName, "TIE for " + inputString + " ! \n");
            System.out.println("Breadth wins for " + inputString + " ! \n");
            helpers.writeOneRow(pFileName, "Breadth path:");
            System.out.println( "Breadth path:");
            int x = breadthStringPath(inputString, pFileName, option);
            helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            helpers.writeOneRow(pFileName, "Depth path:");
            System.out.println( "Depth path:");
            x = depthStringPath(inputString, pFileName, option);
            helpers.writeOneRow(pFileName, x + " steps\n");
            System.out.println(x + " steps\n");
            System.out.println("See " + pFileName + " for saved ranked paths");
        }
    }


}