package com.mariangel.clinicadental.controller;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.mariangel.clinicadental.model.Citas;
import com.mariangel.clinicadental.model.CitasDto;
import com.mariangel.clinicadental.model.Pacientes;
import com.mariangel.clinicadental.model.PacientesDto;
import com.mariangel.clinicadental.service.CitasService;
import com.mariangel.clinicadental.service.PacientesService;
import com.mariangel.clinicadental.util.EntityManagerHelper;
import com.mariangel.clinicadental.util.Formato;
import com.mariangel.clinicadental.util.Mensaje;
import com.mariangel.clinicadental.util.Respuesta;
import java.awt.Desktop.Action;
import java.net.URL;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javafx.collections.FXCollections;

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
    private TableColumn<PacientesDto, String> tblvDireccionInfoPacientes;

    @FXML
    private TableColumn<PacientesDto, Long> tblvEdadInfoPacientes;

    @FXML
    private TableColumn<PacientesDto, String> tblvNombreInfoPacientes;

    @FXML
    private TableColumn<PacientesDto, String> tblvPrimerApellidoInfoPacientes;

    @FXML
    private TableColumn<PacientesDto, String> tblvSegundoApellidoInfoPacientes;
    @FXML
    private TableView<Pacientes> tblvInformacionPacientes;
    
    @FXML
    private TableView<Citas> tblvRegistrarCita;
    @FXML
    private TableColumn<CitasDto, Date> tblvFechaRegistrarCita;

    @FXML
    private TableColumn<CitasDto,String> tblvHoraRegistrarCita;  
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
    private TextField txtSegundoApellidoPaciente;

    @FXML
    private Label txtSegundoApellidoRegistrarCita;

    /**
     * Initializes the controller class.
     */
    PacientesDto paciente;
    CitasDto cita;

    List<Node> requeridos = new ArrayList<>();
    @FXML
    private Button btnModificarPaciente;

    @FXML
    private Button btnCancelarRegistrarCita;
    @FXML
    private Label labelAdministracionClinicaDental;

    Media sound = new Media(getClass().getResource("/com/mariangel/laboratorioiiiclinicadental/musica/agregadocita.wav").toExternalForm());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        paciente = new PacientesDto();
        cita = new CitasDto();
        
        txtNombrePaciente.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtPrimerApellidoPaciente.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtSegundoApellidoPaciente.setTextFormatter(Formato.getInstance().letrasFormat(30));
        txtCedulaPaciente.setTextFormatter(Formato.getInstance().integerFormat());
        txtDireccionPaciente.setTextFormatter(Formato.getInstance().letrasFormat(150));
        txtCedulaRegistrarCita.setTextFormatter(Formato.getInstance().integerFormat());
        nuevoCita();
        indicarRequeridosCitas();
        indicarRequeridos();
        nuevoPaciente();

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
        datePickerFecNacPaciente.valueProperty().bindBidirectional(paciente.pacFecnac);

    }

    private void unbindPaciente() {

        txtCedulaPaciente.textProperty().unbindBidirectional(paciente.pacCedula);
        txtNombrePaciente.textProperty().unbindBidirectional(paciente.pacNombre);
        txtPrimerApellidoPaciente.textProperty().unbindBidirectional(paciente.pacPapellido);
        txtSegundoApellidoPaciente.textProperty().unbindBidirectional(paciente.pacSapellido);
        txtDireccionPaciente.textProperty().unbindBidirectional(paciente.pacDirec);
        datePickerFecNacPaciente.valueProperty().unbindBidirectional(paciente.pacFecnac);
    }

    public void indicarRequeridos() {
        System.out.println(" ENTRO AL indicar requeridos de cargar paciente");
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombrePaciente, txtPrimerApellidoPaciente, txtSegundoApellidoPaciente, txtCedulaPaciente, txtDireccionPaciente, txtHoraRegistrarCita, datePickerFecNacPaciente));
    }

    private void nuevoPaciente() {
        System.out.println(" ENTRO AL Nuevo paciente  de cargar paciente");
        unbindPaciente();
        paciente = new PacientesDto();
        bindPaciente(true);
        txtCedulaPaciente.clear();
        txtCedulaPaciente.requestFocus();
    }

    private void cargarPaciente(Long pcedula) {
        PacientesService service = new PacientesService();
        Respuesta respuesta = service.getPaciente(pcedula);
        if (respuesta.getEstado()) {
            unbindPaciente();
            System.out.println("despues del unbind");
            paciente = (PacientesDto) respuesta.getResultado("Pacientes");

            bindPaciente(false);
            System.out.println("METODO CARGAR PACIENTE Paciente despues del bind" + pcedula);
            validarRequeridos();
            System.out.println("valida requeridos" + pcedula);
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar paciente", getStage(), respuesta.getMensaje());
        }

    }

    @FXML
    private void onActionBuscarPaciente(ActionEvent event) {
        String cedulaText = txtCedulaPaciente.getText();
        Long cedula = Long.parseLong(cedulaText);
        cargarPaciente(cedula);
        mediaPlayer.play();

    }

    @FXML
    private void onAnctionGuardarPaciente(ActionEvent event) {
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

                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar paciente", getStage(), "Paciente guardo con éxito.");
                    //Sonido de agregado con éxito
                    mediaPlayer.play();
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
            mediaPlayer.play();
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
                    mediaPlayer.play();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PantallaPrincipalViewController.class.getName()).log(Level.SEVERE, "Error eliminando el Paciente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Eliminar Paciente", getStage(), "Ocurrio un error eliminando el Paciente.");
        }
    }

    //citas
    @FXML
    private void onActionBuscarCitas(ActionEvent event) {
        if (!txtCedulaRegistrarCita.getText().isBlank()) {
            String cedulaText = txtCedulaRegistrarCita.getText();
            Long cedula = Long.parseLong(cedulaText);
            cargarCedulaCita(cedula);
            mediaPlayer.play();
        }
    }

    public void indicarRequeridosCitas() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtNombreRegistrarCita, txtPrimerApellidoRegistrarCita, txtSegundoApellidoRegistrarCita));
    }

    private void bindCitas(Boolean nuevo) {

        txtNombreRegistrarCita.textProperty().bindBidirectional(paciente.pacNombre);
        txtPrimerApellidoRegistrarCita.textProperty().bindBidirectional(paciente.pacPapellido);
        txtSegundoApellidoRegistrarCita.textProperty().bindBidirectional(paciente.pacSapellido);
    }

    private void unbindCitas() {
        txtNombreRegistrarCita.textProperty().unbindBidirectional(paciente.pacNombre);
        txtPrimerApellidoRegistrarCita.textProperty().unbindBidirectional(paciente.pacPapellido);
        txtSegundoApellidoRegistrarCita.textProperty().unbindBidirectional(paciente.pacSapellido);

    }

    private void cargarCedulaCita(Long pcedula) {
        PacientesService service = new PacientesService();
        Respuesta respuesta = service.getPaciente(pcedula);

        if (respuesta.getEstado()) {
            unbindCitas();
            paciente = (PacientesDto) respuesta.getResultado("Pacientes");

            bindCitas(false);
            cita.setCitaCedupac(paciente);
            validarRequeridos();
        } else {
            new Mensaje().showModal(Alert.AlertType.ERROR, "Cargar paciente", getStage(), respuesta.getMensaje());
        }

    }

