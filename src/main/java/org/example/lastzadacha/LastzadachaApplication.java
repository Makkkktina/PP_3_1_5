package org.example.lastzadacha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class LastzadachaApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(LastzadachaApplication.class, args);

        Communication communication = context.getBean("communication", Communication.class);

        //  Получение списка пользователей
        List<User> allUser = communication.getAllUsers();
        System.out.println(allUser);

        // Создание нового пользователя
        User u1 = new User(3L, "James", "Brown", (byte) 29);
        communication.saveUser(u1);

        //  Обновление пользователя
        User u2 = new User(3L, "Thomas", "Shelby", (byte) 29);
        communication.updateUser(u2);

        // Удаление пользователя
        communication.deleteUser(3L);

        // Итоговый код
        System.out.println("Final Code: " + communication.getSb().toString());
    }
}