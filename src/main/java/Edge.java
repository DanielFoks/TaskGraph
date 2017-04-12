import org.apache.log4j.Logger;

/**
 * This thread safe class implies an edge in the graph
 * @author Danil Tkachuk
 */
public final class Edge {
    /**
     *
     * Initial vertex of an edge
     */
    private final int vertexBegin;
    /**
     * Terminal vertex of an edge
     */
    private final int vertexEnd;
    /**
     * Weight of an edge
     */
    private final int weight;

    /**
     * log4j logger
     */
    private static final Logger log =  Logger.getLogger(Edge.class);

    /**
     *
     * @param vertexBegin Initial vertex of an edge
     * @param vertexEnd Terminal vertex of an edge
     * @param weight Weight of an edge
     * @throws Exception When vertexBegin,vertexEnd,weight = 0 throw Exception
     */
    public Edge(int vertexBegin, int vertexEnd, int weight) throws Exception {
        this.vertexBegin = vertexBegin;
        this.vertexEnd = vertexEnd;
        this.weight = weight;
        if (vertexBegin >=0&& vertexEnd >=0&&weight>0){
            log.info(this.toString()+" was created");

        }else {
            log.error("Can not create an edge with these conditions");
            throw new Exception("You can not create an edge with these conditions");
        }

    }

    /**
     *
     * @return Initial vertex of an edge
     */
    public int getVertexBegin() {
        return vertexBegin;
    }

    /**
     *
     * @return Terminate vertex of an edge
     */
    public int getVertexEnd() {
        return vertexEnd;
    }

    /**
     *
     * @return Weight of an edge
     */
    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;

        Edge edge = (Edge) o;

        if (vertexBegin != edge.vertexBegin) return false;
        if (vertexEnd != edge.vertexEnd) return false;
        return weight == edge.weight;

    }

    @Override
    public int hashCode() {
        int result = vertexBegin;
        result = 31 * result + vertexEnd;
        result = 31 * result + weight;
        return result;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertexBegin=" + vertexBegin +
                ", vertexEnd=" + vertexEnd +
                ", weight=" + weight +
                '}';
    }
}
