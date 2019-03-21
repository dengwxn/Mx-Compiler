class Apple {
    int [][][] x;

    void hello(int [] x) {}
}

int main() {
    Apple a;
    a.x = new int [2][5][];
    int b = a.x.size();
    a.hello(new int [10]);
}