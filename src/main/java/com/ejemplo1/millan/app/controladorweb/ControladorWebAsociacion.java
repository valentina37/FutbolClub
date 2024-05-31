package com.ejemplo1.millan.app.controladorweb;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ejemplo1.millan.app.repository.AsociacionRepositorio;
import com.ejemplo1.millan.app.variables.Asociacion;


@Controller
public class ControladorWebAsociacion {
	
	@Autowired
	private AsociacionRepositorio asociacionRepositorio;
	
	@GetMapping("/verAsociacion.html")  
	public String listarAsociacion(Model model) {
		List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);
		
		return "verAsociacion";
	}
	
	@GetMapping("/formAsociacion.html")
	public String mostrarFormulario(Model model) {
		model.addAttribute("asociacion", new Asociacion());

		List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);
		
		List<String> listaPaises = Arrays.asList("Colombia", "Bolivia", "España", "Italia", "Inglaterra", "Francia" ,"Uruguay");
		model.addAttribute("listaPaises", listaPaises);

		return "formAsociacion";
	}
	
	@PostMapping("/guardarAsociacion")
	public String guardarAsociacion(Asociacion asociacion) {
		asociacionRepositorio.save(asociacion);
		return "redirect:/verAsociacion.html";
	}
	
	@GetMapping("/asociacion/editar/{id}")
	public String modificarAsociacion(@PathVariable("id") Integer id, Model model) {
		Asociacion asociacion = asociacionRepositorio.findById(id).get();
		model.addAttribute("asociacion", asociacion);
		
		List<Asociacion> listaAsociacion = asociacionRepositorio.findAll();
		model.addAttribute("listaAsociacion", listaAsociacion);
		
		List<String> listaPaises = Arrays.asList("Colombia", "Bolivia", "España", "Italia", "Inglaterra", "Francia","Uruguay");
		model.addAttribute("listaPaises", listaPaises);

		return "formAsociacion";
	}
	
	@GetMapping("/asociacion/eliminar/{id}")
	public String eliminarAsociacion(@PathVariable("id") Integer id, Model model) {
		asociacionRepositorio.deleteById(id);
		return "redirect:/verAsociacion.html";
	}
}
