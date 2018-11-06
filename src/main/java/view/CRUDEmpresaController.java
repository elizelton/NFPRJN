package view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static Config.Config.ALTERAR;
import static Config.Config.EXCLUIR;
import static Config.Config.INCLUIR;
import static Config.DAO.cidadeRepository;
import static Config.DAO.empresaRepository;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.Cidade;
import org.springframework.data.domain.Sort;

/**
 * FXML Controller class
 *
 * @author Elizelton
 */
public class CRUDEmpresaController implements Initializable
{

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
    private ComboBox cmbBoxCidade;

    @FXML
    private void btnCancelaClick()
    {
        anchorPane.getScene().getWindow().hide();
//        controllerPai.tblViewAlunos.requestFocus();
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
//            controllerPai.tblViewAlunos.refresh();
//            controllerPai.tblViewAlunos.getSelectionModel().clearSelection();
//            controllerPai.tblViewAlunos.getSelectionModel().select(controllerPai.aluno);
            anchorPane.getScene().getWindow().hide();
        } catch (Exception e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Cadastro de Disciplina");
            if (e.getMessage().contains("duplicate key"))
            {
                alert.setContentText("Código já cadastrado");
            } else
            {
                alert.setContentText(e.getMessage());
            }
            alert.showAndWait();
        }
    }

}
