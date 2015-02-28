import static org.junit.Assert.*;

import org.junit.Test;


public class TextBuddyTest {

    @Test
    public void testExecuteCommand() {

        // test basic commands
        testOneCommand("simple add", "added to mytextfile.txt: cookies", "add cookies");
        testOneCommand("simple add", "added to mytextfile.txt: milk", "add milk");
        testOneCommand("simple add", "added to mytextfile.txt: eggs", "add eggs");
        testOneCommand("simple add", "added to mytextfile.txt: butter", "add butter");
        testOneCommand("simple display", "1. cookies, 2. milk, 3. eggs, 4. butter", "display");
        testOneCommand("simple delete first item on the list", "deleted from mytextfile.txt: cookies", "delete 1");
        testOneCommand("simple display after deleting", "1. milk, 2. eggs, 3. butter", "display");
        testOneCommand("simple clear", "all content deleted from mytextfile.txt", "clear");

        //test sort function
        testOneCommand("simple add", "added to mytextfile.txt: sally", "add sally");
        testOneCommand("simple add", "added to mytextfile.txt: larry", "add larry");
        testOneCommand("simple add", "added to mytextfile.txt: ben", "add ben");
        testOneCommand("simple add", "added to mytextfile.txt: alicia", "add alicia");
        testOneCommand("simple sort", "mytextfile.txt is sorted", "sort");
        testOneCommand("simple display sorted list", "1. alicia, 2. ben, 3. larry, 4. sally", "display");

        //test search
        testOneCommand("simple add", "added to mytextfile.txt: alicia law", "add alicia law");
        testOneCommand("simple search", "1. alicia, 5. alicia law", "search alicia");

    }


    private void testOneCommand(String description, String expected, String command) {
        assertEquals(description, expected, TextBuddy.executeCommand(command));
    }
}
