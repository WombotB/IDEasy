package com.example.demoN;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestController
public class HttpControllerREST extends HttpServlet {

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (request.getParameter("codeSTR") != null)
            if (!request.getParameter("codeSTR").equals("")) {
                String code = request.getParameter("codeSTR");
                String name = request.getParameter("name");
                String path = "C:\\Users\\Uchenik\\IdeaProjects\\demoN\\src\\main\\java\\com\\example\\demoN\\";
                File file = new File(path + name + ".java");
                if (file.createNewFile()){
                    FileWriter writer = new FileWriter(path + name + ".java");
                    writer.write("package com.example.demoN;\n" + code);
                    writer.close();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);

                    // Перенаправляем поток вывода на ByteArrayOutputStream
                    PrintStream originalOut = System.out;
                    System.setOut(printStream);
                    test.main();
//                    // Вызываем метод из другого класса, который выводит что-то на System.out
//                    Class<?> clazz = Class.forName(name);
//
//                    Object dynamicObject = clazz.newInstance();
//
//                    // Находим метод "dynamicMethod" в классе "DynamicClass"
//                    Method dynamicMethod = clazz.getMethod("main");
//
//                    // Вызываем метод "dynamicMethod" у экземпляра dynamicObject
//                    dynamicMethod.invoke(dynamicObject);

                    // Получаем результат из ByteArrayOutputStream в виде строки
                    String output = outputStream.toString();

                    // Возвращаем обратно стандартный поток вывода
                    System.setOut(originalOut);
                    return "Run:\n" + output;
                }else {
                    FileWriter writer = new FileWriter(path + name + ".java");
                    writer.write("package com.example.demoN;\n" + code);
                    writer.close();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);

                    // Перенаправляем поток вывода на ByteArrayOutputStream
                    PrintStream originalOut = System.out;
                    System.setOut(printStream);
                    test.main();
//                    //Вызываем метод из другого класса, который выводит что-то на System.out
//                    Class<?> dynamicClass = Class.forName(name);
//
//                    Object dynamicObject = dynamicClass.newInstance();
//
//                    // Находим метод "dynamicMethod" в классе "DynamicClass"
//                    Method dynamicMethod = dynamicClass.getMethod("main");
//
//                    // Вызываем метод "dynamicMethod" у экземпляра dynamicObject
//                    dynamicMethod.invoke(dynamicObject);

                    // Получаем результат из ByteArrayOutputStream в виде строки
                    String output = outputStream.toString();

                    // Возвращаем обратно стандартный поток вывода
                    System.setOut(originalOut);
                    return "Run:\n" + output;
                }
            } else
                return "No POST data";
        return null;
    }
}