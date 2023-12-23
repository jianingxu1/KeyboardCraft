package presentation;

public interface SyncViews {

    public void syncVistaBienvenida_a_CrearCuenta();

    public void syncVistaCrearCuenta_a_Bienvenida();

    public void syncVistaBienvenida_a_MenuPrincipal();

    public void syncVistaMenuPrincipal_a_Bienvenida();

    public void syncVistaGestionAlfabetos_a_MenuPrincipal();

    public void syncVistaMenuPrincipal_a_GestionAlfabetos();

    public void syncVistaGestionPerfil_a_MenuPrincipal();

    public void syncVistaGestionPerfil_a_Bienvenida();

    public void syncVistaMenuPrincipal_a_GestionPerfil();

    public void syncVistaMenuPrincipal_a_GestionTeclados();

    public void syncVistaGestionTeclados_a_MenuPrincipal();

    public void syncVistaGestionPerfil_a_CambiarContrasena();

    public void syncVistaCambiarContrasena_a_GestionPerfil();
}
