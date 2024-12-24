package kpp.pz5_webapp.DAO;

import kpp.pz5_webapp.hierarchy.classes.OfficeLEDMonitor;
import kpp.pz5_webapp.myList.MyListImpl;

import java.sql.*;

public class MySQLDAO implements IMyDAO {
    private final Connection connection;

    public MySQLDAO() throws SQLException {
        this.connection = Connector.createConnection();
    }

    @Override
    public void addMonitor(OfficeLEDMonitor monitor) throws SQLException {
        String query = "INSERT INTO Monitor (brand, model, screenSize, price) VALUES (?, ?, ?, ?)";
        String queryChild = "INSERT INTO LEDMonitor (id, isCurved) VALUES (?, ?)";
        String queryGrandChild = "INSERT INTO OfficeLEDMonitor (id, hasBlueLightFilter) VALUES (?, ?)";

        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        connection.setAutoCommit(false);
        try {
            try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement psChild = connection.prepareStatement(queryChild);
                 PreparedStatement psGrandChild = connection.prepareStatement(queryGrandChild)) {
                ps.setString(1, monitor.getBrand());
                ps.setString(2, monitor.getModel());
                ps.setDouble(3, monitor.getScreenSize());
                ps.setDouble(4, monitor.getPrice());
                ps.executeUpdate();

                ResultSet keys = ps.getGeneratedKeys();
                if (keys.next()) {
                    int monitorId = keys.getInt(1);

                    psChild.setInt(1, monitorId);
                    psChild.setBoolean(2, monitor.getIsCurved());
                    psChild.executeUpdate();


                    psGrandChild.setInt(1, monitorId);
                    psGrandChild.setBoolean(2, monitor.getHasBlueLightFilter());
                    psGrandChild.executeUpdate();

                }
            }
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public void removeMonitor(String model) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
        connection.setAutoCommit(false);
        String find = "SELECT id FROM Monitor WHERE model = ?";
        String delete = "DELETE FROM Monitor WHERE id = ?";
        try {
            try (PreparedStatement findPs = connection.prepareStatement(find);
                 PreparedStatement deletePs = connection.prepareStatement(delete)) {
                findPs.setString(1, model);
                ResultSet resultSet = findPs.executeQuery();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");

                    deletePs.setInt(1, id);
                    deletePs.executeUpdate();

                    System.out.println("Deleted monitor model: " + model);
                } else {
                    System.out.println("No monitor model: " + model);
                }
            }
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public MyListImpl<OfficeLEDMonitor> findMonitor(String brand) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        String query = "SELECT m.id, m.brand, m.model, m.screenSize, m.price, l.isCurved, o.hasBlueLightFilter FROM Monitor m JOIN LEDMonitor l ON m.id = l.id JOIN OfficeLEDMonitor o ON l.id = o.id WHERE m.brand = ?";
        MyListImpl<OfficeLEDMonitor> monitors = new MyListImpl<>();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, brand);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    monitors.add(new OfficeLEDMonitor(
                            rs.getInt("id"),
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getDouble("screenSize"),
                            rs.getDouble("price"),
                            rs.getBoolean("isCurved"),
                            rs.getBoolean("hasBlueLightFilter")
                    ));
                }
            }
        }
        return monitors;
    }

    @Override
    public MyListImpl<OfficeLEDMonitor> getAllMonitors() throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        MyListImpl<OfficeLEDMonitor> monitors = new MyListImpl<>();
        String query = "SELECT * FROM Monitor m JOIN LEDMonitor l ON m.id = l.id JOIN OfficeLEDMonitor o ON l.id = o.id";
        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                monitors.add(new OfficeLEDMonitor(
                        rs.getInt("id"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getDouble("screenSize"),
                        rs.getDouble("price"),
                        rs.getBoolean("isCurved"),
                        rs.getBoolean("hasBlueLightFilter")
                ));
            }
        }
        return monitors;
    }

    @Override
    public void updateMonitor(OfficeLEDMonitor updatedMonitor) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        connection.setAutoCommit(false);
        String query = "UPDATE Monitor SET brand = ?, screenSize = ?, price = ? WHERE model = ?";
        String queryChild = "UPDATE LEDMonitor SET isCurved = ? WHERE id = (SELECT id FROM Monitor WHERE model = ? LIMIT 1)";
        String queryGrandChild = "UPDATE OfficeLEDMonitor SET hasBlueLightFilter = ? WHERE id = (SELECT id FROM Monitor WHERE model = ? LIMIT 1)";
        try  {
            try (
                    PreparedStatement ps = connection.prepareStatement(query);
                    PreparedStatement psChild = connection.prepareStatement(queryChild);
                    PreparedStatement psGrandChild = connection.prepareStatement(queryGrandChild)
            ) {
                ps.setString(1, updatedMonitor.getBrand());
                ps.setDouble(2, updatedMonitor.getScreenSize());
                ps.setDouble(3, updatedMonitor.getPrice());
                ps.setString(4, updatedMonitor.getModel());
                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    psChild.setBoolean(1, updatedMonitor.getIsCurved());
                    psChild.setString(2, updatedMonitor.getModel());
                    psChild.executeUpdate();

                    psGrandChild.setBoolean(1, updatedMonitor.getHasBlueLightFilter());
                    psGrandChild.setString(2, updatedMonitor.getModel());
                    psGrandChild.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public OfficeLEDMonitor getOfficeLEDMonitorByModel(String model) throws SQLException {
        connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        String query = "SELECT * FROM Monitor m JOIN LEDMonitor l ON m.id = l.id JOIN OfficeLEDMonitor o ON l.id = o.id WHERE m.model = ? LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, model);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new OfficeLEDMonitor(
                        resultSet.getInt("id"),
                        resultSet.getString("brand"),
                        resultSet.getString("model"),
                        resultSet.getDouble("screenSize"),
                        resultSet.getDouble("price"),
                        resultSet.getBoolean("isCurved"),
                        resultSet.getBoolean("hasBlueLightFilter")
                );
            }
        }
        return null;
    }

}

