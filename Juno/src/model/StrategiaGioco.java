package model;

/**
 * Interfaccia che serve per modellare una gerarchia di comportamenti
 * @author michelemarchetti
 *
 */
public interface StrategiaGioco {
	
	/**
	 * Metodo che fa eseguire una mossa di gioco
	 * @throws GridaUnoExceprtion 
	 * @throws ManoVuotaException 
	 */
	public void faiMossa() throws GridaUnoExceprtion, ManoVuotaException;
}
