#language: es
#Automatizador: Roddy Zamora
#Comando Ejecutar: mvn clean verify -Dcucumber.filter.tags="@FlujoCompras"


@FeatureCompletoShoppingCart
Característica: Compra de Productos en Swag Labs

  COMO usuario de la plataforma Sauce
  QUIERO realizar la compra de 2 productos
  PARA evidenciar el correcto funcionamiento de la pagina

  @FlujoCompras
  Escenario: Flujo de compra en la pagina Sauce
    Dado Usuario ingresa a la aplicacion Swag Labs
    Cuando ingresa su <usuario> y <contrasena> en la pantalla de inicio
    Y hace clic en boton Login
    Y se elige el <productoUno> y <productoDos> para comprar
    Y se hace clic en el carrito de compras para verificar los productos agregados
    Y se hace clic en boton verificar
    Y se ingresa <nombre>, <apellido> y <codigoPostal> del comprador, se hace clic en boton continuar
    Entonces finalmente se hace clic en boton finalizar y se valida compra exitosa

