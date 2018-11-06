package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Config.Config.INCLUIR;
import static Config.Config.ALTERAR;
import static Config.Config.i18n;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import model.Empresa;
import org.controlsfx.control.PopOver;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author elizelton.santos
 */
public class EmpresaController implements Initializable
{

    @FXML
     TableView tblViewEmpresa;
    char acao;
    @FXML
    private MaterialDesignIconView btnIncluir;
    @FXML
    private MaterialDesignIconView btnAlterar;
    Empresa empresa;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void acIncluir()
    {
        acao = INCLUIR;
        empresa = new Empresa();
        showCRUD();
    }

    @FXML
    private void acExcluir()
    {

    }

    @FXML
    private void acAlterar()
    {
        acao = ALTERAR;
        showCRUD();
    }

    private void showCRUD()
    {
        String cena = "/fxml/CRUDEmpresa.fxml";
        XPopOver popOver = null;

        switch (acao)
        {
            case INCLUIR:
                popOver = new XPopOver(cena, i18n.getString("incluir.empresa.text"), btnIncluir, PopOver.ArrowLocation.TOP_LEFT);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, "alterar.empresa.text", btnAlterar, PopOver.ArrowLocation.TOP_LEFT);
                break;
        }
        CRUDEmpresaController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

}
