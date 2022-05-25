package reflection;

import java.lang.reflect.*;
import java.util.Arrays;

public class Reflections {

    // Метод принимает класс и возвращает созданный объект этого класса
    public static Object createObject(Class clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance();
        return object;
    }

    // Метод принимает object и вызывает у него все методы без параметров
    public static void invokeMethods(Object value) throws InvocationTargetException, IllegalAccessException {
        Class clazz = value.getClass();
        for (Method methods : clazz.getDeclaredMethods()) {
            methods.setAccessible(true);
            methods.invoke(value);
        }
    }

    // Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    public static void printFinalSignature(Object value) {
        Class clazz = value.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.FINAL == method.getModifiers()) {
                System.out.println("methods with FINAL modifiers : " + method);
            }
        }
    }

    // Метод принимает Class и выводит все не публичные методы этого класса
    public static void printNoPublic(Class clazz) {
        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method methods : allMethods) {
            if (Modifier.PUBLIC == methods.getModifiers()) {
                break;
            } else System.out.println(methods);
        }
    }

    //Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    public static void printSuperclassAndInterface(Class clazz) {
        System.out.println("Superclass is : " + clazz.getSuperclass() + ".");
        System.out.println("Implement Interface : " + Arrays.toString(clazz.getInterfaces()));
    }

    //Метод принимает объект и меняет все его приватные поля на их нулевые значение (null, 0, false etc)+  // what is mean etc??
    public static void changePrivateParameters(Object value) throws IllegalAccessException {
        Class clazz = value.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(value, null);
            }
            if (field.getName().equals("age")) {
                field.setAccessible(true);
                field.set(value, 0);
            }
            if (field.getName().equals("gay")) {
                field.setAccessible(true);
                field.set(value, false);
            }
            //System.out.println(field.get(value)); // як вивести на екран значення ??
        }
    }
}
