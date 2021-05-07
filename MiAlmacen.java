import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;

/**
 * Class MiAlmacen
 */
public class MiAlmacen
{
    /**
     * Defino como estaticas para que puedan usarse dentro de la clase sin necesidad de pasarlas como parametros.
     */
    static private ModeloAbs almacen;
    static private Scanner sc;
    
    public static void main(String[] args){
        almacen=new ModeloArrayList ();
        sc = new Scanner(System.in);
        int opcion=0;
        do{
		mostrarMenu();
                opcion=leerOpcion(1,9);
                switch(opcion){
                    case 1: crear();break;
                    case 2: consultar();break;
                    case 3: borrar();break;
                    case 4: modificarPrecio();break;
                    case 5: comprar();break;
                    case 6: vender();break;
                    case 7: listar();break;
                    case 8: listarPocoStock();break;
                }
                System.out.println("\n---------------------------- ");
                System.out.print("Pulse enter para continuar");
                sc.nextLine();
        }while(opcion!=9);
        sc.close();
    }

    private static void mostrarMenu(){
        System.out.println("\n\n    MENU");
        System.out.println("1. Nuevo producto ");
        System.out.println("2. Consulta producto ");
        System.out.println("3. Borrar producto ");
        System.out.println("4. Modificar precio ");
        System.out.println("5. Compra de productos ");
        System.out.println("6. Venta de productos ");
        System.out.println("7. Listado completo de productos ");
        System.out.println("8. Listado de productos con stock inferior al mínimo");
        System.out.println("9. Terminar ");
        System.out.print("Elige una opción (1-9)");        
    }

    /**
     * Lee un entero del System.in que este comprendido entre primero y ultimo
     * @param primero ->Primera Opcion menu
     * @param ultimo ->Ultima Opcion menu
     * @return ->Opcion seleccionada
     */
    private static int leerOpcion(int primero, int ultimo){
        int valor = leerEntero();

        while ( valor <primero || valor > ultimo){
            valor = leerEntero();
        }
        return valor;
    }


    /**
     * Metodos Auxiliares leerFloat y LeerEntero,
     * Lee de la System.in con el scanner sc y controlan la excepcion de NumberFormatException
     * @return
     */
    static private int leerEntero(){
        boolean error = false;
        int  valor =0;
        String cadena;

        do {
        error = false;  
          try {
             /* Intento leer directamente un entero
             lee opcion del usuario como una cadena y lo transforma al tipo(entero) que necesita*/
             cadena = sc.nextLine();
             valor = Integer.parseInt(cadena);
             
            } catch(NumberFormatException e){
              System.out.println("Error en formato.");
              error = true;
            }
        } while (error);
       return valor; 
    }

    /**
     * Metodos Auxiliares leerFloat
     * Lee de la System.in con el scanner sc y controlan la excepcion de NumberFormatException
     * @return
     */
    static private float leerFloat(){
        boolean error = false;
        float  valor =0.0f;
        String cadena;

        do {
            error = false;
            try {
                 /* Intento leer directamente un entero
                lee opcion del usuario como una cadena y lo transforma al tipo(float) que necesita*/
                cadena = sc.nextLine();
                valor = Float.parseFloat(cadena);

            } catch(NumberFormatException e){
                System.out.println("Error en formato.");
                error = true;
            }
        } while (error);
        return valor;
    }

    /**
     * Muestra los datos de un producto a partir de su codigo
     */
    private static void consultar(){        
       System.out.println("<CONSULTA>");
       System.out.print("Introduzca codigo:");
       int codigo = leerEntero();
       Producto p = almacen.buscarProducto(codigo);

       if ( p == null){
           System.out.println("El producto no se encuentra en almacen");
       } else {
           System.out.println("PRODUCTO "+p.toString());
       }
    }

    /**
     * Borrar un producto a partir de su codigo
     */
    private static void borrar(){
        System.out.println("<ELIMINAR>");
        System.out.println("Introduzco codigo del producto que desea borrar");
        int codigo = leerEntero();
        boolean auxBorrar = almacen.borrarProducto(codigo);

        if (!auxBorrar){
            System.out.println("El producto no se encuentra en almacen y no se ha podido eliminar");
        } else {
            System.out.println("PRODUCTO BORRADO");
        }
    }

    /**
     * Cambia el precio de un producto a partir de su codigo
     */
    private static void modificarPrecio (){
        System.out.println("<MODIFICAR PRECIO>");
        System.out.println("Introduzco codigo del producto qal que desea modificar el precio");
        int codigo = leerEntero();
        Producto p = almacen.buscarProducto(codigo);

        if ( p == null){
            System.out.println("El producto no se encuentra en almacen");
        } else {
            System.out.println("Introduzca precio nuevo");
            float nuevoPrecio = leerFloat();
            //Modificamos precio sobre el objeto buscado
            p.setPrecio(nuevoPrecio);
            //Guardamos cambios
            almacen.modificarProducto(p);
            System.out.println("PRECIO MODIFICADO"+p.toString());
        }
    }

    /**
     * Incrementa el stock
     */
    private static void comprar(){     
       System.out.println("<COMPRAR PRODUCTO>");
        System.out.println("Introduzco codigo del producto que desea comprar");
        int codigo = leerEntero();
        Producto p = almacen.buscarProducto(codigo);

        if ( p == null){
            System.out.println("El producto no se encuentra en almacen");
        } else {
            System.out.println("Introduzca el numero de unidades compradas del producto");
            int nuevasUnidades = leerEntero();
            //Sumamos las nuevas unidades al Stock ya existente
            p.setStock(p.getStock() + nuevasUnidades);
            //Guardamos cambios
            almacen.modificarProducto(p);
            System.out.println("STOCK MODIFICADO"+p.toString());
        }
    }

    /**
     * Decrementa el stock
     */
    private static void vender(){
        System.out.println("<VENDER PRODUCTO>");
        System.out.println("Introduzco codigo del producto que desea vender");
        int codigo = leerEntero();
        Producto p = almacen.buscarProducto(codigo);

        if ( p == null){
            System.out.println("El producto no se encuentra en almacen");
        } else {
            System.out.println("Introduzca el numero de unidades vemdidas del producto");
            int unidadesVendidas = leerEntero();
            //Restamos las unidades vendidas al Stock
            p.setStock(p.getStock() - unidadesVendidas);
            //Guardamos cambios
            almacen.modificarProducto(p);
            System.out.println("STOCK MODIFICADO"+p.toString());
        }
    }

    /**
     * Listado de todos los productos
     */
    private static void listar(){        
         System.out.println("<LISTAR>");
         almacen.listarProductosTodos();
    }

    /**
     * Listado de todos los productos con stock inferior a stock minimo
     */
    private static void listarPocoStock(){
        System.out.println("<LISTAR STOCK BAJO MINIMOS>");
        almacen.listarProductosStockMin();
    }

    /**
     * Solicita datos al usuario para dar de alta un nuevo producto
     * codigo no se puede repetir
     */
    private static void crear(){
       System.out.println("<NUEVO PRODUCTO>");
       System.out.println("Introduzca nombre del producto");
       String nombre = sc.nextLine();
       System.out.println("Introduzca Stock del producto");
       int stock = leerEntero();
       System.out.println("Introduzca Stock minimo recomendado del producto");
       int stock_min = leerEntero();
       System.out.println("Introduzca precio del producto");
       float precio = leerFloat();
        //Creamos Producto
       Producto p = new Producto( nombre, stock, stock_min, precio);
       almacen.insertarProducto(p);
    }
}

