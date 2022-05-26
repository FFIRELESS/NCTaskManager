package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayUserList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;
import ua.edu.sumdu.j2se.tokarenko.tasks.view.AuthActionsView;

public class AuthController extends UserActionsController {
    protected static final Logger logger = Logger.getLogger(AuthController.class);

    private final AuthActionsView authActionsView = new AuthActionsView();

    /**
     * Метод авторизації користувача.
     *
     * @param users колекція користувачів.
     * @return наступний(обраний) режим програми.
     */
    @Override
    public ProgramModes process(ArrayUserList users) {
        if (getBufferedUser() == null) {
            createNewUser();
        }

        authActionsView.setUsername();
        username = ConsoleInputController.nextString();
        editUsername();

        authActionsView.setPassword();
        password = ConsoleInputController.nextString();
        editPassword();

        userId = users.isUserAuth(username, password);

        if (userId != null) {
            try {
                selectedUser(users.getUser(userId));
            } catch (CloneNotSupportedException e) {
                logger.error("Exception: " + e);
                return ProgramModes.AUTH_MENU;
            }
            logger.debug("Authorization success");
            authActionsView.authSuccess();
            return ProgramModes.MAIN_MENU;
        }
        logger.error("Authorization failed");
        clearBuffer();
        authActionsView.authError();
        return ProgramModes.AUTH_MENU;
    }
}
