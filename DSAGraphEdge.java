/* TAKEN FROM PRACTICAL 6 WORK */


public class DSAGraphEdge {
    private DSAGraphVertex from;
    private DSAGraphVertex to;
    private Object label;
    private Object value;
    private boolean directed;

    public DSAGraphEdge(DSAGraphVertex fromVertex, DSAGraphVertex toVertex, Object inLabel, Object inValue, boolean inDirected)
    {
        from = fromVertex;
        to = toVertex;
        label = inLabel;
        value = inValue;
        directed = inDirected;
    }

    public Object getLabel()
    {
        return label;
    }

    public Object getValue()
    {
        return value;
    }

    public DSAGraphVertex getFrom()
    {
        return from;
    }

    public DSAGraphVertex getTo()
    {
        return to;
    }

    public boolean isDirected()
    {
        return directed;
    }

}
