package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author elizelton.santos
 */
public class MesEmpresaController implements Initializable
{

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView tblViewTotal;

    @FXML
    private void acImportar()
    {

    }

    @FXML
    private void acNaoSeiOqFaz()
    {

    }

    @FXML
    private void acRemover()
    {

    }

    @FXML
    private void acAlterar()
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tblViewTotal.getStyleClass().add("noheader");

    }

}
