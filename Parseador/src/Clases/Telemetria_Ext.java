package Clases;

import java.io.IOException;

public class Telemetria_Ext extends Telemetria_Basica{
String te;
int igni=0;
int ext_pwr=0;
int acele=0;
int idle=0;
int vo=0;
String id;
int ioi, iof;
int pai, paf;
int pidlei, pidlef;
int pvoi, pvof;
int pidi, pidf;
int valores=1;
boolean telemetria_Ex;
boolean creado_c;
String io="", ac="", cl="", vos="", ids="";
boolean x=false;
boolean tipoT;
	public Telemetria_Ext(String t) {
		super(t);
	}
	
	public void Generar() throws IOException {
		if(this.telem.length()>=42) {
			if(this.telem.charAt(0)=='>' && this.telem.charAt(this.telem.length()-1)=='<') {
				if(this.telem.contains("REV")) {
					this.cambiar();
					if(this.telem.charAt(41)=='<') {
						telemetria_Ex=false;
						setCreado();
					}else {
						if(this.telem.charAt(41)==';') {
							telemetria_Ex=true;
							if(this.telem.contains(";IO")) {
								ioi=this.telem.indexOf(";IO")+4;
								iof=ioi+1;
								IO_Evento(telem.substring(ioi,iof));
							}else {
								io="no contiene IO";
							}
							if(this.telem.contains(";AC")) {
								pai=this.telem.indexOf(";AC")+4;
								paf=this.telem.indexOf(";", pai);
								Aceleracion_Evento(telem.substring(pai,paf));
							}else {
								ac="no contiene AC";
							}
							if(this.telem.contains(";CL")) {
								pidlei=this.telem.indexOf(";CL")+4;
								pidlef=this.telem.indexOf(";", pidlei);
								IDLE_Evento(telem.substring(pidlei, pidlef));
							}else {
								cl="no contiene IDLE";
							}
							if(this.telem.contains(";VO")) {
								pvoi=this.telem.indexOf(";VO")+4;
								pvof=this.telem.indexOf(";", pvoi);
								VO_Evento(telem.substring(pvoi,pvof));
							}else {
								vos="no contiene VO";
							}
							if(this.telem.contains(";ID")) {
								pidi=this.telem.indexOf(";ID")+4;
								pidf=this.telem.indexOf("<", pidi);
								ID_Evento(telem.substring(pidi,pidf));
							}else {
								ids="no contiene ID";
							}
							setCreado();
						}else {
							this.error="Error caracter no valido";
						}
					}
				}else {
					this.error="Tipo de linea no valida";
				}
			}else {
				this.error="No se encuentran el valor inicio o cierre de la linea";
			}
		}else {
			this.error="Error de tamaño";
		}
	}
	
	public void IO_Evento(String val) {
		try {
			int v= Integer.parseInt(val);
			switch(v) {
			case 0:
				igni=0;
				ext_pwr=0;
				break;
			case 1:
				igni=1;
				ext_pwr=0;
				break;
			case 2:
				igni=0;
				ext_pwr=1;
				break;
			case 3:
				igni=1;
				ext_pwr=1;
				break;
			default:
				this.error="Error valor no encontrado";
				igni=0;
				ext_pwr=0;
				break;
			}
		}catch(Exception e) {
			this.error="Error"+e;
			e.printStackTrace();
		}
	}
	
	public void Aceleracion_Evento(String val) throws IOException{
		try {
			acele=Integer.parseInt(val);
		}catch(Exception e) {
			this.error="Error: "+e;
			e.printStackTrace();
		}
		
	}
	
	public void IDLE_Evento(String val) {
		try {
			idle=Integer.parseInt(val);
		}catch(Exception e) {
			this.error="Error"+e;
			e.printStackTrace();
		}
	}
	
	public void VO_Evento(String val) {
		try {
			vo=Integer.parseInt(val);
		}catch(Exception e) {
			e.printStackTrace();
			this.error="Error"+e;
		}
	}
	
	public void ID_Evento(String val) {
		try {
			id=val;
		}catch(Exception e) {
			e.printStackTrace();
			this.error="Error"+e;
		}
	}
	
	public void GetTelem() {
		this.EnviarTele_Ext();
		if(io=="") {
			System.out.print("Ignicion: "+igni);
			if(igni==1) {
				System.out.println(" Activo");
			}else {
				System.out.println(" Inactivo");
			}
			System.out.print("Fuente de alimentación principal: "+ext_pwr);
			if(ext_pwr==1) {
				System.out.println(" EXT-PWR");
			}else {
				System.out.println(" BACKUP-BATTERY");
			}
		}else {
			System.out.println(io);
		}
		if(ac=="") {
			System.out.println("Aceleracion: "+acele+" Millas/Horas");
			System.out.println("Aceleracion: "+acele*3600+" Millas/Segundos");
		}else {
			System.out.println(ac);
		}
		
		if(cl=="") {
			System.out.println("Idle: "+idle);
		}else {
			System.out.println(cl);
		}
		
		if(vos=="") {
			System.out.println("Odometro Virtual: "+vo+"M");
		}else {
			System.out.println(vos);
		}
		
		if(ids=="") {
			System.out.println("ID: "+id);
		}else {
			System.out.println(ids);
		}
		
		System.out.println("-----------------------------------------------------------------------------------------------------");
	}
	
	public void setCreado() {
		if(error=="") {
			creado_c=true;
		}else {
			creado_c=false;
		}
	}
	
	public boolean getCreado() {
		return creado_c;
	}
	
	public void mostrartipo(){
		if(telemetria_Ex) {
			GetTelem();
		}else {
			EnviarTeleBasica();
		}
	}
	
	
}
