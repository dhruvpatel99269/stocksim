/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package stocksim;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Portfolio3 extends javax.swing.JFrame {
    LinkedList<Integer> itemIdList = new LinkedList<>();
    LinkedList<Integer> itemPriceList = new LinkedList<>();
    LinkedList<Integer> itemQuantityList = new LinkedList<>();
    int userid = UserData.getUserId();
    int orderid3 = PortfolioData.getOrderId3();
    int sum=0;
    int plsum=0;
    /**
     * Creates new form Order3
     */
    public Portfolio3() {
        initComponents();
        loadItems();
        loadPortfolio();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void loadPortfolio() {
        String playlistQuery = "SELECT portfolioname FROM portfolio WHERE portfolioid=?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(playlistQuery)) {
                statement.setInt(1, orderid3);
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String portfolio = resultSet.getString("portfolioname");
                    ordername.setText(portfolio);
                } else {
                    System.err.println("Portfolio not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing portfolio query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }
    }

    private void loadItems() {
        String playlistTracksQuery = "SELECT COUNT(stockdata.stockid) AS cnt, stockdata.stockid, stockdata.stockname, stockdata.price, portfoliostocks.stockamt, portfoliostocks.stockqty "
                + "FROM stockdata "
                + "JOIN portfoliostocks ON portfoliostocks.stockid = stockdata.stockid "
                + "WHERE portfoliostocks.userid = ? AND portfoliostocks.portfolioid = ? "
                + "GROUP BY stockdata.stockid, stockdata.stockname, stockdata.price, portfoliostocks.stockamt, portfoliostocks.stockqty;";
        sum = 0;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(playlistTracksQuery)) {
                statement.setInt(1, userid);
                statement.setInt(2, orderid3);
                java.sql.ResultSet resultSet = statement.executeQuery();
                int itemCount = 0;
                while (resultSet.next() && itemCount < 10) {
                    String itemName = resultSet.getString("stockdata.stockname");
                    int itemPrice = resultSet.getInt("portfoliostocks.stockamt");
                    int itemid = resultSet.getInt("stockdata.stockid");
                    int itemqty = resultSet.getInt("portfoliostocks.stockqty");
                    itemIdList.add(itemid);
                    itemPriceList.add(itemPrice);
                    itemQuantityList.add(itemqty);
                    totalValue.setText(Integer.toString(sum));
                    switch (itemCount) {
                        case 0:

                            sn1.setText(itemName);
                            sa1.setText(Integer.toString(itemPrice));
                            st1.setText(Integer.toString(itemqty));
                            break;
                        case 1:

                            sn2.setText(itemName);
                            sa2.setText(Integer.toString(itemPrice));
                            st2.setText(Integer.toString(itemqty));
                            break;
                        case 2:

                            sn3.setText(itemName);
                            sa3.setText(Integer.toString(itemPrice));
                            st3.setText(Integer.toString(itemqty));
                            break;
                        case 3:

                            sn4.setText(itemName);
                            sa4.setText(Integer.toString(itemPrice));
                            st4.setText(Integer.toString(itemqty));
                            break;
                        case 4:

                            sn5.setText(itemName);
                            sa5.setText(Integer.toString(itemPrice));
                            st5.setText(Integer.toString(itemqty));
                            break;
                        case 5:

                            sn6.setText(itemName);
                            sa6.setText(Integer.toString(itemPrice));
                            st6.setText(Integer.toString(itemqty));
                            break;
                        case 6:

                            sn7.setText(itemName);
                            sa7.setText(Integer.toString(itemPrice));
                            st7.setText(Integer.toString(itemqty));
                            break;
                        case 7:

                            sn9.setText(itemName);
                            sa9.setText(Integer.toString(itemPrice));
                            st9.setText(Integer.toString(itemqty));
                            break;
                        case 8:

                            sn10.setText(itemName);
                            sa10.setText(Integer.toString(itemPrice));
                            st10.setText(Integer.toString(itemqty));
                            break;
                        case 9:

                            sn11.setText(itemName);
                            sa11.setText(Integer.toString(itemPrice));
                            st11.setText(Integer.toString(itemqty));
                            break;
                        case 10:

                            sn12.setText(itemName);
                            sa12.setText(Integer.toString(itemPrice));
                            st12.setText(Integer.toString(itemqty));
                            break;
                        case 11:

                            sn13.setText(itemName);
                            sa13.setText(Integer.toString(itemPrice));
                            st13.setText(Integer.toString(itemqty));
                            break;
                        case 12:

                            sn14.setText(itemName);
                            sa14.setText(Integer.toString(itemPrice));
                            st14.setText(Integer.toString(itemqty));
                            break;
                        case 13:

                            sn15.setText(itemName);
                            sa15.setText(Integer.toString(itemPrice));
                            st15.setText(Integer.toString(itemqty));
                            break;
                        case 14:

                            sn16.setText(itemName);
                            sa16.setText(Integer.toString(itemPrice));
                            st16.setText(Integer.toString(itemqty));
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
        totalPrice();
    }

    public void totalPrice() {
        for (Integer price : itemPriceList) {
            sum += price;
        }
        totalValue.setText(Integer.toString(sum));

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "Dhruv@99269!")) {
            String updateSql = "UPDATE portfolio SET portfoliovalue = ? WHERE userid = ? AND portfolioid = ?";
            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, sum);
                updateStmt.setInt(2, userid); // Assuming userId is accessible here
                updateStmt.setInt(3, orderid3); // Assuming orderId1 is accessible here
                updateStmt.executeUpdate();
            }
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace();
        }
    }

    private void deleteOrderItems(int userId, int orderId3) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "Dhruv@99269!")) {
            String sql = "DELETE FROM portfoliostocks WHERE userid = ? AND portfolioid = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userid);
                pstmt.setInt(2, orderid3);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace();
        }
    }

    private void deleteOrder(int userId, int orderId3) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "Dhruv@99269!")) {
            String sql = "DELETE FROM portfolio WHERE userid = ? AND portfolioid = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userid);
                pstmt.setInt(2, orderid3);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace();
        }
    }

    private void removeItemFromOrder(int itemidToRemove, int qty) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/stocks", "root", "Dhruv@99269!")) {
            String deleteQuery = "DELETE FROM portfoliostocks WHERE stockid = ? AND userid = ? AND portfolioid = ?";

            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, itemidToRemove);
                deleteStatement.setInt(2, userid);
                deleteStatement.setInt(3, orderid3);

                int rowsAffected = deleteStatement.executeUpdate();

                if (rowsAffected > 0) {
                    // Stock deleted successfully, now retrieve price and sell columns
                    String selectQuery = "SELECT price, sellprice FROM stockdata1 WHERE stockid = ?";
                    try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                        selectStatement.setInt(1, itemidToRemove);
                        java.sql.ResultSet resultSet = selectStatement.executeQuery();

                        if (resultSet.next()) {
                            int buy = resultSet.getInt("price");
                            int sell = resultSet.getInt("sellprice");
                            System.out.println("Buy Price: " + buy);
                            System.out.println("Sell Price: " + sell);
                            System.out.println("Quantity: " + qty);
                            int pl = (sell - buy) * qty;
                            plsum += pl;
                            System.out.println("P/l: " + pl);
                            System.out.println("Total pl: " + plsum);
                            profitloss.setText(Integer.toString(plsum));

                            // Update profit_loss in portfolio table
                            String updateQuery = "UPDATE portfolio SET profit_loss = ? WHERE userid = ? AND portfolioid = ?";
                            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                                updateStatement.setInt(1, plsum);
                                updateStatement.setInt(2, userid);
                                updateStatement.setInt(3, orderid3);
                                updateStatement.executeUpdate();
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Error updating profit_loss: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            JOptionPane.showMessageDialog(null, "Stock deleted from portfolio successfully.\nPrice: " + buy + "\nSell Price: " + sell + "\nStock Quantity: " + qty, "Success", JOptionPane.INFORMATION_MESSAGE);
                            itemPriceList = new LinkedList<>();
                            itemIdList = new LinkedList<>();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to retrieve price and sell for the deleted stock.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete stock from portfolio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error deleting stock from portfolio: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error connecting to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        homeicon = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        ordername = new javax.swing.JLabel();
        addItemsBtn = new javax.swing.JButton();
        deleteOrderBtn = new javax.swing.JButton();
        grandTotal = new javax.swing.JLabel();
        totalValue = new javax.swing.JLabel();
        pl = new javax.swing.JLabel();
        profitloss = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        sc1 = new javax.swing.JLabel();
        sn1 = new javax.swing.JLabel();
        sa1 = new javax.swing.JLabel();
        st1 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        sc2 = new javax.swing.JLabel();
        sn2 = new javax.swing.JLabel();
        sa2 = new javax.swing.JLabel();
        st2 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
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
        jPanel21 = new javax.swing.JPanel();
        sc13 = new javax.swing.JLabel();
        sn13 = new javax.swing.JLabel();
        sa13 = new javax.swing.JLabel();
        st13 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        sc14 = new javax.swing.JLabel();
        sn14 = new javax.swing.JLabel();
        sa14 = new javax.swing.JLabel();
        st14 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        sc15 = new javax.swing.JLabel();
        sn15 = new javax.swing.JLabel();
        sa15 = new javax.swing.JLabel();
        st15 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        sc16 = new javax.swing.JLabel();
        sn16 = new javax.swing.JLabel();
        sa16 = new javax.swing.JLabel();
        st16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(950, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 650));

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

        ordername.setFont(new java.awt.Font("Poor Richard", 0, 30)); // NOI18N
        ordername.setForeground(new java.awt.Color(255, 255, 255));
        ordername.setText("Portfolio 1");

        addItemsBtn.setBackground(new java.awt.Color(0, 102, 153));
        addItemsBtn.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        addItemsBtn.setForeground(new java.awt.Color(255, 255, 255));
        addItemsBtn.setText("Buy Stocks");
        addItemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemsBtnActionPerformed(evt);
            }
        });

        deleteOrderBtn.setBackground(new java.awt.Color(0, 102, 153));
        deleteOrderBtn.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        deleteOrderBtn.setForeground(new java.awt.Color(255, 255, 255));
        deleteOrderBtn.setText("Delete Portfolio");
        deleteOrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteOrderBtnActionPerformed(evt);
            }
        });

        grandTotal.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        grandTotal.setForeground(new java.awt.Color(255, 255, 255));
        grandTotal.setText("Portfolio Value:");

        totalValue.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        totalValue.setForeground(new java.awt.Color(255, 255, 255));

        pl.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        pl.setForeground(new java.awt.Color(255, 255, 255));
        pl.setText("Total Profit/Loss:");

        profitloss.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        profitloss.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(ordername, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(grandTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(totalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(deleteOrderBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addItemsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(profitloss, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ordername, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(grandTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(totalValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(profitloss, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(addItemsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deleteOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
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
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setBackground(new java.awt.Color(20, 20, 20));

        jPanel6.setBackground(new java.awt.Color(25, 25, 25));

        sc1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc1.setForeground(new java.awt.Color(255, 255, 255));

        sn1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn1.setForeground(new java.awt.Color(255, 255, 255));
        sn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn1MouseReleased(evt);
            }
        });

        sa1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa1.setForeground(new java.awt.Color(255, 255, 255));

        st1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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

        jPanel18.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(st3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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

        sc8.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sc8.setForeground(new java.awt.Color(255, 255, 255));

        sn8.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sn8.setForeground(new java.awt.Color(255, 255, 255));
        sn8.setText("Stocks Bought");
        sn8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn8MouseReleased(evt);
            }
        });

        sa8.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sa8.setForeground(new java.awt.Color(255, 255, 255));
        sa8.setText("Stock Amount");

        st8.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        st8.setForeground(new java.awt.Color(255, 255, 255));
        st8.setText("Qty");

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
                .addGap(18, 18, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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

        jPanel21.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn13, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa13, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(st13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(st14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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

        jPanel23.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
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
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel4);

        jPanel3.setBackground(new java.awt.Color(20, 20, 20));

        jPanel5.setBackground(new java.awt.Color(20, 20, 20));

        jPanel24.setBackground(new java.awt.Color(20, 20, 20));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Click on stock name to sell.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(7, 7, 7))
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

    private void addItemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemsBtnActionPerformed
        // TODO add your handling code here:
        PortfolioData.setOrderId(orderid3);
        dispose();
        Stocks ads = new Stocks();
        ads.setVisible(true);
    }//GEN-LAST:event_addItemsBtnActionPerformed

    private void deleteOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteOrderBtnActionPerformed
        if (userid != -1 && orderid3 != -1) {
            deleteOrderItems(userid, orderid3);
            deleteOrder(userid, orderid3);
            dispose();
            Dashboard dashboard = new Dashboard();
            dashboard.setVisible(true);
        } else {
            System.out.println("Error: Unable to get user ID or order ID.");
        }
    }//GEN-LAST:event_deleteOrderBtnActionPerformed

    private void sn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn1MouseReleased
        int itemidToAdd = itemIdList.get(0);
        int itemqty = itemQuantityList.get(0);
        removeItemFromOrder(itemidToAdd, itemqty);
        totalPrice();
        loadItems();
    }//GEN-LAST:event_sn1MouseReleased

    private void sn2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn2MouseReleased
        int itemidToAdd = itemIdList.get(1);
        int itemqty = itemQuantityList.get(1);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn2MouseReleased

    private void sn3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn3MouseReleased
        int itemidToAdd = itemIdList.get(2);
        int itemqty = itemQuantityList.get(2);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn3MouseReleased

    private void sn4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn4MouseReleased
        int itemidToAdd = itemIdList.get(3);
        int itemqty = itemQuantityList.get(3);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn4MouseReleased

    private void sn5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn5MouseReleased
        int itemidToAdd = itemIdList.get(4);
        int itemqty = itemQuantityList.get(4);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn5MouseReleased

    private void sn6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn6MouseReleased
        int itemidToAdd = itemIdList.get(5);
        int itemqty = itemQuantityList.get(5);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn6MouseReleased

    private void sn7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn7MouseReleased
        int itemidToAdd = itemIdList.get(6);
        int itemqty = itemQuantityList.get(6);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn7MouseReleased

    private void sn8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn8MouseReleased
        int itemidToAdd = itemIdList.get(7);
        int itemqty = itemQuantityList.get(7);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn8MouseReleased

    private void sn9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn9MouseReleased
        int itemidToAdd = itemIdList.get(8);
        int itemqty = itemQuantityList.get(8);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn9MouseReleased

    private void sn10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn10MouseReleased
        int itemidToAdd = itemIdList.get(9);
        int itemqty = itemQuantityList.get(9);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn10MouseReleased

    private void sn11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn11MouseReleased
        int itemidToAdd = itemIdList.get(10);
        int itemqty = itemQuantityList.get(10);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn11MouseReleased

    private void sn12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn12MouseReleased
        int itemidToAdd = itemIdList.get(11);
        int itemqty = itemQuantityList.get(11);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn12MouseReleased

    private void sn13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn13MouseReleased
        int itemidToAdd = itemIdList.get(12);
        int itemqty = itemQuantityList.get(12);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn13MouseReleased

    private void sn14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn14MouseReleased
        int itemidToAdd = itemIdList.get(13);
        int itemqty = itemQuantityList.get(13);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn14MouseReleased

    private void sn15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn15MouseReleased
        int itemidToAdd = itemIdList.get(14);
        int itemqty = itemQuantityList.get(14);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn15MouseReleased

    private void sn16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn16MouseReleased
        int itemidToAdd = itemIdList.get(15);
        int itemqty = itemQuantityList.get(15);
        removeItemFromOrder(itemidToAdd, itemqty);
        loadItems();
    }//GEN-LAST:event_sn16MouseReleased

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
            java.util.logging.Logger.getLogger(Portfolio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Portfolio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Portfolio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Portfolio3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Portfolio3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemsBtn;
    private javax.swing.JButton deleteOrderBtn;
    private javax.swing.JLabel grandTotal;
    private javax.swing.JLabel homeicon;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel ordername;
    private javax.swing.JLabel pl;
    private javax.swing.JLabel profitloss;
    private javax.swing.JLabel sa1;
    private javax.swing.JLabel sa10;
    private javax.swing.JLabel sa11;
    private javax.swing.JLabel sa12;
    private javax.swing.JLabel sa13;
    private javax.swing.JLabel sa14;
    private javax.swing.JLabel sa15;
    private javax.swing.JLabel sa16;
    private javax.swing.JLabel sa2;
    private javax.swing.JLabel sa3;
    private javax.swing.JLabel sa4;
    private javax.swing.JLabel sa5;
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
    private javax.swing.JLabel sc2;
    private javax.swing.JLabel sc3;
    private javax.swing.JLabel sc4;
    private javax.swing.JLabel sc5;
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
    private javax.swing.JLabel sn2;
    private javax.swing.JLabel sn3;
    private javax.swing.JLabel sn4;
    private javax.swing.JLabel sn5;
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
    private javax.swing.JLabel st2;
    private javax.swing.JLabel st3;
    private javax.swing.JLabel st4;
    private javax.swing.JLabel st5;
    private javax.swing.JLabel st6;
    private javax.swing.JLabel st7;
    private javax.swing.JLabel st8;
    private javax.swing.JLabel st9;
    private javax.swing.JLabel totalValue;
    // End of variables declaration//GEN-END:variables
}
