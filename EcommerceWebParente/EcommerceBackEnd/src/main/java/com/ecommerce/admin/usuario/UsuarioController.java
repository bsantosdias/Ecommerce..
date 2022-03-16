package com.ecommerce.admin.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.comum.entity.Cargo;
import com.ecommerce.comum.entity.Usuario;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@GetMapping("/usuarios")
	public String listarPrimeiraPagina(Model model) {
		return listByPage(1, model, null);
	}

	@GetMapping("/usuarios/pagina/{numPagina}")
	public String listByPage(@PathVariable(name = "numPagina") int numPagina, Model model,
			@Param("keyword") String keyword) {

		Page<Usuario> pagina = service.listByPage(numPagina, keyword);
		List<Usuario> listarUsuarios = pagina.getContent();

		long comecoConta = (numPagina - 1) * UsuarioService.USERS_PER_PAGE + 1;
		long finalConta = comecoConta + UsuarioService.USERS_PER_PAGE - 1;

		if (finalConta > pagina.getTotalElements()) {
			finalConta = pagina.getTotalElements();
		}

		model.addAttribute("paginaAtual", numPagina);
		model.addAttribute("totalPaginas", pagina.getTotalPages());
		model.addAttribute("comecoConta", comecoConta);
		model.addAttribute("finalConta", finalConta);
		model.addAttribute("totalItens", pagina.getTotalElements());
		model.addAttribute("listarUsuarios", listarUsuarios);
		model.addAttribute("keyword", keyword);
		return "usuarios";

	}

	@GetMapping("/usuarios/cadastrar")
	public String novoUsuario(Model model) {
		List<Cargo> listarCargos = service.listarCargos();
		Usuario usuario = new Usuario();
		usuario.setAtivado(true);
		model.addAttribute("usuario", usuario);
		model.addAttribute("listarCargos", listarCargos);
		model.addAttribute("pageTitle", "Criar novo usuário");

		return "formulario_usuario";
	}

	@PostMapping("/usuarios/salvar")
	public String salvarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
		System.out.println(usuario);
		service.salvar(usuario);
		redirectAttributes.addFlashAttribute("message", "O usuário foi salvo com sucesso");
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/editar/{id}")
	public String editarUsuario(@PathVariable(name = "id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			Usuario usuario = service.get(id);
			List<Cargo> listarCargos = service.listarCargos();
			model.addAttribute("usuario", usuario);
			model.addAttribute("pageTitle", "Editar usuário (ID: " + id + ")");
			model.addAttribute("listarCargos", listarCargos);
			return "formulario_usuario";
		} catch (UserNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
			return "redirect:/usuarios";
		}

	}

	@GetMapping("/usuarios/deletar/{id}")
	public String deletarUsuario(@PathVariable(name = "id") Integer id, Model model,
			RedirectAttributes redirectAttributes) {
		try {
			service.delete(id);
			redirectAttributes.addFlashAttribute("message", "O usuário de ID " + id + " foi excluido");

		} catch (UserNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		return "redirect:/usuarios";
	}

	@GetMapping("/usuarios/{id}/ativado/{status}")
	public String atualizarStatusAtivadoUsuario(@PathVariable("id") Integer id, @PathVariable("status") boolean enabled,
			RedirectAttributes redirectAttributes) {

		service.atualizarStatusAtivadoUsuario(id, enabled);
		String status = enabled ? "ativado" : "desativado";
		String message = "O usuário " + id + " foi " + status;
		redirectAttributes.addFlashAttribute("message", message);

		return "redirect:/usuarios";

	}

}
