package day10.exec;

import java.lang.reflect.*;

final class Students extends Person implements Comparable {
    private String name;
    public int age;
    public Students() {
    }

    public Students(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String playGame(String name) throws RuntimeException {
        return "CS";
    }

    public void move() {
        System.out.println("moving.....");
    }

    public int compareTo(Object o) {
        return 0;
    }
}


class Person {

}


public class ReflectTest {
    public static void main(String[] args) {
        Students S = new Students();
        Class c = S.getClass();
        System.out.print(Modifier.toString(c.getModifiers()));
        System.out.print(" " + c.getName());
        System.out.print(" extends " + c.getSuperclass().getName());
        Class I[] = c.getInterfaces();
        if (I.length > 0) {
            System.out.print(" implements ");
            for (int j = 0; j < I.length; j++) {
                System.out.print(I[j].getName());
                if (j < I.length - 1) {
                    System.out.print(",");
                }
            }
        }
        System.out.println("{");
        printField(c);
        printConstruter(c);
        printMethods(c);
        System.out.println("}");
    }


    public static void printField(Class c) {
        Field[] f = c.getDeclaredFields();
        if (f.length > 0) {
            for (int i = 0; i < f.length; i++) {
                System.out.print(Modifier.toString(f[i].getModifiers()));

                System.out.print(" " + f[i].getType().getName() + " " +
                                 f[i].getName());
                System.out.println(";");
            }
        }
    }

    public static void printConstruter(Class c) {
        Constructor[] Con = c.getConstructors();
        if (Con.length > 0) {
            for (int i = 0; i < Con.length; i++) {
                System.out.print("\t" + Modifier.toString(Con[i].getModifiers()) +
                                 " " + Con[i].getName() + "(");
                Class[] ty = Con[i].getParameterTypes();
                for (int j = 0; j < ty.length; j++) {
                    System.out.print(Modifier.toString(ty[i].getModifiers()) +
                                     ty[i].getName());
                    if (j < ty.length - 1) {
                        System.out.println(",");
                    }
                }
                System.out.print(")");
                Class cl[] = Con[i].getExceptionTypes();
                if (cl.length > 0) {
                    System.out.print("throws ");
                    for (int j = 0; j < cl.length; j++) {
                        System.out.print(cl[j].getName());
                        if (j < cl.length - 1) {
                            System.out.print(",");
                        }
                    }
                }

                System.out.println("{");
                System.out.println("}");
            }

        }

    }

    public static void printMethods(Class c) {
        Method[] M = c.getMethods();
        if (M.length > 0) {
            for (int i = 0; i < M.length; i++) {
                System.out.print(Modifier.toString(M[i].getModifiers()));
                System.out.print(" " + M[i].getReturnType().getName());
                System.out.print(" " + M[i].getName() + "(");
                Class[] P = M[i].getParameterTypes();
                for (int j = 0; j < P.length; j++) {
                    System.out.println(" " +
                                       Modifier.toString(P[j].getModifiers()) +
                                       " " + P[j].getName());
                    if (j < P.length - 1) {
                        System.out.print(",");
                    }
                }
                System.out.print(")");
                System.out.println("{");
                System.out.println("}");
            }
        }

    }


}
