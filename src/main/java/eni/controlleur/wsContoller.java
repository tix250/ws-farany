package eni.controlleur;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import eni.dao.BapampaInterface;
import eni.dao.MikaImpl;
import eni.dao.MikaInterface;
import eni.dao.TixInterface;
import eni.entities.Region;
import eni.entities.Signalement;
import eni.entities.StatSignialement;
import eni.entities.UserBackoffice;
import eni.entities.UtilisateurBO;
import eni.entities.UtilisateurFO;
import eni.entities.UtilisateurMobile;
import eni.repository.UserRepository;

@RestController
public class wsContoller {
	
	 @Autowired
	 private  TixInterface tixInterface;
	 
	 @Autowired
	 private  MikaInterface mikaInterface;
	 
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private BapampaInterface bapampaInterface;
	
	 @RequestMapping(value="/mobile",method=RequestMethod.GET)
	 public ArrayList<UtilisateurMobile> recupererUserMobile()
	 {
		 return  (ArrayList<UtilisateurMobile>) tixInterface.getAllUserMobile();
	 }
	 
	 @RequestMapping(value="/ListSignalementRegion/{id}",method=RequestMethod.GET)
	 public ArrayList<Signalement> recupererSignialement(@PathVariable int id)
	 {
		 return  (ArrayList<Signalement>) bapampaInterface.ListSignalementRegion(id);
	 }
	 
	 @RequestMapping(value="/donnerStatRegionSignialement",method=RequestMethod.GET)
	 public ArrayList<StatSignialement> donnerStatRegionSignialement()
	 {
		 return  (ArrayList<StatSignialement>) tixInterface.donnerStatRegionSignialement();
	 }
	 
	 @RequestMapping(value="/findRegion/{id}",method=RequestMethod.GET)
	 public Region findRegion(@PathVariable int id)
	 {
		 return   bapampaInterface.findRegion(id);
	 }
	 
	 @RequestMapping(value="/findUtilisateurMobile/{id}",method=RequestMethod.GET)
	 public UtilisateurMobile findUtilisateurMobile(@PathVariable int id)
	 {
		 return   bapampaInterface.findUtilisateurMobile(id);
	 }
	 
	 @RequestMapping(value="/signialement",method=RequestMethod.GET)
	 public ArrayList<Signalement> ListSignalementRegion()
	 {
		 return  tixInterface.recupererToutSignialement();
	 }
	 
	 @RequestMapping(value="/signialementNonAffecter",method=RequestMethod.GET)
	 public ArrayList<Signalement> signialementNonAffecter()
	 {
		 return  (ArrayList<Signalement>) mikaInterface.listeSignalement();
	 }
	 
	@RequestMapping(value="/login/{nom}/{mdp}",method=RequestMethod.GET)
	public UtilisateurMobile loginMobile(@PathVariable String nom ,@PathVariable String mdp )
	{
		UtilisateurMobile um = new UtilisateurMobile();
		um.setNom(nom);
		um.setMdp(mdp);
		return tixInterface.loginMobile(nom , mdp);
	}
	 
	 @RequestMapping(value="/loginMobile",method=RequestMethod.POST)
	 public UtilisateurMobile loginBO (@RequestBody UtilisateurMobile umobile )
	 {
		 UtilisateurMobile err = new UtilisateurMobile();
		 err.setNom("mot de passe ou email incorrect");
		 if(tixInterface.loginMobile(umobile.getEmail(), umobile.getMdp())!=null)
		 {
			 return tixInterface.loginMobile(umobile.getNom(), umobile.getMdp());
		 }
		 return err;
	 }
	 
	 @RequestMapping(value="/loginBo",method=RequestMethod.POST)
	 public UtilisateurBO loginBO (@RequestBody UtilisateurBO u )
	 {
		if(mikaInterface.connexion(u.getLogin(), u.getMdp()).getIdBO() != 0)
		{
			return mikaInterface.connexion(u.getLogin(), u.getMdp());
		}
		else
			return null;
	 }
	 
	 @RequestMapping(value="/loginFo",method=RequestMethod.POST)
	 public UtilisateurFO loginFO (@RequestBody UtilisateurFO ufo )
	 {
		 UtilisateurFO err = new UtilisateurFO();
		 err.setNom("mot de passe ou email incorrect");
		 if(bapampaInterface.connexionFO(ufo.getLogin(), ufo.getMdp())!=null)
		 {
			 return bapampaInterface.connexionFO(ufo.getLogin(), ufo.getMdp());
		 }
		 return null;
	 }
	 
	 @RequestMapping(value="/affecterSignalement",method=RequestMethod.POST)
	 public boolean affecterSignalement (@RequestBody  Signalement s )
	 {
		 
		 mikaInterface.affecterSignalement(s);
		 return true;
	 }
	 
	 @RequestMapping(value="/inscriptionMobile",method=RequestMethod.POST)
	 public String inscription (@RequestBody UtilisateurMobile umobile )
	 {
		 if(tixInterface.InscriptionMobile(umobile))
		 {
			 return "inscriction avec succee";
		 }
		return "desole le mot de passe est incorrect";
	 }
	 
	 @RequestMapping(value="/inscriptionBo",method=RequestMethod.POST)
	 public boolean inscription (@RequestBody UtilisateurBO u )
	 {
		 mikaInterface.insertUserBo(u);
		 return true;
	 }
	 
	 @RequestMapping(value="/inscriptionFO",method=RequestMethod.POST)
	 public boolean inscription (@RequestBody UtilisateurFO u )
	 {
		 bapampaInterface.insertUserFo(u);
		 return true;
	 }
	 
	 @Value("${file.upload-dir}")
	 String FILE_DIRECTORY;
	 
	 @RequestMapping(value="/insererSignialement", method=RequestMethod.POST)
	 public ResponseEntity<Object> insertSignialment(
			 @RequestParam("file") MultipartFile file ,
			 @RequestParam("id_user") String id_user ,
			 @RequestParam("description") String description,
			 @RequestParam("id_type_signalement") String id_type_signalement
			 ) throws IOException
	 { 
		 File myFile = new File(FILE_DIRECTORY+file.getOriginalFilename());
		 myFile.createNewFile();
		 FileOutputStream fos =new FileOutputStream(myFile);
		 fos.write(file.getBytes());
		 fos.close();
		 
		 Signalement s = new Signalement();
		 s.setDate(new Date());
		 s.setCoordonnee("ok");
		 s.setDescription(description);
		 s.setId_user(Integer.valueOf(id_user));
		 s.setId_type_signalement(Integer.valueOf(id_type_signalement));
		 s.setPhoto(FILE_DIRECTORY+file.getOriginalFilename());
		 s.setId_region(0);
		 
		 tixInterface.insererSignalement(s);
		 
		 return new ResponseEntity<Object>("image uploder avec succée", HttpStatus.OK);
	 }
	 
}
