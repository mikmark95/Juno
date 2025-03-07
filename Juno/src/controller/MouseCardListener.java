package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Optional;

import model.CampoDiGioco;
import model.Carta;
import model.ColoreCartaSpeciale;
import model.GiocatoreAvversario;
import model.GridaUnoExceprtion;
import model.ManoVuotaException;
import model.PartitaTerminataException;
import model.ValoreCartaSpeciale;
import view.JCard;
import view.PannelloUno;
import view.PannelloVittoriaRound;
import view.SceltaColore;

/**
 * Classe che contiene gli eventi scatenati dal click dell'utente
 * 
 * @author michele marchetti
 *
 */
public class MouseCardListener implements MouseListener {

    /**
     * Variabile che contiene il riferimento del modello
     */
    private CampoDiGioco campoDiGioco;

    /**
     * Metodo costruttore
     */
    public MouseCardListener() {
        this.campoDiGioco = CampoDiGioco.instance();
    }

    /**
     * Metodo che esegue le istruzioni al verificarsi del click
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JCard card = (JCard) e.getSource();
        Carta cartaCampo = campoDiGioco.getPilaScarti().guardaCima();
        if (isValidMove(card, cartaCampo)) {
            try {
                handleValidMove(card, cartaCampo);
            } catch (GridaUnoExceprtion | ManoVuotaException e1) {
                handleUnoOrEmptyHandException(e1);
            }
        } else {
            // Pesca una carta!
        }
    }

    private boolean isValidMove(JCard card, Carta cartaCampo) {
        return cardMatchesPilaScarti(card, cartaCampo) || isFirstMove() || isSpecialCard(card);
    }

    private boolean cardMatchesPilaScarti(JCard card, Carta cartaCampo) {
        return card.getCarta().getValore().equals(cartaCampo.getValore())
                || card.getCarta().getColore().equals(cartaCampo.getColore());
    }

    private boolean isFirstMove() {
        return campoDiGioco.getPilaScarti().getPila().size() == 0;
    }

    private boolean isSpecialCard(JCard card) {
        return card.getCarta().getColore().equals(ColoreCartaSpeciale.NERO)
                || card.getCarta().getColore().equals(campoDiGioco.getColoreCampo());
    }

    private void handleValidMove(JCard card, Carta cartaCampo) throws GridaUnoExceprtion, ManoVuotaException {
        if (card.getCarta().getValore().equals(ValoreCartaSpeciale.JOLLY_PESCA_4)) {
            handleJollyPescaQuattro(card);
        } else {
            handleRegularMove(card);
        }
    }

    private void handleJollyPescaQuattro(JCard card) throws GridaUnoExceprtion, ManoVuotaException {
        Optional<Carta> cOpt = campoDiGioco.getGiocatoreUtente().getManoCarte().getMano().stream()
                .filter(x -> x.getColore().equals(campoDiGioco.getColoreCampo())).findAny();
        Carta c = cOpt.orElse(null);
        if (c == null) {
            playSpecialCard(card);
        } else {
            // Pesca una carta!
        }
    }

    private void handleRegularMove(JCard card) throws GridaUnoExceprtion, ManoVuotaException {
        campoDiGioco.getGiocatoreUtente().getManoCarte().scartaCarta(card.getCarta());
        playSpecialCard(card);
    }

    private void playSpecialCard(JCard card) throws GridaUnoExceprtion {
        AudioManager.getInstance().play("res/carta.wav"); // Effetto audio
        if (card.getCarta().getColore().equals(ColoreCartaSpeciale.NERO)) {
            new SceltaColore();
            campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);
        } else {
            campoDiGioco.getPilaScarti().aggiungiCarta(card.getCarta());
            campoDiGioco.setColoreCampo(card.getCarta().getColore());
            campoDiGioco.aggiorna(card.getCarta());
            campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);
            campoDiGioco.getTurnoPartita().prossimoTurno();
            campoDiGioco.aggiorna();
        }
    }

    private void handleUnoOrEmptyHandException(Exception e) {
        if (campoDiGioco.getPilaScarti().guardaCima().getColore().equals(ColoreCartaSpeciale.NERO)) {
            new SceltaColore();
            campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);
        }
        campoDiGioco.getGiocatoreUtente().setTurnoInCorso(false);
        campoDiGioco.getTurnoPartita().getTimerUno().getTimer().start();
        new PannelloUno(campoDiGioco.getGiocatoreUtente().getNickname());
        campoDiGioco.aggiorna();
    }

    private void handleGameEndException(PartitaTerminataException e) {
        int risultato = campoDiGioco.getListaAvversari().stream().mapToInt(GiocatoreAvversario::calcolaPuntiCarte).sum();
        try {
            campoDiGioco.getGiocatoreUtente().aggiungiPunti(risultato);
            new PannelloVittoriaRound(campoDiGioco.getGiocatoreUtente().getNickname());
        } catch (PartitaTerminataException e2) {
            campoDiGioco.getGiocatoreUtente().vittoria();
            campoDiGioco.getGiocatoreUtente().azzeraPunti();
            CampoDiGioco.instance().setPartitaTerminata(true);
            campoDiGioco.aggiorna();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // System.out.println("Entrato");
    }

    @Override
    public void mouseExited(MouseEvent e) {
       
    }
    
}
