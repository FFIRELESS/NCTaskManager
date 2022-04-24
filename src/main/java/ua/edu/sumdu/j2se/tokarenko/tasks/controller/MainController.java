package ua.edu.sumdu.j2se.tokarenko.tasks.controller;

import ua.edu.sumdu.j2se.tokarenko.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.tokarenko.tasks.utils.ProgramModes;

public class MainController extends BaseController {
    private final BaseController todayTasksController = new TodayTasksController();

    private final BaseController mainMenuController = new MainMenuController();

    private final BaseController createTaskController = new CreateTaskController();

    private final BaseController changeTaskController = new ChangeTaskController();

    private final BaseController printCalendarController = new PrintCalendarController();

    private final BaseController printTasksController = new PrintTasksController();

    private final BaseController fileIOController = new FileIOController();

    private final AlertsController alertsController = new AlertsController();

    ProgramModes mode = ProgramModes.MAIN_MENU;

    @Override
    public void process() {
        AbstractTaskList storedTasks = fileIOController.readFileProcess();

        if (storedTasks.size() != 0) {
            alertsController.process(storedTasks);
        }

        while (!mode.equals(ProgramModes.EXIT)) {
            if (mode.equals(ProgramModes.MAIN_MENU)) {
                mode = todayTasksController.process(storedTasks);
                mode = mainMenuController.process(mode);
            }

            switch (mode) {
                case ADD:
                    mode = mainMenuController.process(mode);
                    mode = createTaskController.process(storedTasks, mode, alertsController);
                    break;
                case CHANGE:
                    mode = mainMenuController.process(mode);
                    mode = changeTaskController.process(storedTasks, mode, alertsController);
                    break;
                case PRINT_CALENDAR:
                    mode = printCalendarController.process(storedTasks);
                    break;
                case PRINT_ALL:
                    mode = printTasksController.process(storedTasks);
                    break;
                default:
                    System.out.println("Incorrect");
                    break;
            }
        }
        fileIOController.writeFileProcess(storedTasks);
    }
}
