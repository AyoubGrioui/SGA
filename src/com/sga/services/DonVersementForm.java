package com.sga.services;

import com.sga.entities.DonVersement;
import com.sga.helpers.SGAUtil;
import com.sga.repositories.Repository;
import com.sga.repositories.RepositoryFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class DonVersementForm {

	private static final String CHAMP_NUMERO_DE_COMPTE = "numeroCompteBanqueDonVersement";
	private static final String CHAMP_DATE_DON = "dateDon";
	private static final String CHAMP_MONTANT = "montant";

	private Map<String, String> erreurs = new HashMap<String, String>();

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public DonVersement creerDonVersement(HttpServletRequest request) {

		String numCompte = getValeurChamp(request, CHAMP_NUMERO_DE_COMPTE);
		String dateDon = getValeurChamp(request, CHAMP_DATE_DON);
		String montant = getValeurChamp(request, CHAMP_MONTANT);

		DonVersement donVersement = new DonVersement();

		try {
			validationNumCompte(numCompte);
		} catch (Exception e) {
			setErreurs(CHAMP_NUMERO_DE_COMPTE, e.getMessage());
		}
		donVersement.setNumeroCompteBanque(numCompte);

		double valeurMontant = -1;
		try {
			valeurMontant = validationMontant(montant);
		} catch (Exception e) {
			setErreurs(CHAMP_MONTANT, e.getMessage());
		}
		donVersement.setMontant(valeurMontant);

		try {
			validationDate(CHAMP_DATE_DON);
		} catch (Exception e) {
			setErreurs(CHAMP_DATE_DON, e.getMessage());
		}
		donVersement.setDateDon(SGAUtil.StringToLocalDate(dateDon));


		RepositoryFactory repFactory = new RepositoryFactory();
		Repository rep = repFactory.getDonVersementRepository();
		rep.create(donVersement);

		return donVersement;
	}

	/*Fonction de validation d'un numero de compte */

	private void validationNumCompte(String numCompte) throws Exception {
		if (numCompte != null) {
			if (!numCompte.matches("^\\d{10,}$")) {
				throw new Exception("Merci d'entrer un numero de compte valide.");
			}
		} else {
			throw new Exception("Merci d'entrer un numero de compte.");
		}
	}

	/*Fonction de validation d'un montant*/

	private double validationMontant(String montant) throws Exception {
		double temp;
		if (montant != null) {
			try {
				temp = Double.parseDouble(montant);
				if (temp < 0) {
					throw new Exception("Le montant doit �tre un nombre positif.");
				}
			} catch (NumberFormatException e) {
				temp = -1;
				throw new Exception("Le montant doit �tre un nombre.");
			}
		} else {
			temp = -1;
			throw new Exception("Merci d'entrer un montant.");
		}
		return temp;
	}

	//validation date
	private void validationDate(String date) throws Exception
	{

		if (date == null) {
			{
				throw new Exception("Merci d'entrer une date.");
			}
		}
	}
		/* ajoute un message correspondant au champ specifie a la map des erreurs */

		private void setErreurs (String champ, String message){
			erreurs.put(champ, message);
		}


		/* methode utilitaire qui retourne null si un champ est vide, et son contenu sinon */

		private static String getValeurChamp (HttpServletRequest request, String nomChamp){
			String valeur = request.getParameter(nomChamp);
			if (valeur == null || valeur.trim().length() == 0) {
				return null;
			} else {
				return valeur;
			}
		}
}

