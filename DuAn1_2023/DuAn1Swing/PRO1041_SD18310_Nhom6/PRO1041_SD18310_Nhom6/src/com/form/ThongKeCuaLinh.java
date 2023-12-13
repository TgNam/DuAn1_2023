/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.form;

import com.model.ChartModel;
import com.repository.BillRepository;
import com.repository.Return_Bill_Repository;
import com.repository.StatisticsRepository;
import java.awt.Color;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import raven.chart.ModelChart;

/**
 *
 * @author lenovo
 */
public class ThongKeCuaLinh extends javax.swing.JPanel {

    private StatisticsRepository statisticsRepository = new StatisticsRepository();
    private Return_Bill_Repository returnBillRepository = new Return_Bill_Repository();
    private BillRepository billRepository = new BillRepository();

    public ThongKeCuaLinh() {
        initComponents();
        chart.addLegend("doanhThu", Color.decode("#7b4397"), Color.decode("#dc2430"));
        openForm();
    }

    public void openForm() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        chart.setTitle("Biểu đồ thống kê tổng doanh thu năm "+year);
        List<ChartModel> data = statisticsRepository.getAllDataChart();
        // sửa lại tổng tiền trừ trả hàng
        if (data.size() <= 1) {
            ChartModel chartModel = new ChartModel();
            ChartModel chartModel1 = statisticsRepository.getAllDataChart().get(0);
            chart.addData(new ModelChart(chartModel1.getMonth(), new double[]{chartModel1.getTotalMoney()- Double.valueOf(returnBillRepository.getTotalMoney()+"")}));
            for (int i = chartModel.getDataNull().length - 1; i >= 0; i--) {
                chart.addData(new ModelChart(chartModel.getDataNull()[i], new double[]{0.0, 0.0}));
            }
        } else {
            for (int i = data.size() - 1; i >= 0; i--) {
                ChartModel chartModel = statisticsRepository.getAllDataChart().get(i);
                chart.addData(new ModelChart(chartModel.getMonth(), new double[]{chartModel.getTotalMoney()}));
            }
        }
        chart.start();

        txtRevenue.setText(formatCurrency(revenue()) + "");
        loadFilterYear();
        lbMoneyBillSucess.setText(formatCurrency(statisticsRepository.getSumIntoMoneyByStatus(3, 3, 3)));
        lbMoneyBillExchange.setText(formatCurrency(statisticsRepository.getSumIntoMoneyByStatus(7, 7, 7)));
        lbMoneyBillReturn.setText(formatCurrency(returnBillRepository.getTotalMoney()));

