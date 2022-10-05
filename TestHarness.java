import java.io.*;
import java.util.Iterator;
public class TestHarness {
    public static void main(String args[])
    {
        // VARIABLE DECLARATIONS
        int iNumPassed = 0;
        int iNumTests = 0;
        
        DSAGraph DSAG = new DSAGraph();
        Helpers helpers = new Helpers();

//---------------------------------------------------------------------------
        System.out.println("=======================================");

        // TEST 1 : addVertex
        try {
            iNumTests++;
            System.out.print("Testing addVertex: ");
            DSAG.addVertex("4", "4");
            DSAG.addVertex("5", "5");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

//---------------------------------------------------------------------------

        // TEST 2 : addEdge
        try {
            iNumTests++;
            System.out.print("Testing addEdge: ");
            DSAG.addEdge("4", "5", false);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


        // TEST 3 : hasVertex
        try {
            iNumTests++;
            System.out.print("Testing hasVertex: ");
            if(!(DSAG.hasVertex("4")))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


        // TEST 4 : hasEdge
        try {
            iNumTests++;
            System.out.print("Testing hasEdge: ");
            if(!(DSAG.hasEdge("4","5")))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 5 : getVertexCount
        try {
            iNumTests++;
            System.out.print("Testing getVertexCount: ");
            if(DSAG.getVertexCount() != 2)
            {
                throw new IllegalArgumentException("VCount isn't right");
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }

        // TEST 6 : getEdgeCount
        try {
            iNumTests++;
            System.out.print("Testing getEdgeCount: ");
            if(DSAG.getEdgeCount() != 1)
            {
                throw new IllegalArgumentException("ECount isn't right");
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


        // TEST 7 : isAdjacent
        try {
            iNumTests++;
            System.out.print("Testing isAdjacent: ");
            if(!(DSAG.isAdjacent("4", "5")))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


        // TEST 8 : Delete vertice

        try {
            iNumTests++;
            System.out.print("Testing deleteVertice: ");
            DSAG.deleteVertice("4");
            if(DSAG.hasVertex("4"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


        // TEST 9 : Update node

        try {
            iNumTests++;
            System.out.print("Testing updateNode: ");
            DSAG.updateNode("5", "7");
            if(!(DSAG.hasVertex("7")) || DSAG.hasVertex("5"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


        //TEST 10 : Display list

        try {
            iNumTests++;
            System.out.print("Testing displayAsList: \n");
            DSAG.displayAsList();
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }


        //TEST 11 : Display matrix

        try {
            iNumTests++;
            System.out.print("Testing displayAsMatrix: \n");
            DSAG.displayAsMatrix();
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }



        DSAG.addVertex("6", "6");
        DSAG.addVertex("5", "5");
        DSAG.addVertex("4", "4");
        DSAG.addVertex("3", "3");
        DSAG.addVertex("2", "2");
        DSAG.addVertex("1", "1");
        DSAG.addEdge("7", "6", false);
        DSAG.addEdge("6", "5", false);
        DSAG.addEdge("6", "4", true);
        DSAG.addEdge("3", "6", true);
        DSAG.addEdge("4", "3", true);
        DSAG.addEdge("4", "2", false);
        DSAG.addEdge("2", "1", false);
        DSAG.addEdge("3", "1", true);
        DSAG.addEdge("1", "7", true);
        System.out.println();
        DSAG.displayAsMatrix();
        System.out.println();
        DSAG.displayAsList();


        //TEST 12 : breadthFirstSearchFind and shortestPathBreadth
        try {
            iNumTests++;
            System.out.print("Testing breadthFirstSearchFind and shortestPathBreadth: \n");
            DSAG.breadthFirstSearchFind("7", "1", null, 1);
            DSAG.breadthFirstSearchFind("2", "7", "output1ForTest12.txt", 0);
            DSAG.breadthFirstSearchFind("1", "3", "output2ForTest12.txt", 2);
            DSAG.breadthFirstSearchFind("2", "6", null, 4);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }
        
        //TEST 13 : depthFirstSearchFind and shortestPathDepth
        try {
            iNumTests++;
            System.out.print("Testing depthFirstSearchFind and shortestPathDepth: \n");
            DSAG.depthFirstSearchFind("7", "1", null, 1);
            DSAG.depthFirstSearchFind("2", "7", "output1ForTest13.txt", 0);
            DSAG.depthFirstSearchFind("1", "3", "output2ForTest13.txt", 2);
            DSAG.depthFirstSearchFind("2", "6", null, 4);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED"); }



        //TEST 14 : readInGraph
        try {
            iNumTests++;
            System.out.print("Testing readInGraph: ");
            helpers.readInGraph("switch.al", DSAG);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }


        //TEST 15 : breadthStringPath (and by extension, breadthFirstSearchFind and shortestPathBreadth)
        try {
            iNumTests++;
            System.out.print("Testing breadthStringPath (and by extension, breadthFirstSearchFind and shortestPathBreadth): \n");
            DSAG.breadthStringPath("heYa", null, 1);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 16 : depthStringPath (and by extension, depthFirstSearchFind and shortestPathDepth)
        try {
            iNumTests++;
            System.out.print("Testing depthStringPath (and by extension, depthFirstSearchFind and shortestPathDepth): \n");
            DSAG.depthStringPath("heYa", null, 1);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 17 : breadthFirstSearchCAPSCHECK and shortestPathBreadthCAPSCHECK
        try {
            iNumTests++;
            System.out.print("Testing breadthFirstSearchCAPSCHECK and shortestPathBreadthCAPSCHECK: \n");
            if(DSAG.breadthFirstSearchFindCAPSCHECK("a", "b", false, false) != 0)
            {
                throw new IllegalArgumentException("0");
            }
            if(DSAG.breadthFirstSearchFindCAPSCHECK("a", "A", false, false) != 1)
            {
                throw new IllegalArgumentException("1");
            }
            if(DSAG.breadthFirstSearchFindCAPSCHECK("A", "a", true, false) != 2)
            {
                throw new IllegalArgumentException("2");
            }
            if(DSAG.breadthFirstSearchFindCAPSCHECK("a", "$", false, false) != 3)
            {
                throw new IllegalArgumentException("3");
            }
            if(DSAG.breadthFirstSearchFindCAPSCHECK("A", "$", true, false) != 4)
            {
                throw new IllegalArgumentException("4");
            }
            if(DSAG.breadthFirstSearchFindCAPSCHECK("$", "a", false, true) != 5)
            {
                throw new IllegalArgumentException("5");
            }
            if(DSAG.breadthFirstSearchFindCAPSCHECK("$", "A", false, true) != 6)
            {
                throw new IllegalArgumentException("6");
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }


        //TEST 18 : depthFirstSearchCAPSCHECK and shortestPathDepthCAPSCHECK
        try {
            iNumTests++;
            System.out.print("Testing depthFirstSearchCAPSCHECK and shortestPathDepthCAPSCHECK: \n");
            if(DSAG.depthFirstSearchFindCAPSCHECK("b", "a", false, false) != 0)
            {
                throw new IllegalArgumentException("0");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("b", "B", false, false) != 1)
            {
                throw new IllegalArgumentException("1");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("B", "b", true, false) != 2)
            {
                System.out.println(DSAG.depthFirstSearchFindCAPSCHECK("B", "b", true, false));
                DSAG.depthFirstSearchFind("B","b",null,1);
                throw new IllegalArgumentException("2");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("b", "{", false, false) != 3)
            {
                throw new IllegalArgumentException("3");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("B", "{", true, false) != 4)
            {
                throw new IllegalArgumentException("4");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("{", "b", false, true) != 5)
            {
                throw new IllegalArgumentException("5");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("$", "B", false, true) != 6)
            {
                throw new IllegalArgumentException("6");
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }



        System.out.println("Number PASSED: " + iNumPassed + "/" + iNumTests);
    }
}

