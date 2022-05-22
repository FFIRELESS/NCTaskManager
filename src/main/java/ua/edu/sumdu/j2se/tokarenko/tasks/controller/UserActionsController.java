package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.User;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.AuthActionsView;

import java.util.UUID;

public class UserActionsController extends BaseController {
    protected static final Logger logger = Logger.getLogger(UserActionsController.class);

    private static User bufferedUser;
    protected AuthActionsView authActionsView = new AuthActionsView();

    protected UUID userId;
    protected String username;
    protected String password;

    /**
     * Метод створення користувача.
     */
    public void createNewUser() {
        bufferedUser = new User();
    }

    /**
     * Метод обирання даного користувача.
     *
     * @param user користувач.
     * @throws CloneNotSupportedException при помилці клонування користувача.
     */
    public void selectedUser(User user) throws CloneNotSupportedException {
        bufferedUser = user.clone();
        logger.debug("User selected: " + bufferedUser.toString());
    }

    /**
     * Метод перевірки користувача на null.
     */
    public boolean isUserNull() {
        return userId == null || username == null || password == null;
    }

    /**
     * Метод редагування імені користувача.
     */
    public void editUsername() {
        bufferedUser.setUsername(username);
    }

    /**
     * Метод редагування ідентифікатора користувача.
     */
    public void editId() {
        bufferedUser.setUserId(UUID.randomUUID());
    }

    /**
     * Метод редагування паролю користувача.
     */
    public void editPassword() {
        bufferedUser.setPassword(password);
    }

    /**
     * Метод отримання буферизованого користувача.
     *
     * @return буферизовану задачу.
     */
    static public User getBufferedUser() {
        return bufferedUser;
    }

    /**
     * Метод очищення буферу користувача.
     */
    public void clearBuffer() {
        bufferedUser = null;
        logger.debug("User buffer cleared");
    }
}
