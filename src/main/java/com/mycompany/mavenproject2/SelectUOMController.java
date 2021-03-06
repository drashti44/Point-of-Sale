/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.bson.Document;

/**
 * FXML Controller class
 *
 * @author Third Ev
 */
public class SelectUOMController implements Initializable {
    @FXML ListView List;
    int id;
    Statement stmt;
    MongoClient client = new MongoClient();
    MongoDatabase  db = client.getDatabase("FinalDemo");
    @FXML
    public void handleDeleteButtonAction(ActionEvent a){
      
        /*  String umo=List.getSelectionModel().getSelectedItem().toString();
          System.out.println("selcted item is "+umo);             
          String qry="select id from uom_detail where name='"+umo+"'";
          java.sql.Connection conn=Connection.getConnect();
          stmt = conn.createStatement();
          PreparedStatement pst2=conn.prepareStatement(qry);
          ResultSet rs = pst2.executeQuery(qry);
          while(rs.next()){              
              id= rs.getInt("id");
             System.out.println("selected id is"+id);
         }
          String del="delete from uom_detail where id='"+id+"'";          
          List.getItems().removeAll(umo);         
          stmt.executeUpdate(del);           
          List.getSelectionModel().clearSelection();*/
            String sel=List.getSelectionModel().getSelectedItem().toString();     
        List.getItems().removeAll(sel); 
        db.getCollection("UOMDetail").deleteOne(eq("Name", sel));            
        List.getSelectionModel().clearSelection();
      
    }
    @FXML
   public void handleAddButtonAction(ActionEvent a){
       try {
           Stage stg=new Stage();
           FXMLLoader fxml1=new FXMLLoader(getClass().getResource("/fxml/AddUOM.fxml"));
           Parent root1=(Parent)fxml1.load();
           stg.setScene(new Scene(root1));
           stg.setTitle("Add Unit of Measure");
           stg.show();
       } catch (Exception ex) {
           Logger.getLogger(SelectDeptController.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
            /*String qry="select name from uom_detail";
            java.sql.Connection conn=Connection.getConnect();            
            PreparedStatement pst2=conn.prepareStatement(qry);
            ResultSet rs = pst2.executeQuery(qry);
            while(rs.next()){
                
               List.getItems().addAll(rs.getString("name"));
            }*/
           MongoCursor<Document> cursor4 = db.getCollection("UOMDetail").find().iterator();
            try {
            while (cursor4.hasNext()) {
                
                String rs=cursor4.next().getString("Name");
                 List.getItems().addAll(rs);
            }
        } finally {
            cursor4.close();
        }
        
    }
    }    
    

