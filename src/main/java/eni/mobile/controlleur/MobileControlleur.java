package eni.mobile.controlleur;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import antlr.StringUtils;
import eni.dao.TixInterface;
import eni.entities.Region;
import eni.entities.Signalement;
import eni.repository.RegionRepository;

@Controller
public class MobileControlleur {
	@Autowired
	RegionRepository region ;
	
	@Autowired
	TixInterface metier;
	
	String uplodDirectory = "src/main/resources/";
    	
	@RequestMapping(value="/index")
	public String index(Model model)
	{
		//List<Region> listeRegion = region.findAll();
		List<Region> listeRegion = metier.findAllRegion();
		model.addAttribute("listeRegion" , listeRegion);
		return "viewmobile";
	}
	
	@RequestMapping(value="/loginMobile")
	public String loginMobile(Model model)
	{
		//List<Region> listeRegion = region.findAll();
		List<Region> listeRegion = metier.findAllRegion();
		model.addAttribute("listeRegion" , listeRegion);
		return "login";
	}
	
	@PostMapping(value="/checkLoginMobile")
	public String checkLoginMobile(
			@RequestParam("email") String email , 
			@RequestParam("mdp") String mdp , 
			HttpSession session)
	{
		if(metier.loginMobile(email, mdp)!=null)
		{
			session.setAttribute("id", metier.loginMobile(email, mdp).getId_user());
			return "viewmobile";
		}
		else
			return "login";
	}
	
	@PostMapping("/test")
	@ResponseBody
	public String submitForm(
			@RequestParam String nom_signalement , 
			@RequestParam String description ,
			@RequestParam int id_type_signalement,
			@RequestParam("image") MultipartFile image
			) {
		
		
		Signalement s = new Signalement();
		s.setCoordonnee("test");
		s.setDate(new java.util.Date());
		s.setDescription(description);
		s.setId_type_signalement(id_type_signalement);
		s.setStatut(1);
		s.setId_region(0);
		
		Path path = Paths.get("uplods/");
		try {
			InputStream inputStream = image.getInputStream();
			Files.copy(inputStream , path.resolve(image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
			s.setPhoto(image.getOriginalFilename().toLowerCase());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		metier.insererSignalement(s);
	    return id_type_signalement + " ";
	}
}
