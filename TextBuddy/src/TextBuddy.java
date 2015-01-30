import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextBuddy {

	public static void main(String[] args) {

		String fileName = args[0];

		System.out.println("Welcome to TextBuddy. mytextfile.txt is ready for use");

		Scanner sc = new Scanner(System.in);
		String command = null;

		ArrayList<String> list = new ArrayList<String>();

		do {
			System.out.print("command: ");
			command = sc.next();
			String text = sc.nextLine();
			if (command.equals("add")) {
				add(fileName, text, list);
			}
			else if (command.equals("display")) {
				display(fileName);
			}
			else if (command.equals("delete")) {
				int num = Integer.parseInt(text);
				delete(fileName, num, list);
			}
			else if (command.equals("clear")) {
				clear(fileName, list);
			}
		} while (!command.equals("exit"));



	}

	public static int getIndex(String text, ArrayList<String> list) {
		for (int i=0; i<list.size(); i++) {
			String line = list.get(i);
			if (text.equals(line)) {
				return i;
			}
		}
		return -1;
	}

	public static void updateFile(String fileName, ArrayList<String> list) {

		Scanner sc = null;
		PrintWriter pw = null;

		try {
			sc = new Scanner(new File(fileName));
			pw = new PrintWriter(new File(fileName));		
			for (int i = 0; i<list.size(); i++) {
				String line = list.get(i);
				pw.println(i + ". " + line);
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

	public static void add(String fileName, String text, ArrayList<String> list) {
		Scanner sc = null;
		PrintWriter pw = null;
		int currentIndex = 0;

		try {
			sc = new Scanner(new File(fileName));
			pw = new PrintWriter(new File(fileName));
			if (!(sc.hasNextLine())) {
				list.add(text);
				currentIndex = getIndex(text, list);
				pw.println(currentIndex + ". " + text);
			}
			else if (sc.hasNextLine()) {
				list.add(text);
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
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			} 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sc != null) {
				sc.close();
			}
		}

		System.out.println(fileName + " is empty");
	}

	public static void delete(String fileName, int num, ArrayList<String> list) {
		
		if (!list.isEmpty()) {
			String deletedLine = list.get(num);
			list.remove(num);
			updateFile(fileName, list);
			System.out.println("deleted from " + fileName + ": " + deletedLine);
		}	
	}

	public static void clear(String fileName, ArrayList<String> list) {
		if (!list.isEmpty()) {
			list.clear();
			updateFile(fileName, list);
		}

		System.out.println("all content deleted from " + fileName);
	}

}