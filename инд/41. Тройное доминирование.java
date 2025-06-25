import java.util.*;
import java.io.*;

class TeamCandidates {
    static class Team {
        int x, y, z;

        Team(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class SegmentTree {
        int[] tree;
        int size;

        SegmentTree(int n) {
            size = n;
            tree = new int[4 * n];
            Arrays.fill(tree, Integer.MAX_VALUE);
        }

        void update(int idx, int value, int node, int left, int right) {
            if (left == right) {
                tree[node] = Math.min(tree[node], value);
                return;
            }
            int mid = (left + right) / 2;
            if (idx <= mid) {
                update(idx, value, 2 * node + 1, left, mid);
            } else {
                update(idx, value, 2 * node + 2, mid + 1, right);
            }
            tree[node] = Math.min(tree[2 * node + 1], tree[2 * node + 2]);
        }

        int getMin(int queryLeft, int queryRight, int node, int left, int right) {
            if (queryRight < left || queryLeft > right) return Integer.MAX_VALUE;
            if (queryLeft <= left && right <= queryRight) return tree[node];
            int mid = (left + right) / 2;
            return Math.min(
                    getMin(queryLeft, queryRight, 2 * node + 1, left, mid),
                    getMin(queryLeft, queryRight, 2 * node + 2, mid + 1, right)
            );
        }
    }
}

public class Main implements Runnable {
    public static void main(String[] args) {
        new Thread(null, new Main(), "", 256 * 1024 * 1024).start();
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            PrintWriter pw = new PrintWriter(new File("output.txt"));

            int n = sc.nextInt();
            List<TeamCandidates.Team> teams = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();
                teams.add(new TeamCandidates.Team(x, y, z));
            }

            // Сортируем команды по x (возрастание)
            teams.sort(Comparator.comparingInt(t -> t.x));

            int maxCoord = 3 * n;
            TeamCandidates.SegmentTree segTree = new TeamCandidates.SegmentTree(maxCoord);
            int candidates = 0;

            for (TeamCandidates.Team team : teams) {
                int minZ = segTree.getMin(1, team.y - 1, 0, 1, maxCoord);
                if (minZ > team.z) {
                    candidates++;
                }
                segTree.update(team.y, team.z, 0, 1, maxCoord);
            }

            pw.println(candidates);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}