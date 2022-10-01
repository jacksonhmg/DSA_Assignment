import java.util.Iterator;

public class DSAGraphVertex {
    private Object label;
    private Object value;
    public DSALinkedList links = new DSALinkedList();
    private boolean visited;

    public DSAGraphVertex(Object inLabel, Object inValue)
    {
        label = inLabel;
        value = inValue;
    }

    public Object getLabel()
    {
        return label;
    }
    
    public Object getValue()
    {
        return value;
    }

    public DSALinkedList getAdjacent()
    {
        return links;
    }

    public void addEdge(DSAGraphVertex vertex)
    {
        links.insertLast(vertex);
    }

    public void setLabel(Object inLabel)
    {
        label = inLabel;
    }

    public void setVisited()
    {
        visited = true;
    }

    public void clearVisited()
    {
        visited = false;
    }

    public boolean getVisited()
    {
        return visited;
    }


    /*public String toString()
    {
        
    }*/
}
