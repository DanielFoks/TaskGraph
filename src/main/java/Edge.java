import org.apache.log4j.Logger;

public final class Edge {
    private final int vertexBegin;
    private final int vertexEnd;
    private final int weight;

    private static final Logger log =  Logger.getLogger(Edge.class);

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

    public int getVertexBegin() {
        return vertexBegin;
    }

    public int getVertexEnd() {
        return vertexEnd;
    }

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
