#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

long long Xaff(vector<long long>& fr, int n);
long long getMin(vector<long long>& fr, vector<long long>& sumNodes, int frIndex, int sumNodesIndex, int n, int i);

int main() {
    ifstream sc("huffman.in");
    ofstream pw("huffman.out");

    int n;
    sc >> n;
    vector<long long> fr(n);
    for (int i = 0; i < n; i++) {
        sc >> fr[i];
    }

    long long result = Xaff(fr, n);
    
    pw<<result<<endl;
    pw.close();
    return 0;
}

long long Xaff(vector<long long>& fr, int n) {
    long long result= 0;
    vector<long long> sumNodes(n-1, LLONG_MAX);
    
    int frIndex=0;
    int sumNodesIndex =0;
    int totalSumIndex =0;

    for (int i = 0; i < n-1; i++) {
        long long first = getMin(fr, sumNodes, frIndex, sumNodesIndex, n, i);

        if (frIndex<n && first==fr[frIndex]) {
            frIndex++;
        } else {
            sumNodesIndex++;
        }

        long long second = getMin(fr, sumNodes, frIndex, sumNodesIndex, n, i);

        if (frIndex<n && second==fr[frIndex]) {
            frIndex++;
        } else {
            sumNodesIndex++;
        }

        sumNodes[totalSumIndex++]= first+second;
        result+= first+second;
    }
    return result;
}

long long getMin(vector<long long>& fr, vector<long long>& sumNodes, int frIndex, int sumNodesIndex, int n, int i) {
    bool frAvailable= frIndex<n;
    bool sumAvailable= sumNodesIndex<i;

    if (!sumAvailable || (frAvailable && fr[frIndex]<sumNodes[sumNodesIndex])) {
        return fr[frIndex];
    }
    return sumNodes[sumNodesIndex];
}