#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>
#include <climits>
#include <fstream>

using namespace std;

int findMinSum(int v, int posts, const vector<int>& villages);
vector<vector<int>> findAllDistances(const vector<int>& villages, int v);
vector<vector<int>> fillAllMaxInt(int v);

int main() {
    ifstream input("in.txt");
    ofstream output("out.txt");

    int v, p;
    input >> v >> p; // 1 и 2

    vector<int> villages(v);
    for (int i = 0; i < v; i++) {
        input >> villages[i]; // 3
    }

    // DP solve
    int result = findMinSum(v, p, villages);

    // sout
    output << result << endl;
    input.close();
    output.close();
}

int findMinSum(int v, int posts, const vector<int>& villages) {

    vector<vector<int>> distances = findAllDistances(villages, v); // for each position
    vector<vector<int>> matrix = fillAllMaxInt(v); // for dp

    // filling matrix
    for (int k = 1; k <= posts; k++) {
        for (int i = 1; i <= v; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[j][k - 1] != INT_MAX) {

                    int leftPart = matrix[j][k - 1];
                    int rightPart = distances[j][i - 1];
                    // left part (from 1 to j) is for k-1 posts
                    // right part (from j+1 to i) is for new post
                    int sum = leftPart + rightPart;

                    // find min of splitting and = matrix[][]
                    matrix[i][k] = min(matrix[i][k], sum);
                }
            }
        }
    }
    return matrix[v][posts];
}

vector<vector<int>> findAllDistances(const vector<int>& villages, int v) {
    vector<vector<int>> distances(v, vector<int>(v, 0));

    for (int i = 0; i < v; i++) {
        for (int j = i; j < v; j++) { // the upper block-triangular matrix of distances
            int median = villages[(i + j) / 2];
            for (int village = i; village <= j; village++) {
                distances[i][j] += abs(villages[village] - median); // sum of distances
            }
        }
    }
    return distances;
}

vector<vector<int>> fillAllMaxInt(int v) {
    vector<vector<int>> matrix(v + 1, vector<int>(v + 1, INT_MAX));
    matrix[0][0] = 0;
    return matrix;
}