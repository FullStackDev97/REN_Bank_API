package com.banque.rest;

import com.banque.Login;
import com.banque.entity.CompteEntity;
import com.banque.entity.ICompteEntity;
import com.banque.entity.IOperationEntity;
import com.banque.entity.IUtilisateurEntity;
import com.banque.entity.OperationEntity;
import com.banque.service.IAuthentificationService;
import com.banque.service.ICompteService;
import com.banque.service.IOperationService;
import com.banque.web.bean.HistoriqueBean;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/service-banque")
public class ServiceBanqueController {

    @Autowired
    @Qualifier("authentificationService")
    private IAuthentificationService serviceAuthentification;

    @Autowired
    @Qualifier("compteService")
    private ICompteService serviceCompte;

    @Autowired
    @Qualifier("operationService")
    private IOperationService serviceOperation;

    @PostMapping("/authentifier")
    public Integer authentifier(@RequestBody Login login) throws Exception {
        // NE JAMAIS FAIRE CELA DANS LA REALITE
        // NE JAMAIS LOGUER/AFFICHER LE MOT DE PASSE
        // Logique d'authentification
        // ...
    	System.out.println(login);
        return serviceAuthentification.authentifier(login.getUser_name(), login.getPassword()).getId();
    	//return "Le login est "+unLogin+" et le mdp est "+unMdp;
    	
    }

    @PostMapping("/selectCompte")
    public CompteEntity[] selectCompte(@RequestParam("unUtilisateurId") Integer unUtilisateurId) throws Exception {
        // Logique de sélection des comptes
        // ...
        List<ICompteEntity> comptes = serviceCompte.selectAll(unUtilisateurId);
        System.out.println(comptes.toArray(new CompteEntity[0]));
        return comptes.toArray(new CompteEntity[0]);
    }

    @PostMapping("/selectOperation")
    public OperationEntity[] selectOperation(
        @RequestParam("unUtilisateurId") Integer unUtilisateurId,
        @RequestParam("unCompteId") Integer unCompteId,
        @RequestParam("dateDeb") Date dateDeb,
        @RequestParam("dateFin") Date dateFin,
        @RequestParam("creditDebit") Boolean creditDebit
    ) throws Exception {
        // Logique de sélection des opérations
        // ...
        List<IOperationEntity> operations = serviceOperation.selectCritere(unUtilisateurId, unCompteId, dateDeb, dateFin, creditDebit, !creditDebit);
        return operations.toArray(new OperationEntity[0]);
    }
    
    @PostMapping("/virement")
    public List<IOperationEntity> virement(
        @RequestParam("unUtilisateurId") Integer unUtilisateurId,
        @RequestParam("unCompteIdSrc") Integer unCompteIdSrc,
        @RequestParam("unCompteIdDst") Integer unCompteIdDst,
        @RequestParam("unMontant") Double unMontant
    ) throws Exception {
          List<IOperationEntity> resultat = serviceOperation.faireVirement(unUtilisateurId, unCompteIdSrc, unCompteIdDst, unMontant);
          return resultat;        
    } 
    
    @PostMapping("/compteHistorique")
	public List<IOperationEntity> showHistorique(@RequestParam("inNumeroCompte") String inNumeroCompte,@RequestParam("user_id") String user_id) {
		
		HistoriqueBean historiqueBean = new HistoriqueBean(inNumeroCompte);
		historiqueBean.setCredit(Boolean.TRUE);
		historiqueBean.setDebit(Boolean.TRUE);
		
		List<IOperationEntity> listeOperations = null;
		// c'est l'annotation qui fait le lien avec la session
		
		try {
			 listeOperations = this.serviceOperation.selectAll( Integer.parseInt(user_id) ,historiqueBean.getCptId());
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return  listeOperations ;
		
	}
}
