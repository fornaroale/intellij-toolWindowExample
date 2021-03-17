import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import models.CriticalMethod;
import models.CriticalMethodTableModel;

import javax.swing.*;
import java.awt.*;
import java.io.*;

public class OutputToolWindow {

    private JButton hideToolWindowButton;
    private JPanel myToolWindowContent;
    private JTable outputTable;

    private CriticalMethodTableModel tableModel;

    public OutputToolWindow(ToolWindow toolWindow) {
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));

        tableModel = new CriticalMethodTableModel();
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
                        tableModel.add(new CriticalMethod(data[1], data[2]));
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

        outputTable.setModel(tableModel);
        outputTable.getColumnModel().getColumn(0).setMaxWidth(100);

        // Table Scrollbar
        JScrollPane scrollPane = new JBScrollPane(outputTable);
        myToolWindowContent.setLayout(new BorderLayout());
        myToolWindowContent.add(scrollPane, BorderLayout.CENTER);
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}