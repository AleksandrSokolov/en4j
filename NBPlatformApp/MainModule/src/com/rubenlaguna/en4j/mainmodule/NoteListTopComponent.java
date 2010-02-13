/*
 *  Copyright (C) 2010 Ruben Laguna <ruben.laguna@gmail.com>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.rubenlaguna.en4j.mainmodule;

import java.beans.PropertyChangeEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Property;
import org.jdesktop.observablecollections.ObservableCollections;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor.Task;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
//import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.util.Lookup;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import com.rubenlaguna.en4j.interfaces.NoteFinder;
import com.rubenlaguna.en4j.interfaces.NoteRepository;
import com.rubenlaguna.en4j.noteinterface.Note;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.openide.util.RequestProcessor;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.rubenlaguna.en4j.mainmodule//NoteList//EN",
autostore = false)
public final class NoteListTopComponent extends TopComponent implements ListSelectionListener, PropertyChangeListener {

    private Logger LOG = Logger.getLogger(NoteListTopComponent.class.getName());
    private static final RequestProcessor RP = new RequestProcessor("refresh tasks", 1);
    private static NoteListTopComponent instance;
    /** path to the icon used by the component and its open action */
//    static final String ICON_PATH = "SET/PATH/TO/ICON/HERE";
    private static final String PREFERRED_ID = "NoteListTopComponent";
    private final InstanceContent ic = new InstanceContent();
    private RequestProcessor.Task currentSearchTask = null;
    private RequestProcessor.Task currentRefreshTask = null;

    public NoteListTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(NoteListTopComponent.class, "CTL_NoteListTopComponent"));
        setToolTipText(NbBundle.getMessage(NoteListTopComponent.class, "HINT_NoteListTopComponent"));
//        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        associateLookup(new AbstractLookup(ic));
        jTable1.getSelectionModel().addListSelectionListener(this);
        NoteRepository rep = Lookup.getDefault().lookup(NoteRepository.class);
        rep.addPropertyChangeListener(this);
    }

    public void refresh() {
        LOG.info("refresh notelist " + new SimpleDateFormat("h:mm:ss a").format(new Date()));
        try {
            final NoteRepository rep = Lookup.getDefault().lookup(NoteRepository.class);
            final Collection<Note> allNotes = rep.getAllNotes();
            SwingUtilities.invokeAndWait(new Runnable() {

                @Override
                public void run() {
                    list1.clear();
                    list1.addAll(allNotes);
                }
            });
        } catch (InterruptedException ex) {
            //do nothing
        } catch (InvocationTargetException ex) {
            Exceptions.printStackTrace(ex);
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        list1 = getList();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jTable1.setColumnSelectionAllowed(true);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list1, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${title}"));
        columnBinding.setColumnName("Title");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(NoteListTopComponent.class, "NoteListTopComponent.jTable1.columnModel.title0_2")); // NOI18N

        searchTextField.setText(org.openide.util.NbBundle.getMessage(NoteListTopComponent.class, "NoteListTopComponent.searchTextField.text")); // NOI18N
        searchTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchTextFieldFocusLost(evt);
            }
        });
        searchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchTextFieldKeyTyped(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(NoteListTopComponent.class, "NoteListTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        performSearch();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void performSearch() {
        SwingWorker<Collection<Note>, Object> sw = new SwingWorker<Collection<Note>, Object>() {

            @Override
            protected Collection<Note> doInBackground() throws Exception {
                if (searchTextField.getText().trim().isEmpty()) {
                    return getList();
                }
                NoteFinder finder = Lookup.getDefault().lookup(NoteFinder.class);

                Collection<Note> list = finder.find(searchTextField.getText());
                return list;
            }

            @Override
            protected void done() {
                try {
                    Collection<Note> list = get();
                    list1.clear();
                    list1.addAll(list);
                } catch (InterruptedException ex) {
                    Exceptions.printStackTrace(ex);
                } catch (ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        };
        currentSearchTask = RP.post(sw, 500);
    }

    private void searchTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusGained
        // TODO add your handling code here:
        String initialText = org.openide.util.NbBundle.getMessage(NoteListTopComponent.class, "NoteListTopComponent.searchTextField.text");
        if (initialText.equals(searchTextField.getText())) {
            searchTextField.setText("");
        }
    }//GEN-LAST:event_searchTextFieldFocusGained

    private void searchTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchTextFieldFocusLost
        // TODO add your handling code here:
        if ("".equals(searchTextField.getText())) {
            searchTextField.setText(org.openide.util.NbBundle.getMessage(NoteListTopComponent.class, "NoteListTopComponent.searchTextField.text"));
        }
    }//GEN-LAST:event_searchTextFieldFocusLost

    private void searchTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextFieldKeyTyped
        // TODO add your handling code here:
        if (null != currentSearchTask) {
            currentSearchTask.cancel();
        }
        performSearch();
    }//GEN-LAST:event_searchTextFieldKeyTyped
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.util.List<Note> list1;
    public final javax.swing.JTextField searchTextField = new javax.swing.JTextField();
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized NoteListTopComponent getDefault() {
        if (instance == null) {
            instance = new NoteListTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the NoteListTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized NoteListTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(NoteListTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof NoteListTopComponent) {
            return (NoteListTopComponent) win;
        }
        Logger.getLogger(NoteListTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    @Override
    protected void componentActivated() {
        searchTextField.requestFocusInWindow();
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        NoteListTopComponent singleton = NoteListTopComponent.getDefault();
        singleton.readPropertiesImpl(p);
        return singleton;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    private List<Note> getList() {
        List<Note> toReturn = ObservableCollections.observableList(new ArrayList<Note>());
        NoteRepository rep = Lookup.getDefault().lookup(NoteRepository.class);
        toReturn.addAll(rep.getAllNotes());
        Logger logger = Logger.getLogger(NoteListTopComponent.class.getName());
        logger.log(Level.INFO, "number of entries in db:" + toReturn.size());
        return toReturn;
    }

    @Override
    public void valueChanged(ListSelectionEvent arg0) {
        if (!arg0.getValueIsAdjusting()) {
//            int sr = jTable1.getSelectedRow();
            Property<JTable,Object> p = BeanProperty.create("selectedElement");
            Object value = p.getValue(jTable1);

            //TODO clear this, move ic.set outside the if else block 
            if (value != null) {
                ic.set(Collections.singleton(value), null);
                Logger.getLogger(getName()).log(Level.WARNING, "selection changed: " + p.getValue(jTable1).toString());

            } else {
                ic.set(Collections.emptySet(), null);
                Logger.getLogger(getName()).log(Level.WARNING, "selection changed: nothing selected");
            }




        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (null != currentRefreshTask) {
            currentRefreshTask.cancel();//cancel the last refresh so we only
            //two refresh task at a given moment
        }
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                NoteListTopComponent.this.refresh();
                try {
                    Thread.sleep(5000); //no more than 1 refresh every two seconds
                } catch (InterruptedException ex) {
                    //do nothing
                }
            }
        };
        currentRefreshTask = RP.post(runnable);
    }
}
