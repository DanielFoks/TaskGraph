import com.google.common.collect.Lists;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * This class is oriented graph
 *
 * @author Danil Tkachuk
 */
public final class Graph {
    /**
     * List of edges in graph
     */
    private final List<Edge> edges;
    /**
     * List of vertices in graph
     */
    private final List<Integer> vertices;
    /**
     * The graph is represented as a matrix
     */
    private final int[][] matrix;

    /**
     * log4j logger
     */
    private static final Logger log = Logger.getLogger(Graph.class);


    /**
     * This inner class is Builder pattern class for build the new graph
     */
    public static class Builder {
        /**
         * List of edges
         */
        private final List<Edge> edges = Lists.newArrayList();

        /**
         * Default constructor
         */
        public Builder() {
        }

        /**
         * @param verticesBegin Initial vertex of an edge
         * @param verticesEnd   Terminal vertex of an edge
         * @param weight        Weight of an edge
         * @return Builder. Add the new edge to graph
         */
        public Builder edge(int verticesBegin, int verticesEnd, int weight) {
            try {
                edges.add(new Edge(verticesBegin, verticesEnd, weight));
            } catch (Exception e) {
                log.error("Can not create an edge with these conditions");
            }
            log.info("Edge was added to Graph");
            return this;
        }

        /**
         * Build the new graph
         *
         * @return Graph
         */
        public Graph build() {
            log.info("Graph was created from Builder");
            return new Graph(this);
        }
    }

    /**
     * Closed constructor
     *
     * @param builder Inner class is Builder pattern class for build the new graph
     */
    private Graph(Builder builder) {
        edges = builder.edges;
        vertices = Lists.newArrayList();
        addVertices();
        matrix = getGraphMatrix();
    }

    /**
     * Create the matrix from graph
     *
     * @return The graph is represented as a matrix
     */
    private int[][] getGraphMatrix() {
        int[][] matrix = new int[vertices.size()][vertices.size()];
        for (Edge edge : edges) {
            matrix[vertices.indexOf(edge.getVertexBegin())][vertices.indexOf(edge.getVertexEnd())] = edge.getWeight();
        }

        log.info("Matrix was created");

        return matrix;
    }

    /**
     * Add all vertices to vertices list
     */
    private void addVertices() {
        HashSet<Integer> vertices = new HashSet<>();
        for (Edge edge : edges) {
            vertices.add(edge.getVertexBegin());
            vertices.add(edge.getVertexEnd());
        }

        log.info("Vertices was found");

        this.vertices.addAll(vertices);
    }

    /**
     * Check the graph for connectivity
     *
     * @return TRUE if graph is connected and FALSE if is not
     */
    public boolean isConnected() {
        boolean[] used = new boolean[vertices.size()];
        int[] queue = new int[vertices.size()];
        int qH = 0;
        int qT = 0;
        int tmp = 0;

        used[tmp] = true;
        queue[qT++] = tmp;

        while (qT > qH) {
            tmp = queue[qH++];

            for (int i = 0; i < vertices.size(); i++) {
                if (!used[i] && matrix[tmp][i] != 0) {
                    used[i] = true;
                    queue[qT++] = i;
                }
            }

        }

        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                log.error("Graph is not connected");
                return false;
            }
        }

        log.info("Graph is connected");
        return true;
    }

    /**
     * Look for the optimal way
     *
     * @param vertexFrom Initial vertex for search
     * @param vertexTo   Terminate vertex for search
     * @return PathGraph object
     */
    public PathGraph optimalWay(int vertexFrom, int vertexTo) {
        if (!vertices.contains(vertexFrom) || !vertices.contains(vertexTo)) {
            log.error("One of the vertices is not found");
            return null;
        }

        int[] vector = new int[vertices.size()];
        int[] weightsVertices = new int[vertices.size()];

        vertexFrom = vertices.indexOf(vertexFrom);

        for (int i = 0; i < weightsVertices.length; i++) {
            if (i != vertexFrom) {
                weightsVertices[i] = Integer.MAX_VALUE;
            }
        }

        int tmp;

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix[vertexFrom][i] != 0) {
                tmp = weightsVertices[vertexFrom] + matrix[vertexFrom][i];
                if (tmp < weightsVertices[i]) {
                    weightsVertices[i] = tmp;
                    vector[i] = vertexFrom;
                }
            }
        }

        outer:
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (i == vertexFrom) continue outer;

                if (matrix[i][j] != 0) {
                    tmp = weightsVertices[i] + matrix[i][j];
                    if (tmp < weightsVertices[j] && tmp > 0) {
                        weightsVertices[j] = tmp;
                        vector[j] = i;
                    }
                }

            }
        }

        vertexTo = vertices.indexOf(vertexTo);


        if (weightsVertices[vertexTo] <= 0 || weightsVertices[vertexTo] == Integer.MAX_VALUE) {
            log.error("Optimal way is impossible to find");
            return null;
        } else {

            List<Integer> path = Lists.newArrayList();

            while (vertexTo != vertexFrom) {
                path.add(vertices.get(vertexTo));
                vertexTo = vector[vertexTo];
            }

            path.add(vertices.get(vertexFrom));

            PathGraph pathGraph = new PathGraph(path);

            log.info("Optimal way was found (" + pathGraph + ")");
            return pathGraph;
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                '}';
    }
}
