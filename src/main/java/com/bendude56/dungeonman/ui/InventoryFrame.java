package com.bendude56.dungeonman.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ScrollPaneConstants;

import com.bendude56.dungeonman.GameInstance;
import com.bendude56.dungeonman.entity.EntityDroppedItem;
import com.bendude56.dungeonman.entity.EntityPlayer;
import com.bendude56.dungeonman.item.ItemIdentifiable;
import com.bendude56.dungeonman.item.ItemStack;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

/**
 * @author Benjamin C. Thomas
 */

public class InventoryFrame extends JFrame {
    private static final long serialVersionUID = 0L;
    
    private JPanel contentPane;
    private JList<InventoryItem> list;
    private JButton useButton;
    private JButton dropButton;
    private JButton identifyButton;
    
    /**
     * Create the frame.
     */
    public InventoryFrame() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                GameFrame.activeFrame.activeInventory = null;
            }
        });
        setAlwaysOnTop(true);
        setTitle("Inventory");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 281, 421);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(1.0);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        contentPane.add(splitPane, BorderLayout.CENTER);
        
        JPanel panel = new JPanel();
        splitPane.setRightComponent(panel);
        
        useButton = new JButton("Use");
        useButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemStack i = list.getSelectedValue().i;
                
                if (i.getItem().onUsed(i, GameInstance.getActiveInstance().getPlayerEntity())) {
                    if (i.getAmount() == 1) {
                        GameInstance.getActiveInstance().getPlayerEntity().getInventory().removeItem(i);
                    } else {
                        i.setAmount(i.getAmount() - 1);
                    }
                    
                    GameFrame.activeFrame.doTurn();
                    GameFrame.activeFrame.render();
                }
            }
        });
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        useButton.setEnabled(false);
        panel.add(useButton);
        
        dropButton = new JButton("Drop");
        dropButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemStack i = list.getSelectedValue().i;
                
                GameInstance.getActiveWorld().addEntity(
                        new EntityDroppedItem(GameInstance.getActiveInstance().getPlayerEntity().getLocation(), i));
                GameInstance.getActiveInstance().getPlayerEntity().getInventory().removeItem(i);
                
                GameFrame.activeFrame.doTurn();
                GameFrame.activeFrame.render();
            }
        });
        dropButton.setEnabled(false);
        panel.add(dropButton);
        
        identifyButton = new JButton("Identify");
        identifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemStack i = list.getSelectedValue().i;
                
                if (i.getItem() instanceof ItemIdentifiable) {
                    ((ItemIdentifiable) i.getItem()).tryIdentify(GameInstance.getActiveInstance().getPlayerEntity());
                    GameFrame.activeFrame.doTurn();
                    GameFrame.activeFrame.render();
                } else {
                    GameFrame.activeFrame.logMessage("That item cannot be identified!");
                }
            }
        });
        identifyButton.setEnabled(false);
        panel.add(identifyButton);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        splitPane.setLeftComponent(scrollPane);
        
        list = new JList<InventoryItem>();
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (list.getSelectedIndex() == -1) {
                    useButton.setEnabled(false);
                    dropButton.setEnabled(false);
                    identifyButton.setEnabled(false);
                } else {
                    useButton.setEnabled(list.getSelectedValue().i.getItem().canUse(list.getSelectedValue().i,
                            GameInstance.getActiveInstance().getPlayerEntity()));
                    dropButton.setEnabled(true);
                    identifyButton.setEnabled(list.getSelectedValue().i.getItem() instanceof ItemIdentifiable
                            && !GameInstance.getActiveInstance().isItemIdentified(list.getSelectedValue().i.getItem()));
                }
            }
        });
        scrollPane.setViewportView(list);
        
        doUpdate();
    }
    
    public void doUpdate() {
        EntityPlayer p = GameInstance.getActiveInstance().getPlayerEntity();
        InventoryItem[] items = new InventoryItem[p.getInventory().getItems().size()];
        
        for (int i = 0; i < items.length; i++) {
            items[i] = new InventoryItem(p.getInventory().getItems().get(i));
        }
        
        list.setListData(items);
    }
    
    private static class InventoryItem {
        public ItemStack i;
        
        public InventoryItem(ItemStack i) {
            this.i = i;
        }
        
        @Override
        public String toString() {
            return i.getItem().getItemName(i) + " (x" + i.getAmount() + ")";
        }
    }
    
}
