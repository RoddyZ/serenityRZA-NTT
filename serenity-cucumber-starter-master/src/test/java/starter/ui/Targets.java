package starter.ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class Targets {
    public static final Target USERNAME_FIELD = Target.the("Username").locatedBy("//input[@id='user-name']");
    public static final Target PASSWORD_FIELD = Target.the("Password").locatedBy("//input[@id='password']");
    public static final Target LOGIN_BUTTON = Target.the("Login Button").located(By.id("login-button"));
    public static final Target INVENTORY_CONTAINER = Target.the("Inventory Container")
            .located(By.xpath("//div[@id='inventory_container' and @class='inventory_container']"));
    public static final Target BADGE_CARRITO = Target.the("Carrito Badge")
            .located(By.xpath("//div[@id='shopping_cart_container']//span[@class='shopping_cart_badge']"));
    public static final Target CARRITO_COMPRAS = Target.the("Carrito Compras")
            .located(By.xpath("//div[@id='shopping_cart_container']/a[@class='shopping_cart_link']"));
    public static final Target BOTON_VERIFICAR = Target.the("Botón Verificar").located(By.id("checkout"));
    public static final Target PRIMER_NOMBRE = Target.the("Campo Primer Nombre").located(By.id("first-name"));
    public static final Target PRIMER_APELLIDO = Target.the("Campo Apellido").located(By.id("last-name"));
    public static final Target CODIGO_POSTAL = Target.the("Campo Codigo Postal").located(By.id("postal-code"));
    public static final Target BOTON_CONTINUAR = Target.the("Botón Continuar").located(By.id("continue"));
    public static final Target BOTON_FINALIZAR_COMPRA = Target.the("Botón Finalizar Compra").located(By.id("finish"));
    public static final Target MENSAJE_EXITO = Target.the("Mensaje de Compra Exitosa").located(By.xpath("//h2[@class='complete-header']"));


    public static String obtenerXpathProducto(String nombreProducto, String accion) {
        return "//div[contains(text(),'" + nombreProducto + "')]" +
                "//ancestor::div[@class='inventory_item_description']" +
                "//button[contains(text(),'" + accion + "')]";
    }

}
