package reflection;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reflections {

    public Reflections() {
    }

    // Метод принимает класс и возвращает созданный объект этого класса
    public Object createObject(Class<?> clazz) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance();
        return object;
    }

    // Метод принимает object и вызывает у него все методы без параметров
    public void invokeMethods(Object value) {
        ArrayList<String> methods = createInvokeMethodsList(value);
        printMethods(methods);
    }

    public ArrayList<String> createInvokeMethodsList(Object value) {  // method for test
        ArrayList<String> methodsList = new ArrayList<>();
        Class<?> clazz = value.getClass();
        for (Method methods : clazz.getDeclaredMethods()) {
            if (methods.getParameterCount() == 0) {
                methodsList.add(methods.getName());
            }
        }
        return methodsList;
    }

    // Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    public void printFinalSignatureMethods(Object value) {
        ArrayList<String> methods = createListOfFinalSignatureMethods(value);
        printMethods(methods);
    }

    public ArrayList<String> createListOfFinalSignatureMethods(Object value) {  // method for test
        ArrayList<String> methodsList = new ArrayList<>();
        Class<?> clazz = value.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.FINAL == method.getModifiers()) {
                methodsList.add(method.getName());
            }
        }
        return methodsList;
    }

    // Метод принимает Class и выводит все не публичные методы этого класса
    public void printNotPublicMethods(Class<?> clazz) {
        ArrayList<String> methods = createListOfNotPublicMethods(clazz);
        printMethods(methods);
    }

    public ArrayList<String> createListOfNotPublicMethods(Class<?> clazz) {  // method for test
        ArrayList<String> methodsList = new ArrayList<>();
        Method[] allMethods = clazz.getDeclaredMethods();
        for (Method methods : allMethods) {
            if (!(Modifier.PUBLIC == methods.getModifiers())) {
                methodsList.add(methods.getName());
            }
        }
        return methodsList;
    }

    //Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    public void printSuperclassAndInterface(Class<?> clazz) {
        Class<?> superclass = getSuperclass(clazz);
        Class<?>[] classInterface = getInterface(clazz);
        System.out.println("Class Superclass is : " + superclass + ".");
        System.out.println("Class Implement Interface : " + Arrays.toString(classInterface));
    }

    public Class<?> getSuperclass(Class<?> clazz) {  // method for test
        return clazz.getSuperclass();
    }

    public Class<?>[] getInterface(Class<?> clazz) {  // method for test
        return clazz.getInterfaces();
    }

    //Метод принимает объект и меняет все его приватные поля на их нулевые значение (null, 0, false etc)+
    public void changePrivateParameters(Object object) throws IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.getName().equals("name")) {
                field.setAccessible(true);
                field.set(object, null);
            }
            if (field.getName().equals("age")) {
                field.setAccessible(true);
                field.set(object, 0);
            }
            if (field.getName().equals("white")) {
                field.setAccessible(true);
                field.set(object, false);
            }
        }
    }

    private void printMethods(List<String> methodsList) {
        for (String method : methodsList) {
            System.out.println("methods with FINAL modifiers : " + method);
        }
    }
}
