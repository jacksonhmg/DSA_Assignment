
/* The overall format of these test harnesses is used from "UnitTestLinkedList.java" given in Practical 4 */


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
        System.out.println("===================Testing Graph Methods====================");

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
            if(DSAG.depthFirstSearchFindCAPSCHECK("q", "a", false, false) != 0)
            {
                throw new IllegalArgumentException("0");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("q", "Q", false, false) != 1)
            {
                throw new IllegalArgumentException("1");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("Q", "q", true, false) != 2)
            {
                throw new IllegalArgumentException("2");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("q", "{", false, false) != 3)
            {
                throw new IllegalArgumentException("3");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("Q", "{", true, false) != 4)
            {
                throw new IllegalArgumentException("4");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("{", "q", false, true) != 5)
            {
                throw new IllegalArgumentException("5");
            }
            if(DSAG.depthFirstSearchFindCAPSCHECK("$", "Q", false, true) != 6)
            {
                throw new IllegalArgumentException("6");
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }





        System.out.println("===================Testing GraphEdge Methods====================");
        DSAGraphVertex vert1 = new DSAGraphVertex("1","1");
        DSAGraphVertex vert2 = new DSAGraphVertex("2","2");
        DSAGraphEdge edge = new DSAGraphEdge(vert1,vert2,"1to2",1,true);

        //TEST 19 : getLabel
        try {
            iNumTests++;
            System.out.print("Testing getLabel: ");
            if(!edge.getLabel().equals("1to2"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 20 : getValue
        try {
            iNumTests++;
            System.out.print("Testing getValue: ");
            if(!edge.getValue().equals(1))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 21 : getFrom
        try {
            iNumTests++;
            System.out.print("Testing getFrom: ");
            if(!edge.getFrom().getLabel().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 22 : getTo
        try {
            iNumTests++;
            System.out.print("Testing getTo: ");
            if(!edge.getTo().getLabel().equals("2"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 23 : isDirected
        try {
            iNumTests++;
            System.out.print("Testing isDirected: ");
            if(!edge.isDirected())
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }



        System.out.println("===================Testing GraphVertex Methods====================");
        //TEST 24 : getLabel
        try {
            iNumTests++;
            System.out.print("Testing getLabel: ");
            if(!vert1.getLabel().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 25 : getValue
        try {
            iNumTests++;
            System.out.print("Testing getValue: ");
            if(!vert2.getValue().equals("2"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 26 : setLabel
        try {
            iNumTests++;
            System.out.print("Testing setLabel: ");
            vert1.setLabel("3");
            if(!vert1.getLabel().equals("3"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }
        
        //TEST 27 : setVisited
        try {
            iNumTests++;
            System.out.print("Testing setVisited: ");
            vert1.setVisited();
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 28 : getVisited
        try {
            iNumTests++;
            System.out.print("Testing getVisited: ");
            if(!vert1.getVisited())
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 29 : clearVisited
        try {
            iNumTests++;
            System.out.print("Testing clearVisited: ");
            vert1.clearVisited();
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        
        System.out.println("===================Testing LinkedList Methods====================");
        DSALinkedList list = new DSALinkedList();

        //TEST 30 : listLength
        try {
            iNumTests++;
            System.out.print("Testing list length: ");
            if(list.length() != 0)
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 31 : insertFirst
        try {
            iNumTests++;
            System.out.print("Testing insert first: ");
            list.insertFirst("1");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 32 : insertLast
        try {
            iNumTests++;
            System.out.print("Testing insert last: ");
            list.insertLast("2");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 33 : isEmpty
        try {
            iNumTests++;
            System.out.print("Testing isEmpty: ");
            if(list.isEmpty())
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 34 : peekFirst
        try {
            iNumTests++;
            System.out.print("Testing peekFirst: ");
            if(!list.peekFirst().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 35 : peekLast
        try {
            iNumTests++;
            System.out.print("Testing peekLast: ");
            if(!list.peekLast().equals("2"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 36 : removeFirst
        try {
            iNumTests++;
            System.out.print("Testing removeFirst: ");
            if(!list.removeFirst().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            if(list.length() != 1)
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 37 : removeLast
        try {
            iNumTests++;
            System.out.print("Testing removeLast: ");
            if(!list.removeLast().equals("2"))
            {
                throw new IllegalArgumentException();
            }
            if(list.length() != 0)
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 38 : save
        try {
            iNumTests++;
            System.out.print("Testing save: ");
            list.save(list,"listsave.txt");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 39 : load
        try {
            iNumTests++;
            System.out.print("Testing load: ");
            list.insertFirst(1);
            list.insertFirst(2);
            list.insertFirst(3);
            list.insertFirst(4);
            if(list.length() != 4)
            {
                throw new IllegalArgumentException("0");
            }
            list = list.load("listsave.txt");
            if(list.length() != 0)
            {
                throw new IllegalArgumentException("1");
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }


        System.out.println("===================Testing ListNode Methods====================");
        

        DSAListNode node = new DSAListNode("1");

        //TEST 40 : getValue
        try {
            iNumTests++;
            System.out.print("Testing getValue: ");
            if(!node.getValue().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 41 : setValue
        try {
            iNumTests++;
            System.out.print("Testing setValue: ");
            node.setValue("2");
            if(!node.getValue().equals("2"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 42 : setNext
        try {
            iNumTests++;
            System.out.print("Testing setNext: ");
            node.setNext(node);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 43 : getNext
        try {
            iNumTests++;
            System.out.print("Testing getNext: ");
            if(!node.getNext().getValue().equals("2"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 44 : setPrev
        try {
            iNumTests++;
            System.out.print("Testing setPrev: ");
            node.setPrev(node);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 45 : getPrev
        try {
            iNumTests++;
            System.out.print("Testing getPrev: ");
            if(!node.getNext().getValue().equals("2"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }



        System.out.println("===================Testing Queue Methods====================");
        DSAQueue queue = new DSAQueue();

        //TEST 46 : isEmpty
        try {
            iNumTests++;
            System.out.print("Testing isEmpty: ");
            if(!queue.isEmpty())
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 47 : enqueue
        try {
            iNumTests++;
            System.out.print("Testing enqueue: ");
            queue.enqueue("1");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 48 : peek
        try {
            iNumTests++;
            System.out.print("Testing dequeue: ");
            if(!queue.peek().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 49 : dequeue
        try {
            iNumTests++;
            System.out.print("Testing dequeue: ");
            if(!queue.dequeue().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }


        System.out.println("===================Testing Stack Methods====================");
        DSAStack stack = new DSAStack();

        //TEST 50 : isEmpty
        try {
            iNumTests++;
            System.out.print("Testing isEmpty: ");
            if(!stack.isEmpty())
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 51 : push
        try {
            iNumTests++;
            System.out.print("Testing push: ");
            stack.push("1");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 52 : top
        try {
            iNumTests++;
            System.out.print("Testing top: ");
            if(!stack.top().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 53 : pop
        try {
            iNumTests++;
            System.out.print("Testing pop: ");
            if(!stack.pop().equals("1"))
            {
                throw new IllegalArgumentException();
            }
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }



        System.out.println("===================Testing Helpers Methods====================");

        //TEST 54 : writeOneRow
        try {
            iNumTests++;
            System.out.print("Testing writeOneRow: ");
            helpers.writeOneRow("outputForTest54.csv", "test");
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }


        //TEST 55 : save list
        try {
            iNumTests++;
            System.out.print("Testing saveList: ");
            helpers.saveList("outputForTest55.txt", list);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }

        //TEST 56 : print list
        try {
            iNumTests++;
            System.out.print("Testing saveList: ");
            helpers.printList(list);
            iNumPassed++;
            System.out.println("passed");
        } catch(Exception e) { System.out.println("FAILED" + e); }



        System.out.println("\nNumber PASSED: " + iNumPassed + "/" + iNumTests);
    }
}

