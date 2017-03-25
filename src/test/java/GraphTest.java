import org.junit.Test;


public class GraphTest {
    @Test
    public void testGraph1() {
        Graph graph = new Graph.Builder().edge(1,2,10).edge(2,3,15).build();
        graph.optimalWay(1,2);
    }

    @Test
    public void testGraph2() {
        Graph graph = new Graph.Builder()
                .edge(1, 2, 10)
                .edge(1, 5, 10)
                .edge(5, 1, 10)
                .edge(4, 2, 40)
                .edge(4, 3, 20)
                .edge(5, 4, 30)
                .edge(1, 4, 50)
                .edge(1, 3, 30)
                .edge(5,3,10)
                .edge(3,5,10)
                .build();
        graph.optimalWay(1,4);
    }

    @Test
    public void testGraph3() {
        Graph graph = new Graph.Builder().edge(1,2,10).edge(3, 6, 15).edge(4, 5, 10).edge(5,6,20).build();
        graph.optimalWay(1,6);
    }

    @Test
    public void testGraph4() {
        Graph graph = new Graph.Builder().edge(-1,-2,10).edge(12,3,-1).build();
        graph.optimalWay(1,2);
    }
}