import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class FileManager{
    private Scanner scanner1;
    private Scanner scanner2;

    private FileWriter outputWriter;

    private File file1;
    private File file2;

    public FileManager() {
    }

    //is whitespace sensitive
    //maybe add extra usage to specify delimiters and how to split
    public boolean compareTextFiles(String file1Name, String file2Name) {
        if(!(file1Name.contains(".txt") && file2Name.contains(".txt"))) {
            System.out.println("Method is for two .txt files");
            return false;
        }

        Boolean areSame = true;

        try {

            String[] items1;
            String[] items2;

            scanner1 = new Scanner(new File(file1Name));
            scanner2 = new Scanner(new File(file2Name));

            scanner1.useDelimiter("\n");
            scanner2.useDelimiter("\n");

            while (scanner1.hasNext() && scanner2.hasNext()) {
                items1 = scanner1.next().split(" ");
                items2 = scanner2.next().split(" ");

                if(items1.length != items2.length) {
                    areSame = false;
                    break;
                }



                for(int i = 0; i < items1.length; i++) {
                    System.out.println(items1[i] + " " + items2[i]);
                    if(!(items1[i].equals(items2[i]))) {
                        areSame = false;
                        break;
                    }
                }

                if(!areSame) {
                    break;
                }
            }

            if(scanner1.hasNext() || scanner2.hasNext()) {
                areSame =  false;
            }

        }
        catch(Exception ex) {
            System.err.println(ex);
        }

        //be tidy
        scanner1.close();
        scanner2.close();

        if(areSame) {
            System.out.println("These files are the same!");
            return true;
        }

        System.out.println("These files are not the same :(");
        return false;
    }

    //take in a name to create an output file with
    public void setOutputFileName(String name) {
        if(name == null) {
            System.err.println("No name provided for output file");
            return;
        }

        try {
           outputWriter = new FileWriter(name);
           System.out.println("Successfully created new FileWriter");
        }
        catch(IOException ex) {
            System.err.println();
            return;
        }
    }
    public void writeToCurrentFile(String input) {
        if(outputWriter == null) {
            System.err.println("No file to output to has been provided");
            return;
        }

        try {
            outputWriter.write(input);
        }
        catch(Exception ex) {
            System.err.println(ex);
        }

    }

    //closes the current filewriter
    public void closeCurrentFile() {
        if(outputWriter == null) {
            System.err.println("No file to close");
            return;
        }

        try {
            outputWriter.close();
        }
        catch(IOException ex) {
            System.err.println(ex);
        }

    }


}