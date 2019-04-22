class Test {
    int x;
    int y;
    void f(int x) {
        x = 1;
        this.x = 2;
        y = 3;
    }

    Test() {
        x = 1;
        y = 2;
    }
}

int main() {
    Test t = new Test();
    return 0;
}
