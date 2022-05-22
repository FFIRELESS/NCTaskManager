package ua.edu.sumdu.j2se.tokarenko.tasks.view;

public class AuthActionsView extends PrintTasksView {
    /**
     * Метод виведення поля редагування імені користувача.
     */
    public void setUsername() {
        newEmptyLine();
        printChooser("Введіть логін: ");
    }

    /**
     * Метод виведення поля редагування паролю користувача.
     */
    public void setPassword() {
        newEmptyLine();
        printChooser("Введіть пароль: ");
    }

    /**
     * Метод виведення поля повторного введення паролю користувача.
     */
    public void repeatPassword() {
        newEmptyLine();
        printChooser("Повторіть пароль: ");
    }

    /**
     * Метод виведення помилки повторного введення паролю користувача.
     */
    public void errorRepeatPassword() {
        newEmptyLine();
        printWarning("Паролі не співпадають.");
    }

    /**
     * Метод виведення підтвердження входу користувача.
     */
    public void authSuccess() {
        newEmptyLine();
        printParagraph("Авторизовано");
    }

    /**
     * Метод виведення помилки входу користувача.
     */
    public void authError() {
        newEmptyLine();
        printWarning("Невірний логін або пароль");
    }

    /**
     * Метод виведення підтвердження реєстрації користувача.
     */
    public void registerSuccess() {
        newEmptyLine();
        printParagraph("Користувача створено.");
        printParagraph("Увійдіть у систему");
    }

    /**
     * Метод виведення помилки реєстрації користувача.
     */
    public void registerError() {
        newEmptyLine();
        printWarning("Користувач з таким іменем вже існує");
    }
}
