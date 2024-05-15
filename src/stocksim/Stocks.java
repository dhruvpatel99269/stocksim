/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package stocksim;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Stocks extends javax.swing.JFrame {

    int userid = UserData.getUserId();

    /**
     * Creates new form Menu
     */
    public Stocks() {
        initComponents();
        loadItems();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void addItemsToOrder() {
        int orderid = PortfolioData.getOrderId();
        int itemid = PortfolioData.getItemId();

        boolean entryExists = false;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "Dhruv@99269!")) {
            String checkEntryQuery = "SELECT COUNT(*) AS count FROM portfoliostocks WHERE portfolioid = ? AND userid = ? AND stockid = ?";

            try (java.sql.PreparedStatement entryStatement = connection.prepareStatement(checkEntryQuery)) {
                entryStatement.setInt(1, orderid);
                entryStatement.setInt(2, userid);
                entryStatement.setInt(3, itemid);

                try (java.sql.ResultSet entryResultSet = entryStatement.executeQuery()) {
                    if (entryResultSet.next()) {
                        int count = entryResultSet.getInt("count");
                        entryExists = count > 0;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error checking entry existence: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (entryExists) {
                JOptionPane.showMessageDialog(null, "Entry already exists in portfoliostocks.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Ask user for quantity input
            String quantityString = JOptionPane.showInputDialog(null, "Enter quantity:", "Quantity Input", JOptionPane.QUESTION_MESSAGE);

            if (quantityString == null) {
                // User canceled the input
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid quantity input.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Fetch stock price
            String fetchPriceQuery = "SELECT price FROM stockdata WHERE stockid = ?";
            double price = 0;

            try (java.sql.PreparedStatement fetchPriceStatement = connection.prepareStatement(fetchPriceQuery)) {
                fetchPriceStatement.setInt(1, itemid);

                try (java.sql.ResultSet priceResultSet = fetchPriceStatement.executeQuery()) {
                    if (priceResultSet.next()) {
                        price = priceResultSet.getDouble("price");
                    } else {
                        JOptionPane.showMessageDialog(null, "Stock not found.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error fetching stock price: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double stockAmt = price * quantity;

            String insertPlaylistRacksQuery = "INSERT INTO portfoliostocks (stockid, portfolioid, userid, stockqty, stockamt) VALUES (?, ?, ?, ?, ?)";

            try (java.sql.PreparedStatement insertStatement = connection.prepareStatement(insertPlaylistRacksQuery)) {
                insertStatement.setInt(1, itemid);
                insertStatement.setInt(2, orderid);
                insertStatement.setInt(3, userid);
                insertStatement.setInt(4, quantity);
                insertStatement.setDouble(5, stockAmt);

                int rowsAffected = insertStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Stock added to portfolio successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add stock to portfolio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error inserting into portfolio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadItems() {
        String playlistTracksQuery = "SELECT COUNT(stockid) AS cnt, stockid, stockname, price FROM stockdata GROUP BY stockid, stockname, price;";

        int sum = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(playlistTracksQuery)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                int itemCount = 0;
                while (resultSet.next() && itemCount < 49) { // Assuming you want to display up to 4 tracks                    
                    String itemName = resultSet.getString("stockdata.stockname");
                    int itemPrice = resultSet.getInt("stockdata.price");
                    int itemid = resultSet.getInt("stockdata.stockid");
                    switch (itemCount) {
                        case 0:
                            sn2.setText(itemName);
                            sa2.setText(Integer.toString(itemPrice));
                            break;
                        case 1:
                            sn3.setText(itemName);
                            sa3.setText(Integer.toString(itemPrice));
                            break;
                        case 2:
                            sn4.setText(itemName);
                            sa4.setText(Integer.toString(itemPrice));
                            break;
                        case 3:
                            sn5.setText(itemName);
                            sa5.setText(Integer.toString(itemPrice));
                            break;
                        case 4:
                            sn6.setText(itemName);
                            sa6.setText(Integer.toString(itemPrice));
                            break;
                        case 5:
                            sn7.setText(itemName);
                            sa7.setText(Integer.toString(itemPrice));
                            break;
                        case 6:
                            sn8.setText(itemName);
                            sa8.setText(Integer.toString(itemPrice));
                            break;
                        case 7:
                            sn9.setText(itemName);
                            sa9.setText(Integer.toString(itemPrice));
                            break;
                        case 8:
                            sn10.setText(itemName);
                            sa10.setText(Integer.toString(itemPrice));
                            break;
                        case 9:
                            sn11.setText(itemName);
                            sa11.setText(Integer.toString(itemPrice));
                            break;
                        case 10:
                            sn12.setText(itemName);
                            sa12.setText(Integer.toString(itemPrice));
                            break;
                        case 11:
                            sn13.setText(itemName);
                            sa13.setText(Integer.toString(itemPrice));
                            break;
                        case 12:
                            sn14.setText(itemName);
                            sa14.setText(Integer.toString(itemPrice));
                            break;
                        case 13:
                            sn15.setText(itemName);
                            sa15.setText(Integer.toString(itemPrice));
                            break;
                        case 14:
                            sn16.setText(itemName);
                            sa16.setText(Integer.toString(itemPrice));
                            break;
                        case 15:
                            sn17.setText(itemName);
                            sa17.setText(Integer.toString(itemPrice));
                            break;
                        case 16:
                            sn18.setText(itemName);
                            sa18.setText(Integer.toString(itemPrice));
                            break;
                        case 17:
                            sn19.setText(itemName);
                            sa19.setText(Integer.toString(itemPrice));
                            break;
                        case 18:
                            sn20.setText(itemName);
                            sa20.setText(Integer.toString(itemPrice));
                            break;
                        case 19:
                            sn21.setText(itemName);
                            sa21.setText(Integer.toString(itemPrice));
                            break;
                        case 20:
                            sn22.setText(itemName);
                            sa22.setText(Integer.toString(itemPrice));
                            break;
                        case 21:
    sn23.setText(itemName);
    sa23.setText(Integer.toString(itemPrice));
    break;
case 22:
    sn24.setText(itemName);
    sa24.setText(Integer.toString(itemPrice));
    break;
case 23:
    sn25.setText(itemName);
    sa25.setText(Integer.toString(itemPrice));
    break;
case 24:
    sn26.setText(itemName);
    sa26.setText(Integer.toString(itemPrice));
    break;
case 25:
    sn27.setText(itemName);
    sa27.setText(Integer.toString(itemPrice));
    break;
case 26:
    sn28.setText(itemName);
    sa28.setText(Integer.toString(itemPrice));
    break;
case 27:
    sn29.setText(itemName);
    sa29.setText(Integer.toString(itemPrice));
    break;
case 28:
    sn30.setText(itemName);
    sa30.setText(Integer.toString(itemPrice));
    break;
case 29:
    sn31.setText(itemName);
    sa31.setText(Integer.toString(itemPrice));
    break;
case 30:
    sn32.setText(itemName);
    sa32.setText(Integer.toString(itemPrice));
    break;
case 31:
    sn33.setText(itemName);
    sa33.setText(Integer.toString(itemPrice));
    break;
case 32:
    sn34.setText(itemName);
    sa34.setText(Integer.toString(itemPrice));
    break;
case 33:
    sn35.setText(itemName);
    sa35.setText(Integer.toString(itemPrice));
    break;
case 34:
    sn36.setText(itemName);
    sa36.setText(Integer.toString(itemPrice));
    break;
case 35:
    sn37.setText(itemName);
    sa37.setText(Integer.toString(itemPrice));
    break;
case 36:
    sn38.setText(itemName);
    sa38.setText(Integer.toString(itemPrice));
    break;
case 37:
    sn39.setText(itemName);
    sa39.setText(Integer.toString(itemPrice));
    break;
case 38:
    sn40.setText(itemName);
    sa40.setText(Integer.toString(itemPrice));
    break;
case 39:
    sn41.setText(itemName);
    sa41.setText(Integer.toString(itemPrice));
    break;
case 40:
    sn42.setText(itemName);
    sa42.setText(Integer.toString(itemPrice));
    break;
case 41:
    sn43.setText(itemName);
    sa43.setText(Integer.toString(itemPrice));
    break;
case 42:
    sn44.setText(itemName);
    sa44.setText(Integer.toString(itemPrice));
    break;
case 43:
    sn45.setText(itemName);
    sa45.setText(Integer.toString(itemPrice));
    break;
case 44:
    sn46.setText(itemName);
    sa46.setText(Integer.toString(itemPrice));
    break;
case 45:
    sn47.setText(itemName);
    sa47.setText(Integer.toString(itemPrice));
    break;
case 46:
    sn48.setText(itemName);
    sa48.setText(Integer.toString(itemPrice));
    break;
case 47:
    sn49.setText(itemName);
    sa49.setText(Integer.toString(itemPrice));
    break;
case 48:
    sn50.setText(itemName);
    sa50.setText(Integer.toString(itemPrice));
    break;
case 49:
    sn51.setText(itemName);
    sa51.setText(Integer.toString(itemPrice));
    break;
                    }
                    
                    itemCount++;
                }

                while (itemCount < 15) {
                    switch (itemCount) {
                        case 0:
                            sc1.setIcon(null);
                            sn1.setText("");
                            sa1.setText("");
                            st1.setText("");
                            break;
                        case 1:
                            sc2.setIcon(null);
                            sn2.setText("");
                            sa2.setText("");
                            st2.setText("");
                            break;
                        case 2:
                            sc3.setIcon(null);
                            sn3.setText("");
                            sa3.setText("");
                            st3.setText("");
                            break;
                        case 3:
                            sc4.setIcon(null);
                            sn4.setText("");
                            sa4.setText("");
                            st4.setText("");
                            break;
                        case 4:
                            sc5.setIcon(null);
                            sn5.setText("");
                            sa5.setText("");
                            st5.setText("");
                            break;
                        case 5:
                            sc6.setIcon(null);
                            sn6.setText("");
                            sa6.setText("");
                            st6.setText("");
                            break;
                        case 6:
                            sc7.setIcon(null);
                            sn7.setText("");
                            sa7.setText("");
                            st7.setText("");
                            break;
                        case 7:
                            sc9.setIcon(null);
                            sn9.setText("");
                            sa9.setText("");
                            st9.setText("");
                            break;
                        case 8:
                            sc10.setIcon(null);
                            sn10.setText("");
                            sa10.setText("");
                            st10.setText("");
                            break;
                        case 9:
                            sc11.setIcon(null);
                            sn11.setText("");
                            sa11.setText("");
                            st11.setText("");
                            break;
                        case 10:
                            sc12.setIcon(null);
                            sn12.setText("");
                            sa12.setText("");
                            st12.setText("");
                            break;
                        case 11:
                            sc13.setIcon(null);
                            sn13.setText("");
                            sa13.setText("");
                            st13.setText("");
                            break;
                        case 12:
                            sc14.setIcon(null);
                            sn14.setText("");
                            sa14.setText("");
                            st14.setText("");
                            break;
                        case 13:
                            sc15.setIcon(null);
                            sn15.setText("");
                            sa15.setText("");
                            st15.setText("");
                            break;
                        case 14:
                            sc16.setIcon(null);
                            sn16.setText("");
                            sa16.setText("");
                            st16.setText("");
                            break;
                    }
                    itemCount++;
                }
            } catch (SQLException ex) {
                System.err.println("Error executing portfolio stocks query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        homeicon = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        playlistname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        sc1 = new javax.swing.JLabel();
        sn1 = new javax.swing.JLabel();
        sa1 = new javax.swing.JLabel();
        st1 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        sc2 = new javax.swing.JLabel();
        sn2 = new javax.swing.JLabel();
        sa2 = new javax.swing.JLabel();
        st2 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        sc3 = new javax.swing.JLabel();
        sn3 = new javax.swing.JLabel();
        sa3 = new javax.swing.JLabel();
        st3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        sc4 = new javax.swing.JLabel();
        sn4 = new javax.swing.JLabel();
        sa4 = new javax.swing.JLabel();
        st4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        sc5 = new javax.swing.JLabel();
        sn5 = new javax.swing.JLabel();
        sa5 = new javax.swing.JLabel();
        st5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        sc6 = new javax.swing.JLabel();
        sn6 = new javax.swing.JLabel();
        sa6 = new javax.swing.JLabel();
        st6 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        sc7 = new javax.swing.JLabel();
        sn7 = new javax.swing.JLabel();
        sa7 = new javax.swing.JLabel();
        st7 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        sc8 = new javax.swing.JLabel();
        sn8 = new javax.swing.JLabel();
        sa8 = new javax.swing.JLabel();
        st8 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        sc9 = new javax.swing.JLabel();
        sn9 = new javax.swing.JLabel();
        sa9 = new javax.swing.JLabel();
        st9 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        sc10 = new javax.swing.JLabel();
        sn10 = new javax.swing.JLabel();
        sa10 = new javax.swing.JLabel();
        st10 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        sc11 = new javax.swing.JLabel();
        sn11 = new javax.swing.JLabel();
        sa11 = new javax.swing.JLabel();
        st11 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        sc12 = new javax.swing.JLabel();
        sn12 = new javax.swing.JLabel();
        sa12 = new javax.swing.JLabel();
        st12 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        sc13 = new javax.swing.JLabel();
        sn13 = new javax.swing.JLabel();
        sa13 = new javax.swing.JLabel();
        st13 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        sc14 = new javax.swing.JLabel();
        sn14 = new javax.swing.JLabel();
        sa14 = new javax.swing.JLabel();
        st14 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        sc15 = new javax.swing.JLabel();
        sn15 = new javax.swing.JLabel();
        sa15 = new javax.swing.JLabel();
        st15 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        sc16 = new javax.swing.JLabel();
        sn16 = new javax.swing.JLabel();
        sa16 = new javax.swing.JLabel();
        st16 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        sc17 = new javax.swing.JLabel();
        sn17 = new javax.swing.JLabel();
        sa17 = new javax.swing.JLabel();
        st17 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        sc18 = new javax.swing.JLabel();
        sn18 = new javax.swing.JLabel();
        sa18 = new javax.swing.JLabel();
        st18 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        sc19 = new javax.swing.JLabel();
        sn19 = new javax.swing.JLabel();
        sa19 = new javax.swing.JLabel();
        st19 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        sc20 = new javax.swing.JLabel();
        sn20 = new javax.swing.JLabel();
        sa20 = new javax.swing.JLabel();
        st20 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        sc21 = new javax.swing.JLabel();
        sn21 = new javax.swing.JLabel();
        sa21 = new javax.swing.JLabel();
        st21 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        sc22 = new javax.swing.JLabel();
        sn22 = new javax.swing.JLabel();
        sa22 = new javax.swing.JLabel();
        st22 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        sc23 = new javax.swing.JLabel();
        sn23 = new javax.swing.JLabel();
        sa23 = new javax.swing.JLabel();
        st23 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        sc24 = new javax.swing.JLabel();
        sn24 = new javax.swing.JLabel();
        sa24 = new javax.swing.JLabel();
        st24 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        sc25 = new javax.swing.JLabel();
        sn25 = new javax.swing.JLabel();
        sa25 = new javax.swing.JLabel();
        st25 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        sc26 = new javax.swing.JLabel();
        sn26 = new javax.swing.JLabel();
        sa26 = new javax.swing.JLabel();
        st26 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        sc27 = new javax.swing.JLabel();
        sn27 = new javax.swing.JLabel();
        sa27 = new javax.swing.JLabel();
        st27 = new javax.swing.JLabel();
        jPanel33 = new javax.swing.JPanel();
        sc28 = new javax.swing.JLabel();
        sn28 = new javax.swing.JLabel();
        sa28 = new javax.swing.JLabel();
        st28 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        sc29 = new javax.swing.JLabel();
        sn29 = new javax.swing.JLabel();
        sa29 = new javax.swing.JLabel();
        st29 = new javax.swing.JLabel();
        jPanel37 = new javax.swing.JPanel();
        sc30 = new javax.swing.JLabel();
        sn30 = new javax.swing.JLabel();
        sa30 = new javax.swing.JLabel();
        st30 = new javax.swing.JLabel();
        jPanel38 = new javax.swing.JPanel();
        sc31 = new javax.swing.JLabel();
        sn31 = new javax.swing.JLabel();
        sa31 = new javax.swing.JLabel();
        st31 = new javax.swing.JLabel();
        jPanel39 = new javax.swing.JPanel();
        sc32 = new javax.swing.JLabel();
        sn32 = new javax.swing.JLabel();
        sa32 = new javax.swing.JLabel();
        st32 = new javax.swing.JLabel();
        jPanel40 = new javax.swing.JPanel();
        sc33 = new javax.swing.JLabel();
        sn33 = new javax.swing.JLabel();
        sa33 = new javax.swing.JLabel();
        st33 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        sc34 = new javax.swing.JLabel();
        sn34 = new javax.swing.JLabel();
        sa34 = new javax.swing.JLabel();
        st34 = new javax.swing.JLabel();
        jPanel42 = new javax.swing.JPanel();
        sc35 = new javax.swing.JLabel();
        sn35 = new javax.swing.JLabel();
        sa35 = new javax.swing.JLabel();
        st35 = new javax.swing.JLabel();
        jPanel43 = new javax.swing.JPanel();
        sc36 = new javax.swing.JLabel();
        sn36 = new javax.swing.JLabel();
        sa36 = new javax.swing.JLabel();
        st36 = new javax.swing.JLabel();
        jPanel44 = new javax.swing.JPanel();
        sc37 = new javax.swing.JLabel();
        sn37 = new javax.swing.JLabel();
        sa37 = new javax.swing.JLabel();
        st37 = new javax.swing.JLabel();
        jPanel45 = new javax.swing.JPanel();
        sc38 = new javax.swing.JLabel();
        sn38 = new javax.swing.JLabel();
        sa38 = new javax.swing.JLabel();
        st38 = new javax.swing.JLabel();
        jPanel46 = new javax.swing.JPanel();
        sc39 = new javax.swing.JLabel();
        sn39 = new javax.swing.JLabel();
        sa39 = new javax.swing.JLabel();
        st39 = new javax.swing.JLabel();
        jPanel47 = new javax.swing.JPanel();
        sc40 = new javax.swing.JLabel();
        sn40 = new javax.swing.JLabel();
        sa40 = new javax.swing.JLabel();
        st40 = new javax.swing.JLabel();
        jPanel48 = new javax.swing.JPanel();
        sc41 = new javax.swing.JLabel();
        sn41 = new javax.swing.JLabel();
        sa41 = new javax.swing.JLabel();
        st41 = new javax.swing.JLabel();
        jPanel49 = new javax.swing.JPanel();
        sc42 = new javax.swing.JLabel();
        sn42 = new javax.swing.JLabel();
        sa42 = new javax.swing.JLabel();
        st42 = new javax.swing.JLabel();
        jPanel50 = new javax.swing.JPanel();
        sc43 = new javax.swing.JLabel();
        sn43 = new javax.swing.JLabel();
        sa43 = new javax.swing.JLabel();
        st43 = new javax.swing.JLabel();
        jPanel51 = new javax.swing.JPanel();
        sc44 = new javax.swing.JLabel();
        sn44 = new javax.swing.JLabel();
        sa44 = new javax.swing.JLabel();
        st44 = new javax.swing.JLabel();
        jPanel52 = new javax.swing.JPanel();
        sc45 = new javax.swing.JLabel();
        sn45 = new javax.swing.JLabel();
        sa45 = new javax.swing.JLabel();
        st45 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        sc46 = new javax.swing.JLabel();
        sn46 = new javax.swing.JLabel();
        sa46 = new javax.swing.JLabel();
        st46 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        sc47 = new javax.swing.JLabel();
        sn47 = new javax.swing.JLabel();
        sa47 = new javax.swing.JLabel();
        st47 = new javax.swing.JLabel();
        jPanel55 = new javax.swing.JPanel();
        sc48 = new javax.swing.JLabel();
        sn48 = new javax.swing.JLabel();
        sa48 = new javax.swing.JLabel();
        st48 = new javax.swing.JLabel();
        jPanel56 = new javax.swing.JPanel();
        sc49 = new javax.swing.JLabel();
        sn49 = new javax.swing.JLabel();
        sa49 = new javax.swing.JLabel();
        st49 = new javax.swing.JLabel();
        jPanel57 = new javax.swing.JPanel();
        sc50 = new javax.swing.JLabel();
        sn50 = new javax.swing.JLabel();
        sa50 = new javax.swing.JLabel();
        st50 = new javax.swing.JLabel();
        jPanel58 = new javax.swing.JPanel();
        sc51 = new javax.swing.JLabel();
        sn51 = new javax.swing.JLabel();
        sa51 = new javax.swing.JLabel();
        st51 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(950, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 650));

        jPanel3.setBackground(new java.awt.Color(20, 20, 20));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 98, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(20, 20, 20));

        jPanel7.setBackground(new java.awt.Color(25, 25, 25));

        homeicon.setBackground(new java.awt.Color(0, 0, 0));
        homeicon.setForeground(new java.awt.Color(255, 255, 255));
        homeicon.setText("home");
        homeicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                homeiconMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeicon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(25, 25, 25));

        playlistname.setFont(new java.awt.Font("Poor Richard", 0, 30)); // NOI18N
        playlistname.setForeground(new java.awt.Color(255, 255, 255));
        playlistname.setText("Stock Market");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(playlistname, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playlistname, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(377, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(20, 20, 20));
        jScrollPane1.setBorder(null);

        jPanel4.setBackground(new java.awt.Color(20, 20, 20));

        jPanel6.setBackground(new java.awt.Color(25, 25, 25));

        sc1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sc1.setForeground(new java.awt.Color(255, 255, 255));

        sn1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sn1.setForeground(new java.awt.Color(255, 255, 255));
        sn1.setText("Stocks");
        sn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn1MouseReleased(evt);
            }
        });

        sa1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sa1.setForeground(new java.awt.Color(255, 255, 255));
        sa1.setText("Stock Price");

        st1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        st1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel34.setBackground(new java.awt.Color(25, 25, 25));

        sc2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc2.setForeground(new java.awt.Color(255, 255, 255));

        sn2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn2.setForeground(new java.awt.Color(255, 255, 255));
        sn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn2MouseReleased(evt);
            }
        });

        sa2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa2.setForeground(new java.awt.Color(255, 255, 255));

        st2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel35.setBackground(new java.awt.Color(25, 25, 25));

        sc3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc3.setForeground(new java.awt.Color(255, 255, 255));

        sn3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn3.setForeground(new java.awt.Color(255, 255, 255));
        sn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn3MouseReleased(evt);
            }
        });

        sa3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa3.setForeground(new java.awt.Color(255, 255, 255));

        st3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(25, 25, 25));

        sc4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc4.setForeground(new java.awt.Color(255, 255, 255));

        sn4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn4.setForeground(new java.awt.Color(255, 255, 255));
        sn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn4MouseReleased(evt);
            }
        });

        sa4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa4.setForeground(new java.awt.Color(255, 255, 255));

        st4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(25, 25, 25));

        sc5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc5.setForeground(new java.awt.Color(255, 255, 255));

        sn5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn5.setForeground(new java.awt.Color(255, 255, 255));
        sn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn5MouseReleased(evt);
            }
        });

        sa5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa5.setForeground(new java.awt.Color(255, 255, 255));

        st5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st5.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(25, 25, 25));

        sc6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc6.setForeground(new java.awt.Color(255, 255, 255));

        sn6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn6.setForeground(new java.awt.Color(255, 255, 255));
        sn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn6MouseReleased(evt);
            }
        });

        sa6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa6.setForeground(new java.awt.Color(255, 255, 255));

        st6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st6.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(25, 25, 25));

        sc7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc7.setForeground(new java.awt.Color(255, 255, 255));

        sn7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn7.setForeground(new java.awt.Color(255, 255, 255));
        sn7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn7MouseReleased(evt);
            }
        });

        sa7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa7.setForeground(new java.awt.Color(255, 255, 255));

        st7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st7.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(25, 25, 25));

        sc8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc8.setForeground(new java.awt.Color(255, 255, 255));

        sn8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn8.setForeground(new java.awt.Color(255, 255, 255));
        sn8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn8MouseReleased(evt);
            }
        });

        sa8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa8.setForeground(new java.awt.Color(255, 255, 255));

        st8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st8.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 209, Short.MAX_VALUE)
                .addComponent(st8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(25, 25, 25));

        sc9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc9.setForeground(new java.awt.Color(255, 255, 255));

        sn9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn9.setForeground(new java.awt.Color(255, 255, 255));
        sn9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn9MouseReleased(evt);
            }
        });

        sa9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa9.setForeground(new java.awt.Color(255, 255, 255));

        st9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st9.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa9, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(25, 25, 25));

        sc10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc10.setForeground(new java.awt.Color(255, 255, 255));

        sn10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn10.setForeground(new java.awt.Color(255, 255, 255));
        sn10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn10MouseReleased(evt);
            }
        });

        sa10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa10.setForeground(new java.awt.Color(255, 255, 255));

        st10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st10.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn10, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(25, 25, 25));

        sc11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc11.setForeground(new java.awt.Color(255, 255, 255));

        sn11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn11.setForeground(new java.awt.Color(255, 255, 255));
        sn11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn11MouseReleased(evt);
            }
        });

        sa11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa11.setForeground(new java.awt.Color(255, 255, 255));

        st11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn11, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(25, 25, 25));

        sc12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc12.setForeground(new java.awt.Color(255, 255, 255));

        sn12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn12.setForeground(new java.awt.Color(255, 255, 255));
        sn12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn12MouseReleased(evt);
            }
        });

        sa12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa12.setForeground(new java.awt.Color(255, 255, 255));

        st12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st12.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn12, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(25, 25, 25));

        sc13.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc13.setForeground(new java.awt.Color(255, 255, 255));

        sn13.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn13.setForeground(new java.awt.Color(255, 255, 255));
        sn13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn13MouseReleased(evt);
            }
        });

        sa13.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa13.setForeground(new java.awt.Color(255, 255, 255));

        st13.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st13.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn13, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa13, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(25, 25, 25));

        sc14.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc14.setForeground(new java.awt.Color(255, 255, 255));

        sn14.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn14.setForeground(new java.awt.Color(255, 255, 255));
        sn14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn14MouseReleased(evt);
            }
        });

        sa14.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa14.setForeground(new java.awt.Color(255, 255, 255));

        st14.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st14.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(25, 25, 25));

        sc15.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc15.setForeground(new java.awt.Color(255, 255, 255));

        sn15.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn15.setForeground(new java.awt.Color(255, 255, 255));
        sn15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn15MouseReleased(evt);
            }
        });

        sa15.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa15.setForeground(new java.awt.Color(255, 255, 255));

        st15.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st15.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn15, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa15, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(25, 25, 25));

        sc16.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc16.setForeground(new java.awt.Color(255, 255, 255));

        sn16.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn16.setForeground(new java.awt.Color(255, 255, 255));
        sn16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn16MouseReleased(evt);
            }
        });

        sa16.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa16.setForeground(new java.awt.Color(255, 255, 255));

        st16.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st16.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn16, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa16, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(25, 25, 25));

        sc17.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc17.setForeground(new java.awt.Color(255, 255, 255));

        sn17.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn17.setForeground(new java.awt.Color(255, 255, 255));
        sn17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn17MouseReleased(evt);
            }
        });

        sa17.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa17.setForeground(new java.awt.Color(255, 255, 255));

        st17.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st17.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn17, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(25, 25, 25));

        sc18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc18.setForeground(new java.awt.Color(255, 255, 255));

        sn18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn18.setForeground(new java.awt.Color(255, 255, 255));
        sn18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn18MouseReleased(evt);
            }
        });

        sa18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa18.setForeground(new java.awt.Color(255, 255, 255));

        st18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st18.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn18, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa18, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(25, 25, 25));

        sc19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc19.setForeground(new java.awt.Color(255, 255, 255));

        sn19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn19.setForeground(new java.awt.Color(255, 255, 255));
        sn19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn19MouseReleased(evt);
            }
        });

        sa19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa19.setForeground(new java.awt.Color(255, 255, 255));

        st19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st19.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn19, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa19, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(25, 25, 25));

        sc20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc20.setForeground(new java.awt.Color(255, 255, 255));

        sn20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn20.setForeground(new java.awt.Color(255, 255, 255));
        sn20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn20MouseReleased(evt);
            }
        });

        sa20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa20.setForeground(new java.awt.Color(255, 255, 255));

        st20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st20.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn20, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(25, 25, 25));

        sc21.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc21.setForeground(new java.awt.Color(255, 255, 255));

        sn21.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn21.setForeground(new java.awt.Color(255, 255, 255));
        sn21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn21MouseReleased(evt);
            }
        });

        sa21.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa21.setForeground(new java.awt.Color(255, 255, 255));

        st21.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st21.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn21, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa21, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(25, 25, 25));

        sc22.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc22.setForeground(new java.awt.Color(255, 255, 255));

        sn22.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn22.setForeground(new java.awt.Color(255, 255, 255));
        sn22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn22MouseReleased(evt);
            }
        });

        sa22.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa22.setForeground(new java.awt.Color(255, 255, 255));

        st22.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st22.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn22, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(25, 25, 25));

        sc23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc23.setForeground(new java.awt.Color(255, 255, 255));

        sn23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn23.setForeground(new java.awt.Color(255, 255, 255));
        sn23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn23MouseReleased(evt);
            }
        });

        sa23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa23.setForeground(new java.awt.Color(255, 255, 255));

        st23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st23.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn23, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa23, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel29.setBackground(new java.awt.Color(25, 25, 25));

        sc24.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc24.setForeground(new java.awt.Color(255, 255, 255));

        sn24.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn24.setForeground(new java.awt.Color(255, 255, 255));
        sn24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn24MouseReleased(evt);
            }
        });

        sa24.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa24.setForeground(new java.awt.Color(255, 255, 255));

        st24.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st24.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn24, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa24, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel30.setBackground(new java.awt.Color(25, 25, 25));

        sc25.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc25.setForeground(new java.awt.Color(255, 255, 255));

        sn25.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn25.setForeground(new java.awt.Color(255, 255, 255));
        sn25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn25MouseReleased(evt);
            }
        });

        sa25.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa25.setForeground(new java.awt.Color(255, 255, 255));

        st25.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st25.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn25, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa25, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel31.setBackground(new java.awt.Color(25, 25, 25));

        sc26.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc26.setForeground(new java.awt.Color(255, 255, 255));

        sn26.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn26.setForeground(new java.awt.Color(255, 255, 255));
        sn26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn26MouseReleased(evt);
            }
        });

        sa26.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa26.setForeground(new java.awt.Color(255, 255, 255));

        st26.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st26.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn26, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa26, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel32.setBackground(new java.awt.Color(25, 25, 25));

        sc27.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc27.setForeground(new java.awt.Color(255, 255, 255));

        sn27.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn27.setForeground(new java.awt.Color(255, 255, 255));
        sn27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn27MouseReleased(evt);
            }
        });

        sa27.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa27.setForeground(new java.awt.Color(255, 255, 255));

        st27.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st27.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc27, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn27, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa27, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn27, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st27, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa27, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel33.setBackground(new java.awt.Color(25, 25, 25));

        sc28.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc28.setForeground(new java.awt.Color(255, 255, 255));

        sn28.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn28.setForeground(new java.awt.Color(255, 255, 255));
        sn28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn28MouseReleased(evt);
            }
        });

        sa28.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa28.setForeground(new java.awt.Color(255, 255, 255));

        st28.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st28.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc28, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn28, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa28, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn28, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st28, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa28, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel36.setBackground(new java.awt.Color(25, 25, 25));

        sc29.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc29.setForeground(new java.awt.Color(255, 255, 255));

        sn29.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn29.setForeground(new java.awt.Color(255, 255, 255));
        sn29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn29MouseReleased(evt);
            }
        });

        sa29.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa29.setForeground(new java.awt.Color(255, 255, 255));

        st29.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st29.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc29, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn29, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa29, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn29, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st29, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa29, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel37.setBackground(new java.awt.Color(25, 25, 25));

        sc30.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc30.setForeground(new java.awt.Color(255, 255, 255));

        sn30.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn30.setForeground(new java.awt.Color(255, 255, 255));
        sn30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn30MouseReleased(evt);
            }
        });

        sa30.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa30.setForeground(new java.awt.Color(255, 255, 255));

        st30.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st30.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc30, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn30, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa30, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn30, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st30, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa30, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel38.setBackground(new java.awt.Color(25, 25, 25));

        sc31.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc31.setForeground(new java.awt.Color(255, 255, 255));

        sn31.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn31.setForeground(new java.awt.Color(255, 255, 255));
        sn31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn31MouseReleased(evt);
            }
        });

        sa31.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa31.setForeground(new java.awt.Color(255, 255, 255));

        st31.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st31.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc31, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn31, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa31, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn31, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st31, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa31, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel39.setBackground(new java.awt.Color(25, 25, 25));

        sc32.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc32.setForeground(new java.awt.Color(255, 255, 255));

        sn32.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn32.setForeground(new java.awt.Color(255, 255, 255));
        sn32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn32MouseReleased(evt);
            }
        });

        sa32.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa32.setForeground(new java.awt.Color(255, 255, 255));

        st32.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st32.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc32, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn32, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa32, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn32, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st32, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa32, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel40.setBackground(new java.awt.Color(25, 25, 25));

        sc33.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc33.setForeground(new java.awt.Color(255, 255, 255));

        sn33.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn33.setForeground(new java.awt.Color(255, 255, 255));
        sn33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn33MouseReleased(evt);
            }
        });

        sa33.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa33.setForeground(new java.awt.Color(255, 255, 255));

        st33.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st33.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc33, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn33, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa33, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn33, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st33, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa33, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel41.setBackground(new java.awt.Color(25, 25, 25));

        sc34.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc34.setForeground(new java.awt.Color(255, 255, 255));

        sn34.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn34.setForeground(new java.awt.Color(255, 255, 255));
        sn34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn34MouseReleased(evt);
            }
        });

        sa34.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa34.setForeground(new java.awt.Color(255, 255, 255));

        st34.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st34.setForeground(new java.awt.Color(255, 255, 255));

        jPanel42.setBackground(new java.awt.Color(25, 25, 25));

        sc35.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc35.setForeground(new java.awt.Color(255, 255, 255));

        sn35.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn35.setForeground(new java.awt.Color(255, 255, 255));
        sn35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn35MouseReleased(evt);
            }
        });

        sa35.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa35.setForeground(new java.awt.Color(255, 255, 255));

        st35.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st35.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc35, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn35, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa35, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                .addComponent(st35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn35, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st35, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa35, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc34, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn34, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa34, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn34, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st34, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa34, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel41Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel43.setBackground(new java.awt.Color(25, 25, 25));

        sc36.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc36.setForeground(new java.awt.Color(255, 255, 255));

        sn36.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn36.setForeground(new java.awt.Color(255, 255, 255));
        sn36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn36MouseReleased(evt);
            }
        });

        sa36.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa36.setForeground(new java.awt.Color(255, 255, 255));

        st36.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st36.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc36, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn36, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa36, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn36, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st36, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa36, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel44.setBackground(new java.awt.Color(25, 25, 25));

        sc37.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc37.setForeground(new java.awt.Color(255, 255, 255));

        sn37.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn37.setForeground(new java.awt.Color(255, 255, 255));
        sn37.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn37MouseReleased(evt);
            }
        });

        sa37.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa37.setForeground(new java.awt.Color(255, 255, 255));

        st37.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st37.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc37, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn37, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa37, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn37, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st37, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa37, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel45.setBackground(new java.awt.Color(25, 25, 25));

        sc38.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc38.setForeground(new java.awt.Color(255, 255, 255));

        sn38.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn38.setForeground(new java.awt.Color(255, 255, 255));
        sn38.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn38MouseReleased(evt);
            }
        });

        sa38.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa38.setForeground(new java.awt.Color(255, 255, 255));

        st38.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st38.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc38, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn38, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa38, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st38, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn38, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st38, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa38, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc38, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel46.setBackground(new java.awt.Color(25, 25, 25));

        sc39.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc39.setForeground(new java.awt.Color(255, 255, 255));

        sn39.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn39.setForeground(new java.awt.Color(255, 255, 255));
        sn39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn39MouseReleased(evt);
            }
        });

        sa39.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa39.setForeground(new java.awt.Color(255, 255, 255));

        st39.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st39.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc39, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn39, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa39, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn39, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st39, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa39, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel47.setBackground(new java.awt.Color(25, 25, 25));

        sc40.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc40.setForeground(new java.awt.Color(255, 255, 255));

        sn40.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn40.setForeground(new java.awt.Color(255, 255, 255));
        sn40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn40MouseReleased(evt);
            }
        });

        sa40.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa40.setForeground(new java.awt.Color(255, 255, 255));

        st40.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st40.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc40, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn40, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa40, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn40, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st40, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa40, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel48.setBackground(new java.awt.Color(25, 25, 25));

        sc41.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc41.setForeground(new java.awt.Color(255, 255, 255));

        sn41.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn41.setForeground(new java.awt.Color(255, 255, 255));
        sn41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn41MouseReleased(evt);
            }
        });

        sa41.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa41.setForeground(new java.awt.Color(255, 255, 255));

        st41.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st41.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc41, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn41, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa41, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st41, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn41, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st41, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa41, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel49.setBackground(new java.awt.Color(25, 25, 25));

        sc42.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc42.setForeground(new java.awt.Color(255, 255, 255));

        sn42.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn42.setForeground(new java.awt.Color(255, 255, 255));
        sn42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn42MouseReleased(evt);
            }
        });

        sa42.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa42.setForeground(new java.awt.Color(255, 255, 255));

        st42.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st42.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc42, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn42, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa42, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st42, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn42, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st42, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa42, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel50.setBackground(new java.awt.Color(25, 25, 25));

        sc43.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc43.setForeground(new java.awt.Color(255, 255, 255));

        sn43.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn43.setForeground(new java.awt.Color(255, 255, 255));
        sn43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn43MouseReleased(evt);
            }
        });

        sa43.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa43.setForeground(new java.awt.Color(255, 255, 255));

        st43.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st43.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc43, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn43, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa43, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn43, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st43, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa43, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel51.setBackground(new java.awt.Color(25, 25, 25));

        sc44.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc44.setForeground(new java.awt.Color(255, 255, 255));

        sn44.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn44.setForeground(new java.awt.Color(255, 255, 255));
        sn44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn44MouseReleased(evt);
            }
        });

        sa44.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa44.setForeground(new java.awt.Color(255, 255, 255));

        st44.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st44.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc44, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn44, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa44, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn44, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st44, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa44, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel52.setBackground(new java.awt.Color(25, 25, 25));

        sc45.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc45.setForeground(new java.awt.Color(255, 255, 255));

        sn45.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn45.setForeground(new java.awt.Color(255, 255, 255));
        sn45.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn45MouseReleased(evt);
            }
        });

        sa45.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa45.setForeground(new java.awt.Color(255, 255, 255));

        st45.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st45.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc45, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn45, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa45, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn45, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st45, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa45, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel53.setBackground(new java.awt.Color(25, 25, 25));

        sc46.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc46.setForeground(new java.awt.Color(255, 255, 255));

        sn46.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn46.setForeground(new java.awt.Color(255, 255, 255));
        sn46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn46MouseReleased(evt);
            }
        });

        sa46.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa46.setForeground(new java.awt.Color(255, 255, 255));

        st46.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st46.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc46, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn46, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa46, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn46, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st46, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa46, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel54.setBackground(new java.awt.Color(25, 25, 25));

        sc47.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc47.setForeground(new java.awt.Color(255, 255, 255));

        sn47.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn47.setForeground(new java.awt.Color(255, 255, 255));
        sn47.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn47MouseReleased(evt);
            }
        });

        sa47.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa47.setForeground(new java.awt.Color(255, 255, 255));

        st47.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st47.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc47, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn47, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa47, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st47, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn47, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st47, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa47, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel55.setBackground(new java.awt.Color(25, 25, 25));

        sc48.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc48.setForeground(new java.awt.Color(255, 255, 255));

        sn48.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn48.setForeground(new java.awt.Color(255, 255, 255));
        sn48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn48MouseReleased(evt);
            }
        });

        sa48.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa48.setForeground(new java.awt.Color(255, 255, 255));

        st48.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st48.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc48, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn48, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa48, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn48, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st48, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa48, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc48, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel56.setBackground(new java.awt.Color(25, 25, 25));

        sc49.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc49.setForeground(new java.awt.Color(255, 255, 255));

        sn49.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn49.setForeground(new java.awt.Color(255, 255, 255));
        sn49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn49MouseReleased(evt);
            }
        });

        sa49.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa49.setForeground(new java.awt.Color(255, 255, 255));

        st49.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st49.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc49, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn49, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa49, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st49, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn49, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st49, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa49, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel57.setBackground(new java.awt.Color(25, 25, 25));

        sc50.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc50.setForeground(new java.awt.Color(255, 255, 255));

        sn50.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn50.setForeground(new java.awt.Color(255, 255, 255));
        sn50.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn50MouseReleased(evt);
            }
        });

        sa50.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa50.setForeground(new java.awt.Color(255, 255, 255));

        st50.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st50.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc50, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn50, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa50, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn50, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st50, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa50, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel58.setBackground(new java.awt.Color(25, 25, 25));

        sc51.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc51.setForeground(new java.awt.Color(255, 255, 255));

        sn51.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn51.setForeground(new java.awt.Color(255, 255, 255));
        sn51.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn51MouseReleased(evt);
            }
        });

        sa51.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa51.setForeground(new java.awt.Color(255, 255, 255));

        st51.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st51.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc51, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn51, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa51, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn51, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st51, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa51, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel55, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel56, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeiconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeiconMouseReleased
        // TODO add your handling code here:
        dispose();
        Dashboard ads = new Dashboard();
        ads.setVisible(true);
    }//GEN-LAST:event_homeiconMouseReleased

    private void sn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn1MouseReleased

    private void sn2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn2MouseReleased
        PortfolioData.setItemId(1);
        addItemsToOrder();
    }//GEN-LAST:event_sn2MouseReleased

    private void sn3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn3MouseReleased
        PortfolioData.setItemId(2);
        addItemsToOrder();
    }//GEN-LAST:event_sn3MouseReleased

    private void sn4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn4MouseReleased
        PortfolioData.setItemId(3);
        addItemsToOrder();
    }//GEN-LAST:event_sn4MouseReleased

    private void sn5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn5MouseReleased
        PortfolioData.setItemId(4);
        addItemsToOrder();
    }//GEN-LAST:event_sn5MouseReleased

    private void sn6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn6MouseReleased
        PortfolioData.setItemId(5);
        addItemsToOrder();
    }//GEN-LAST:event_sn6MouseReleased

    private void sn7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn7MouseReleased
        PortfolioData.setItemId(6);
        addItemsToOrder();
    }//GEN-LAST:event_sn7MouseReleased

    private void sn8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn8MouseReleased
        PortfolioData.setItemId(7);
        addItemsToOrder();
    }//GEN-LAST:event_sn8MouseReleased

    private void sn9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn9MouseReleased
        PortfolioData.setItemId(8);
        addItemsToOrder();
    }//GEN-LAST:event_sn9MouseReleased

    private void sn10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn10MouseReleased
        PortfolioData.setItemId(9);
        addItemsToOrder();
    }//GEN-LAST:event_sn10MouseReleased

    private void sn11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn11MouseReleased
        PortfolioData.setItemId(10);
        addItemsToOrder();
    }//GEN-LAST:event_sn11MouseReleased

    private void sn12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn12MouseReleased
        PortfolioData.setItemId(11);
        addItemsToOrder();
    }//GEN-LAST:event_sn12MouseReleased

    private void sn13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn13MouseReleased
        PortfolioData.setItemId(12);
        addItemsToOrder();
    }//GEN-LAST:event_sn13MouseReleased

    private void sn14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn14MouseReleased
        PortfolioData.setItemId(13);
        addItemsToOrder();
    }//GEN-LAST:event_sn14MouseReleased

    private void sn15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn15MouseReleased
        PortfolioData.setItemId(14);
        addItemsToOrder();
    }//GEN-LAST:event_sn15MouseReleased

    private void sn16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn16MouseReleased
        PortfolioData.setItemId(15);
        addItemsToOrder();
    }//GEN-LAST:event_sn16MouseReleased

    private void sn17MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn17MouseReleased
        PortfolioData.setItemId(16);
        addItemsToOrder();
    }//GEN-LAST:event_sn17MouseReleased

    private void sn18MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn18MouseReleased
        PortfolioData.setItemId(17);
        addItemsToOrder();
    }//GEN-LAST:event_sn18MouseReleased

    private void sn19MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn19MouseReleased
        PortfolioData.setItemId(18);
        addItemsToOrder();
    }//GEN-LAST:event_sn19MouseReleased

    private void sn20MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn20MouseReleased
        PortfolioData.setItemId(19);
        addItemsToOrder();
    }//GEN-LAST:event_sn20MouseReleased

    private void sn21MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn21MouseReleased
        PortfolioData.setItemId(20);
        addItemsToOrder();
    }//GEN-LAST:event_sn21MouseReleased

    private void sn22MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn22MouseReleased
        PortfolioData.setItemId(21);
        addItemsToOrder();
    }//GEN-LAST:event_sn22MouseReleased

    private void sn23MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn23MouseReleased
        PortfolioData.setItemId(22);
        addItemsToOrder();
    }//GEN-LAST:event_sn23MouseReleased

    private void sn24MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn24MouseReleased
        PortfolioData.setItemId(23);
        addItemsToOrder();
    }//GEN-LAST:event_sn24MouseReleased

    private void sn25MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn25MouseReleased
        PortfolioData.setItemId(24);
        addItemsToOrder();
    }//GEN-LAST:event_sn25MouseReleased

    private void sn26MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn26MouseReleased
        PortfolioData.setItemId(25);
        addItemsToOrder();
    }//GEN-LAST:event_sn26MouseReleased

    private void sn27MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn27MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn27MouseReleased

    private void sn28MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn28MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn28MouseReleased

    private void sn29MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn29MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn29MouseReleased

    private void sn30MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn30MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn30MouseReleased

    private void sn31MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn31MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn31MouseReleased

    private void sn32MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn32MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn32MouseReleased

    private void sn33MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn33MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn33MouseReleased

    private void sn34MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn34MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn34MouseReleased

    private void sn35MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn35MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn35MouseReleased

    private void sn36MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn36MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn36MouseReleased

    private void sn37MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn37MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn37MouseReleased

    private void sn38MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn38MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn38MouseReleased

    private void sn39MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn39MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn39MouseReleased

    private void sn40MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn40MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn40MouseReleased

    private void sn41MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn41MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn41MouseReleased

    private void sn42MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn42MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn42MouseReleased

    private void sn43MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn43MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn43MouseReleased

    private void sn44MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn44MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn44MouseReleased

    private void sn45MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn45MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn45MouseReleased

    private void sn46MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn46MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn46MouseReleased

    private void sn47MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn47MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn47MouseReleased

    private void sn48MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn48MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn48MouseReleased

    private void sn49MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn49MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn49MouseReleased

    private void sn50MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn50MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn50MouseReleased

    private void sn51MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn51MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn51MouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stocks.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stocks().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel homeicon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel playlistname;
    private javax.swing.JLabel sa1;
    private javax.swing.JLabel sa10;
    private javax.swing.JLabel sa11;
    private javax.swing.JLabel sa12;
    private javax.swing.JLabel sa13;
    private javax.swing.JLabel sa14;
    private javax.swing.JLabel sa15;
    private javax.swing.JLabel sa16;
    private javax.swing.JLabel sa17;
    private javax.swing.JLabel sa18;
    private javax.swing.JLabel sa19;
    private javax.swing.JLabel sa2;
    private javax.swing.JLabel sa20;
    private javax.swing.JLabel sa21;
    private javax.swing.JLabel sa22;
    private javax.swing.JLabel sa23;
    private javax.swing.JLabel sa24;
    private javax.swing.JLabel sa25;
    private javax.swing.JLabel sa26;
    private javax.swing.JLabel sa27;
    private javax.swing.JLabel sa28;
    private javax.swing.JLabel sa29;
    private javax.swing.JLabel sa3;
    private javax.swing.JLabel sa30;
    private javax.swing.JLabel sa31;
    private javax.swing.JLabel sa32;
    private javax.swing.JLabel sa33;
    private javax.swing.JLabel sa34;
    private javax.swing.JLabel sa35;
    private javax.swing.JLabel sa36;
    private javax.swing.JLabel sa37;
    private javax.swing.JLabel sa38;
    private javax.swing.JLabel sa39;
    private javax.swing.JLabel sa4;
    private javax.swing.JLabel sa40;
    private javax.swing.JLabel sa41;
    private javax.swing.JLabel sa42;
    private javax.swing.JLabel sa43;
    private javax.swing.JLabel sa44;
    private javax.swing.JLabel sa45;
    private javax.swing.JLabel sa46;
    private javax.swing.JLabel sa47;
    private javax.swing.JLabel sa48;
    private javax.swing.JLabel sa49;
    private javax.swing.JLabel sa5;
    private javax.swing.JLabel sa50;
    private javax.swing.JLabel sa51;
    private javax.swing.JLabel sa6;
    private javax.swing.JLabel sa7;
    private javax.swing.JLabel sa8;
    private javax.swing.JLabel sa9;
    private javax.swing.JLabel sc1;
    private javax.swing.JLabel sc10;
    private javax.swing.JLabel sc11;
    private javax.swing.JLabel sc12;
    private javax.swing.JLabel sc13;
    private javax.swing.JLabel sc14;
    private javax.swing.JLabel sc15;
    private javax.swing.JLabel sc16;
    private javax.swing.JLabel sc17;
    private javax.swing.JLabel sc18;
    private javax.swing.JLabel sc19;
    private javax.swing.JLabel sc2;
    private javax.swing.JLabel sc20;
    private javax.swing.JLabel sc21;
    private javax.swing.JLabel sc22;
    private javax.swing.JLabel sc23;
    private javax.swing.JLabel sc24;
    private javax.swing.JLabel sc25;
    private javax.swing.JLabel sc26;
    private javax.swing.JLabel sc27;
    private javax.swing.JLabel sc28;
    private javax.swing.JLabel sc29;
    private javax.swing.JLabel sc3;
    private javax.swing.JLabel sc30;
    private javax.swing.JLabel sc31;
    private javax.swing.JLabel sc32;
    private javax.swing.JLabel sc33;
    private javax.swing.JLabel sc34;
    private javax.swing.JLabel sc35;
    private javax.swing.JLabel sc36;
    private javax.swing.JLabel sc37;
    private javax.swing.JLabel sc38;
    private javax.swing.JLabel sc39;
    private javax.swing.JLabel sc4;
    private javax.swing.JLabel sc40;
    private javax.swing.JLabel sc41;
    private javax.swing.JLabel sc42;
    private javax.swing.JLabel sc43;
    private javax.swing.JLabel sc44;
    private javax.swing.JLabel sc45;
    private javax.swing.JLabel sc46;
    private javax.swing.JLabel sc47;
    private javax.swing.JLabel sc48;
    private javax.swing.JLabel sc49;
    private javax.swing.JLabel sc5;
    private javax.swing.JLabel sc50;
    private javax.swing.JLabel sc51;
    private javax.swing.JLabel sc6;
    private javax.swing.JLabel sc7;
    private javax.swing.JLabel sc8;
    private javax.swing.JLabel sc9;
    private javax.swing.JLabel sn1;
    private javax.swing.JLabel sn10;
    private javax.swing.JLabel sn11;
    private javax.swing.JLabel sn12;
    private javax.swing.JLabel sn13;
    private javax.swing.JLabel sn14;
    private javax.swing.JLabel sn15;
    private javax.swing.JLabel sn16;
    private javax.swing.JLabel sn17;
    private javax.swing.JLabel sn18;
    private javax.swing.JLabel sn19;
    private javax.swing.JLabel sn2;
    private javax.swing.JLabel sn20;
    private javax.swing.JLabel sn21;
    private javax.swing.JLabel sn22;
    private javax.swing.JLabel sn23;
    private javax.swing.JLabel sn24;
    private javax.swing.JLabel sn25;
    private javax.swing.JLabel sn26;
    private javax.swing.JLabel sn27;
    private javax.swing.JLabel sn28;
    private javax.swing.JLabel sn29;
    private javax.swing.JLabel sn3;
    private javax.swing.JLabel sn30;
    private javax.swing.JLabel sn31;
    private javax.swing.JLabel sn32;
    private javax.swing.JLabel sn33;
    private javax.swing.JLabel sn34;
    private javax.swing.JLabel sn35;
    private javax.swing.JLabel sn36;
    private javax.swing.JLabel sn37;
    private javax.swing.JLabel sn38;
    private javax.swing.JLabel sn39;
    private javax.swing.JLabel sn4;
    private javax.swing.JLabel sn40;
    private javax.swing.JLabel sn41;
    private javax.swing.JLabel sn42;
    private javax.swing.JLabel sn43;
    private javax.swing.JLabel sn44;
    private javax.swing.JLabel sn45;
    private javax.swing.JLabel sn46;
    private javax.swing.JLabel sn47;
    private javax.swing.JLabel sn48;
    private javax.swing.JLabel sn49;
    private javax.swing.JLabel sn5;
    private javax.swing.JLabel sn50;
    private javax.swing.JLabel sn51;
    private javax.swing.JLabel sn6;
    private javax.swing.JLabel sn7;
    private javax.swing.JLabel sn8;
    private javax.swing.JLabel sn9;
    private javax.swing.JLabel st1;
    private javax.swing.JLabel st10;
    private javax.swing.JLabel st11;
    private javax.swing.JLabel st12;
    private javax.swing.JLabel st13;
    private javax.swing.JLabel st14;
    private javax.swing.JLabel st15;
    private javax.swing.JLabel st16;
    private javax.swing.JLabel st17;
    private javax.swing.JLabel st18;
    private javax.swing.JLabel st19;
    private javax.swing.JLabel st2;
    private javax.swing.JLabel st20;
    private javax.swing.JLabel st21;
    private javax.swing.JLabel st22;
    private javax.swing.JLabel st23;
    private javax.swing.JLabel st24;
    private javax.swing.JLabel st25;
    private javax.swing.JLabel st26;
    private javax.swing.JLabel st27;
    private javax.swing.JLabel st28;
    private javax.swing.JLabel st29;
    private javax.swing.JLabel st3;
    private javax.swing.JLabel st30;
    private javax.swing.JLabel st31;
    private javax.swing.JLabel st32;
    private javax.swing.JLabel st33;
    private javax.swing.JLabel st34;
    private javax.swing.JLabel st35;
    private javax.swing.JLabel st36;
    private javax.swing.JLabel st37;
    private javax.swing.JLabel st38;
    private javax.swing.JLabel st39;
    private javax.swing.JLabel st4;
    private javax.swing.JLabel st40;
    private javax.swing.JLabel st41;
    private javax.swing.JLabel st42;
    private javax.swing.JLabel st43;
    private javax.swing.JLabel st44;
    private javax.swing.JLabel st45;
    private javax.swing.JLabel st46;
    private javax.swing.JLabel st47;
    private javax.swing.JLabel st48;
    private javax.swing.JLabel st49;
    private javax.swing.JLabel st5;
    private javax.swing.JLabel st50;
    private javax.swing.JLabel st51;
    private javax.swing.JLabel st6;
    private javax.swing.JLabel st7;
    private javax.swing.JLabel st8;
    private javax.swing.JLabel st9;
    // End of variables declaration//GEN-END:variables
}
