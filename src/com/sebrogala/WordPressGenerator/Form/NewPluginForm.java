package com.sebrogala.WordPressGenerator.Form;

import com.intellij.openapi.ui.Messages;
import com.sebrogala.WordPressGenerator.ViewModel.NewPluginViewModel;

import javax.swing.*;
import java.awt.event.*;

public class NewPluginForm extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField pluginName;
    private JTextField version;
    private JTextField author;
    private JTextField description;
    private JCheckBox addAdminCheckBox;
    private JCheckBox addPublicCheckBox;
    private JCheckBox addAPICheckBox;

    public NewPluginForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!formIsValid()) {
                    Messages.showInfoMessage("All fields are required and cannot be empty", "Empty Input");
                } else {
                    dispose();
                }
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private boolean formIsValid() {
        if("".equals(pluginName.getText())) {
            return false;
        }

        if("".equals(version.getText())) {
            return false;
        }

        if("".equals(author.getText())) {
            return false;
        }

        if("".equals(description.getText())) {
            return false;
        }

        return true;
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static NewPluginViewModel run(String title) {
        NewPluginForm dialog = new NewPluginForm();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setTitle(title);
        dialog.setVisible(true);

        return new NewPluginViewModel(
                dialog.pluginName.getText(),
                dialog.version.getText(),
                dialog.author.getText(),
                dialog.description.getText(),
                dialog.addAdminCheckBox.isSelected(),
                dialog.addAPICheckBox.isSelected(),
                dialog.addPublicCheckBox.isSelected()
        );
    }

}
