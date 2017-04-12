import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;


public class GraphTest {
    @Test
    public void normalTestGraph() {
        Graph graph = new Graph.Builder().edge(1,2,10).edge(2,3,15).build();
        List<Integer> list = Lists.newArrayList(2,1);
        PathGraph pathGraph = new PathGraph(list);
        Assert.assertEquals(pathGraph,graph.optimalWay(1,2));
    }

    @Test
    public void testWithMoreVertices() {
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
        List<Integer> list = Lists.newArrayList(4,5,1);
        PathGraph pathGraph = new PathGraph(list);
        Assert.assertEquals(pathGraph,graph.optimalWay(1,4));
    }

    @Test
    public void impossibleFindOptimalWay() {
        Graph graph = new Graph.Builder().edge(1,2,10).edge(3, 6, 15).edge(4, 5, 10).edge(5,6,20).build();
        Assert.assertNull(graph.optimalWay(1,6));
    }

    @Test
    public void incorrectArguments() {
        Graph graph = new Graph.Builder().edge(-1,-2,10).edge(12,3,-1).build();
        Assert.assertNull(graph.optimalWay(1, 2));
    }
}
