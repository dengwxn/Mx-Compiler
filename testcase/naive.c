A get() { return new A; } // this is valid

class A {
    A() {
        return get(); // cannot return any value in a constructor
    }
}

int main() { return 0; }
