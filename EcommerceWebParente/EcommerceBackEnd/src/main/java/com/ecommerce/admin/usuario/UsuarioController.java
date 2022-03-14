package com.ecommerce.admin.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.comum.entity.Cargo;
import com.ecommerce.comum.entity.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping("/usuarios")
	public String listarTodos(Model model) {
		List<Usuario> listarUsuarios = service.listarTodos();
		model.addAttribute("listarUsuarios", listarUsuarios);
		return "usuarios";
	}

	@GetMapping("/usuarios/cadastrar")
	public String novoUsuario(Model model) {
		List<Cargo> listarCargos = service.listarCargos();

		Usuario usuario = new Usuario();
		usuario.setAtivado(true);
		model.addAttribute("usuario", usuario);
		model.addAttribute("listarCargos", listarCargos);
		return "formulario_usuario";
	}

	@PostMapping("/usuarios/salvar")
	public String salvarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
		System.out.println(usuario);
		service.salvar(usuario);
		
		redirectAttributes.addFlashAttribute("message", "O usuário foi cadastrado com sucesso");
		return "redirect:/usuarios";
	}

}
