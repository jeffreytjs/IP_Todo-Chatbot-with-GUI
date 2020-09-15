import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

public class Duke {
    public static void main(String[] args) throws DukeException {
        System.out.println("Hi there, I'm TARS!\nWhat can I do for you?");
        ArrayList<Task> taskList = ReadWriteFile.readTaskList();
        Scanner scanner = new Scanner(System.in);
        boolean shouldStop = false;

        while (!shouldStop) {
            String[] line = scanner.nextLine().split(" ", 2);
            String operation = line[0];
            switch (operation) {
            case "bye":
                ReadWriteFile.writeToFile(taskList);
                shouldStop = true;
                System.out.println("Bye bye. See you again soon!");
                break;
            case "list":
                try {
                    Helper.showList(taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "done":
                try {
                    Helper.markAsDone(line, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case "todo":
            case "deadline":
            case "event":
                if (line.length == 1) {
                    System.out.println("☹ OOPS! Description for command '"
                            + operation + "' not found, try again!");
                    break;
                } else if (operation.equals("todo")) {
                    try {
                        Helper.addToDo(line, taskList);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (operation.equals("deadline")) {
                    try {
                        Helper.addDeadline(line, taskList);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    try {
                        Helper.addEvent(line, taskList);
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                }
                break;
            case "delete":
                try {
                    Helper.deleteTask(line, taskList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
                break;
            default:
                // Handles all other inputs
                System.out.println("☹ Sorry, I don't recognise that command! "
                        + "Try one of the following instead: todo, event, deadline, done or delete");
            }
            ReadWriteFile.writeToFile(taskList);
        }
    }
}