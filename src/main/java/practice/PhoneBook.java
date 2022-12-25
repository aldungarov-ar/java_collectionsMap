package practice;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class PhoneBook {

    private final TreeMap<String, String> phoneBook = new TreeMap<>();

    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // (рекомедуется написать отдельные методы для проверки является строка именем/телефоном)
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if (!isPhoneValid(phone) || !isNameValid(name)) {
            System.out.println("Неверный формат ввода");
            return;
        }

        if (phoneBook.containsKey(name)) {
            phoneBook.put(name, phoneBook.get(name) + ", " + phone);
        } else {
            if (phoneBook.containsValue(phone)) {
                replaceNumber(phone);
            }
            phoneBook.put(name, phone);
        }
        System.out.println("Контакт сохранен!");
    }
    
    public void replaceNumber(String newNumber) {
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            String number = entry.getValue();
            if (number.contains(newNumber)) {
                number = number.replace(newNumber, "");
                if (number.isBlank()) {
                    phoneBook.remove(entry.getKey());
                } else {
                    entry.setValue(number);
                }
            }
        }
    }

    public String getContactByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            if (entry.getValue().equals(phone)) {
                return entry.getKey() + " - " + entry.getValue();
            }
        }
        return "";
    }

    public Set<String> getContactByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        String contact = phoneBook.get(name);

        if (contact != null) {
            contact = name + " - " + contact;
        }

        TreeSet<String> set = new TreeSet<>();
        set.add(contact);
        return set;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> allContacts = new TreeSet<>();
        for (Map.Entry<String, String> entry : phoneBook.entrySet()) {
            allContacts.add(entry.getKey() + " - " + entry.getValue());
        }
        return allContacts;
    }

    public static boolean isPhoneValid(String phone) {
        String patterns = "^7\\d{10,}$";
        Pattern pattern = Pattern.compile(patterns);
        return pattern.matcher(phone).matches();
    }

    public static boolean isNameValid(String name) {
        String patterns = "^[A-ZА-Я][a-zа-я]+$";
        Pattern pattern = Pattern.compile(patterns);
        return pattern.matcher(name).matches();
    }
    // для обхода Map используйте получение пары ключ->значение Map.Entry<String,String>
    // это поможет вам найти все ключи (key) по значению (value)
    /*
        for (Map.Entry<String, String> entry : map.entrySet()){
            String key = entry.getKey(); // получения ключа
            String value = entry.getValue(); // получения ключа
        }
    */
}