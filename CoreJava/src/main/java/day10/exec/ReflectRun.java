package day10.exec;


import java.lang.reflect.*;

public class ReflectRun {
    public ReflectRun() {

    }

    public static void main(String[] args) {
        ReflectRun reflectrun = new ReflectRun();
        Class c = null;
        try {
            c = Class.forName("day10.Student");
            Object o = c.newInstance();
            Field f = null;
            f = c.getDeclaredField("name");
            f.setAccessible(true);
            f.set(o,"liucy");
            System.out.println(f.get(o));
            Method m = null;
            Object p=new String("mycorejava");
            m = c.getDeclaredMethod("study",String.class);
            m.invoke(o,p);
            		//"corejava");
            }
            catch (NoSuchFieldException ex4) {
            } catch (InvocationTargetException ex3) {
            } catch (IllegalArgumentException ex3) {
            } catch (SecurityException ex2) {
            } catch (NoSuchMethodException ex2) {
            } catch (ClassNotFoundException ex1) {
            } catch (IllegalAccessException ex) {
            } catch (InstantiationException ex) {
            }
        }
    }
