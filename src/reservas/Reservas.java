package reservas;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 * @author LuisTobon
 */

public class Reservas{
    
  public static void main(String[] args) {
    
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Reservas");
    Row row = null;
    Cell cell = null;
    int cantidadDatos = 100; // Cambia este numero según la cantidad de datos a generar
    Set<String> codigosGenerados = new HashSet<>();
    Scanner in = new Scanner(System.in);
    
    for (int i = 0; i < cantidadDatos; i++) {
        
        String codigoReserva;
      
        do {

          codigoReserva = generarCodigoReserva();

        } while (codigosGenerados.contains(codigoReserva));

        codigosGenerados.add(codigoReserva);

        String fechaReserva = generarFechaReserva(); // Generar una fecha aleatoria
        float ivaReserva = generarIvaReserva(); // Generar un valor de IVA aleatorio
        int tipoReserva = generarTipoReserva(); // Generar un tipo de reserva aleatorio
        int tipoVuelo = generarTipoVuelo(); // Generar un tipo de vuelo aleatorio
        int metodoPago = generarMetodoPago(); // Generar un método de pago aleatorio
        String numeroCuentaCliente = generarNumeroCuentaCliente(); // Generar un número de cuenta de cliente aleatorio
        int idEmpleado = generarIdEmpleado(); // Generar un ID de empleado aleatorio
        Integer idCupon = generarIdCupon(); // Generar un ID de cupón aleatorio

        // Crear una nueva fila en el archivo Excel
        row = sheet.createRow(i);

        // Agregar nombres a las columnas
        Row headerRow = sheet.createRow(0);
        cell = headerRow.createCell(0);
        cell.setCellValue("Código_reserva");
        cell = headerRow.createCell(1);
        cell.setCellValue("Fecha_reserva");
        cell = headerRow.createCell(2);
        cell.setCellValue("iva_reserva");
        cell = headerRow.createCell(3);
        cell.setCellValue("tipo_reserva");
        cell = headerRow.createCell(4);
        cell.setCellValue("tipo_vuelo");
        cell = headerRow.createCell(5);
        cell.setCellValue("método_pago");
        cell = headerRow.createCell(6);
        cell.setCellValue("número_cuenta_cliente");
        cell = headerRow.createCell(7);
        cell.setCellValue("id _empleado");
        cell = headerRow.createCell(8);
        cell.setCellValue("id_cupón");
      
        // Insertar los datos en las celdas correspondientes
        cell = row.createCell(0);
        cell.setCellValue(codigoReserva);
        cell = row.createCell(1);
        cell.setCellValue(fechaReserva);
        cell = row.createCell(2);
        cell.setCellValue(ivaReserva);
        cell = row.createCell(3);
        cell.setCellValue(tipoReserva);
        cell = row.createCell(4);
        cell.setCellValue(tipoVuelo);
        cell = row.createCell(5);
        cell.setCellValue(metodoPago);
        cell = row.createCell(6);
        cell.setCellValue(numeroCuentaCliente);
        cell = row.createCell(7);
        cell.setCellValue(idEmpleado);
        cell = row.createCell(8);
      
        if (idCupon != null) {
          cell.setCellValue(idCupon);
        }

        // Escribir el archivo Excel en el disco
        try (FileOutputStream outputStream = new FileOutputStream("C:\\Users\\ltob9\\Desktop\\Reservas\\Reserva.xlsx")) {
          workbook.write(outputStream);
        } catch (IOException e) {
          e.printStackTrace();
        }
        
    }
    
  }

  private static String generarCodigoReserva() {
      
    Random random = new Random();
    String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    StringBuilder sb = new StringBuilder();
    
    for (int i = 0; i < 14; i++) {
      int index = random.nextInt(caracteres.length());
      sb.append(caracteres.charAt(index));
    }
    return sb.toString();
    
  }

  private static String generarFechaReserva() {
    /*  
    Random random = new Random();
    int dia = random.nextInt(28) + 1;
    int mes = random.nextInt(6) * 2 + 1;
    int anio = 2024;
    return String.format("%04d-%02d-%02d", anio, mes, dia);
    */
    Random random = new Random();
    int dia = random.nextInt(28) + 1;
    int mes = random.nextInt(6) * 2 + 1;
    int anio = 2024;
    int count = 0;
    String fecha = String.format("%04d-%02d-%02d", anio, mes, dia);
    while (count < 3) {
        int newDia = random.nextInt(28) + 1;
        int newMes = random.nextInt(6) * 2 + 1;
        String newFecha = String.format("%04d-%02d-%02d", anio, newMes, newDia);
        if (newFecha.equals(fecha)) {
            count++;
        } else {
            fecha = newFecha;
            count = 0;
        }
    }
    return fecha;
  }

  private static float generarIvaReserva() {
      
    return 5.0f;
    
  }

  private static int generarTipoReserva() {
      
    Random random = new Random();
    return random.nextInt(2);
    
  }

  private static int generarTipoVuelo() {
      
    Random random = new Random();
    return random.nextInt(3);
    
  }

  private static int generarMetodoPago() {
      
    Random random = new Random();
    return random.nextInt(5);
    
  }

  private static String generarNumeroCuentaCliente() {
      
    Random random = new Random();
    int numeroAleatorio;
    
    if (random.nextInt(10) < 3) {
      return "";
    } else {
      numeroAleatorio = random.nextInt(94000) + 1;
      return Integer.toString(numeroAleatorio);
    }
    
  }

  private static int generarIdEmpleado() {
      
    Random random = new Random();
    return random.nextInt(100) + 1;
    
  }
    
  private static Integer generarIdCupon() {
      
    Random random = new Random();
    if (random.nextBoolean()){
        return random.nextInt(4);
    }else{
        return null;
    }
    
  }
  
}
