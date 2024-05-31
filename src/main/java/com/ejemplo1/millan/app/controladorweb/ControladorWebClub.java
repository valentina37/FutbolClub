package com.ejemplo1.millan.app.controladorweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ejemplo1.millan.app.repository.AsociacionRepositorio;
import com.ejemplo1.millan.app.repository.ClubRepositorio;
import com.ejemplo1.millan.app.repository.CompeticionRepositorio;
import com.ejemplo1.millan.app.repository.EntrenadorRepositorio;
import com.ejemplo1.millan.app.variables.Asociacion;
import com.ejemplo1.millan.app.variables.Club;
import com.ejemplo1.millan.app.variables.Competicion;
import com.ejemplo1.millan.app.variables.Entrenador;


@Controller
public class ControladorWebClub {
	@Autowired
	private ClubRepositorio clubRepositorio;
	
	@Autowired
	private EntrenadorRepositorio entrenadorRepositorio;
	
	@Autowired
	private AsociacionRepositorio asociacionRepositorio;
	
	@Autowired
	private CompeticionRepositorio competicionRepositorio;
	
	@GetMapping("/verClub.html")  
	public String listarClub(Model model) {
		List<Club> listaClub = clubRepositorio.findAll();
		model.addAttribute("listaClub", listaClub);
		
		return "verClub"; 
	}
	
	@GetMapping("formClub.html")
	public String mostrarFormulario(Model model) {
		model.addAttribute("club", new Club());
		
		List<Entrenador> listaEntrenador = entrenadorRepositorio.findAll();
		model.addAttribute("listaEntrenador", listaEntrenador);
		
		List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);
		
		List<Competicion> listaCompeticion = competicionRepositorio.findAll();
		model.addAttribute("listaCompeticion", listaCompeticion);
		
		return "formClub";
	}
	
	@PostMapping("/guardarClub")
	public String guardarClub(Club club) {
		clubRepositorio.save(club);
		return "redirect:/verClub.html";
	}
	
	@GetMapping("/club/editar/{id}")
	public String modificarClub(@PathVariable("id") Integer id, Model model) {
		Club club = clubRepositorio.findById(id).get();
		model.addAttribute("club", club);
		
		List<Entrenador> listaEntrenador = entrenadorRepositorio.findAll();
		model.addAttribute("listaEntrenador", listaEntrenador);
		
		List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);
		
		List<Competicion> listaCompeticion = competicionRepositorio.findAll();
		model.addAttribute("listaCompeticion", listaCompeticion);
		
		return "formClub";
	}
	
	@GetMapping("/club/eliminar/{id}")
	public String eliminarClub(@PathVariable("id") Integer id, Model model) {
		clubRepositorio.deleteById(id);
		return "redirect:/verClub";
	}
	
	@GetMapping("/inicio")
	public String Inicio() {
		return "index.html";
	}
}
