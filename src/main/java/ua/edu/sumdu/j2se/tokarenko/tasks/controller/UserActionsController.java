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
     * Метод створення буферизованого користувача.
     */
    public void createNewUser() {
        bufferedUser = new User();
        logger.debug("Created new buffered user");
    }

    /**
     * Метод обирання буферизованого користувача.
     *
     * @param user користувач.
     * @throws CloneNotSupportedException при помилці клонування користувача.
     */
    public void selectedUser(User user) throws CloneNotSupportedException {
        bufferedUser = user.clone();
        logger.debug("User selected: " + bufferedUser.toString());
    }

    /**
     * Метод редагування імені буферизованого користувача.
     */
    public void editUsername() {
        bufferedUser.setUsername(username);
        logger.debug("Set buffered user username: '" + username + "'");
    }

    /**
     * Метод редагування ідентифікатора буферизованого користувача.
     */
    public void editId() {
        bufferedUser.setUserId(UUID.randomUUID());
        logger.debug("Set buffered user userId: '" + userId + "'");
    }

    /**
     * Метод редагування паролю буферизованого користувача.
     */
    public void editPassword() {
        bufferedUser.setPassword(password);
        logger.debug("Set buffered user password: '" + password + "'");
    }

    /**
     * Метод отримання буферизованого користувача.
     *
     * @return буферизований користувач.
     */
    static public User getBufferedUser() {
        return bufferedUser;
    }

    /**
     * Метод очищення буферизованого користувача.
     */
    public void clearBuffer() {
        bufferedUser = null;
        logger.debug("Buffered user cleared");
    }
}
