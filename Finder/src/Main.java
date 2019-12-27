import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        String maze = ".";

        Node[][] nodes = buildNodes(maze);

        LinkedList<Node> queue = new LinkedList<>();
        queue.add(nodes[0][0]);
        while (queue.size() > 0) {
            Node current = queue.remove();
            if (current.isFinish) {
                break;
            }
            current.isVisited = true;
            List<Node> unvsited = getUnvisitedNeighbors(current, nodes);
            if (unvsited.size() > 0) {
                unvsited.forEach(c -> {
                    c.previousNode = current;
                    if(queue.contains(c)) queue.remove(c);
                    queue.add(c);
                });
            }
        }
        int steps = 0;
        Node finish = nodes[nodes.length - 1][nodes.length - 1];
        if (finish.isStart && finish.isFinish) return;
        while (finish.previousNode != null) {
            steps++;
            finish = finish.previousNode;
        }
        steps = steps != 0 ? steps : -1;
    }

    static List<Node> getUnvisitedNeighbors(Node c, Node[][] nodes) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int row = c.row;
        int col = c.col;
        if (row > 0) neighbors.add(nodes[row - 1][col]);
        if (row < nodes.length - 1) neighbors.add(nodes[row + 1][col]);
        if (col > 0) neighbors.add(nodes[row][col - 1]);
        if (col < nodes[0].length - 1) neighbors.add(nodes[row][col + 1]);
        return neighbors.stream().filter(node -> !node.isVisited && !node.isWall).collect(Collectors.toList());
    }

    static Node[][] buildNodes(String maze) {
        int n = maze.indexOf("\n");
        Node[][] nodes;
        if (n > 0) {
            maze = maze.replace("\n", "");

            nodes = new Node[n][n];
        } else {
            nodes = new Node[1][1];
        }

        int l = maze.length();

        for (int i = 0; i < l; i++) {
            char c = maze.charAt(i);
            boolean isStart = false;
            boolean isFinish = false;
            boolean isWall = false;
            if (i == 0) {
                isStart = true;
            }
            if (i == l - 1) {
                isFinish = true;
            } else if (c == 'W') {
                isWall = true;
            }
            int row = i != 0 ? i / n : 0;
            int col = i != 0 ? i % n : 0;
            nodes[row][col] = new Node(row, col, isStart, isFinish, isWall);
        }
        return nodes;
    }
}

class Node {
    int col;
    int row;
    boolean isStart;
    boolean isFinish;
    boolean isWall;
    boolean isVisited;
    Node previousNode;

    public Node(int row, int col, boolean isStart, boolean isFinish, boolean isWall) {
        this.col = col;
        this.row = row;
        this.isStart = isStart;
        this.isFinish = isFinish;
        this.isWall = isWall;
    }
}
