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
    }

    public void guardarUsuarios(CjtUsuarios cjtUsuarios) throws EscrituraIncorrectaFicheroExcepcion {
        ctrlFicherosUsuarios = new CtrlFicherosUsuarios();
        ctrlFicherosUsuarios.guardarUsuarios(cjtUsuarios);
    }

    public void guardarAlfabetos(CjtAlfabetos cjtAlfabetos, String userName)
            throws EscrituraIncorrectaFicheroExcepcion {
        ctrlFicherosAlfabetos = new CtrlFicherosAlfabetos();
        ctrlFicherosAlfabetos.guardarAlfabetos(cjtAlfabetos, userName);
    }

    public void guardarTeclados(CjtTeclados cjtTeclados, String userName) throws EscrituraIncorrectaFicheroExcepcion {
        ctrlFicherosTeclados = new CtrlFicherosTeclados();
        ctrlFicherosTeclados.guardarTeclados(cjtTeclados, userName);
    }

    public CjtUsuarios cargarUsuarios() throws LecturaIncorrectaFicheroExcepcion {
        ctrlFicherosUsuarios = new CtrlFicherosUsuarios();
        return ctrlFicherosUsuarios.cargarUsuarios();
    }

    public CjtAlfabetos cargarAlfabetos(String userName) throws LecturaIncorrectaFicheroExcepcion {
        ctrlFicherosAlfabetos = new CtrlFicherosAlfabetos();
        return ctrlFicherosAlfabetos.cargarAlfabetos(userName);
    }

    public CjtTeclados cargarTeclados(String userName) throws LecturaIncorrectaFicheroExcepcion {
        ctrlFicherosTeclados = new CtrlFicherosTeclados();
        return ctrlFicherosTeclados.cargarTeclados(userName);
    }

    public void eliminarUsuario(String nombreUsuario) {
        ctrlFicherosUsuarios = new CtrlFicherosUsuarios();
        ctrlFicherosUsuarios.eliminarUsuario(nombreUsuario);
    }
}
