package week6;

import java.util.List;

//overriding ?
//class A {
//   public static void print() {
//       System.out.println("a");
//   }
//}
//class B extends A {
//    public static void print() {
//        System.out.println("b");
//    }
//}

// private default protected public
//class A {
//    void print() {
//        System.out.println("a");
//    }
//}
//class B extends A {
//    protected void print() { //private or default scopes ?
//        System.out.println("b");
//    }
//
//    public static void main(String[] args) {
//        A a = new B();
//        a.print();
//    }
//}
//class A {
//    public A() {
//        System.out.println("a constructor");
//    }
//
//    public void print() {
//        System.out.println("a");
//    }
//}
//class B extends A {
//    public B() {
//        System.out.println("b consturctor");
//    }
//
//    public void print() {
//        System.out.println("b");
//    }
//
//    public static void main(String[] args) {
//        A a = new B();
//        a.print();
//    }
//}
//class A {
//
//    public static void main(String[] args) {
//        String a = "[\"abc\"]";
//        System.out.println(a.substring(1, a.length() - 1));
//    }
//}
//
////case5
//class StringPrint {
//    public static void main(String[] args) {
//        String a = "123";
//        String b = "123";
//        String c = new String("123");
//        System.out.println(a == b); //true
//        System.out.println(a == c); //false
//        System.out.println(a.equals(c)); //true
//
//        Integer n1 = 1;
//        Integer n2 = 1;
//        Integer n3 = 128;
//        Integer n4 = 128;
//        System.out.println(n1 == n2);
//        System.out.println(n3 == n4);
//    }
//}

//case6
/**
 * hashcode -> index of the bucket
 * equals -> compare key in that bucket
 * override equals X hashcode -> cannot find same bucket , cannot find previous key
 * override hashcode X equals -> find the bucket, cannot find previous key
 *
 *
 * Singleton dobule check thread safe lazy loading
 */