import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This thread safe class implies an optimal path in the graph
 *
 * @author Danil Tkachuk
 */
public final class PathGraph {
    /**
     * Optimal path in the edge
     */
    private final List<Integer> list;

    /**
     * Accept list with optimal way in reverse order and reverse it
     *
     * @param path Optimal path in the edge in reverse order
     */
    public PathGraph(List<Integer> path) {
        this.list = path;
        Collections.reverse(list);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PathGraph)) return false;
        PathGraph pathGraph = (PathGraph) o;
        return Objects.equals(list, pathGraph.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer val : list) {
            stringBuilder.append(val + " -> ");
        }
        return "Path ( " + stringBuilder.toString().substring(0, stringBuilder.length() - 4) + " )";
    }
}
