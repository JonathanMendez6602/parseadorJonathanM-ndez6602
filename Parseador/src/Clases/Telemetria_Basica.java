package Clases;

import java.io.IOException;
import java.util.Calendar;

public class Telemetria_Basica {
	String telem;
    String indice;
    int codigo_evento;
    Calendar fecha_ev;
    String diaev;
    Calendar hora_ev;
    double lat;
    double lng;
    float velm;
    float velk;
    int orient;
    String error="";
    
    public Telemetria_Basica(String t) {
    	telem=t;
    }
    
    public void cambiar() throws IOException {
    	indice(telem.substring(1,2));
    	codigo_Evento(telem.substring(4,6));
    	FechaActual(telem.substring(6,10), telem.substring(10,11));
    	DiaActual(telem.substring(10,11));
    	horaActual(telem.substring(11, 16));
    	latitud(telem.substring(16,24));
    	longitud(telem.substring(24,33));
    	velocidad(telem.substring(33,36));
    	orientacion(telem.substring(36,39));
    }
    
    public String error() {
    	return error;
    }
    
    public void getTelemetria_B() {
    	
    }
    
    public String getTelem() {
    	return telem;
    }
    
    public void indice(String val) {
    	if(val.toUpperCase().charAt(0)=='R'){
            this.indice="Response";
        }else{
            if(val.toUpperCase().charAt(0)=='Q'){
                this.indice="Query";
            }else{
                if(val.toUpperCase().charAt(0)=='S'){
                    this.indice="Set";
                }else{
                    this.indice="";
                    this.error="Error en indice";
                }
            }
        }
    }
    
