import com.intellij.openapi.wm.ToolWindow;
import javax.swing.*;
import java.io.*;

public class OutputToolWindow {

    private JButton hideToolWindowButton;
    private JPanel myToolWindowContent;
    private JLabel OutputLabel;

    public OutputToolWindow(ToolWindow toolWindow) {
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));

        readFiloCSV();
    }

    private void readFiloCSV(){
        String pathToCsv = "C:\\Users\\ale\\IdeaProjects\\toolWindowExample\\src\\main\\resources\\FiloOutput.csv";
        String outputText = "<html>";

        File csvFile = new File(pathToCsv);
        if (csvFile.isFile()) {
            BufferedReader csvReader = null;
            try {
                csvReader = new BufferedReader(new FileReader(pathToCsv));
                String row = "";
                boolean firstLine = true;
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split("\\|");

                    if(!firstLine) {
                        // Add the MethodName
                        outputText = outputText + "Method: " + data[1] + "<br/>";
                        // Add the relevancy
                        outputText = outputText + "Relevancy: " + data[2] + "<br/><br/>";
                    }

                    firstLine = false;
                }
                csvReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OutputLabel.setText(outputText + "</html>");
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

}