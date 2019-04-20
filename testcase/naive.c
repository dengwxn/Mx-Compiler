class A {
  B t;
  A() {
    t = new B();
  }
}

class B {
  A t;
  B() {
    t = new A();
  }
}

int main() {
  A a = new A;
  B b = new B;
  a.t = b;
  b.t = a;
}