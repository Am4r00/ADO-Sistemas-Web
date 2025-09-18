package com.meuapp.ado.controller;

import com.meuapp.ado.dto.SignUpUser;
import com.meuapp.ado.entity.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.meuapp.ado.service.UserService;

import java.util.Optional;

@Controller
public class UserWebController {

    private final UserService userService;

    public UserWebController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("form", new SignUpUser());
        model.addAttribute("action", "/cadastrar");
        return "index";
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") SignUpUser form,
                            BindingResult br,
                            RedirectAttributes ra,
                            Model model) {
        if (br.hasErrors()) {
            model.addAttribute("action", "/cadastrar");
            return "index";
        }
        userService.saveUser(form);
        ra.addFlashAttribute("msg", "Usuário cadastrado!");
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @ModelAttribute("msg") String msg, @ModelAttribute("msgErro") String msgErro) {
        model.addAttribute("users", userService.findAll());
        return "dashboard";
    }

    @GetMapping("/users/{id}/edit")
    public String editForm(@PathVariable("id") String id, Model model, RedirectAttributes ra) {
        var opt = userService.findAll().stream().filter(u -> u.getId().equals(id)).findFirst();
        if (opt.isEmpty()) {
            ra.addFlashAttribute("msgErro", "Usuário não encontrado.");
            return "redirect:/dashboard";
        }
        var u = opt.get();
        var form = new SignUpUser();
        form.setName(u.getNome());
        form.setCpf(u.getCpf());

        model.addAttribute("form", form);
        model.addAttribute("action", "/users/" + id + "/update");
        return "index";
    }

    @PostMapping("/users/{id}/update")
    public String update(@PathVariable("id") String id,
                         @Valid @ModelAttribute("form") SignUpUser form,
                         BindingResult br,
                         RedirectAttributes ra,
                         Model model) {
        if (br.hasErrors()) {
            model.addAttribute("action", "/users/" + id + "/update");
            return "index";
        }

        User payload = new User();
        payload.setNome(form.getName());
        payload.setCpf(form.getCpf());

        boolean ok = userService.updateUser(id, payload);
        if (!ok) {
            ra.addFlashAttribute("msgErro", "Usuário não encontrado.");
            return "redirect:/dashboard";
        }
        ra.addFlashAttribute("msg", "Usuário atualizado com sucesso!");
        return "redirect:/dashboard";
    }

    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable("id") String id, RedirectAttributes ra) {
        boolean ok = userService.deleteUser(id);
        ra.addFlashAttribute(ok ? "msg" : "msgErro", ok ? "Usuário excluído." : "Usuário não encontrado.");
        return "redirect:/dashboard";
    }
}
