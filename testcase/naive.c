class test { int x; }

int main() {
    test[][] a;
    a = new test[10][];
    a[0] = new test[20];
    a[0][0] = new test();
    return 0;
}