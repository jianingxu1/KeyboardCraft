package persistence;

import domain.CjtAlfabetos;
import domain.CjtTeclados;
import domain.CjtUsuarios;
import exceptions.*;

public class CtrlPersistencia {

    private CtrlFicherosTeclados ctrlFicherosTeclados;
    private CtrlFicherosUsuarios ctrlFicherosUsuarios;
    private CtrlFicherosAlfabetos ctrlFicherosAlfabetos;

    public CtrlPersistencia() {
        // Constructor vacío
    }

    public void guardarUsuarios(CjtUsuarios cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        try {
            ctrlFicherosUsuarios = new CtrlFicherosUsuarios();
            ctrlFicherosUsuarios.guardarUsuarios(cjtUsuarios);
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion(e.getMessage());
        }
    }

    public void guardarAlfabetos(CjtAlfabetos cjtAlfabetos, String userName) throws EscrituraIncorrectaFicheroExcepcion {
        try {
            ctrlFicherosAlfabetos = new CtrlFicherosAlfabetos();
            ctrlFicherosAlfabetos.guardarAlfabetos(cjtAlfabetos, userName);
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion(e.getMessage());
        }
    }

    public void guardarTeclados(CjtTeclados cjtTeclados, String userName) throws EscrituraIncorrectaFicheroExcepcion {
        try {
            ctrlFicherosTeclados = new CtrlFicherosTeclados();
            ctrlFicherosTeclados.guardarTeclados(cjtTeclados, userName);
        } catch (Exception e) {
            throw new EscrituraIncorrectaFicheroExcepcion(e.getMessage());
        }
    }

    public CjtUsuarios cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion {
        try {
            ctrlFicherosUsuarios = new CtrlFicherosUsuarios();
            return ctrlFicherosUsuarios.cargarUsuarios();
        } catch (Exception e) {
            throw new LecturaIncorrectaFicheroExcepcion(e.getMessage());
        }
    }

    public CjtAlfabetos cargarAlfabetos(String userName) throws LecturaIncorrectaFicheroExcepcion {
        try {
            ctrlFicherosAlfabetos = new CtrlFicherosAlfabetos();
            return ctrlFicherosAlfabetos.cargarAlfabetos(userName);
        } catch (Exception e) {
            throw new LecturaIncorrectaFicheroExcepcion(e.getMessage());
        }
    }

    public CjtTeclados cargarTeclados(String userName) throws LecturaIncorrectaFicheroExcepcion {
        try {
            ctrlFicherosTeclados = new CtrlFicherosTeclados();
            return ctrlFicherosTeclados.cargarTeclados(userName);
        } catch (Exception e) {
            throw new LecturaIncorrectaFicheroExcepcion(e.getMessage());
        }
    }

    public void eliminarUsuario(String nombreUsuario) {
        ctrlFicherosUsuarios = new CtrlFicherosUsuarios();
        ctrlFicherosUsuarios.eliminarUsuario(nombreUsuario);
    }
}
