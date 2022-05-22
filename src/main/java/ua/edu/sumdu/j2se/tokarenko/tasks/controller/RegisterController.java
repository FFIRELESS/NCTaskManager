package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.tokarenko.tasks.model.ArrayUserList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

public class RegisterController extends UserActionsController {
    protected static final Logger logger = Logger.getLogger(RegisterController.class);

    protected BaseController savingController = new SavingController();

    /**
     * Метод реєстрації користувача.
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
        username = ConsoleInputController.nextUsername();
        editUsername();

        if (!users.isUserExists(username)) {
            authActionsView.setPassword();
            password = ConsoleInputController.nextPassword();

            authActionsView.repeatPassword();
            String repeatPassword = ConsoleInputController.nextString();

            if (!password.equals(repeatPassword)) {
                logger.error("Password error");
                clearBuffer();
                authActionsView.errorRepeatPassword();
                return ProgramModes.AUTH_MENU;
            }

            editPassword();
            editId();

            logger.debug("Registration success");
            savingController.process(users, this);
            authActionsView.registerSuccess();
            return ProgramModes.AUTH_MENU;
        }
        logger.error("Registration error");
        clearBuffer();
        authActionsView.registerError();
        return ProgramModes.AUTH_MENU;
    }
}
