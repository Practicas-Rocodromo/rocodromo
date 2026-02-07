package com.gbjoanna.rocodromo.controllers;

import com.gbjoanna.rocodromo.Util.FileData;
import com.gbjoanna.rocodromo.models.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Optional;


public class UsuarioController {

    private ArrayList<Usuario> UsuarioList;

    private String nombre = "";
    private String apellidos = "";
    private String telefono = "";
    private String email = "";
    private String dni = "";

    @FXML
    private Label labelnombre;
    @FXML
    private Label labelapellido;
    @FXML
    private Label labeltelefono;
    @FXML
    private Label labelemail;
    @FXML
    private Label labeldni;
    @FXML
    private Label labellist;
    @FXML
    private Label labeltitleusuarios;
    @FXML
    private Label labelaviso;
    @FXML
    private TextField textfieldnombre;
    @FXML
    private TextField textfieldapellido;
    @FXML
    private TextField textfieldtelefono;
    @FXML
    private TextField textfieldemail;
    @FXML
    private TextField textfielddni;
    @FXML
    private ListView<String> listviewusuarios;
    @FXML
    private Button buttonmodificar;
    @FXML
    private Button buttoneliminar;
    @FXML
    private Button buttonregistrar;

    //Constructor
    public UsuarioController() {
        if (Files.exists((Path.of(FileData.USUARIOS_DAT)))) {
            UsuarioList = FileData.loadFile(FileData.USUARIOS_DAT);
        } else {
            UsuarioList = new ArrayList<>();
        }
    }

    //CRUD Usuario
    @FXML
    protected void onregistrar(ActionEvent event) {

        if (textfieldnombre.getText().isEmpty() || textfielddni.getText().isEmpty()) {
            labelaviso.setText("Te falta un campo obligatorio (Nombre o DNI)");
            return;
        }

        nombre = textfieldnombre.getText();
        apellidos = textfieldapellido.getText();
        telefono = textfieldtelefono.getText();
        email = textfieldemail.getText();
        dni = textfielddni.getText();

        if (!dni.matches("\\d{8}[A-Za-z]")) {
            labelaviso.setText("El DNI introducido es erróneo");
            return;
        }

        saveFileAndRefresh();

        limpiaDatos();

        refreshListView(UsuarioList);
    }

    @FXML
    protected void clicarlistview(MouseEvent event) {

        limpiaDatos();

        for (Usuario usuarionombre: UsuarioList) {
            if (usuarionombre.getNombre().equals(listviewusuarios.getSelectionModel().getSelectedItem())) {
                showDataInField(usuarionombre.getNombre());
                break;

            }
        }
    }

    @FXML
    protected void onmodificar(ActionEvent event) {

        if (textfieldnombre.getText().isEmpty() || textfielddni.getText().isEmpty()) {
            labelaviso.setText("Te falta un campo obligatorio (Nombre o DNI)");
            return;
        }

        nombre = textfieldnombre.getText();
        apellidos = textfieldapellido.getText();
        telefono = textfieldtelefono.getText();
        email = textfieldemail.getText();
        dni = textfielddni.getText();

        if (!dni.matches("d{8}[A-Za-z]")) {
            labelaviso.setText("El DNI introducido es erróneo");
            return;
        }

        saveFileAndRefresh();

        limpiaDatos();

        refreshListView(UsuarioList);
    }

    @FXML
    protected void oneliminar(ActionEvent event) {

        var usuario = textfieldnombre.getText();
        for (Usuario usuarionombre : UsuarioList) {
            if (usuario.equals(usuarionombre.getNombre())) {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Borrado de datos");
                alerta.setContentText("El borrado será permanente. ¿Quieres continuar?");
                Optional<ButtonType> opcion = alerta.showAndWait();
                if (opcion.isPresent() && opcion.get() == ButtonType.OK) {
                    UsuarioList.remove(usuarionombre);
                    labelaviso.setText("El usuario se ha borrado correctamente");
                    refreshListView(UsuarioList);
                    saveFileAndRefresh();
                    limpiaDatos();
                    break;
                } else {
                    labelaviso.setText("La operación ha sido cancelada");
                    limpiaDatos();
                    break;
                }
            }
        }
    }

    private void saveFileAndRefresh() {
        Usuario usuario = new Usuario(nombre, apellidos, telefono, email, dni);
        UsuarioList.add(usuario);
        FileData.saveFile(UsuarioList, FileData.USUARIOS_DAT);
        UsuarioList = FileData.loadFile(FileData.USUARIOS_DAT);
    }

    private void showDataInField(String nombre) {
        labelaviso.setText("Para modificar o eliminar pulsa su respectivo botón una vez hecho los cambios");

        for (Usuario usuarionombre : UsuarioList) {
            if (usuarionombre.getNombre().equals(nombre)) {
                textfieldnombre.setText(usuarionombre.getNombre());
                textfieldapellido.setText(usuarionombre.getApellidos());
                textfieldtelefono.setText(usuarionombre.getTelefono());
                textfieldemail.setText(usuarionombre.getEmail());
                textfielddni.setText(usuarionombre.getDni());
            }
        }
    }

    private void limpiaDatos() {
        textfieldnombre.setText("");
        textfieldapellido.setText("");
        textfieldtelefono.setText("");
        textfieldemail.setText("");
        textfielddni.setText("");
    }

    private void refreshListView(ArrayList<Usuario> UsuarioList) {

        listviewusuarios.getItems().clear();
        for (Usuario usuarionombre: UsuarioList) {
            listviewusuarios.getItems().add(usuarionombre.getNombre());
        }
    }
}
