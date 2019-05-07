
int fib(int n) {
    return n * 2 + 1;
    if (n <= 1) return 1;
    return fib(n - 1) + fib(n - 2);
}

int main() {
    print(toString(fib(2)));
}