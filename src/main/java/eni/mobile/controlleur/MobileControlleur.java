package eni.mobile.controlleur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import eni.dao.TixInterface;
import eni.entities.Region;
import eni.repository.RegionRepository;

@Controller
public class MobileControlleur {
	@Autowired
	RegionRepository region ;
	
	@Autowired
	TixInterface metier;
	
	@RequestMapping(value="/index")
	public String index(Model model)
	{
		//List<Region> listeRegion = region.findAll();
		List<Region> listeRegion = metier.findAllRegion();
		model.addAttribute("listeRegion" , listeRegion);
		return "viewmobile";
	}
}
