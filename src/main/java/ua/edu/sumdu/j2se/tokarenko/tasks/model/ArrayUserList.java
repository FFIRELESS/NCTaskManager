package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import com.google.common.hash.Hashing;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ListTypes;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ArrayUserList implements Iterable<User> {
    private static final Logger logger = Logger.getLogger(ArrayUserList.class);

    private User[] users;
    private int usersAmount;

    private final int INTERVAL = 3;

    /**
     * Конструктор ArrayUserList.
     */
    public ArrayUserList() {
        users = new User[INTERVAL];
    }

    /**
     * Метод, що повертає розмір колекції.
     *
     * @return розмір колекції.
     */
    public int size() {
        return usersAmount;
    }

    /**
     * Метод для додавання користувача в колекцію.
     *
     * @param user - користувач, що додається в колекцію.
     * @throws NullPointerException якщо користувач не заданий.
     */
    public void add(User user) {
        if (user == null) {
            logger.error("User object is null");
            throw new NullPointerException("User object parameter is null");
        }
        if (users.length == usersAmount) {
            User[] tempArr = new User[usersAmount + INTERVAL];

            System.arraycopy(users, 0, tempArr, 0, usersAmount);
            users = tempArr;
        }
        users[usersAmount++] = user;

        logger.debug("User added: " + user);
    }

    /**
     * Метод для видалення користувача зх колекції.
     *
     * @param user - користувач, що додається в колекцію.
     * @return true якщо користувача було видалено, false - користувача не знайдено в колекції.
     * @throws NullPointerException якщо користувач не заданий.
     */
    public boolean remove(User user) {
        if (user == null) {
            logger.error("User object is null");
            throw new NullPointerException("User object parameter has null value!");
        }

        boolean status = false;
        int index_to_del;

        for (index_to_del = 0; index_to_del < usersAmount; index_to_del++) {
            if (users[index_to_del].equals(user)) {
                status = true;
                break;
            }
        }

        if (!status) {
            logger.warn("Removing user not found");

            return false;
        }

        users[index_to_del] = null;
        usersAmount--;

        if (index_to_del != usersAmount) {
            System.arraycopy(users, index_to_del + 1, users, index_to_del, usersAmount - index_to_del);
        }

        if (users.length - INTERVAL == usersAmount && usersAmount != 0) {
            User[] tempArray = new User[usersAmount];
            System.arraycopy(users, 0, tempArray, 0, usersAmount);
            users = tempArray;

            logger.debug("List resized to size " + users.length);
        }
        logger.debug("User removed: " + user);

        return true;
    }

    /**
     * Метод знаходження користувача в колекції.
     *
     * @param index - індекс користувача.
     * @return знайдений користувач.
     * @throws IndexOutOfBoundsException при невірно введених параметрах.
     */
    public User getUser(int index) {
        if (index < 0 || index >= usersAmount) {
            logger.error("User index is out of bound");
            throw new IndexOutOfBoundsException("Invalid user index");
        }
        return users[index];
    }

    /**
     * Метод знаходження користувача в колекції.
     *
     * @param uuid - ідентифікатор користувача.
     * @return знайдений користувач.
     * @throws IllegalArgumentException при невірно введених параметрах.
     */
    public User getUser(UUID uuid) {
        if (uuid == null) {
            logger.error("User parameters are invalid");
            throw new IllegalArgumentException("Invalid user parameters");
        }
        for (int cnt = 0; cnt < usersAmount; cnt++) {
            if (uuid.equals(getUser(cnt).getUserId())) {
                logger.debug("Found user by id");
                return users[cnt];
            }
        }
        logger.debug("User doesnt exists");
        return null;
    }

    /**
     * Метод перевірки існування користувача в колекції.
     *
     * @param username - ім'я користувача.
     * @param password - пароль користувача.
     * @return ідентифікатор користувача.
     * @throws IllegalArgumentException при невірно введених параметрах.
     */
    public UUID isUserAuth(String username, String password) {
        if (username == null || password == null) {
            logger.error("User parameters are invalid");
            throw new IllegalArgumentException("Invalid user parameters");
        }

        String passwordHash = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        for (int cnt = 0; cnt < usersAmount; cnt++) {
            if (username.equals(getUser(cnt).getUsername()) && passwordHash.equals(getUser(cnt).getPassword())) {
                logger.debug("Checked is user exists by auth data");
                return getUser(cnt).getUserId();
            }
        }
        logger.debug("User auth error");
        return null;
    }

    /**
     * Метод перевірки існування користувача в колекції.
     *
     * @param username - ім'я користувача.
     * @return true - користувач існує, false - ні.
     * @throws IllegalArgumentException при невірно введених параметрах.
     */
    public boolean isUserExists(String username) {
        if (username == null) {
            logger.error("User username is invalid");
            throw new IllegalArgumentException("Invalid username parameter");
        }
        for (int cnt = 0; cnt < usersAmount; cnt++) {
            if (username.equals(getUser(cnt).getUsername())) {
                logger.debug("Found user by username");
                return true;
            }
        }
        logger.debug("User doesnt exists");
        return false;
    }

    /**
     * Ітератор колекції.
     *
     * @return ітератор для даної колекції.
     */
    @Override
    public Iterator<User> iterator() {
        return new Iterator<User>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < usersAmount;
            }

            @Override
            public User next() {
                if (index == usersAmount) {
                    throw new NoSuchElementException("An iterator reached the end of the list");
                }
                return users[index++];
            }

            @Override
            public void remove() {
                if (index == 0) {
                    throw new IllegalStateException("Iterator method next() wasn't called");
                }

                index--;
                users[index] = null;
                usersAmount--;

                if (index != usersAmount) {
                    System.arraycopy(users, index + 1, users, index, usersAmount - index);
                }

                if (users.length - INTERVAL == usersAmount && usersAmount != 0) {
                    User[] tempList = new User[usersAmount];
                    System.arraycopy(users, 0, tempList, 0, usersAmount);
                    users = tempList;
                }
            }
        };
    }

    /**
     * Метод клонування об'єктів класу ArrayUserList.
     *
     * @return копію об'єкту.
     */
    @Override
    public ArrayUserList clone() {
        ArrayUserList finalObject = new ArrayUserList();
        for (int cnt = 0; cnt < usersAmount; cnt++) {
            finalObject.add(users[cnt]);
        }
        return finalObject;
    }

    public String toString() {
        Iterator<User> strIterator = this.iterator();
        StringBuilder finalString = new StringBuilder();
        int number = 0;

        finalString.append("ArrayUserList.class | ");
        finalString.append(usersAmount);

        while (strIterator.hasNext()) {
            finalString.append(" | Object");
            finalString.append(number);
            finalString.append(" | ");
            finalString.append(strIterator.next().toString());
            number++;
        }
        return new String(finalString);
    }
}
