package com.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.metal.MetalTabbedPaneUI;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.interpolation.PropertySetter;

public class SwingTabbedPane extends JTabbedPane{

    public SwingTabbedPane() {
        setUI(new danhSachUI());
    }
    
    public class danhSachUI extends MetalTabbedPaneUI {
        
        public void setCurrectangle(Rectangle currectangle) {
            this.currectangle = currectangle;
            repaint();
        }
        
        private Animator animator;
        private Rectangle currectangle;
        private TimingTarget target;
        
        public danhSachUI() {

        }
        
        

        @Override
        public void installUI(JComponent c) {
            super.installUI(c);
            animator = new Animator(500);
            animator.setResolution(0);
            animator.setAcceleration(.5f);
            animator.setDeceleration(.5f);
            tabPane.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    int selectted = tabPane.getSelectedIndex();
                    if (selectted != -1) {
                        if (currectangle != null) {
                            if (animator.isRunning()) {
                                animator.stop();
                            }
                            animator.removeTarget(target);
                            target = new PropertySetter(danhSachUI.this, "currectangle", currectangle, getTabBounds(selectted, calcRect));
                            animator.addTarget(target);
                            animator.start();
                        }
                    }
                }
            });
        }

        @Override
        protected Insets getTabInsets(int tabPlacement, int tabIndex) {
            return new Insets(10, 10, 10, 10);
        }

        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(3, 155, 216));
            if (currectangle == null || !animator.isRunning()) {
                if (isSelected) {
//                    setCurrectangle(new Rectangle(x, y, w, h));
                    currectangle = new Rectangle(x, y, w, h);
                }
            }
            if (currectangle != null) {
                g2.fillRect(currectangle.x, currectangle.y + currectangle.height - 3, currectangle.width, 3);
            }
            g2.dispose();
        }

        @Override
        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {

        }

        @Override
        protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {

        }

    }
}
