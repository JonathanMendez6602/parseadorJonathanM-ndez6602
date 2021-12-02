import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import Clases.*;

public class Parseador {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Telemetria_Ext t;
		Scanner teclado = new Scanner(System.in);
		Scanner teclado2 = new Scanner(System.in);
		String valor;
		int c=0,i=0;
		char car, res;
		String ruta;
		ArrayList<Telemetria_Ext> correcto = new ArrayList<Telemetria_Ext>();
		ArrayList<Telemetria_Ext> incorrecto = new ArrayList<Telemetria_Ext>();;
		try{
			//Ojo la primera vez de ejecucion del programa ya esta definida la ruta en doc.
			File doc = new File("D:\\taip.txt");
				
			do {
				System.out.println("Escriba la ruta del archivo");
				  ruta=teclado2.nextLine();
				  doc= new File(ruta);
				BufferedReader obj = new BufferedReader(new FileReader(doc));
				  while ((valor = obj.readLine()) != null) {
								t=new Telemetria_Ext(valor);
								t.Generar();
								if(t.getCreado()) {
									correcto.add(t);
								}else {
									incorrecto.add(t);
								}
				  }
				  System.out.println("Creados Incorrectamente: ");
				  for(int x=0; x<incorrecto.size(); x++) {
					  System.out.println(incorrecto.get(x).getError());
				  }
				  System.out.println("Creados Correctamente: ");
				  for(int x=0; x<correcto.size(); x++) {
						correcto.get(x).mostrartipo();
					}
				  c=correcto.size();
				  i=incorrecto.size();
				  System.out.println("Creados correctamente: "+c);
				  System.out.println("Creados Incorrectamente: "+i);
				  
				  System.out.print("Deseas realizar algo mas?");
				  System.out.print("| s=si, n=no |");
				  car = teclado.next().charAt(0);
				  if(car=='s') {
					  System.out.println("Que desea realizar");
					  System.out.println("a = cambiar el archivo, b = ingresar datos, c=nada");
					  car = teclado.next().charAt(0);
					  if(car == 'a') {
						  System.out.println("Escriba la nueva ruta del archivo");
						  ruta=teclado2.nextLine();
						  doc= new File(ruta);
					  }
					  if(car == 'b') {
						  do {
							  System.out.println("Escriba el paquete de informacion");
							  valor=teclado2.nextLine();
							  t=new Telemetria_Ext(valor);
							  t.Generar();
								if(t.getCreado()) {
									t.mostrartipo();
								}else {
									System.out.print(t.getError());
								}
								System.out.println("Desea insertar otro paquete");
								System.out.println("s=si, n=no");
								res=teclado.next().charAt(0);
						  }while(res!='n');
					  }
				  }
			}while((car!='n') && (car!='c'));
			  
			  
        }catch(IOException e){
        	e.printStackTrace();
            System.out.println("Error E/S: "+e);
        }
	}

}
