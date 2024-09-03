package br.com.citrus.ticket.shared.utils;

import lombok.Getter;

import java.util.Calendar;

public enum TimeUnitCustom {

	DIA("enum.unidade.tempo.dia", Calendar.DAY_OF_YEAR) {
		@Override
		public Long converterTempoMinutos(Integer tempo) {
			if (tempo != null) {
				return Long.valueOf(tempo * 1440);// 1440 minutos a cada a dia de 24 horas
			}
			return null;
		}
	},
	HORA("enum.unidade.tempo.hora", Calendar.HOUR_OF_DAY) {
		@Override
		public Long converterTempoMinutos(Integer tempo) {
			if (tempo != null) {
				return Long.valueOf(tempo * 60);//60 minutos por hora
			}
			return null;
		}
	},
	MINUTO("enum.unidade.tempo.minuto", Calendar.MINUTE) {
		@Override
		public Long converterTempoMinutos(Integer tempo) {
			if (tempo != null) {
				return Long.valueOf(tempo);
			}
			return null;
		}
	};

	@Getter
	private String label;
	@Getter
	private int tempo;

	private TimeUnitCustom(String label, int tempo) {
		this.label = label;
		this.tempo = tempo;
	}
	
	public abstract Long converterTempoMinutos(Integer tempo);
	
	/**
	 * Método que formata em hora:minutos:segundos
	 * @param segundos
	 * @return
	 */
	public static String formatar(long segundos) {
		long segundo = segundos % 60;
		long minutos = segundos / 60; 
		long minuto = minutos % 60; 
		long horas = minutos / 60;

		return String.format ("%02d:%02d:%02d", horas, minuto, segundo);
	}
	
	/**
	 * Método que formata em dia(s) hora:minutos:segundos
	 * @param segundos
	 * @return
	 */
	public static String formatarDiasHoras(long segundos) {
		long segundo = segundos % 60;
		long minutos = segundos / 60; 
		long minuto = minutos % 60; 
		long horas = minutos / 60;
		if(horas >= 24){
			long dias = horas / 24;
			return String.format ("%2d Dia(s) %02d:%02d", dias, (horas % 24), minuto);
		}else{
			return String.format ("%02d:%02d:%02d", horas, minuto, segundo);
		}
	}
	
	public static Long converterSegundos(Long minutos) {
		return minutos * 60;
	}
	
	public static Long converterMilesegundos(Long segundos) {
		return segundos * 1000;
	}
	
	public static Long parseMinutosMilliseconds(Long minutos) {
		return minutos * 60 * 1000;
	}

}
