import com.intellij.openapi.wm.ToolWindow;
import models.CriticalMethod;
import models.CriticalMethodTableModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class OutputToolWindow {

    private JButton hideToolWindowButton;
    private JPanel myToolWindowContent;
    private JScrollPane outputScrollPane;
    private JTable outputTable;

    private CriticalMethodTableModel tableModel;

    public OutputToolWindow(ToolWindow toolWindow) {
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));

        tableModel = new CriticalMethodTableModel();
        readFiloCSV();
    }

    private void readFiloCSV(){
        String pathToCsv = "C:\\Users\\ale\\IdeaProjects\\toolWindowExample\\src\\main\\resources\\FiloOutput.csv";

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

        outputTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = outputTable.getSelectedRow();
                int col = outputTable.getSelectedColumn();

                String valueClicked = outputTable.getValueAt(row, col).toString();

                if(!isNumeric(valueClicked))
                    System.out.println("You clicked on method: " + valueClicked);
            }
        });

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}