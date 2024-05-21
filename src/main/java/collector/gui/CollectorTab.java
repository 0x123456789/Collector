/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package collector.gui;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import javax.swing.*;
import javax.swing.tree.*;

public class CollectorTab extends javax.swing.JFrame {

    private List<TreePath> getExpandedPaths(JTree tree) {
        List<TreePath> expandedPaths = new ArrayList<>();
        Enumeration<TreePath> enumeration = tree.getExpandedDescendants(new TreePath(tree.getModel().getRoot()));
        while (enumeration != null && enumeration.hasMoreElements()) {
            expandedPaths.add(enumeration.nextElement());
        }
        return expandedPaths;
    }

    public void updateTree(String host, String key, String value)
    {
        List<TreePath> expandedPaths = getExpandedPaths(jTree);
        DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        // in real life number of hosts is one project usually small (<10), so it's ok to use O(n)
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(i);
            // found our host
            if (node.toString().equals(host)) {
                for (int j = 0; j < node.getChildCount(); j++) {
                    DefaultMutableTreeNode treeKey = (DefaultMutableTreeNode) node.getChildAt(j);
                    // found our key
                    if (treeKey.toString().equals(key)) {
                        // tree is up-to-date, value was inserted, so just check
                        // if current selected path is updated and if so, reload list
                        if (Objects.equals(jTree.getSelectionPath(), new TreePath(new String[]{host, key}))) {
                            DefaultListModel<String> listModel = (DefaultListModel<String>) jListValues.getModel();
                            listModel.addElement(value);
                        }
                        return;
                    }
                }
                // if we not found this key
                node.add(new DefaultMutableTreeNode(key));
                model.reload();
                // Re-expand the previously expanded paths
                for (TreePath path : expandedPaths) {
                    jTree.expandPath(path);
                }
                return;
            }
        }
        // if we not found this host
        DefaultMutableTreeNode hostNode = new DefaultMutableTreeNode(host);
        hostNode.add(new DefaultMutableTreeNode(key));
        model.insertNodeInto(hostNode, root, root.getChildCount());
        // Re-expand the previously expanded paths
        for (TreePath path : expandedPaths) {
            jTree.expandPath(path);
        }
    }

    //public void addValue(String host, ) {

    //}

    public void updateList(String[] values) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        listModel.addAll(List.of(values));
        jListValues.setModel(listModel);
    }

    public void copyAllListItems() {
        StringBuilder sb = new StringBuilder();
        ListModel<String> model = jListValues.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            sb.append(model.getElementAt(i)).append("\n");
        }
        // Copy the string to the clipboard
        StringSelection stringSelection = new StringSelection(sb.toString().trim());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    public String[] getSelectionPath() {
        TreePath path = jTree.getSelectionPath();
        if (path == null) {
            return new String[0];
        }
        Object[] objArray = path.getPath();
        String[] stringArray = new String[objArray.length];
        for (int i = 0; i < objArray.length; i++) {
            stringArray[i] = objArray[i].toString();
        }
        return stringArray;
    }

    public JTree getTree() {
        return jTree;
    }

    public JButton getCopyListButton() {
        return jButtonCopyList;
    }

    public CollectorTab() {
        // UIManager.put("Tree.expandedIcon", new ImageIcon("icons8-folder-48.png"));
        // UIManager.put("Tree.collapsedIcon", new ImageIcon("icons8-folder-48.png"));


        /*
         * Creates new form CollectorTab
         */

        // int defaultInitialTimeout = ToolTipManager.sharedInstance().getInitialDelay();


        initComponents();
        // initTestData();
        renderData();


        ToolTipManager.sharedInstance().setInitialDelay(1000);
        jSplitPane1.setResizeWeight(0.3);

//        jTree.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                TreePath path = jTree.getSelectionPath();
//                if (path != null) {
//                    DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
//                    // we can get related index this way:
//                    // int selectedNodeIdx = selectedNode.getParent().getIndex(selectedNode);
//
//                    // check if it's leaf
//                    if (!selectedNode.isLeaf()) {
//                        // TODO: clean list here
//                        return;
//                    }
//
//                    // here selected node is leaf, we need get parent (branch name) and get data for list
//                    String branch = selectedNode.getParent().toString();
//                    if (!data.containsKey(branch)) {
//
//                        // TODO: must never occurred, log runtime error here
//                        return;
//                    }
//
//                    data.get(branch).forEach((key, value) -> {
//                        if (key.equals(selectedNode.toString())) {
//                            DefaultListModel<String> listModel = new DefaultListModel<>();
//                            listModel.addAll(List.of(value));
//                            jListValues.setModel(listModel);
//                        }
//                    });
//                }
//                super.mouseClicked(e);
//            }
//        });
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
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTree = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListValues = new javax.swing.JList<>();
        jButtonCopyList = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 588, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jLabel1.setText("Collected Data");

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setDividerSize(10);
        jSplitPane1.setToolTipText("");

        jScrollPane1.setViewportView(jTree);

        jSplitPane1.setLeftComponent(jScrollPane1);

        jListValues.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jListValues);

        jSplitPane1.setRightComponent(jScrollPane2);

        jButtonCopyList.setText("Copy List");
        jButtonCopyList.setToolTipText("Copy all list items");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonCopyList, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(212, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCopyList)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 113, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(CollectorTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CollectorTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CollectorTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CollectorTab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CollectorTab().setVisible(true);
            }
        });
    }



    private void renderData() {
        // creating root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        // add leafs
//        data.forEach((key, value) -> {
//            DefaultMutableTreeNode branch = new DefaultMutableTreeNode(key);
//            // adding all child leafs
//            value.forEach((kk, vv) -> {
//                branch.add(new DefaultMutableTreeNode(kk));
//            });
//            // connect branch to root
//            root.add(branch);
//        });

        jTree.setModel(new DefaultTreeModel(root));
        // TODO:
        // jTree.setSelectionPath(new TreePath(root.getNextNode().getPath()));

        // https://stackoverflow.com/questions/3407987/jtree-set-handle-visible-on-first-node-level-when-root-is-not-displayed
        jTree.setShowsRootHandles(true);
        jTree.expandRow(0);
        jTree.setRootVisible(false);

        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        ImageIcon icon = createIcon("/icons8-folder-48.png");
        ImageIcon scaled = new ImageIcon(icon.getImage().getScaledInstance(15, 15, Image.SCALE_DEFAULT));

        renderer.setLeafIcon(scaled);
        jTree.setCellRenderer(renderer);

      //  jTree.
    }

    private ImageIcon createIcon(String path) {
        URL url = this.getClass().getResource(path);
        if (url == null) {
            // TODO:
            System.out.println("XXXXXXXXXX");
        }

        ImageIcon icon = new ImageIcon(url);
        return icon;
    }

    public javax.swing.JPanel getUI() {
        return this.jPanel1;
    }


//    private static DefaultMutableTreeNode findNodeByName(DefaultMutableTreeNode root, String name) {
//        if (root.getUserObject().toString().equals(name)) {
//            return root;
//        }
//
//        for (int i = 0; i < root.getChildCount(); i++) {
//            DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(i);
//            DefaultMutableTreeNode found = findNodeByName(child, name);
//            if (found != null) {
//                return found;
//            }
//        }
//
//        return null;
//    }

//    public void addHostNode(String host) {
//        DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
//        DefaultMutableTreeNode node = new DefaultMutableTreeNode(host);
//        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
//        model.insertNodeInto(node, root, root.getChildCount());
//    }


    private HashMap<String, HashMap<String, String[]>> data;

    private void initTestData() {
        data = new HashMap<String, HashMap<String,String[]>>() {{
            put("https://example.com", new HashMap<>() {
                { put("userId", new String[]{"15", "24", "555"}); put("roomId", new String[]{"99", "1234"});}});
            put("https://nano.example.com", new HashMap<>() {
                { put("id", new String[]{"44", "64", "3434"}); put("externalId", new String[]{"222", "944"});}});
        }};

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCopyList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<String> jListValues;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTree jTree;
    // End of variables declaration//GEN-END:variables
}
