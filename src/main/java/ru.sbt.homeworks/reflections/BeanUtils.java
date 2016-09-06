package ru.sbt.homeworks.reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanUtils {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Person p = new Person();
        p.setAge(12);
        p.setFirstName("Petr");
        p.setLastName("Petrov");
        p.setPhoneNumber("123");

        Contact c = new Contact();

        assign(c, p);

        System.out.println(c);

    }

    /**
     * Этот метод сканирует класс объекта from на предмет get методов и в соответствующий set метод класса объекта to
     * передает значение, которое вернул get метод
     * @param to
     * @param from
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */

    public static	void	assign(Object	to,	Object	from) throws InvocationTargetException, IllegalAccessException {
        Class toClass = to.getClass();
        Class fromClass = from.getClass();

        Method[] methodsTo = toClass.getMethods();
        Method[] methodFrom = fromClass.getMethods();


        for (Method method : methodFrom) {
            if (method.getName().substring(0,3).equals("get")) {  // Find methods "getters" among all methods in fromClass
                for (Method method1 : methodsTo) {
                    if ((method1.getName().substring(0,3).equals("set"))&&                                                      // Find methods "setters" among all methods in toClass
                            (method.getName().substring(3).equals(method1.getName().substring(3)))&&                            // that corresponds to "getters" in fromClass
                            (method.getReturnType().equals(method1.getParameterTypes()[0])                                      // with the same parameter type as the type of returned parameter of corresponding "getter" method
                                    || (method1.getParameterTypes()[0].equals(method.getReturnType().getSuperclass())))) {      // or with the parameter type that is the superclass of the type of returned parameter
                        Object o = method.invoke(from, null);
                        method1.invoke(to, o);

                    }

                }
            }

        }
    }

}
