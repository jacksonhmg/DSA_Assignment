import java.util.*;
import java.io.*;

public class DSAGraph{
    private DSALinkedList vertices = new DSALinkedList();
    private DSALinkedList edges = new DSALinkedList();

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
        if(!(hasEdge(label1, label2)))
        {
            DSAGraphEdge edge = new DSAGraphEdge(getVertex(label1), getVertex(label2), null, null, directed);
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


    public int breadthFirstSearchFind(Object source, Object dest)
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
                    return shortestPathBreadth(T, getVertex(dest), getVertex(source));
                }
            }
        }
        return 0;
    }



    public int shortestPathBreadth(DSAQueue queue, DSAGraphVertex dest, DSAGraphVertex source)
    {
        DSALinkedList list = new DSALinkedList();
        int count = 0;
        
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
        return printList(list);
    }


    public int breadthStringPath(String string)
    {
        int count = 0;
        for(int i = 0; i < string.length()-1; i++)
        {
            count += breadthFirstSearchFind(String.valueOf(string.charAt(i)), String.valueOf(string.charAt(i+1)));
        }
        return count;
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


    public int depthFirstSearchFind(Object source, Object dest, String pFileName)
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
                    //v = w;
                }
                if(w.getLabel().equals(dest))
                {
                    return shortestPathDepth(T, getVertex(dest), getVertex(source), pFileName);
                }
            }
            v = (DSAGraphVertex)S.pop();
            
        }
        return 0;
    }


    public int shortestPathDepth(DSAQueue queue, DSAGraphVertex dest, DSAGraphVertex source, String pFileName)
    {
        DSALinkedList list = new DSALinkedList();
        int count = 0;
        
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
        saveList(pFileName, list);
        return printList(list);
        /*Iterator ill2 = list.iterator();
        while(ill2.hasNext())
        {
            DSAGraphVertex printV = (DSAGraphVertex)ill2.next();
            System.out.print(printV.getLabel() + " ");
            count ++;
        }
        System.out.println();
        return count-1;*/
    }


    public int printList(DSALinkedList list)
    {
        int count = 0;
        Iterator ill2 = list.iterator();
        while(ill2.hasNext())
        {
            DSAGraphVertex printV = (DSAGraphVertex)ill2.next();
            System.out.print(printV.getLabel() + " ");
            count ++;
        }
        System.out.println();
        return count-1;
    }

    public void saveList(String pFileName, DSALinkedList list)
    {
        //FileOutputStream fileStrm = null;
        PrintWriter pw;
        try {
            //fileStrm = new FileOutputStream(pFileName);
            pw = new PrintWriter(new FileWriter(pFileName,true));
            Iterator ill2 = list.iterator();
            while(ill2.hasNext())
            {
                DSAGraphVertex printV = (DSAGraphVertex)ill2.next();
                pw.print(printV.getLabel() + " ");
            }
            pw.println();
            pw.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file" + e.getMessage());
        }

    }


    public int depthStringPath(String string, String pFileName)
    {
        File f= new File(pFileName);           //file to be delete  
        f.delete();  
        int count = 0;
        for(int i = 0; i < string.length()-1; i++)
        {
            count += depthFirstSearchFind(String.valueOf(string.charAt(i)), String.valueOf(string.charAt(i+1)), pFileName);
        }
        return count;
    }


    public static void writeOneRow(String pFileName, String pInputString){
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        try {
            fileStrm = new FileOutputStream(pFileName);
            pw = new PrintWriter(fileStrm);
            pw.println(pInputString);
            pw.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file" + e.getMessage());
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