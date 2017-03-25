import org.apache.log4j.Logger;

import java.util.*;

public final class Graph {
    private final ArrayList<Edge> edges;
    private final ArrayList<Integer> vertices;
    private static final Logger log = Logger.getLogger(Graph.class);

    public static class Builder{
        private final  ArrayList<Edge> edges = new ArrayList<>();
        public Builder() {
        }
        public Builder edge(int verticesBegin,int verticesEnd,int weight) {
            try {
                edges.add(new Edge(verticesBegin,verticesEnd,weight));
            } catch (Exception e) {
                System.err.println("Edge {"+verticesBegin+" | "+verticesEnd+" | "+weight+"} can not create");
                log.error("Can not create an edge with these conditions");
            }
            log.info("Edge was added to Graph");
            return this;
        }
        public Graph build(){
                log.info("Graph was created from Builder");
                return new Graph(this);
    }
    }

    private Graph(Builder builder){
        edges = builder.edges;
        vertices = new ArrayList<>();
        addVertices();
    }

    public int[][] getGraphMatrix(){
        int[][] matrix = new int[vertices.size()][vertices.size()];
        Iterator iterator = edges.iterator();
        while (iterator.hasNext()){
           Edge edge = (Edge) iterator.next();
           matrix[vertices.indexOf(edge.getVertexBegin())][vertices.indexOf(edge.getVertexEnd())] = edge.getWeight();
        }

        log.info("Matrix was created");

        return matrix;
    }

    private void addVertices(){
        HashSet<Integer> vertices = new HashSet<>();
        for (Edge edge:edges){
            vertices.add(edge.getVertexBegin());
            vertices.add(edge.getVertexEnd());
        }

        log.info("Vertices was found");

        this.vertices.addAll(vertices);
    }

    public boolean isConnected(){
        int[][] graph = getGraphMatrix();

        boolean[] used = new boolean [vertices.size()];
        int[] queue = new int [vertices.size()];
        int qH = 0;
        int qT = 0;
        int tmp = 0;

        used[tmp] = true;
        queue[qT++] = tmp;

        while (qT>qH){
            tmp = queue[qH++];

            for (int i = 0; i < vertices.size(); i++) {
                if (!used[i] && graph[tmp][i]!=0){
                    used[i]=true;
                    queue[qT++]=i;
                }
            }

        }

        for (int i = 0; i < used.length; i++) {
            if (!used[i])
                log.error("Graph is not connected");
                return false;
        }

        log.info("Graph is connected");
        return true;
    }

    public void optimalWay(int vertexFrom,int vertexTo){
        if (!vertices.contains(vertexFrom)||!vertices.contains(vertexTo)){
            log.error("One of the vertices is not found");
        return;
        }

        int[][] graph = getGraphMatrix();
        int[] vector = new int[vertices.size()];
        int[] weightsVertices = new int[vertices.size()];

        vertexFrom = vertices.indexOf(vertexFrom);

        for (int i = 0; i < weightsVertices.length; i++) {
            if (i!=vertexFrom){
                weightsVertices[i]=Integer.MAX_VALUE;
            }
        }

        int tmp;

        for (int i = 0; i < vertices.size(); i++) {
            if (graph[vertexFrom][i]!=0){
               tmp = weightsVertices[vertexFrom] + graph[vertexFrom][i];
                if (tmp<weightsVertices[i]){
                    weightsVertices[i]=tmp;
                    vector[i] = vertexFrom;
                }
            }
        }

        outer : for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                if (i==vertexFrom) continue outer;

                if (graph[i][j]!=0){
                    tmp = weightsVertices[i] + graph[i][j];
                    if (tmp<weightsVertices[j]&&tmp>0){
                        weightsVertices[j]=tmp;
                        vector[j] = i;
                    }
                }

            }
        }

        vertexTo = vertices.indexOf(vertexTo);

        StringBuilder stringBuilder = new StringBuilder();

        if (weightsVertices[vertexTo]<=0 || weightsVertices[vertexTo]==Integer.MAX_VALUE){
            log.error("Optimal way is impossible to find");
            System.err.println("Optimal way is impossible to find");
        }else {

            while (vertexTo!=vertexFrom){
                stringBuilder.append(vertices.get(vertexTo)+">-");
                vertexTo = vector[vertexTo];
            }

            log.info("Optimal way was found (" + vertices.get(vertexFrom) + "" + stringBuilder.reverse() + ")");
            System.out.println("Optimal way was found (" + vertices.get(vertexFrom) + "" + stringBuilder + ")");
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "edges=" + edges +
                '}';
    }
}
