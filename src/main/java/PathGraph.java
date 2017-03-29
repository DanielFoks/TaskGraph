import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class PathGraph {
  private final List<Integer> list;

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
        for (Integer val:list){
            stringBuilder.append(val+" -> ");
        }
        return "Path ( " + stringBuilder.toString().substring(0,stringBuilder.length()-4)+" )";
    }
}
