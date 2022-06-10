package reflectionTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reflection.Person;
import reflection.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionTest {

    private Reflections reflections;
    private Person object;

    @BeforeEach
    public void before() {
        reflections = new Reflections();
        object = new Person();
    }

    @DisplayName("test Method Create Object Work Correctly")
    @Test
    public void testMethodCreateObject() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Object object = reflections.createObject(Person.class);
        assertEquals(Person.class, object.getClass());
    }

    @DisplayName("test Evoke Methods Without Parameters Work Correctly")
    @Test
    public void testEvokeMethodsWithoutParametersWorkCorrectly() {
        ArrayList<String> methodsList = reflections.createInvokeMethodsList(object);

        assertEquals(12, methodsList.size());
        assertTrue(methodsList.remove("firstPublicMethodsWithoutParameters"));
        assertTrue(methodsList.remove("secondPublicMethodsWithoutParameters"));
        assertTrue(methodsList.remove("finalMethod1"));
        assertTrue(methodsList.remove("finalMethod2"));
        assertTrue(methodsList.remove("finalMethod3"));
        assertTrue(methodsList.remove("staticMethod"));
        assertTrue(methodsList.remove("privateMethod"));
        assertTrue(methodsList.remove("toString"));
        assertTrue(methodsList.remove("methodFromInterface"));
        assertTrue(methodsList.remove("getName"));
        assertTrue(methodsList.remove("getAge"));
        assertTrue(methodsList.remove("getGender"));
        assertEquals(0, methodsList.size());
    }

    @DisplayName("test Print All Methods With Signature Final Work Correctly")
    @Test
    public void testPrintAllMethodsWithSignatureFinalWorkCorrectly() {
        ArrayList<String> methodsList = reflections.createListOfFinalSignatureMethods(object);

        assertEquals(3, methodsList.size());
        assertTrue(methodsList.remove("finalMethod1"));
        assertTrue(methodsList.remove("finalMethod2"));
        assertTrue(methodsList.remove("finalMethod3"));
        assertFalse(methodsList.remove("staticMethod"));
        assertEquals(0, methodsList.size());
    }

    @DisplayName("test Print All Methods Without Modifier Public Work Correctly")
    @Test
    public void testPrintAllMethodsWithoutModifierPublicWorkCorrectly() {
        ArrayList<String> methods = reflections.createListOfNotPublicMethods(Person.class);

        assertEquals(5, methods.size());
        assertTrue(methods.remove("finalMethod1"));
        assertTrue(methods.remove("finalMethod2"));
        assertTrue(methods.remove("finalMethod3"));
        assertTrue(methods.remove("staticMethod"));
        assertTrue(methods.remove("privateMethod"));
        assertEquals(0, methods.size());
    }

    @DisplayName("test Print Super Class And Interface Work Correctly")
    @Test
    public void testPrintSuperClassAndInterfaceWorkCorrectly() {
        Class<?> superclassActual = reflections.getSuperclass(Person.class);
        Class<?> superclassExpect = Person.class.getSuperclass();
        Class<?>[] actualInterface = reflections.getInterface(Person.class);
        Class<?>[] expectInterface = Person.class.getInterfaces();

        assertEquals(superclassExpect, superclassActual);
        assertArrayEquals(expectInterface, actualInterface);
    }

    @Test
    public void testChangePrivateParameters() throws IllegalAccessException {
        reflections.changePrivateParameters(object);
        assertNull(object.getName());
        assertEquals(0, object.getAge());
        assertFalse(object.getGender());
    }
}


