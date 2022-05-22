package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import com.google.common.hash.Hashing;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class User implements Cloneable, Serializable {
    private static final Logger logger = Logger.getLogger(User.class);

    private UUID userId;
    private String username;
    private String password;

    /**
     * Конструктор порожніх користувачів.
     */
    public User() {
        logger.debug("Created new user: " + this);
    }

    /**
     * Конструктор користувачів.
     *
     * @param userId   ідентифікатор користувача.
     * @param username ім'я користувача.
     * @param password пароль користувача.
     * @throws IllegalArgumentException якщо параметри = null.
     */
    public User(UUID userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    /**
     * Getter ідентифікатору користувача.
     *
     * @return ідентифікатор користувача.
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Setter ідентифікатору користувача.
     *
     * @param userId ідентифікатор користувача.
     * @throws IllegalArgumentException якщо userId = null.
     */
    public void setUserId(UUID userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId parameter is null");
        }
        this.userId = userId;
        logger.debug("Saved new userId of user: " + this);
    }

    /**
     * Getter імені користувача.
     *
     * @return ім'я користувача.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter імені користувача.
     *
     * @param username ім'я користувача.
     * @throws IllegalArgumentException якщо username = null.
     */
    public void setUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username parameter is null");
        }
        this.username = username;
        logger.debug("Saved new username of user: " + this);
    }

    /**
     * Getter паролю користувача.
     *
     * @return пароль користувача.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter паролю користувача.
     *
     * @param password пароль користувача.
     * @throws IllegalArgumentException якщо password = null.
     */
    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("password parameter is null");
        }
        this.password = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        logger.debug("Saved new password of user: " + this);
    }

    /**
     * Метод клонування об'єктів класу User.
     *
     * @return вказівник на клонований об'єкт.
     * @throws CloneNotSupportedException якщо клас не наслідує інтерфейс Cloneable.
     */
    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    /**
     * Метод приведення об'єкту колекції до текстового формату.
     *
     * @return рядок, що вміщує значення всіх полів об'єкту колекції.
     */
    @Override
    public String toString() {
        return "User {" +
                "userId='" + userId + "'" +
                ", username=" + username +
                ", password=" + password +
                "}";
    }
}
