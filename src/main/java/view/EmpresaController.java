package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Config.Config.INCLUIR;
import static Config.Config.ALTERAR;
import static Config.Config.EXCLUIR;
import static Config.Config.i18n;
import static Config.DAO.empresaRepository;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Empresa;
import org.controlsfx.control.PopOver;
import org.springframework.data.domain.Sort;
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
    @FXML
    private MaterialDesignIconView btnExcluir;
    @FXML
    private TextField txtFldPesquisar;
            
    
    Empresa empresa;

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void acPesquisar() {
        tblViewEmpresa.setItems(FXCollections.observableList(
                empresaRepository.findByNomeFantasiaLikeIgnoreCaseOrCnpjLikeOrRazaoSocialLikeIgnoreCase(txtFldPesquisar.getText(),txtFldPesquisar.getText(),txtFldPesquisar.getText())));
    }


    @FXML
    private void acLimpar() {
        txtFldPesquisar.setText("");
        tblViewEmpresa.setItems(
                FXCollections.observableList(empresaRepository.findAll(new Sort(new Sort.Order("cnpj")))));
    }
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
        empresa = (Empresa) tblViewEmpresa.getSelectionModel().getSelectedItem();
        acao = EXCLUIR;
        showCRUD();
    }

    @FXML
    private void acAlterar()
    {
        empresa = (Empresa) tblViewEmpresa.getSelectionModel().getSelectedItem();
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
                popOver = new XPopOver(cena, i18n.getString("alterar.empresa.text"), btnAlterar, PopOver.ArrowLocation.TOP_LEFT);
                break;
            case EXCLUIR:
                popOver = new XPopOver(cena, i18n.getString("excluir.empresa.text"), btnExcluir, PopOver.ArrowLocation.TOP_LEFT);
                break;
        }
        CRUDEmpresaController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tblViewEmpresa.setItems(FXCollections.observableArrayList(empresaRepository.findAll()));
        
        btnAlterar.visibleProperty().bind(
                Bindings.isEmpty((tblViewEmpresa.getSelectionModel().getSelectedItems())).not());
        btnExcluir.visibleProperty().bind(btnAlterar.visibleProperty());
    }

}
