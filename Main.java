package SmartDraw;

public class Main
{
    public static void main(String[] args)
    {
        new JavaGenericsTest().Test();
    }
}

class JavaGenericsTest
{
    void Test()
    {
        new A();
        new B();
        new C();
        System.out.println(A.testint);
        System.out.println(B.testint);
        System.out.println(C.testint);
    }
}

class TestBase<T>
{
    static int testint=0;
}
class A extends TestBase<A>
{
    A()
    {
        testint=1;
    }
}
class B extends TestBase<B>
{
    B()
    {
        testint=2;
    }
}
class C extends TestBase<B>
{
    C()
    {
        testint=3;
    }
}
