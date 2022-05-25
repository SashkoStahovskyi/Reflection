package reflectionTest;

import org.junit.jupiter.api.Test;
import reflection.Person;
import reflection.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReflectionTest {

    @Test
    public void testCreateObject() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Object object = Reflections.createObject(Person.class);
        assertEquals(Person.class, object.getClass());
    }

    // Метод принимает object и вызывает у него все методы без параметров
    @Test
    public void testEvokeMethodsWithoutParameters() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Reflections.invokeMethods(Reflections.createObject(Person.class));
    }

    // Метод принимает object и выводит на экран все сигнатуры методов в который есть final
    @Test
    public void testPrintSignatureFinalMethods() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Reflections.printFinalSignature(Reflections.createObject(Person.class));
    }

    // Метод принимает Class и выводит все не публичные методы этого класса
    @Test
    public void testPrintMethodsModifierExceptPublic() {
        Reflections.printNoPublic(Person.class);
    }

    //Метод принимает Class и выводит всех предков класса и все интерфейсы которое класс имплементирует
    @Test
    public void testPrintSuperClassAndInterface() {
        Reflections.printSuperclassAndInterface(ArrayList.class);
    }

    //Метод принимает объект и меняет всего его приватные поля на их нулевые значение (null, 0, false etc)+
    @Test
    public void testChangePrivateParameters() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        Reflections.changePrivateParameters(Reflections.createObject(Person.class));
    }

}


