import java.util.*;
import java.io.*;
import java.lang.*;

public class keyMeUp
{
    public static Helpers helpers = new Helpers();

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
            Scanner sc = new Scanner(System.in);
            String pathString = null;
            boolean loop = true;
            
            
            while(loop)
            {
                System.out.println("(1) Load keyboard file");
                System.out.println("(2) Node and edge operations");
                System.out.println("(3) Display graph operations");
                System.out.println("(4) Path operations");
                System.out.println("(5) Save keyboard to file");
                System.out.println("(6) Exit");
                int input1 = sc.nextInt();
                switch(input1)
                {
                    
                    case 1:
                        System.out.println("Enter filename to read keyboard from:");
                        sc.nextLine();
                        String inputFile = sc.nextLine();
                        helpers.readInGraph(inputFile, graph);
                    break;

                    case 2:
                        System.out.println("(1) Node operations (find, insert, delete, update)");
                        System.out.println("(2) Edge operations (find, add, remove, update)");
                        int input2 = sc.nextInt();
                        switch(input2)
                        {
                            case 1:
                                System.out.println("Do you want to (1) Find node and its adjacents, (2) Insert node, (3) Delete node, (4) Update node");
                                int input3 = sc.nextInt();
                                switch(input3)
                                {
                                    case 1:
                                        System.out.println("Enter node label");
                                        sc.nextLine();
                                        Object vLabel = sc.nextLine();
                                        DSAGraphVertex fVertex = graph.getVertex(vLabel);
                                        Iterator ill = fVertex.getAdjacent().iterator();
                                        System.out.print(fVertex.getLabel() + ": ");
                                        while(ill.hasNext())
                                        {
                                            DSAGraphVertex w = (DSAGraphVertex)ill.next();
                                            System.out.print(" " + w.getLabel());
                                        }
                                        System.out.println();
                                    break;
                                    case 2:
                                        System.out.println("Enter node label to insert");
                                        sc.nextLine();
                                        vLabel = sc.nextLine();
                                        graph.addVertex(vLabel, vLabel);
                                    break;
                                    case 3:
                                        
                                    break;
                                    case 4:
                                        sc.nextLine();
                                        System.out.println("Enter current label on node");
                                        Object oldLabel = sc.nextLine();
                                        System.out.println("Enter new label for node");
                                        Object newLabel = sc.nextLine();
                                        graph.updateNode(oldLabel, newLabel);
                                    break;
                                }
                            break;
                            case 2:
                                System.out.println("Do you want to (1) Find if there is an edge between two vertexes, (2) Insert edge, (3) Delete edge, (4) Update edge");
                                input3 = sc.nextInt();
                                switch(input3)
                                {
                                    case 1:
                                        System.out.println("Enter first vertex label");
                                        sc.nextLine();
                                        Object label1 = sc.nextLine();

                                        System.out.println("Enter second vertex label");
                                        Object label2 = sc.nextLine();

                                        if(graph.hasEdge(label1,label2) && graph.hasEdge(label2, label1))
                                        {
                                            System.out.println("These vertexes have a non-directional edge connecting both ways");
                                        }
                                        else if(graph.hasEdge(label1, label2) && !(graph.hasEdge(label2, label1)))
                                        {
                                            System.out.println("There is a directional edge going from " + label1 + " to " + label2);
                                        }
                                        else if(graph.hasEdge(label2, label1) && !(graph.hasEdge(label1, label2)))
                                        {
                                            System.out.println("There is a directional edge going from " + label2 + " to " + label1);
                                        }
                                        else if(!(graph.hasEdge(label2, label1)) && !(graph.hasEdge(label1, label2)))
                                        {
                                            System.out.println("There is no edge between these two labels");
                                        }
                                    break;
                                    
                                    case 2:
                                        System.out.println("Enter first vertex (if edge is directed, this should be your source)");
                                        sc.nextLine();
                                        Object soLabel = sc.nextLine();
                                        System.out.println("Enter second vertex (if edge is directed, this should be your sink)");
                                        Object siLabel = sc.nextLine();
                                        System.out.println("Is this edge directed? Enter true or false");
                                        boolean directed = sc.nextBoolean();
                                        graph.addEdge(soLabel, siLabel, directed);
                                    break;

                                    case 3:
                                    
                                    break;

                                    case 4:
                                    
                                    break;
                                }
                            break;   
                        }
                    break;

                    case 3:
                        System.out.println("(1) Display graph (list and matrix)");
                        System.out.println("(2) Display graph info");
                        input2 = sc.nextInt();
                        switch(input2)
                        {
                            case 1:
                                graph.displayAsList();
                                graph.displayAsMatrix();
                            break;

                            case 2:
                                System.out.println("Vertex count = " + graph.getVertexCount() + " and edge count = " + graph.getEdgeCount());
                            break;
                        }
                    break;

                    case 4:
                        System.out.println("(1) Enter string for finding path");
                        System.out.println("(2) Display ranked paths (option to save)");
                        input2 = sc.nextInt();
                        switch(input2)
                        {
                            case 1:
                                System.out.println("Enter string");
                                sc.nextLine();
                                pathString = sc.nextLine();
                            break;
                            case 2:
                                System.out.println("Do you want to (1) Display ranked paths to terminal or (2) Display and save ranked paths to file");
                                int input3 = sc.nextInt();
                                switch(input3)
                                {
                                    case 1:
                                        graph.displayRankedPaths(pathString, "outputPath.txt", 1);
                                    break;

                                    case 2:
                                        graph.displayRankedPaths(pathString, "outputPath.txt", 2);
                                        System.out.println("\nCheck outputPath.txt for saved path\n");
                                    break;
                                }
                            break;
                        }
                    break;

                    case 5:
                        System.out.println("Enter filename to save to");
                        sc.nextLine();
                        String saveFile = sc.nextLine();
                        File f = new File(saveFile);           //file to be delete  
                        f.delete();
                        Iterator vIll = graph.vertices.iterator();
                        while(vIll.hasNext())
                        {
                            int count = 0;
                            DSAGraphVertex w = (DSAGraphVertex)vIll.next();
                            String writeString = (String)w.getLabel();
                            Iterator vIll2 = w.getAdjacent().iterator();
                            while(vIll2.hasNext())
                            {
                                DSAGraphVertex y = (DSAGraphVertex)vIll2.next();
                                if(graph.hasEdge(w.getLabel(), y.getLabel()) && !graph.hasEdge(y.getLabel(), w.getLabel()))
                                {
                                    writeString += " -d" + y.getLabel();
                                }
                                else
                                {
                                    writeString += " " + y.getLabel();
                                }
                            }
                            helpers.writeOneRow(saveFile, writeString);
                        }
                    break;

                    case 6:
                        loop = false;
                    break;
                }
            }
        }
        else if(args[0].equals("-s"))
        {
            helpers.readInGraph(args[1],graph);

            File f= new File(args[args.length - 1]);//file to be deleted
            f.delete();
            System.out.println();
            graph.displayRankedPaths(helpers.readInString(args), args[args.length - 1], 0);
        }
        else
        {
            System.out.println("Please enter in the format of 'java keyMeUp -i' for interative or 'java keyMeUp -s keyFile strFile pathFile' for silent mode");
            return;
        }


    }
    

}