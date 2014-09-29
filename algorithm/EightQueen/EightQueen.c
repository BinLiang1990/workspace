#include <stdio.h>
#include <stdlib.h>
int queen[8];
int sum = 0;
void searchQueen(int row, int queenNum) {
    if(row == queenNum) {
        ++sum;
    } else {
        int col = 0, i = 0;
        for(col = 0; col < queenNum; ++col) {
            queen[row] = col;
            int found = 1;
            for(i = 0; i < row; ++i) {
                if(queen[row] == queen[i] || abs(queen[i] - queen[row]) == abs(i - row)) {
                    found = 0;
                    break;
                }
            }
            if(found == 1) {
                searchQueen(row + 1, queenNum);
            }
        }
    }
}
int main() {
    searchQueen(0, 8);
    printf("%d\n", sum);
    return 0;
}
