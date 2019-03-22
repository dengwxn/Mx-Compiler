void hello(int a, int b) {
    int c = a + b;
}

class Apple {
    void hello(int a) {}
}

class Banana {
    Apple a;
}

int main() {
    Apple a;
    a.hello(getInt());
    Banana b;
    b.a.hello(getInt());
    return 0;
}