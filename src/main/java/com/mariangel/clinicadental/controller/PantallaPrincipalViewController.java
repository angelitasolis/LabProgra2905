package com.mariangel.clinicadental.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.clinicadental.model.PacientesDto;
import com.mariangel.clinicadental.service.PacientesService;
import com.mariangel.clinicadental.util.Formato;
import com.mariangel.clinicadental.util.Mensaje;
import com.mariangel.clinicadental.util.Respuesta;
import java.awt.Desktop.Action;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Mariangel
 */
public class PantallaPrincipalViewController extends Controller implements Initializable {

    @FXML
    private Button btnBuscarPaciente;

    @FXML
    private Button btnBuscarRegistrarCita;

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnEliminarPaciente;

    @FXML
    private Button btnGuardarPaciente;

    @FXML
    private Button btnRegistrar;

    @FXML
    private DatePicker datePickerFecNacPaciente;

    @FXML
    private DatePicker datePickerFechaRegistrarCita;

    @FXML
    private Tab tapPaneInfoPacientes;

    @FXML
    private Tab tapPanePaciente;

    @FXML
    private Tab tapPaneRegistarCita;

    @FXML
    private TableColumn<PacientesDto, String> tblvCedulaInfoPacientes;

    @FXML
    private TableColumn<PacientesDto, ?> tblvDireccionInfoPacientes;

    @FXML
    private TableColumn<?, ?> tblvEdadInfoPacientes;

    @FXML
    private TableColumn<?, ?> tblvFechaInfoPacientes;

    @FXML
    private TableColumn<?, ?> tblvFechaRegistrarCita;

    @FXML
    private TableColumn<?, ?> tblvHoraRegistrarCita;

    @FXML
    private TableColumn<?, ?> tblvNombreInfoPacientes;

    @FXML
    private TableColumn<?, ?> tblvPrimerApellidoInfoPacientes;

    @FXML
    private TableColumn<?, ?> tblvSegundoApellidoInfoPacientes;

    @FXML
    private TextField txtCedulaPaciente;

    @FXML
    private TextField txtCedulaRegistrarCita;

    @FXML
    private TextArea txtDireccionPaciente;

    @FXML
    private TextField txtHoraRegistrarCita;

    @FXML
    private TextField txtNombrePaciente;

    @FXML
    private Label txtNombreRegistrarCita;

    @FXML
    private TextField txtPrimerApellidoPaciente;

    @FXML
    private Label txtPrimerApellidoRegistrarCita;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtSegundoApellidoPaciente;

    @FXML
    private Label txtSegundoApellidoRegistrarCita;

