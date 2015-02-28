// Tricia Tan
// A0116733J
// F09

import java.io.*;
import java.util.*;

public class TextBuddy {

    private static final String WELCOME_MESSAGE = "Welcome to TextBuddy. mytextfile.txt is ready for use";

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println(WELCOME_MESSAGE);

        readInput(args[0]);

	}

    public static void readInput(String fileName) throws FileNotFoundException {

        Scanner sc = new Scanner(System.in);
        String command = null;

        List<String> list = new ArrayList<String>();
        synchronize(fileName, list);
        //synchronise arraylist and file

        List<String> sortList = new ArrayList<String>();

        do {
            System.out.print("command: ");
            command = sc.next();
            String text = sc.nextLine();
            if (command.equals("add")) {
                add(fileName, text, list, sortList);
            }
            else if (command.equals("display")) {
                display(fileName);
            }
            else if (command.equals("delete")) {
                int num = Integer.parseInt(text.trim());
                delete(fileName, num-1, list, sortList);
            }
            else if (command.equals("clear")) {
                clear(fileName, list, sortList);
            }
            else if (command.equals("search")) {
                search(text, fileName, list);
            }
            else if (command.equals("sort")) {
                sort(fileName, list, sortList);
            }
        } while (!command.equals("exit"));

    }
	
	public static void synchronize(String fileName, List<String> list) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(fileName));
		while (sc.hasNextLine()) {
			String text = sc.nextLine();
			list.add(text);
		}
	}

	public static int getIndex(String text, List<String> list) {
		for (int i=0; i<list.size(); i++) {
			String line = list.get(i);
			if (text.equals(line)) {
				return i+1;
			}
		}
		return 0;
	}

	public static void updateFile(String fileName, List<String> list) throws FileNotFoundException {

		Scanner sc = null;
		PrintWriter pw = null;

		try {
			sc = new Scanner(new File(fileName));
			pw = new PrintWriter(new File(fileName));
			for (int i = 0; i<list.size(); i++) {
				String line = list.get(i);
				pw.println(i+1 + "." + line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (pw != null) {
				pw.close();
			}
		}
	}

	public static void add(String fileName, String text, List<String> list, List<String> sortList) {
		Scanner sc = null;
		PrintWriter pw = null;

		try {
			sc = new Scanner(new File(fileName));
			pw = new PrintWriter(new File(fileName));
			if (!(sc.hasNextLine())) {
				list.add(text);
                sortList.add(text);
				updateFile(fileName, list);
			}
			else if (sc.hasNextLine()) {
				list.add(text);
                sortList.add(text);
				updateFile(fileName, list);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
			if (pw != null) {
				pw.close();
			}
		}
		System.out.println("added to " + fileName + ": " + text);
	}

	public static void display(String fileName) {
		Scanner sc = null;

		try {
			sc = new Scanner(new File(fileName));	
			if (sc.hasNextLine()) {
				do {
				System.out.println(sc.nextLine());
				} while (sc.hasNextLine());
			} else {
				System.out.println(fileName + " is empty");
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
	}

	public static void delete(String fileName, int num, List<String> list, List<String> sortList) throws FileNotFoundException {

        if (list.isEmpty()) {
            System.out.println(fileName + " is empty. There's nothing to delete!");
        }

		if (!list.isEmpty()) {
			String deletedLine = list.get(num);
			list.remove(num);
            sortList.remove(num);
			updateFile(fileName, list);
			System.out.println("deleted from " + fileName + ": " + deletedLine);
		}
	}

	public static void clear(String fileName, List<String> list, List<String> sortList) throws FileNotFoundException {

        if (list.isEmpty()) {
            System.out.println(fileName + " is empty. There's nothing to clear!");
        }
		if (!list.isEmpty()) {
			list.clear();
            sortList.clear();
			updateFile(fileName, list);
            System.out.println("all content deleted from " + fileName);
		}


	}

    public static void sort(String fileName, List<String> list, List<String> sortList) throws FileNotFoundException {
        Collections.sort(sortList, String.CASE_INSENSITIVE_ORDER);
        if (!list.isEmpty()) {
            list.clear();
            updateFile(fileName, list);
        }
        Iterator<String> it = sortList.iterator();
        int index = 1;
        while (it.hasNext()) {
            String text = it.next();
            list.add(index + "." + text);
        }

       updateFile(fileName, sortList);

        System.out.println(fileName + " is sorted.");
    }

    public static void search(String word, String fileName, List<String> list) {
        List<String> searchList = new ArrayList<String>();
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String line = itr.next();
            int index = getIndex(line, list);
            if (line.contains(word)) {
                searchList.add(index + "." + line);
            }
        }
        Iterator<String> iter = searchList.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

}