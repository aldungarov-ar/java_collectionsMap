package practice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        PhoneBook phoneBook = new PhoneBook();
        while (!input.equals("0")) {
            System.out.println("Введите номер, имя или команду:");
            input = scanner.nextLine().strip();

            if (PhoneBook.isNameValid(input)) {
                if (phoneBook.getContactByName(input).isEmpty()) {
                    System.out.println("Такого имени в телефонной книге нет.\n" +
                            "Введите номер телефона для абонента “" + input + "”:");
                    phoneBook.addContact(scanner.nextLine(), input);
                } else {
                    System.out.println("Введите номер телефона для абонента “" + input + "”:");
                    phoneBook.addContact(scanner.nextLine(), input);
                }
            } else if (PhoneBook.isPhoneValid(input)) {
                if (phoneBook.getContactByPhone(input).isEmpty()) {
                    System.out.println("Такого номера нет в телефонной книге.\n" +
                            "Введите имя абонента для номера “" + input + "”:");
                    phoneBook.addContact(input, scanner.nextLine());
                } else {
                    System.out.println("Такой номер уже есть в телефонной книге: ");
                    System.out.println(phoneBook.getContactByPhone(input));

                }
            } else if (input.equals("LIST")) {
                for (String contact : phoneBook.getAllContacts()) {
                    System.out.println(contact);
                }
            }
        }
    }
}
