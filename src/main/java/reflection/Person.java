package reflection;

public class Person implements Interface {
    private String name = "Sasha";
    private int age = 25;
    private boolean white = true;


    public void firstPublicMethodsWithoutParameters() {
        System.out.println(" You call first methods without parameters !");
    }

    public void secondPublicMethodsWithoutParameters() {
        System.out.println(" You call second methods without parameters !");
    }

    final void finalMethod1() {
    }

    final void finalMethod2() {
    }

    final void finalMethod3() {
    }

    static void staticMethod() {
    }

    private void privateMethod() {
    }

    public void methodWithParameters(String name){

    }

    @Override
    public String toString() {
        return "Person{"
                + "name = '" + name + '\''
                + ", age = " + age
                + ", white = " + white
                + '}';
    }

    @Override
    public String methodFromInterface() {
        return "Hi! Im from Interface !";
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public boolean getGender() {
        return white;
    }
}


