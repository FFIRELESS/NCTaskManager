package ua.edu.sumdu.j2se.tokarenko.tasks.model;

import com.google.gson.Gson;
import org.apache.log4j.Logger;

import java.io.*;

public class UserIO {
    private static final Logger logger = Logger.getLogger(UserIO.class);

    /**
     * Метод запису користувачів в потік в бінарному вигляді.
     *
     * @param users колекція користувачів.
     * @param out   вихідний потік OutputStream.
     */
    public static void write(ArrayUserList users, OutputStream out) {
        try (ObjectOutputStream listStream = new ObjectOutputStream(out)) {
            logger.debug("Writing user list in binary format to the stream");

            listStream.writeInt(users.size());

            for (User currentUser : users) {
                listStream.writeObject(currentUser);
            }
        } catch (IOException e) {
            logger.fatal("User list writing error");
            e.printStackTrace();
        }
    }

    /**
     * Метод зчитування користувачів з потоку в бінарному вигляді.
     *
     * @param users колекція користувачів.
     * @param in    вхідний потікInputStream.
     */
    public static void read(ArrayUserList users, InputStream in) {
        try (ObjectInputStream listStream = new ObjectInputStream(in)) {
            logger.debug("Reading user list in binary format from the stream");

            int usersAmount = listStream.readInt();

            for (int counter = 0; counter < usersAmount; counter++) {
                users.add((User) listStream.readObject());
            }
        } catch (ClassNotFoundException e) {
            logger.fatal("Error casting read object to the User class");
            e.printStackTrace();
        } catch (IOException e) {
            logger.fatal("Reading error");
            e.printStackTrace();
        }
    }

    /**
     * Метод запису списку користувачів у файл в бінарному вигляді.
     *
     * @param users колекція користувачів.
     * @param file  бінарний файл.
     * @throws FileNotFoundException коли файл не було знайдено.
     */
    public static void writeBinary(ArrayUserList users, File file) throws FileNotFoundException {
        try (ObjectOutputStream listStream = new ObjectOutputStream(new FileOutputStream(file))) {
            logger.debug("Writing user list in binary format to the file");

            listStream.writeInt(users.size());

            for (User currentUser : users) {
                listStream.writeObject(currentUser);
            }
        } catch (FileNotFoundException e) {
            logger.error("File " + file + " not found");
            throw e;
        } catch (IOException e) {
            logger.fatal("Writing error");
            e.printStackTrace();
        }
    }

    /**
     * Метод зчитування списку користувачів з файлу в бінарному вигляді.
     *
     * @param users колекція користувачів.
     * @param file  бінарний файл.
     * @throws FileNotFoundException коли файл не було знайдено.
     */
    public static void readBinary(ArrayUserList users, File file) throws FileNotFoundException {
        try (ObjectInputStream listStream = new ObjectInputStream(new FileInputStream(file))) {
            logger.debug("Reading user list in binary format from the file");

            int userAmount = listStream.readInt();

            for (int counter = 0; counter < userAmount; counter++) {
                users.add((User) listStream.readObject());
            }
        } catch (ClassNotFoundException e) {
            logger.fatal("Error casting read object to class User");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            logger.error("File " + file + " not found");
            throw e;
        } catch (IOException e) {
            logger.fatal("Error reading users from list");
            e.printStackTrace();
        }
    }

    /**
     * Метод запису списку користувачів в потік.
     *
     * @param users колекція користувачів.
     * @param out   вихідний потік Writer.
     */
    public static void write(ArrayUserList users, Writer out) {
        try (BufferedWriter listStream = new BufferedWriter(out)) {
            logger.debug("Writing user list as text to the stream");

            Gson json = new Gson();
            listStream.write(String.valueOf(users.size()));
            listStream.newLine();

            for (User currentTask : users) {
                json.toJson(currentTask, listStream);
                listStream.newLine();
            }
            listStream.flush();
        } catch (IOException e) {
            logger.fatal("Error writing user list");
            e.printStackTrace();
        }
    }

    /**
     * Метод зчитування списку користувачів з потоку.
     *
     * @param users колекція користувачів.
     * @param in    вхідний потік Reader.
     */
    public static void read(ArrayUserList users, Reader in) {
        try (LineNumberReader listStream = new LineNumberReader(in)) {
            logger.debug("Reading user list as text from the stream");

            Gson json = new Gson();
            String lineValue;
            int userNumber = Integer.parseInt(listStream.readLine());

            for (int counter = 0; counter < userNumber; counter++) {
                lineValue = listStream.readLine();
                users.add(json.fromJson(lineValue, User.class));
            }
        } catch (IOException e) {
            logger.fatal("Reading error");
            e.printStackTrace();
        }
    }

    /**
     * Метод запису списку користувачів до файлу в текстовому форматі.
     *
     * @param users колекція користувачів.
     * @param file  файл json.
     * @throws FileNotFoundException коли файл не було знайдено.
     */
    public static void writeText(ArrayUserList users, File file) throws FileNotFoundException {
        try (Writer writeStream = new OutputStreamWriter(new FileOutputStream(file))) {
            logger.debug("Writing user list as text to the file");

            write(users, writeStream);
        } catch (FileNotFoundException e) {
            logger.error("File " + file + " not found");
            throw e;
        } catch (IOException e) {
            logger.fatal("Creating stream error");
            e.printStackTrace();
        }
    }

    /**
     * Метод зчитування списку користувачів з файлу в текстовому форматі.
     *
     * @param users колекція користувачів.
     * @param file  файл json.
     * @throws FileNotFoundException коли файл не було знайдено.
     */
    public static void readText(ArrayUserList users, File file) throws FileNotFoundException {
        try (Reader readStream = new InputStreamReader(new FileInputStream(file))) {
            logger.debug("Reading user list as text from the file");
            read(users, readStream);
        } catch (FileNotFoundException e) {
            logger.error("File " + file + " not found");
            throw e;
        } catch (IOException e) {
            logger.fatal("Creating stream error");
            e.printStackTrace();
        }
    }
}
