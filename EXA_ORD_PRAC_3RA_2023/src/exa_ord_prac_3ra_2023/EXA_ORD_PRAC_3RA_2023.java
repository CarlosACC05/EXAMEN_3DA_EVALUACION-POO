/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exa_ord_prac_3ra_2023;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EXA_ORD_PRAC_3RA_2023 {

    public static void main(String[] args) {
        // TODO code application logic here
        
        Inventario inventario = new Inventario();

        Producto producto1 = new Producto(1, "Cebolla", 10.99, 50);
        Producto producto2 = new Producto(2, "Aguacate", 5.45, 30);
        Producto producto3 = new Producto(3, "Pera", 44.50, 12);

        inventario.addProduct(producto1);
        inventario.removeProduct(1);
        inventario.addProduct(producto2);
        inventario.addProduct(producto3);
        inventario.displayInventory();
        inventario.saveToFile("inventario");
        inventario.loadFromFile("inventario");
        
        Producto buscaProd = inventario.searchProduct(4);
        if (buscaProd != null) {
            System.out.println("Producto encontrado: " + buscaProd.getNombre());
        } else {
            System.out.println("Producto no encontrado en el inventario.");
        }
    }

}

class Producto {

    private int id;
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto() {

    }

    public Producto(int id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}

class Inventario {

    Producto[] productos = new Producto[10];
    int inventario = 0;

    public void addProduct(Producto producto) {
        if (inventario < 10) {
            productos[inventario] = producto;
            inventario++;
            System.out.println("Se agrego un nuevo producto");
        } else {
            System.out.println("No se puede agregar otro prodcuto");
        }
    }

    public void removeProduct(int productId) {

        for (int i = 0; i < inventario; i++) {
            if (productos[i].getId() == productId) {
                System.out.println("Se elimino el producto: " + productos[i].getId());
                productos[i] = null;

                break;
            }
        }
    }

    public Producto searchProduct(int productId) {
        
        for (int i = 0; i < inventario; i++) {
            if (productos[i] != null && productos[i].getId() == productId) {
                return productos[i];
            }
        }
        return null;

    }

    public void displayInventory() {
        System.out.println("Inventario actual:");
        for (int i = 0; i < inventario; i++) {
            if (productos[i] != null) {
                System.out.println(productos[i].getNombre() + "\n Precio: " + productos[i].getPrecio() + "\n Cantidad: " + productos[i].getCantidad() + "\n ID: " + productos[i].getId());
                System.out.println("-----------------------------------------------------");
            }

        }
    }

    public void saveToFile(String filename) {
        File directorio = new File("C:/Documentos");
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
            } else {
                System.out.println("Error al crear directorio");
            }
        }
        
        File file = new File("C:/Documentos/" + filename + ".txt");
        try {
            if (file.createNewFile()) {
                System.out.println("Se creo el archivo");
            }

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        String filePath = ("C:/Documentos/" + filename + ".txt");
        try {

            FileWriter fileWriter = new FileWriter("C:/Documentos/" + filename + ".txt");

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("Inventario actual\n");
            for (int i = 0; i < 10; i++) {
                if (productos[i] != null) {
                    bufferedWriter.write(productos[i].getNombre() + "\n Precio: " + productos[i].getPrecio() + "\n Cantidad: " + productos[i].getCantidad() + "\n ID: " + productos[i].getId());
                    bufferedWriter.write("\n-----------------------------------------------------\n");
                }
            }
            System.out.println("Archivo escrito correctamente.");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }

    }

    public void loadFromFile(String filename) {

        try {

            FileReader fileReader = new FileReader("C:/Documentos/" + filename + ".txt");

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            System.out.println("Contenido del archivo:");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

}
