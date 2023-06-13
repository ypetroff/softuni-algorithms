import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class o1_ConnectedComponents {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        List<List<Integer>> graph = new ArrayList<>();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String input = reader.readLine();

            if (input.trim().length() == 0) {
                graph.add(new ArrayList<>());
                continue;
            }

            List<Integer> ints =
                    Arrays.stream(input.split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
            graph.add(ints);
        }

        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);

        for (Deque<Integer> component : connectedComponents) {
            sb.append("Connected component: ");

            for (int integer : component) {
                sb.append(integer).append(" ");
            }

            sb.setLength(sb.length() - 1);
            System.out.println(sb);
            sb.setLength(0);
        }
    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        List<Deque<Integer>> result = new ArrayList<>();

        for (int index = 0; index < graph.size(); index++) {
            if (!visited[index]) {
                result.add(new ArrayDeque<>());
                dsf(visited, index, result, graph);
//        bsf(visited, index, result, graph);
//        dfsIter(visited, index, result, graph);
            }
        }

        return result;
    }

    private static void bsf(
            boolean[] visited, int index, List<Deque<Integer>> components, List<List<Integer>> graph) {
        Deque<Integer> queue = new ArrayDeque<>();
        visited[index] = true;
        queue.offer(index);

        while (!queue.isEmpty()) {
            int node = queue.poll();

            components.get(components.size() - 1).offer(node);

            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
    }

    private static void dfsIter(
            boolean[] visited, int index, List<Deque<Integer>> components, List<List<Integer>> graph) {
        Deque<Integer> stack = new ArrayDeque<>();
        visited[index] = true;
        stack.push(index);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            components.get(components.size() - 1).offer(node);

            for (int child : graph.get(node)) {
                if (!visited[child]) {
                    visited[child] = true;
                    stack.push(child);
                }
            }
        }
    }

    private static void dsf(
            boolean[] visited, int node, List<Deque<Integer>> components, List<List<Integer>> graph) {
        if (!visited[node]) {
            visited[node] = true;
            for (int child : graph.get(node)) {
                dsf(visited, child, components, graph);
            }
            components.get(components.size() - 1).offer(node);
        }
    }
}
