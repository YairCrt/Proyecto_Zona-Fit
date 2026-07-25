package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.datos.IClienteDAO;
import zona_fit.dominio.Cliente;


import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        var salir = false;
        Scanner scan = new Scanner(System.in);

        IClienteDAO clienteDao = new ClienteDAO();
        //List<Cliente> clientes = new ArrayList<>();

        System.out.println("*|||| ZONA FIT APP ||||*");
        while(!salir){
            try {
                var opcion = mostrarMenu(scan);
                salir = ejecutarOpciones( scan, opcion, clienteDao);
            }catch (Exception e){
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
            finally {
                System.out.println();
            }
        }
    }

    private static int mostrarMenu(Scanner scan){
        System.out.print("""
                Menu:
                1. Mostrar clientes
                2. Buscar cliente por ID
                3. Agregar cliente
                4. Editar cliente
                5. Eliminar cliente
                6. Salir
                Elige una opcion: \s""");
        return Integer.parseInt(scan.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner scan, int opcion,IClienteDAO clienteDAO){
        var salir = false;
        switch (opcion){
            case 1 -> {
                System.out.println("** Listado de clientes **");
                var clientes = clienteDAO.listarClientes();
                clientes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.print("Ingrese el ID del cliente a buscar: ");
                var idCliente = Integer.parseInt(scan.nextLine());
                var cliente = new Cliente(idCliente);
                var encontrado = clienteDAO.buscarClientePorId(cliente);
                if (encontrado) {
                    System.out.println("Cliente encontrado: " + cliente);
                } else {
                    System.out.println("No se encontro cliente: " + cliente.getId());
                }
            }
            case 3 ->{
                System.out.print("Ingrese el nombre del cliente: ");
                var nombreCliente = scan.nextLine();
                System.out.print("Ingrese el apellido del cliente: ");
                var apellidoCliente = scan.nextLine();
                System.out.print("Ingrese membresia del cliente: ");
                var membresiaCliente = Integer.parseInt(scan.nextLine());
                var clienteNuevo = new Cliente(nombreCliente, apellidoCliente, membresiaCliente);
                var agregado = clienteDAO.agregarCliente(clienteNuevo);
                if(agregado){
                    System.out.println("Cliente agregado: " + clienteNuevo);
                }else{
                    System.out.println("No se agrego el cliente: " + clienteDAO);
                }
            }
        }

        return salir;
    }
}