//Registrar Citas
    private void nuevoCita() {
        unbindCita();
        cita = new CitasDto();
        bindCita(true);
    }

    private void bindCita(Boolean nuevo) {
        txtHoraRegistrarCita.textProperty().bindBidirectional(cita.citaHora);
        datePickerFechaRegistrarCita.valueProperty().bindBidirectional(cita.citaDia);
    }

    private void unbindCita() {

        txtHoraRegistrarCita.textProperty().unbindBidirectional(cita.citaHora);
        datePickerFechaRegistrarCita.valueProperty().unbindBidirectional(cita.citaDia);
    }

    public void indicarRequeridosRegistrarCitas() {
        requeridos.clear();
        requeridos.addAll(Arrays.asList(txtHoraRegistrarCita, datePickerFechaRegistrarCita));
    }

    @FXML
    private void onActionRegistrarCita(ActionEvent event) {

        try {
            String invalidos = validarRequeridos();

            if (!invalidos.isEmpty()) {
                new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar cita", getStage(), invalidos);
            } else {
                CitasService citaService = new CitasService();
                Respuesta respuesta = citaService.guardarCita(cita);

                if (!respuesta.getEstado()) {
                    new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar cita", getStage(), respuesta.getMensaje());
                } else {
                    unbindCita();
                    cita = (CitasDto) respuesta.getResultado("Citas");

                    bindCita(false);

                    new Mensaje().showModal(Alert.AlertType.INFORMATION, "Guardar cita", getStage(), "Cita guardada correctamente.");
                    //sonido agregado correctamente
                    mediaPlayer.play();
                }
            }
        } catch (Exception ex) {

            Logger.getLogger(PantallaPrincipalViewController.class.getName()).log(Level.SEVERE, "Error guardando el paciente.", ex);
            new Mensaje().showModal(Alert.AlertType.ERROR, "Guardar paciente", getStage(), "Ocurrio un error guardando el paciente.");
        }

    }

    public static List<Pacientes> obtenerPacientesBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Pacientes> pacientesList = new ArrayList<>();
        try {
            pacientesList = em.createQuery("SELECT p FROM Pacientes p", Pacientes.class).getResultList();
            for (Pacientes paciente : pacientesList) {
                LocalDate now = LocalDate.now();
                LocalDate fechaNacimiento = paciente.getPacFecnac().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                int edad = Period.between(fechaNacimiento, now).getYears();
                paciente.setEdad(edad);
            }
        } catch (Exception e) {
            System.out.println("Error al obtener todos los pacientes de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return pacientesList;
    }
    
   public static List<Citas> obtenerCitasBD() {
        EntityManager em = EntityManagerHelper.getManager();
        List<Citas> citasList = new ArrayList<>();
        try {
            citasList = em.createQuery("SELECT c FROM Citas c", Citas.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al obtener las citas de la base de datos");
            e.printStackTrace();
        } finally {
            em.close();
        }
        return citasList;
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

    //limpiar
    @FXML
    private void onActionCancelarRegistrarCita(ActionEvent event) {
        datePickerFechaRegistrarCita.setValue(null);
        txtHoraRegistrarCita.clear();
        txtCedulaRegistrarCita.clear();
        txtSegundoApellidoRegistrarCita.setText(null);
        txtNombreRegistrarCita.setText(null);
        txtPrimerApellidoRegistrarCita.setText(null);

    }

    @FXML
    private void onActionBtnCancelar(ActionEvent event) {
        txtCedulaPaciente.clear();
        txtDireccionPaciente.clear();
        txtNombrePaciente.clear();
        txtPrimerApellidoPaciente.clear();
        txtSegundoApellidoPaciente.clear();
        datePickerFecNacPaciente.setValue(null);
    }

//TapPanes
    @FXML
    private void onSelectionTapRegistrarPacientes(Event event) {

    }

    @FXML
    private void onSelectionTapRegistrarCita(Event event) {
   if (tapPaneRegistarCita.isSelected()) {
            tblvHoraRegistrarCita.setCellValueFactory(new PropertyValueFactory<>("citaHora"));
            tblvFechaRegistrarCita.setCellValueFactory(new PropertyValueFactory<>("citaDia"));
           

            List<Citas> list = obtenerCitasBD();
            ObservableList<Citas> observableList = FXCollections.observableArrayList(list);

            // Asigna los nuevos datos a la TableView
          tblvRegistrarCita.setItems(observableList);}
    }

    @FXML
    private void onSelectionTapInformacionPacientes(Event event) {
        if (tapPaneInfoPacientes.isSelected()) {
            tblvNombreInfoPacientes.setCellValueFactory(new PropertyValueFactory<>("pacNombre"));
            tblvCedulaInfoPacientes.setCellValueFactory(new PropertyValueFactory<>("pkPacCedula"));
            tblvEdadInfoPacientes.setCellValueFactory(new PropertyValueFactory<>("edad"));
            tblvDireccionInfoPacientes.setCellValueFactory(new PropertyValueFactory<>("pacDirec"));
            tblvPrimerApellidoInfoPacientes.setCellValueFactory(new PropertyValueFactory<>("pacPapellido"));
            tblvSegundoApellidoInfoPacientes.setCellValueFactory(new PropertyValueFactory<>("pacSapellido"));

            List<Pacientes> list = obtenerPacientesBD();
            ObservableList<Pacientes> observableList = FXCollections.observableArrayList(list);

            // Asigna los nuevos datos a la TableView
            tblvInformacionPacientes.setItems(observableList);
        }
    }
}
