package com.schoolManage.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.schoolManage.dao.AttestationRepository;
import com.schoolManage.dao.EtudiantRepository;
import com.schoolManage.dao.FiliereRepository;
import com.schoolManage.dao.SemestreFiliereRepository;
import com.schoolManage.dao.SessionRepository;
import com.schoolManage.entite.Attestation;
import com.schoolManage.entite.Etudiant;
import com.schoolManage.entite.Filiere;
import com.schoolManage.entite.SemestreFiliere;
import com.schoolManage.entite.Session;

@Service
@Transactional
public class AttestationInitServiceIMPL implements AttestationInitService{

	@Autowired private SemestreFiliereRepository semestreFiliereRepository;
	@Autowired private FiliereRepository filiereRepository;
	@Autowired private SessionRepository sessionRepository;
	@Autowired private EtudiantRepository etudiantRepository;
	@Autowired private AttestationRepository attestationRepository;
	
	private long inscription_annee=2019;
	private int cmp =0; 
	
	@Override
	public void initFilieres() {
		// TODO Auto-generated method stub
		Stream.of("BDCC","GLSID","GEMSI","GECSI").forEach(nameFiliere -> {
			System.out.println(nameFiliere);
			Filiere filiere = new Filiere();
			filiere.setFiliereLibelle(nameFiliere);
			filiereRepository.save(filiere);
		});
	}
	@Override
	public void initSemestreFilieres() {
		// TODO Auto-generated method stub
		//String[] session_year = new String[] {"2019","2020","2021"};
		//sessionRepository.findAll().forEach(session -> {
			filiereRepository.findAll().forEach(filiere -> {
				inscription_annee=2019;
				Stream.of("1ere année Filière ING","2eme année Filière ING","3eme année Filière ING").forEach(year -> {
					SemestreFiliere semestreFiliere = new SemestreFiliere();
					semestreFiliere.setFiliere(filiere);
					semestreFiliere.setSession(sessionRepository.findById(Long.toString(inscription_annee)).get());
					semestreFiliere.setSemestreFiliereLibelle(year + " : " + filiere.getFiliereLibelle());
					semestreFiliereRepository.save(semestreFiliere);
					inscription_annee = inscription_annee + 1;
				});
			});
		//});
	}
	@Override
	public void initSessions() {
		// TODO Auto-generated method stub
		System.out.println("Session");
		Stream.of("2019","2020","2021").forEach(year -> {
			Session session = new Session();
			session.setAnnee(year);
			session.setNbrSemestre(2);
			sessionRepository.save(session);
		});
	}
	@Override
	public void initEtudiants() {
		// TODO Auto-generated method stub
		
		Long[] code = new Long[] {(long) 11111111,(long) 22222222,(long) 33333333};
		String[] firstName = new String[] {"El-RHOMARI","AMZIL","BOUBLEH"};
		String[] lastName = new String[] {"HICHAM","YOUSSEF","AYMAN"};
		String[] cni = new String[] {"JK31566","JK32566","JK33566"};
		String[] cne = new String[] {"D13381528","D14234567","D34256789"};
		Date[] date_naiss;
		try {
			date_naiss = new Date[] {new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-16"),
											new SimpleDateFormat("yyyy-MM-dd").parse("1998-03-26"),
											new SimpleDateFormat("yyyy-MM-dd").parse("1998-09-10")};
			String[] local_naiss = new String[] {"AGADIR","AGADIR","AZEMOUR"};
			String[] mail = new String[] {"hicham.el@gmail.com","youssef.am@gmail.com","ayman.bo@gmail.com"};
			String[] info = new String[] {"Good","Good","Good"};
			filiereRepository.findAll().forEach(filiere -> {
				for(int i=0;i <3; i++) {
					System.out.println("count"+i);
					Etudiant etudiant = new Etudiant();
					etudiant.setId(code[i]+cmp);
					etudiant.setFirstname(firstName[i]);
					etudiant.setLastname(lastName[i]);
					etudiant.setCni(cni[i]);
					etudiant.setCne(cne[i]);
					etudiant.setSex("male");
					etudiant.setDate_naissance(date_naiss[i]);
					etudiant.setVille_naissance(local_naiss[i]);
					etudiant.setEmail(mail[i]);
					etudiant.setInfos(info[i]);
					etudiant.setFiliere(filiere);
					etudiantRepository.save(etudiant);
					cmp = cmp + 1;
					System.out.println("Filiere : "+etudiant.getFiliere().getFiliereLibelle()+" Full Name : "+etudiant.getFirstname()+" "+etudiant.getLastname());
				}
			});
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	@Override
	public void initAttestations() {
		// TODO Auto-generated method stub
		etudiantRepository.findAll().forEach(etudiant -> {
			etudiant.getFiliere().getSemestrefiliereList().forEach(semestre -> {
				if(semestre.getFiliere().equals(etudiant.getFiliere()) && semestre.getSession().getAnnee().equals("2020")) {
					Attestation attestation = new Attestation();
					attestation.setCodeEtudiant(etudiant.getId());
					attestation.setNomComplet(etudiant.getFirstname() + " " + etudiant.getLastname());
					attestation.setCin(etudiant.getCni());
					attestation.setCne(etudiant.getCne());
					attestation.setDate_naissance(etudiant.getDate_naissance());
					attestation.setVille_naissance(etudiant.getVille_naissance());
					attestation.setAnnee_session(semestre.getSession().getAnnee()+"/"+Integer.toString(Integer.parseInt(semestre.getSession().getAnnee())+1));
					attestation.setAnnee_univers(semestre.getSemestreFiliereLibelle());
					attestation.setType_diplome("Fl. Ing. "+etudiant.getFiliere().getFiliereLibelle());
					attestation.setEtudiant(etudiant);
					attestation.setSession(semestre.getSession());
					attestationRepository.save(attestation);
				}
			});
		});
	}
}
