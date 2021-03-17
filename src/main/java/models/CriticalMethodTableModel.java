package models;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class CriticalMethodTableModel extends AbstractTableModel {

    private List<CriticalMethod> items;

    public CriticalMethodTableModel() {
        items = new ArrayList<>();
    }

    public CriticalMethodTableModel(List<CriticalMethod> items) {
        this.items = items;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Float.class;
            case 1: return String.class;
        }

        return Object.class;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Relevancy";
            case 1: return "Eventually critical method";
        }

        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CriticalMethod item = items.get(rowIndex);
        switch (columnIndex) {
            case 0: return item.getRelevancy();
            case 1: return item.getDesc();
        }
        return null;
    }

    public void add(CriticalMethod item) {
        items.add(item);
        int row = items.indexOf(item);
        fireTableRowsInserted(row, row);
    }

    public void remove(CriticalMethod item) {
        if (items.contains(item)) {
            int row = items.indexOf(item);
            items.remove(row);
            fireTableRowsDeleted(row, row);
        }
    }

}