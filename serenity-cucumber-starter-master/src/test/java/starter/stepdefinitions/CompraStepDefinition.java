package starter.stepdefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Enabled;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import starter.DataManager.JsonDataProvider;

import java.io.IOException;
import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static starter.ui.Targets.*;

public class CompraStepDefinition {

    private Map<String, String> datos;
    // Método para cargar datos desde JsonDataProvider
    private void cargarDatos() throws IOException {
        datos = JsonDataProvider.getData();
    }

    @Cuando("ingresa su {string} y {string} en la pantalla de inicio")
    public void ingresa_su_y_en_la_pantalla_de_inicio(String usuario, String contrasena) throws IOException {
        cargarDatos();
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(USERNAME_FIELD).isDisplayed(),
                Ensure.that(PASSWORD_FIELD).isDisplayed(),
                Enter.theValue(datos.get(usuario)).into(USERNAME_FIELD),
                Enter.theValue(datos.get(contrasena)).into(PASSWORD_FIELD)
        );
    }

    @Cuando("hace clic en boton Login")
    public void hace_clic_en_boton_login() {
        theActorInTheSpotlight().attemptsTo(Click.on(LOGIN_BUTTON));
        // Verificamos que el inventario se haya cargado correctamente
        theActorInTheSpotlight().attemptsTo(Ensure.that(theActorInTheSpotlight().asksFor(Enabled.of(INVENTORY_CONTAINER))).
                isTrue());
    }

    @Cuando("se elige el {string} y {string} para comprar")
    public void se_elige_el_y_para_comprar(String productoUno, String productoDos) throws IOException {
        cargarDatos();
        // Añadir productos al carrito
        Target agregarProductoUno = Target.the("Producto Uno Add")
                .located(By.xpath(obtenerXpathProducto(datos.get(productoUno), "Add to cart")));
        Target agregarProductoDos = Target.the("Producto Dos Add")
                .located(By.xpath(obtenerXpathProducto(datos.get(productoDos), "Add to cart")));
        theActorInTheSpotlight().attemptsTo(
                Click.on(agregarProductoUno),
                Click.on(agregarProductoDos)
        );

        // Verificar que los productos han sido añadidos
        Target removerProductoUno = Target.the("Producto Uno Remove")
                .located(By.xpath(obtenerXpathProducto(datos.get(productoUno), "Remove")));
        Target removerProductoDos = Target.the("Producto Dos Remove")
                .located(By.xpath(obtenerXpathProducto(datos.get(productoDos), "Remove")));

        theActorInTheSpotlight().attemptsTo(
                Ensure.that(removerProductoUno).isDisplayed(),
                Ensure.that(removerProductoDos).isDisplayed()
        );

        // Verificar que el carrito no esté vacío
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(Text.of(BADGE_CARRITO)).isNotEqualTo("0")
        );
    }

    @Cuando("se hace clic en el carrito de compras para verificar los productos agregados")
    public void se_hace_clic_en_el_carrito_de_compras_para_verificar_los_productos_agregados() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(CARRITO_COMPRAS)
        );
        // Verificar que el número de productos en el carrito coincida con el badge
        int cantidadProductos = BrowseTheWeb.as(theActorInTheSpotlight()).findAll(By.xpath("//div[@class='cart_item']")).size();
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(Text.of(BADGE_CARRITO)).isEqualTo(String.valueOf(cantidadProductos))
        );
    }

    @Cuando("se hace clic en boton verificar")
    public void se_hace_clic_en_boton_verificar() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(BOTON_VERIFICAR)
        );
    }

    @Cuando("se ingresa {string}, {string} y {string} del comprador, se hace clic en boton continuar")
    public void se_ingresa_y_del_comprador_se_hace_clic_en_boton_continuar(String nombre, String apellido, String codigoPostal) throws IOException {
        cargarDatos();
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(PRIMER_NOMBRE).isDisplayed(),
                Ensure.that(PRIMER_APELLIDO).isDisplayed(),
                Ensure.that(CODIGO_POSTAL).isDisplayed()
        );

        theActorInTheSpotlight().attemptsTo(
                Enter.theValue(datos.get(nombre)).into(PRIMER_NOMBRE),
                Enter.theValue(datos.get(apellido)).into(PRIMER_APELLIDO),
                Enter.theValue(datos.get(codigoPostal)).into(CODIGO_POSTAL)
        );

        theActorInTheSpotlight().attemptsTo(
                Click.on(BOTON_CONTINUAR)
        );
    }

    @Entonces("finalmente se hace clic en boton finalizar y se valida compra exitosa")
    public void finalmente_se_hace_clic_en_boton_finalizar_y_se_valida_compra_exitosa() {
        theActorInTheSpotlight().attemptsTo(
                Click.on(BOTON_FINALIZAR_COMPRA)
        );

        // Validar que la compra fue exitosa
        theActorInTheSpotlight().attemptsTo(
                Ensure.that(MENSAJE_EXITO).text().isEqualTo("Thank you for your order!")
        );
    }
}
