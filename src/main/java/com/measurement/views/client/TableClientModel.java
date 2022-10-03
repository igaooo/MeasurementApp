package com.measurement.views.client;

import java.util.ArrayList;
import java.util.List;

import com.measurement.models.Client;
import com.measurement.views.table.TableModel;

public class TableClientModel extends TableModel<Client> {

    /**
     * 
     */
    private static final long serialVersionUID = -5905367160476065349L;

    private final String column[] = {
            "Código",
            "Nome",
            "Senha",
            "Nome de usuário"
    };

    private final int columnSize[] = {
20, 20, 20, 20
    };

    private List<Client> table;

    public TableClientModel() {
        table = new ArrayList<Client>();

    }

    public TableClientModel(List<Client> table) {
        super(table);
        this.table = table;
        setColumn(column);
    }

    @Override
    public Object getValueAt(int line, int column) {
        switch (column) {
            case 0:
                return table.get(line)
                        .getId();
            case 1:
                return table.get(line)
                        .getName();
            case 2:
                return table.get(line)
                        .getPassword();
            case 3:
                return table.get(line)
                        .getUserName();
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {
        switch (column) {
            case 0:
                return Long.class;
            case 1:
            case 2:
            case 3:
                return String.class;
            default:
                return null;
        }
    }

    public Client getClient(int index) {
        return getTable().get(index);
    }

    public void addClient(Client client) {
        getTable().add(client);

        fireTableRowsInserted(getRowCount() - 1, getColumnCount() - 1);
    }

    public void updateCleint(Client client, int index) {
        getTable().set(index, client);
        fireTableRowsUpdated(index, index);
    }

    public void deleteClient(int index) {
        getTable().remove(index);
        fireTableRowsDeleted(index, index);
    }

    public void deleteAll() {
        getTable().clear();
        fireTableDataChanged();
    }

    public List<Client> getTable() {
        return table;
    }

    public void setTable(List<Client> table) {
        this.table = table;
    }

    public String[] getColumn() {
        return column;
    }

    public int[] getColumnSize() {
        return columnSize;
    }
}
