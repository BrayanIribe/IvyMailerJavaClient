package client;


import IvyMailer.Api;
import IvyMailer.Model.Client;
import IvyMailer.Response.GetClientsResponse;
import IvyMailer.Response.SettingsResponse;
import IvyMailer.Response.TokenResponse;
import client.MainUI;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lv1822
 */
public class MainUIController {
    
    private Api api;
    private SettingsResponse settings;
    private GetClientsResponse clients;
    private MainUI form;
    
    public MainUIController(MainUI form){
        this.form = form;
        try {
            api = new Api();
            form.getToken().setText("ZBAQ5iHy6RejJno87TkEkQ5xfWGLzkPTFeL");
            api.setToken(form.getToken().getText());
            settings = api.getSettings();
            this.SearchAction();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, e, "Server Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void SearchAction() {
        try {
            clients = api.getClients(null, 1);
            DefaultTableModel model = (DefaultTableModel) form.getClientsTable().getModel();
            model.setRowCount(0);
            if (clients.items.length > 0) {
                for (Client client : clients.items) {
                    model.addRow(new String[]{
                        client.id,
                        client.name,
                        client.group_name,
                        client.phone,
                        client.mails.isEmpty() ? null : client.mails.get(0).getData()
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, e, "Server Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void CheckTokenAction(){
        TokenResponse tokenInfo;
        // 1) Check token
        api.setToken(form.getToken().getText());
        try {
            tokenInfo = api.checkToken();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Server Error", JOptionPane.ERROR_MESSAGE);
    }
    }
}