        lbAllBill.setText(billRepository.getQuantity());
        lbBillSucess.setText(statisticsRepository.getQuantityBillByStatus(3));
        lbBillExchange.setText(statisticsRepository.getQuantityBillByStatus(7));
        lbBillReturn.setText(statisticsRepository.getQuantityBillByStatus(5));
    }

    public BigDecimal revenue() {
        BigDecimal totalMoney = BigDecimal.ZERO;
        BigDecimal totalBillSuccess = statisticsRepository.getSumIntoMoneyByStatus(3, 7, 0);
        BigDecimal totalReturnBill = returnBillRepository.getTotalMoney();
        BigDecimal totalBillReturn = statisticsRepository.getSumIntoMoneyByStatus(0, 5, 0);
        if (totalBillSuccess == null) {
            return BigDecimal.ZERO;
        }
        if (totalReturnBill == null) {
            totalMoney = totalBillSuccess;
            return totalMoney;
        }
        totalMoney = totalBillSuccess.add(totalBillReturn.subtract(totalReturnBill));
        return totalMoney;
    }

    public void loadFilterYear() {
        DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cboFilterByYear.getModel();
        boxModel.removeAllElements();
        boxModel.addElement("Tất cả");
        new BillRepository().getYearCreated().forEach(i -> {
            boxModel.addElement(i);
        });
    }

    public String formatCurrency(BigDecimal price) {
        if (price == null) {
            return "0";
        }

        Locale locale = new Locale("vi", "VN");
        Currency currency = Currency.getInstance("VND");

        DecimalFormatSymbols df = DecimalFormatSymbols.getInstance(locale);
        df.setCurrency(currency);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        numberFormat.setCurrency(currency);

        return numberFormat.format(price);
    }

    public void filter() {
        String year = cboFilterByYear.getSelectedItem().toString();
        String month = cboFilterByMonth.getSelectedItem().toString();
        BigDecimal totalMoney = BigDecimal.ZERO;
        BigDecimal totalBillSuccess = BigDecimal.ZERO;
        BigDecimal totalReturnBill = BigDecimal.ZERO;
        BigDecimal totalBillReturn = BigDecimal.ZERO;

        if (year.trim().toLowerCase().equals("tất cả") && month.trim().toLowerCase().equals("tất cả")) {
            txtRevenue.setText(formatCurrency(revenue()) + "");
            lbMoneyBillSucess.setText(formatCurrency(statisticsRepository.getSumIntoMoneyByStatus(3, 3, 3)));
            lbMoneyBillExchange.setText(formatCurrency(statisticsRepository.getSumIntoMoneyByStatus(7, 7, 7)));
            lbMoneyBillReturn.setText(formatCurrency(returnBillRepository.getTotalMoney()));

            lbAllBill.setText(billRepository.getQuantity());
            lbBillSucess.setText(statisticsRepository.getQuantityBillByStatus(3));
            lbBillExchange.setText(statisticsRepository.getQuantityBillByStatus(7));
            lbBillReturn.setText(returnBillRepository.getQuantityByDate());
            return;
        }
        try {
            totalBillSuccess = statisticsRepository.getSumIntoMoneyByStatus(3, 7, 5, Integer.valueOf(year), Integer.valueOf(month));

            totalReturnBill = returnBillRepository.getTotalMoneyByIdBill(Long.valueOf(billRepository.getIdByUpdatedAt(5, Integer.parseInt(year), Integer.valueOf(month))));

            lbMoneyBillSucess.setText(formatCurrency(statisticsRepository.getSumIntoMoneyByStatus(3, 3, 3, Integer.valueOf(year), Integer.valueOf(month))));
            lbMoneyBillExchange.setText(formatCurrency(statisticsRepository.getSumIntoMoneyByStatus(7, 7, 7, Integer.valueOf(year), Integer.valueOf(month))));
            lbMoneyBillReturn.setText(formatCurrency(returnBillRepository.getTotalMoney(Integer.valueOf(year), Integer.valueOf(month))));
            lbAllBill.setText(billRepository.getQuantity(Integer.valueOf(year), Integer.valueOf(month)));

            lbBillSucess.setText(statisticsRepository.getQuantityBillByStatus(3, Integer.valueOf(year), Integer.valueOf(month)));
            lbBillExchange.setText(statisticsRepository.getQuantityBillByStatus(7, Integer.valueOf(year), Integer.valueOf(month)));
            lbBillReturn.setText(returnBillRepository.getQuantityByDate(Integer.valueOf(year), Integer.valueOf(month)));

            if (totalBillSuccess == null) {
                txtRevenue.setText(formatCurrency(totalMoney) + "");
                return;
            } else {
                if (totalReturnBill == null) {
                    totalMoney = totalBillSuccess;
                    txtRevenue.setText(formatCurrency(totalMoney) + "");
                    return;
                }
                totalBillReturn = statisticsRepository.getSumIntoMoneyByStatusAndUpdated(5, Integer.valueOf(year), Integer.valueOf(month));
                totalMoney = totalBillSuccess.add(totalBillReturn.subtract(totalReturnBill));
                txtRevenue.setText(formatCurrency(totalMoney) + "");
            }
        } catch (NumberFormatException e) {
            if (!year.trim().toLowerCase().equals("tất cả") && month.trim().toLowerCase().equals("tất cả")) {
                totalBillSuccess = statisticsRepository.getSumIntoMoneyByStatusAndYear(3, 7, 5, Integer.valueOf(year));
                totalReturnBill = returnBillRepository.getTotalMoneyByIdBill(Long.valueOf(billRepository.getIdByUpdatedAt(5, Integer.parseInt(year))));

                lbMoneyBillSucess.setText(formatCurrency(statisticsRepository.getSumIntoMoneyByStatus(3, 3, 3, Integer.valueOf(year))));
                lbMoneyBillExchange.setText(formatCurrency(statisticsRepository.getSumIntoMoneyByStatus(7, 7, 7, Integer.valueOf(year))));
                lbMoneyBillReturn.setText(formatCurrency(returnBillRepository.getTotalMoney(Integer.valueOf(year))));
                lbAllBill.setText(billRepository.getQuantity(Integer.valueOf(year)));

                lbBillSucess.setText(statisticsRepository.getQuantityBillByStatus(3, Integer.valueOf(year)));
                lbBillExchange.setText(statisticsRepository.getQuantityBillByStatus(7, Integer.valueOf(year)));
                lbBillReturn.setText(returnBillRepository.getQuantityByDate(Integer.valueOf(year)));

                if (totalBillSuccess == null) {
                    txtRevenue.setText(formatCurrency(totalMoney) + "");
                    return;
                } else {
                    if (totalReturnBill == null) {
                        totalMoney = totalBillSuccess;
                        txtRevenue.setText(formatCurrency(totalMoney) + "");
                        return;
                    }
                    totalBillReturn = statisticsRepository.getSumIntoMoneyByStatusAndUpdated(5, Integer.valueOf(year));
                    totalMoney = totalBillSuccess.add(totalBillReturn.subtract(totalReturnBill));
                    txtRevenue.setText(formatCurrency(totalMoney) + "");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không thể xem tất cả", "Loi", 2);
                cboFilterByMonth.setSelectedIndex(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            totalMoney = BigDecimal.ZERO;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnChart = new javax.swing.JPanel();
        chart = new raven.chart.CurveLineChart();
        pnStatisticalDetails = new javax.swing.JPanel();
        pnSuccess = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbMoneyBillSucess = new javax.swing.JLabel();
        lbBillSucess = new javax.swing.JLabel();
        pnReturn = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lbMoneyBillReturn = new javax.swing.JLabel();
        lbBillReturn = new javax.swing.JLabel();
        pnExchange = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lbMoneyBillExchange = new javax.swing.JLabel();
        lbBillExchange = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnUnpaid = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        lbAllBill = new javax.swing.JLabel();
        pnFilter = new javax.swing.JPanel();
        pnFilterMain = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cboFilterByMonth = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cboFilterByYear = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtRevenue = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        pnTitle.setBackground(new java.awt.Color(123, 207, 255));
        pnTitle.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("THỐNG KÊ");

        javax.swing.GroupLayout pnTitleLayout = new javax.swing.GroupLayout(pnTitle);
        pnTitle.setLayout(pnTitleLayout);
        pnTitleLayout.setHorizontalGroup(
            pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnTitleLayout.setVerticalGroup(
            pnTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pnChart.setBackground(new java.awt.Color(40, 58, 80));
        pnChart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnChart.setForeground(new java.awt.Color(27, 77, 90));

        chart.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chart.setFillColor(true);

        javax.swing.GroupLayout pnChartLayout = new javax.swing.GroupLayout(pnChart);
        pnChart.setLayout(pnChartLayout);
        pnChartLayout.setHorizontalGroup(
            pnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnChartLayout.setVerticalGroup(
            pnChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );

        pnSuccess.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Thành công");

        lbMoneyBillSucess.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbMoneyBillSucess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMoneyBillSucess.setText("số tiền bán thành công");

        lbBillSucess.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbBillSucess.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBillSucess.setText("sl đơn");

        javax.swing.GroupLayout pnSuccessLayout = new javax.swing.GroupLayout(pnSuccess);
        pnSuccess.setLayout(pnSuccessLayout);
        pnSuccessLayout.setHorizontalGroup(
            pnSuccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnSuccessLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnSuccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbBillSucess, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMoneyBillSucess, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnSuccessLayout.setVerticalGroup(
            pnSuccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSuccessLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbBillSucess, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMoneyBillSucess, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnReturn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Trả hàng");

        lbMoneyBillReturn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbMoneyBillReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMoneyBillReturn.setText("số tiền bán thành công");

        lbBillReturn.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbBillReturn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBillReturn.setText("sl đơn");

        javax.swing.GroupLayout pnReturnLayout = new javax.swing.GroupLayout(pnReturn);
        pnReturn.setLayout(pnReturnLayout);
        pnReturnLayout.setHorizontalGroup(
            pnReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnReturnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbBillReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbMoneyBillReturn, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnReturnLayout.setVerticalGroup(
            pnReturnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnReturnLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbBillReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMoneyBillReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnExchange.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Đổi hàng");

        lbMoneyBillExchange.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbMoneyBillExchange.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMoneyBillExchange.setText("số tiền bán thành công");

        lbBillExchange.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbBillExchange.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBillExchange.setText("sl đơn");

        javax.swing.GroupLayout pnExchangeLayout = new javax.swing.GroupLayout(pnExchange);
        pnExchange.setLayout(pnExchangeLayout);
        pnExchangeLayout.setHorizontalGroup(
            pnExchangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnExchangeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnExchangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbMoneyBillExchange, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                    .addComponent(lbBillExchange, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnExchangeLayout.setVerticalGroup(
            pnExchangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnExchangeLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbBillExchange, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbMoneyBillExchange, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Hóa đơn");

        pnUnpaid.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Tất cả");

        lbAllBill.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbAllBill.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAllBill.setText("sl đơn");

        javax.swing.GroupLayout pnUnpaidLayout = new javax.swing.GroupLayout(pnUnpaid);
        pnUnpaid.setLayout(pnUnpaidLayout);
        pnUnpaidLayout.setHorizontalGroup(
            pnUnpaidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnUnpaidLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbAllBill, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnUnpaidLayout.setVerticalGroup(
            pnUnpaidLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnUnpaidLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel13)
                .addGap(12, 12, 12)
                .addComponent(lbAllBill, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnStatisticalDetailsLayout = new javax.swing.GroupLayout(pnStatisticalDetails);
        pnStatisticalDetails.setLayout(pnStatisticalDetailsLayout);
        pnStatisticalDetailsLayout.setHorizontalGroup(
            pnStatisticalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnStatisticalDetailsLayout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnStatisticalDetailsLayout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(pnUnpaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnSuccess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnExchange, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        pnStatisticalDetailsLayout.setVerticalGroup(
            pnStatisticalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnStatisticalDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnStatisticalDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnExchange, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnReturn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnSuccess, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnUnpaid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        pnFilter.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/icon/filter.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel16.setText("Bộ lọc");

        cboFilterByMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        cboFilterByMonth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboFilterByMonthMouseClicked(evt);
            }
        });
        cboFilterByMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterByMonthActionPerformed(evt);
            }
        });

        jLabel17.setText("Tháng");

        jLabel18.setText("Năm");

        cboFilterByYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFilterByYearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnFilterMainLayout = new javax.swing.GroupLayout(pnFilterMain);
        pnFilterMain.setLayout(pnFilterMainLayout);
        pnFilterMainLayout.setHorizontalGroup(
            pnFilterMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFilterMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnFilterMainLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(pnFilterMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(pnFilterMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboFilterByMonth, 0, 91, Short.MAX_VALUE)
                    .addComponent(cboFilterByYear, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnFilterMainLayout.setVerticalGroup(
            pnFilterMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFilterMainLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnFilterMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnFilterMainLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel16)))
                .addGap(52, 52, 52)
                .addGroup(pnFilterMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFilterByMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(32, 32, 32)
                .addGroup(pnFilterMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cboFilterByYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Doanh thu");

        txtRevenue.setEditable(false);
        txtRevenue.setBackground(new java.awt.Color(255, 255, 255));
        txtRevenue.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtRevenue.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRevenue.setBorder(null);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(139, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnFilterLayout = new javax.swing.GroupLayout(pnFilter);
        pnFilter.setLayout(pnFilterLayout);
        pnFilterLayout.setHorizontalGroup(
            pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnFilterMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnFilterLayout.setVerticalGroup(
            pnFilterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnFilterLayout.createSequentialGroup()
                .addComponent(pnFilterMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnStatisticalDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(pnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pnChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pnStatisticalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboFilterByMonthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboFilterByMonthMouseClicked

    }//GEN-LAST:event_cboFilterByMonthMouseClicked

    private void cboFilterByMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterByMonthActionPerformed
        filter();
    }//GEN-LAST:event_cboFilterByMonthActionPerformed

    private void cboFilterByYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFilterByYearActionPerformed
        filter();
    }//GEN-LAST:event_cboFilterByYearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboFilterByMonth;
    private javax.swing.JComboBox<String> cboFilterByYear;
    private raven.chart.CurveLineChart chart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbAllBill;
    private javax.swing.JLabel lbBillExchange;
    private javax.swing.JLabel lbBillReturn;
    private javax.swing.JLabel lbBillSucess;
    private javax.swing.JLabel lbMoneyBillExchange;
    private javax.swing.JLabel lbMoneyBillReturn;
    private javax.swing.JLabel lbMoneyBillSucess;
    private javax.swing.JPanel pnChart;
    private javax.swing.JPanel pnExchange;
    private javax.swing.JPanel pnFilter;
    private javax.swing.JPanel pnFilterMain;
    private javax.swing.JPanel pnReturn;
    private javax.swing.JPanel pnStatisticalDetails;
    private javax.swing.JPanel pnSuccess;
    private javax.swing.JPanel pnTitle;
    private javax.swing.JPanel pnUnpaid;
    private javax.swing.JTextField txtRevenue;
    // End of variables declaration//GEN-END:variables
}
