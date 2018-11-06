package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Config.Config.ALTERAR;
import static Config.Config.EXCLUIR;
import static Config.Config.INCLUIR;
import static Config.Config.i18n;
import static Config.DAO.cidadeRepository;
import static Config.DAO.empresaRepository;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Cidade;
import model.Empresa;
import org.controlsfx.control.PopOver;
import org.springframework.data.domain.Sort;
import utility.XPopOver;

/**
 * FXML Controller class
 *
 * @author Elizelton
 */
public class CRUDEmpresaController implements Initializable
{
    char acao;
    protected Cidade cidade;
    
    private EmpresaController controllerPai;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button btnConfirma;
    @FXML
    private TextField txtFldCNPJ;
    @FXML
    private TextField txtFldRazaoSocial;
    @FXML
    private TextField txtFldNomeFantasia;
    @FXML
    private TextField txtFldObservacao;
    @FXML
    protected ComboBox cmbBoxCidade;
    @FXML
    private MaterialDesignIconView btnIncluir;
    @FXML
    private MaterialDesignIconView btnAlterar;
    @FXML
    private void btnCancelaClick()
    {
        anchorPane.getScene().getWindow().hide();
//        controllerPai.tblViewAlunos.requestFocus();
    }
    
    @FXML
    private void acIncluir()
    {
        acao = INCLUIR;
        cidade = new Cidade();
        showCRUD();
    }
    
    @FXML
    private void acAlterar()
    {
        cidade = (Cidade) cmbBoxCidade.getSelectionModel().getSelectedItem();
        acao = ALTERAR;
        showCRUD();
    }
    
    private void showCRUD()
    {
        String cena = "/fxml/CRUDCidade.fxml";
        XPopOver popOver = null;

        switch (acao)
        {
            case INCLUIR:
                popOver = new XPopOver(cena, i18n.getString("incluir.cidade.text"), btnIncluir, PopOver.ArrowLocation.TOP_LEFT);
                break;
            case ALTERAR:
                popOver = new XPopOver(cena, i18n.getString("alterar.cidade.text"), btnAlterar, PopOver.ArrowLocation.TOP_LEFT);
                break;
        }
        CRUDCidadeController controllerFilho = popOver.getLoader().getController();
        controllerFilho.setCadastroController(this);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        btnConfirma.disableProperty().bind(txtFldCNPJ.textProperty().isEmpty().
                or(txtFldNomeFantasia.textProperty().isEmpty()).or(txtFldObservacao.textProperty().isEmpty()).or(txtFldRazaoSocial.textProperty().isEmpty()).or(cmbBoxCidade.getSelectionModel().selectedItemProperty().isNull()));
    }

    void setCadastroController(EmpresaController controllerPai)
    {
        cmbBoxCidade.setItems(FXCollections.observableArrayList(cidadeRepository.findAll(new Sort(new Sort.Order("nome")))));
        this.controllerPai = controllerPai;
        
        txtFldCNPJ.setText(controllerPai.empresa.getCnpj());
        txtFldNomeFantasia.setText(controllerPai.empresa.getNomeFantasia());
        txtFldRazaoSocial.setText(controllerPai.empresa.getRazaoSocial());
        txtFldObservacao.setText(controllerPai.empresa.getObservacao());
        cmbBoxCidade.setValue(controllerPai.empresa.getCidade());

        txtFldCNPJ.setDisable(controllerPai.acao == EXCLUIR);
        txtFldNomeFantasia.setDisable(controllerPai.acao == EXCLUIR);
        txtFldRazaoSocial.setDisable(controllerPai.acao == EXCLUIR);
        txtFldObservacao.setDisable(controllerPai.acao == EXCLUIR);
        
    }

    @FXML
    private void btnConfirmaClick()
    {
        controllerPai.empresa.setCnpj(txtFldCNPJ.getText());
        controllerPai.empresa.setNomeFantasia(txtFldNomeFantasia.getText());
        controllerPai.empresa.setRazaoSocial(txtFldRazaoSocial.getText());
        controllerPai.empresa.setCidade((Cidade) cmbBoxCidade.getSelectionModel().getSelectedItem());

        try
        {
            switch (controllerPai.acao)
            {
                case INCLUIR:
                    empresaRepository.insert(controllerPai.empresa);
                    break;
                case ALTERAR:
                    empresaRepository.save(controllerPai.empresa);
                    break;
                case EXCLUIR:
                    empresaRepository.delete(controllerPai.empresa);
                    break;
            }
            controllerPai.tblViewEmpresa.setItems(
                    FXCollections.observableList(empresaRepository.findAll(
                            new Sort(new Sort.Order("nome")))));
            controllerPai.tblViewEmpresa.refresh();
            controllerPai.tblViewEmpresa.getSelectionModel().clearSelection();
            controllerPai.tblViewEmpresa.getSelectionModel().select(controllerPai.empresa);
            anchorPane.getScene().getWindow().hide();
        } catch (Exception e)
        {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(i18n.getString("erro.text"));
            alert.setHeaderText(i18n.getString("erro.empresa.header.text"));
            if (e.getMessage().contains("duplicate key")) {
                alert.setContentText(i18n.getString("erro.empresa.text"));
            } else {
                alert.setContentText(e.getMessage());
            }
            alert.showAndWait();
        }
    }

}
