package javaapplication6;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class paincikk extends javax.swing.JFrame {
    Color brushColor = Color.BLACK;
    int old_mouse_x = 0;
    int old_mouse_y = 0;
    BufferedImage canvas;
    BufferedImage tempCanvas;
    Graphics2D graphics2D;
    Graphics2D tempGraphics2D;
    ArrayList<Integer> polygonX = new ArrayList<>();
    ArrayList<Integer> polygonY = new ArrayList<>();
    boolean drawingPolygon = false;
    boolean drawingShape = false;
    int current_mouse_x = 0;
    int current_mouse_y = 0;
    private static final int CLOSE_RADIUS = 10; 
    public paincikk() {
        initComponents();
        canvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        graphics2D = canvas.createGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, 800, 600);
        tempCanvas = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
        tempGraphics2D = tempCanvas.createGraphics();
    }

    private void initComponents() {
        jTextField1 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new DrawingPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jTextField1.setEditable(false);
        jTextField1.setText("KOLOR");
        jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField1MouseClicked(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1px", "2px", "3px", "4px", "5px" }));

        jLabel2.setText("Grubość");

        jLabel1.setText("Narzędzie");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ołówek", "prostokąt", "koło", "linia", "wielokąt" }));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(400, 300));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 600));
        jPanel1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                if (jPanel1.getWidth() > 0 && jPanel1.getHeight() > 0) {
                    BufferedImage newCanvas = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D newGraphics2D = newCanvas.createGraphics();
                    newGraphics2D.setColor(Color.WHITE);
                    newGraphics2D.fillRect(0, 0, jPanel1.getWidth(), jPanel1.getHeight());
                    if (canvas != null) {
                        newGraphics2D.drawImage(canvas, 0, 0, null);
                        graphics2D.dispose();
                    }
                    canvas = newCanvas;
                    graphics2D = newGraphics2D;
                    tempCanvas = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    tempGraphics2D = tempCanvas.createGraphics();
                }
            }
        });
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel1MouseMoved(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jPanel1MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        jButton1.setText("Wyczyść");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Zapisz");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Wczytaj");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap()
        );
        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap()
        );

        pack();
        setMinimumSize(new java.awt.Dimension(400, 400));
    }

    private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {
        Color selectedColor = JColorChooser.showDialog(this, "Wybierz kolor", brushColor);
        if (selectedColor != null) {
            brushColor = selectedColor;
            jTextField2.setBackground(brushColor);
        }
    }

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {
        old_mouse_x = evt.getX();
        old_mouse_y = evt.getY();
        String tool = (String) jComboBox1.getSelectedItem();
        if (tool.equals("prostokąt") || tool.equals("koło") || tool.equals("linia")) {
            drawingShape = true;
            tempGraphics2D.drawImage(canvas, 0, 0, null); 
            jPanel1.repaint();
        } else if (tool.equals("wielokąt")) {
            drawingPolygon = true;
            if (polygonX.isEmpty()) {
                polygonX.add(old_mouse_x);
                polygonY.add(old_mouse_y);
                tempGraphics2D.drawImage(canvas, 0, 0, null); 
                jPanel1.repaint();
            }
        }
    }

    private void jPanel1MouseMoved(java.awt.event.MouseEvent evt) {
        if (drawingPolygon && !polygonX.isEmpty()) {
            current_mouse_x = evt.getX();
            current_mouse_y = evt.getY();
            updatePolygonPreview();
        }
    }

    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();
        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight()) {
            return;
        }
        String tool = (String) jComboBox1.getSelectedItem();
        int thickness = Integer.parseInt(((String) jComboBox2.getSelectedItem()).replace("px", ""));
        tempGraphics2D.setColor(brushColor);
        tempGraphics2D.setStroke(new BasicStroke(thickness));

        if (tool.equals("ołówek")) {
            graphics2D.setColor(brushColor);
            graphics2D.setStroke(new BasicStroke(thickness));
            graphics2D.drawLine(old_mouse_x, old_mouse_y, x, y);
            old_mouse_x = x;
            old_mouse_y = y;
            jPanel1.repaint();
        } else if (tool.equals("prostokąt") || tool.equals("koło") || tool.equals("linia")) {
            tempGraphics2D.drawImage(canvas, 0, 0, null);
            tempGraphics2D.setColor(brushColor);
            tempGraphics2D.setStroke(new BasicStroke(thickness));
            if (tool.equals("prostokąt")) {
                int width = Math.abs(x - old_mouse_x);
                int height = Math.abs(y - old_mouse_y);
                int startX = Math.min(old_mouse_x, x);
                int startY = Math.min(old_mouse_y, y);
                tempGraphics2D.drawRect(startX, startY, width, height);
            } else if (tool.equals("koło")) {
                int width = Math.abs(x - old_mouse_x);
                int height = Math.abs(y - old_mouse_y);
                int startX = Math.min(old_mouse_x, x);
                int startY = Math.min(old_mouse_y, y);
                tempGraphics2D.drawOval(startX, startY, width, height);
            } else if (tool.equals("linia")) {
                tempGraphics2D.drawLine(old_mouse_x, old_mouse_y, x, y);
            }
            jPanel1.repaint();
        } else if (tool.equals("wielokąt")) {
            current_mouse_x = x;
            current_mouse_y = y;
            updatePolygonPreview();
        }
    }

    private void updatePolygonPreview() {
        tempGraphics2D.drawImage(canvas, 0, 0, null); 
        tempGraphics2D.setColor(brushColor);
        int thickness = Integer.parseInt(((String) jComboBox2.getSelectedItem()).replace("px", ""));
        tempGraphics2D.setStroke(new BasicStroke(thickness));
        for (int i = 1; i < polygonX.size(); i++) {
            tempGraphics2D.drawLine(polygonX.get(i - 1), polygonY.get(i - 1), polygonX.get(i), polygonY.get(i));
        }
        if (!polygonX.isEmpty()) {
            tempGraphics2D.drawLine(polygonX.get(polygonX.size() - 1), polygonY.get(polygonY.size() - 1), current_mouse_x, current_mouse_y);
        }
        jPanel1.repaint();
    }

    private void jPanel1MouseReleased(java.awt.event.MouseEvent evt) {
        int x = evt.getX();
        int y = evt.getY();
        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight()) {
            return;
        }
        String tool = (String) jComboBox1.getSelectedItem();
        int thickness = Integer.parseInt(((String) jComboBox2.getSelectedItem()).replace("px", ""));
        graphics2D.setColor(brushColor);
        graphics2D.setStroke(new BasicStroke(thickness));

        if (tool.equals("prostokąt")) {
            int width = Math.abs(x - old_mouse_x);
            int height = Math.abs(y - old_mouse_y);
            int startX = Math.min(old_mouse_x, x);
            int startY = Math.min(old_mouse_y, y);
            graphics2D.drawRect(startX, startY, width, height);
        } else if (tool.equals("koło")) {
            int width = Math.abs(x - old_mouse_x);
            int height = Math.abs(y - old_mouse_y);
            int startX = Math.min(old_mouse_x, x);
            int startY = Math.min(old_mouse_y, y);
            graphics2D.drawOval(startX, startY, width, height);
        } else if (tool.equals("linia")) {
            graphics2D.drawLine(old_mouse_x, old_mouse_y, x, y);
        }
        drawingShape = false;
        tempGraphics2D.drawImage(canvas, 0, 0, null); 
        jPanel1.repaint();
    }

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {
        String tool = (String) jComboBox1.getSelectedItem();
        if (!tool.equals("wielokąt") || !drawingPolygon) {
            return;
        }
        int x = evt.getX();
        int y = evt.getY();
        if (x < 0 || x >= canvas.getWidth() || y < 0 || y >= canvas.getHeight()) {
            return;
        }

        
        if (polygonX.size() >= 3 && !polygonX.isEmpty()) {
            int startX = polygonX.get(0);
            int startY = polygonY.get(0);
            double distance = Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2));
            if (distance <= CLOSE_RADIUS) {
                int thickness = Integer.parseInt(((String) jComboBox2.getSelectedItem()).replace("px", ""));
                graphics2D.setColor(brushColor);
                graphics2D.setStroke(new BasicStroke(thickness));
                Polygon polygon = new Polygon();
                for (int i = 0; i < polygonX.size(); i++) {
                    polygon.addPoint(polygonX.get(i), polygonY.get(i));
                }
                graphics2D.drawPolygon(polygon);
                polygonX.clear();
                polygonY.clear();
                drawingPolygon = false;
                tempGraphics2D.drawImage(canvas, 0, 0, null); 
                jPanel1.repaint();
                return;
            }
        }

        
        polygonX.add(x);
        polygonY.add(y);
        updatePolygonPreview();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tempGraphics2D.drawImage(canvas, 0, 0, null); 
        polygonX.clear();
        polygonY.clear();
        drawingPolygon = false;
        drawingShape = false;
        jPanel1.repaint();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Zapisz obraz");
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".png")) {
                file = new File(file.getAbsolutePath() + ".png");
            }
            try {
                ImageIO.write(canvas, "PNG", file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Wczytaj obraz");
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                BufferedImage newImage = ImageIO.read(fileChooser.getSelectedFile());
                canvas = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
                graphics2D = canvas.createGraphics();
                graphics2D.setColor(Color.WHITE);
                graphics2D.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                graphics2D.drawImage(newImage, 0, 0, null);
                tempCanvas = new BufferedImage(jPanel1.getWidth(), jPanel1.getHeight(), BufferedImage.TYPE_INT_ARGB);
                tempGraphics2D = tempCanvas.createGraphics();
                tempGraphics2D.drawImage(canvas, 0, 0, null); 
                jPanel1.repaint();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class DrawingPanel extends javax.swing.JPanel {
        @Override
        protected void paintComponent(java.awt.Graphics g) {
            super.paintComponent(g);
            if (canvas != null) {
                g.drawImage(canvas, 0, 0, this);
            }
            if (tempCanvas != null && (drawingShape || drawingPolygon)) {
                g.drawImage(tempCanvas, 0, 0, this);
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new paincikk().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
}