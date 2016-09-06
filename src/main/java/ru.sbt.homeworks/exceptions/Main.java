package ru.sbt.homeworks.exceptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static int index = 0;
    public static void main(String[] args) {

        while (index == 0) {

            System.out.println("Please enter correct address: ");

            Scanner in = new Scanner(System.in);  // Вводим адрес страницы с консоли
            String address = in.next();

            try {
                URL url = new URL(address);
                readContent(url);               // Пытаемся считать код страницы
            }
            catch (MalformedURLException e) {
                index = 0;
                System.out.println("Either no legal protocol could be found in a specification string or the string could not be parsed.");
            }

        }


    }

    /**
     * Метод выводит на консоль код переданной страницы, если адрес введен корректно, и страница существует
     * @param url
     */
    public static void readContent(URL url) {

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

            String inputLine;
            index = 1;

            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
        }

        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Something wrong with the address. Perhaps this page does not exist.");
            index = 0;

        }
    }
}