    public void codigo_Evento(String val) throws IOException {
    	try {
    		this.codigo_evento = Integer.parseInt(val.trim());
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    }
    
    public void FechaActual(String val1, String val2)throws IOException {
    	try {
    		int v1, v2, v3;
    		v1=Integer.parseInt(val1)*7;
        	v2=Integer.parseInt(val2);
        	v3=v1+v2;
        	Calendar c = Calendar.getInstance();
        	c.set(Calendar.YEAR, 1980);
        	c.set(Calendar.MONTH, Calendar.JANUARY);
        	c.set(Calendar.DAY_OF_MONTH, 6);	
        	fecha_ev = Calendar.getInstance();
        	fecha_ev.set(Calendar.YEAR, 1980);
        	fecha_ev.set(Calendar.MONTH, Calendar.JANUARY);
        	fecha_ev.set(Calendar.DAY_OF_MONTH, 6);
        	fecha_ev.add(Calendar.DAY_OF_MONTH, v3);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    public void DiaActual(String val) throws IOException {
    	int v;
    	try {
    		v=Integer.parseInt(val.trim());
    		switch(v) {
    		case 1:
    			diaev="Lunes";
    			break;
    		case 2:
    			diaev="Martes";
    			break;
    		case 3:
    			diaev="Miercoles";
    			break;
    		case 4:
    			diaev = "Jueves";
    			break;
    		case 5:
    			diaev = "Viernes";
    			break;
    		case 6:
    			diaev = "Sabado";
    			break;
    		case 7:
    			diaev = "Domingo";
    			break;
    		default:
    			this.error ="Error dia incorrecto";
    			diaev="Error dia incorrecto";
    			break;
    		}
    	}catch(Exception e) {
    		this.error="error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void horaActual(String val) throws IOException{
    	try {
    		int v;
    		v=Integer.parseInt(val);
    		hora_ev=Calendar.getInstance();
        	hora_ev.set(Calendar.HOUR_OF_DAY, 0);
        	hora_ev.set(Calendar.MINUTE, 0);
        	hora_ev.set(Calendar.SECOND, 0);
        	hora_ev.add(Calendar.SECOND, v);
    	}catch(Exception e) {
    		this.error="Error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void latitud(String val) throws IOException {
    	try {
    		String val1, val2, val3;
        	val1=val.substring(0,3);
        	val2=val.substring(3,8);
        	val3=val1+"."+val2;
        	lat=Double.parseDouble(val3);
    	}catch(Exception e) {
    		this.error="error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void longitud(String val) throws IOException{
    	try {
    		String val1, val2, val3;
        	val1=val.substring(0,4);
        	val2=val.substring(4,8);
        	val3=val1+"."+val2;
        	lng=Double.parseDouble(val3);
    	}catch(Exception e) {
    		this.error="error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void velocidad(String val) throws IOException{
    	try {
    		String a="1.609";
        	this.velm=Float.parseFloat(val);
        	this.velk=velm*Float.parseFloat(a);
    	}catch(Exception e) {
    		this.error="Error"+e;
    		e.printStackTrace();
    	}
    }
    
    public void orientacion(String val) throws IOException{
    	try {
    		orient=Integer.parseInt(val);
    	}catch(Exception e) {
    		error="Error: "+e;
    		e.printStackTrace();
    	}
    }
    
    public String getError() {
    	return error;
    }
    
    public void EnviarTeleBasica() {
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    	System.out.println("El paquete de informacion es: "+telem);
    	System.out.println("Indice:"+indice);
    	System.out.println("Codigo:"+codigo_evento);
    	System.out.println("Fecha: "+diaev+" "+fecha_ev.get(Calendar.DAY_OF_MONTH)+"/"+fecha_ev.get(Calendar.MONTH)+"/"+fecha_ev.get(Calendar.YEAR));
    	System.out.println("Dia: "+diaev);
    	System.out.println("Horas:"+hora_ev.get(Calendar.HOUR_OF_DAY)+" Minutos: "+hora_ev.get(Calendar.MINUTE)+" Segundos: "+hora_ev.get(Calendar.SECOND));
    	System.out.println("Latitud: "+lat);
    	System.out.println("Longitud: "+lng);
    	System.out.println("Velocidad en millas por hora: "+velm+" Velocidad en Kilometros por hora: "+velk);
    	System.out.print("La orientacion es: "+orient+" ");
    	if(orient==0 || orient==360) {
    		System.out.print("Norte");
    	}
    	if(orient==180) {
    		System.out.println("Sur");
    	}
    	if(orient==90) {
    		System.out.println("Este");
    	}
    	if(orient==270) {
    		System.out.println("Oeste");
    	}
    	if((orient>0 && orient<90)) {
    		System.out.println("Noreste");
    	}
    	if(orient>90 && orient<180) {
    		System.out.println("Sureste");
    	}
    	if(orient>180 && orient<270) {
    		System.out.println("Suroeste");
    	}
    	if(orient>270 && orient<360) {
    		System.out.println("Noroeste");
    	}
    	System.out.println("--------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void EnviarTele_Ext() {
    	System.out.println("-------------------------------------------------------------------------------------------------------------------------");
    	System.out.println("El paquete de informacion es: "+telem);
    	System.out.println("Indice:"+indice);
    	System.out.println("Codigo:"+codigo_evento);
    	System.out.println("Fecha: "+diaev+" "+fecha_ev.get(Calendar.DAY_OF_MONTH)+"/"+fecha_ev.get(Calendar.MONTH)+"/"+fecha_ev.get(Calendar.YEAR));
    	System.out.println("Dia: "+diaev);
    	System.out.println("Horas:"+hora_ev.get(Calendar.HOUR_OF_DAY)+" Minutos: "+hora_ev.get(Calendar.MINUTE)+" Segundos: "+hora_ev.get(Calendar.SECOND));
    	System.out.println("Latitud: "+lat);
    	System.out.println("Longitud: "+lng);
    	System.out.println("Velocidad en millas por hora: "+velm+" Velocidad en Kilometros por hora: "+velk);
    	System.out.print("La orientacion es: "+orient+" ");
    	if(orient==0 || orient==360) {
    		System.out.print("Norte");
    	}
    	if(orient==180) {
    		System.out.println("Sur");
    	}
    	if(orient==90) {
    		System.out.println("Este");
    	}
    	if(orient==270) {
    		System.out.println("Oeste");
    	}
    	if((orient>0 && orient<90)) {
    		System.out.println("Noreste");
    	}
    	if(orient>90 && orient<180) {
    		System.out.println("Sureste");
    	}
    	if(orient>180 && orient<270) {
    		System.out.println("Suroeste");
    	}
    	if(orient>270 && orient<360) {
    		System.out.println("Noroeste");
    	}
    	System.out.println("");
    }
}