    /**
     * Initializes the controller class.
     */
    // CitasDto cita;
    PacientesDto paciente;
    List<Node> requeridos = new ArrayList<>();
    @FXML
    private Button btnModificarPaciente;
    @FXML
    private TableView<?> tblvInformacionPacientes;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        txtNombrePaciente.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPrimerApellidoPaciente.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtSegundoApellidoPaciente.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCedulaPaciente.setTextFormatter(Formato.getInstance().integerFormat());
        txtDireccionPaciente.setTextFormatter(Formato.getInstance().letrasFormat(150));
        //  txtHoraRegistrarCita.setTextFormatter(Formato.getInstance().integerFormat());
        paciente = new PacientesDto();
        nuevoPaciente();
        indicarRequeridos();
    }

    public void indicarRequeridos() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombrePaciente, txtPrimerApellidoPaciente, txtSegundoApellidoPaciente, txtCedulaPaciente, txtDireccionPaciente, txtHoraRegistrarCita, datePickerFecNacPaciente));
    }

    private void bindPaciente(Boolean nuevo) {
        if (!nuevo) {
            txtCedulaPaciente.textProperty().bindBidirectional(paciente.pacCedula);
        }
        txtCedulaPaciente.textProperty().bindBidirectional(paciente.pacCedula);
        txtNombrePaciente.textProperty().bindBidirectional(paciente.pacNombre);
        txtPrimerApellidoPaciente.textProperty().bindBidirectional(paciente.pacPapellido);
        txtSegundoApellidoPaciente.textProperty().bindBidirectional(paciente.pacSapellido);
        txtDireccionPaciente.textProperty().bindBidirectional(paciente.pacDirec);
        // txtHoraRegistrarCita.textProperty().bindBidirectional(cita.citaHora);
        // datePickerFechaRegistrarCita.valueProperty().bindBidirectional(cita.citaDia);
        datePickerFecNacPaciente.valueProperty().bindBidirectional(paciente.pacFecnac);
    }

    private void unbindPaciente() {

        txtCedulaPaciente.textProperty().unbindBidirectional(paciente.pacCedula);
        txtNombrePaciente.textProperty().unbindBidirectional(paciente.pacNombre);
        txtPrimerApellidoPaciente.textProperty().unbindBidirectional(paciente.pacPapellido);
        txtSegundoApellidoPaciente.textProperty().unbindBidirectional(paciente.pacSapellido);
        txtDireccionPaciente.textProperty().bindBidirectional(paciente.pacDirec);
//        datePickerFechaRegistrarCita.valueProperty().unbindBidirectional(cita.citaDia);
        //    txtHoraRegistrarCita.textProperty().bindBidirectional(cita.citaHora);
        datePickerFecNacPaciente.valueProperty().unbindBidirectional(paciente.pacFecnac);
    }

    private void nuevoPaciente() {
        unbindPaciente();
        paciente = new PacientesDto();
        bindPaciente(true);
        txtCedulaPaciente.clear();
        txtCedulaPaciente.requestFocus();
    }


    private void cargarPaciente(Long pcedula) {
        PacientesService service = new PacientesService();
        Respuesta respuesta = service.getPaciente(pcedula);
        System.out.println("Paciente antes del unbind" +pcedula);
        if (respuesta.getEstado()) {
            unbindPaciente();
            System.out.println("despues del unbind" );
            paciente = (PacientesDto) respuesta.getResultado("Pacientes");
            
            bindPaciente(false);
            System.out.println("Paciente despues del bind" +pcedula);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar paciente", getStage(), respuesta.getMensaje());
        }

    }

    public String validarRequeridos() {
        Boolean validos = true;
        String invalidos = "";
        for (Node node : requeridos) {
            if (node instanceof JFXTextField && !((JFXTextField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXTextField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXTextField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXPasswordField && !((JFXPasswordField) node).validate()) {
                if (validos) {
                    invalidos += ((JFXPasswordField) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXPasswordField) node).getPromptText();
                }
                validos = false;
            } else if (node instanceof JFXDatePicker && ((JFXDatePicker) node).getValue() == null) {
                if (validos) {
                    invalidos += ((JFXDatePicker) node).getAccessibleText();
                } else {
                    invalidos += "," + ((JFXDatePicker) node).getAccessibleText();
                }
                validos = false;
            } else if (node instanceof JFXComboBox && ((JFXComboBox) node).getSelectionModel().getSelectedIndex() < 0) {
                if (validos) {
                    invalidos += ((JFXComboBox) node).getPromptText();
                } else {
                    invalidos += "," + ((JFXComboBox) node).getPromptText();
                }
                validos = false;
            }
        }
        if (validos) {
            return "";
        } else {
            return "Campos requeridos o con problemas de formato [" + invalidos + "].";
        }
    }

    @Override
    public void initialize() {

    }

    @FXML
    private void onAnctionGuardarPaciente(ActionEvent event) {
        System.out.println("Hola");
        try {
            String invalidos = validarRequeridos();

            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar paciente", getStage(), invalidos);
            } else {
                PacientesService pacienteService = new PacientesService();
                System.out.println(paciente);
                Respuesta respuesta = pacienteService.guardarPaciente(paciente);

                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar paciente", getStage(), respuesta.getMensaje());
                } else {
                    unbindPaciente();
                    paciente = (PacientesDto) respuesta.getResultado("Pacientes");
                    System.out.println("aqui estoy" + paciente.toString());
                    bindPaciente(false);

                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", getStage(), "Empresa guardada correctamente.");

                }
            }
        } catch (Exception ex) {

            Logger.getLogger(PantallaPrincipalViewController.class.getName()).log(Level.SEVERE, "Error guardando el paciente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar paciente", getStage(), "Ocurrio un error guardando el paciente.");
        }
    }

    @FXML
    private void onActionBtnModificar(ActionEvent event) {

        try {
            unbindPaciente();
            String cedulaText = txtCedulaPaciente.getText();
            long cedula = Long.parseLong(cedulaText);
            PacientesDto pacientesDto = new PacientesDto();
            pacientesDto.setCedula(Long.parseLong(txtCedulaPaciente.getText()));
            pacientesDto.setPacNombre(txtNombrePaciente.getText());
            pacientesDto.setPacPApellido(txtPrimerApellidoPaciente.getText());
            pacientesDto.setPacSApellido(txtSegundoApellidoPaciente.getText());
            pacientesDto.setPacDirec(txtDireccionPaciente.getText());
            pacientesDto.setPacFecnac(datePickerFecNacPaciente.getValue());

            PacientesService pacientesService = new PacientesService();
            Respuesta respuesta = pacientesService.modificarPaciente(pacientesDto, cedula);
            new Mensaje().showModal(Alert.AlertType.INFORMATION, "Actualizar paciente", getStage(), "Paciente actualizado correctamente.");
            
        } catch (Exception ex) {
            Logger.getLogger(PantallaPrincipalViewController.class.getName()).log(Level.SEVERE, "Error actualizando el Paciente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Actualizar Paciente", getStage(), "Ocurrio un error al actualizar el Paciente.");
        }

    }

    @FXML
    private void onActionEliminarPaciente(ActionEvent event) {
        try {
            if (paciente.getPacCedula() == null) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Paciente", getStage(), "Debe cargar el tipo de Paciente que desea eliminar.");
            } else {

                PacientesService service = new PacientesService();
                Respuesta respuesta = service.eliminarPaciente(paciente.getPacCedula());
                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Paciente", getStage(), respuesta.getMensaje());
                } else {
                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Eliminar Paciente", getStage(), "Paciente eliminado correctamente.");
                    nuevoPaciente();
                    
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PantallaPrincipalViewController.class.getName()).log(Level.SEVERE, "Error eliminando el Paciente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Paciente", getStage(), "Ocurrio un error eliminando el Paciente.");
        }
    }

    @FXML
    private void onActionBuscarPaciente(ActionEvent event) {
        String cedulaText = txtCedulaPaciente.getText();
        Long cedula = Long.parseLong(cedulaText);
        cargarPaciente(cedula);
    }
    

}
