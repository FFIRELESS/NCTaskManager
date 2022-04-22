package ua.edu.sumdu.j2se.tokarenko.tasks;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;

public class TaskIO {
    private static final Logger logger = Logger.getLogger(TaskIO.class);

    public static void write(AbstractTaskList tasks, OutputStream out) {
        try (ObjectOutputStream listStream = new ObjectOutputStream(out)) {
            logger.debug("Writing task list in binary format to the stream");

            listStream.writeInt(tasks.size());

            for (Task currentTask : tasks) {
                listStream.writeObject(currentTask);
            }
        } catch (IOException e) {
            logger.fatal("Task list writing error", e);
            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, InputStream in) {
        try (ObjectInputStream listStream = new ObjectInputStream(in)) {
            logger.debug("Reading task list in binary format from the stream");

            int taskAmount = listStream.readInt();

            for (int counter = 0; counter < taskAmount; counter++) {
                tasks.add((Task) listStream.readObject());
            }
        } catch (ClassNotFoundException e) {
            logger.fatal("Error casting read object to the Task class", e);

            e.printStackTrace();
        } catch (IOException e) {
            logger.fatal("Reading error", e);

            e.printStackTrace();
        }
    }

    public static void writeBinary(AbstractTaskList tasks, File file) throws FileNotFoundException {
        try (ObjectOutputStream listStream = new ObjectOutputStream(new FileOutputStream(file))) {
            logger.debug("Writing task list in binary format to the file");

            listStream.writeInt(tasks.size());

            for (Task currentTask : tasks) {
                listStream.writeObject(currentTask);
            }
        } catch (FileNotFoundException e) {
            logger.error("File " + file + " not found", e);

            throw e;
        } catch (IOException e) {
            logger.fatal("Writing error", e);

            e.printStackTrace();
        }
    }

    public static void readBinary(AbstractTaskList tasks, File file) throws FileNotFoundException {
        try (ObjectInputStream listStream = new ObjectInputStream(new FileInputStream(file))) {
            logger.debug("Reading task list in binary format from the file");

            int taskAmount = listStream.readInt();

            for (int counter = 0; counter < taskAmount; counter++) {
                tasks.add((Task) listStream.readObject());
            }
        } catch (ClassNotFoundException e) {
            logger.fatal("Error casting read object to class Task", e);

            e.printStackTrace();
        } catch (FileNotFoundException e) {
            logger.error("File " + file + " not found", e);

            throw e;
        } catch (IOException e) {
            logger.fatal("Error reading task from list", e);

            e.printStackTrace();
        }
    }

    public static void write(AbstractTaskList tasks, Writer out) {
        try (BufferedWriter listStream = new BufferedWriter(out)) {
            logger.debug("Writing task list as text to the stream");

            Gson json = new Gson();
            listStream.write(String.valueOf(tasks.size()));
            listStream.newLine();

            for (Task currentTask : tasks) {
                json.toJson(currentTask, listStream);
                listStream.newLine();
            }
            listStream.flush();
        } catch (IOException e) {
            logger.fatal("Error writing task list", e);

            e.printStackTrace();
        }
    }

    public static void read(AbstractTaskList tasks, Reader in) {
        try (LineNumberReader listStream = new LineNumberReader(in)) {
            logger.debug("Reading task list as text from the stream");

            Gson json = new Gson();
            String lineValue;
            int taskNumber = Integer.parseInt(listStream.readLine());

            for (int counter = 0; counter < taskNumber; counter++) {
                lineValue = listStream.readLine();
                tasks.add(json.fromJson(lineValue, Task.class));
            }
        } catch (IOException e) {
            logger.fatal("Reading error", e);

            e.printStackTrace();
        }
    }

    public static void writeText(AbstractTaskList tasks, File file) throws FileNotFoundException {
        try (Writer writeStream = new OutputStreamWriter(new FileOutputStream(file))) {
            write(tasks, writeStream);
        } catch (FileNotFoundException e) {
            logger.error("File " + file + " not found", e);

            throw e;
        } catch (IOException e) {
            logger.fatal("Creating stream error", e);

            e.printStackTrace();
        }
    }

    public static void readText(AbstractTaskList tasks, File file) throws FileNotFoundException {
        try (Reader readStream = new InputStreamReader(
                new FileInputStream(file))) {
            read(tasks, readStream);
        } catch (FileNotFoundException e) {
            logger.error("File " + file + " not found", e);

            throw e;
        } catch (IOException e) {
            logger.fatal("Creating stream error", e);

            e.printStackTrace();
        }
    }
}