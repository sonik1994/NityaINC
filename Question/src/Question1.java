
class C extends A{
}


class B {
	int c;
	int a=5;
}

class A {
   B b = new B();
}

public class Question1 {

	public static void main(String[] args) {
		A a = new A();
	    C c = new C();
		a.b.c=c.b.a;
		
	}
}
