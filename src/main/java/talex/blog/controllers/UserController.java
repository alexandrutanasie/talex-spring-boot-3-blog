package talex.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import talex.blog.components.AppComponent;
import talex.blog.entities.SiteUser;
import talex.blog.services.SiteUserService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    private AppComponent appComponent;

    @Autowired
    private SiteUserService siteUserService;

    @Autowired PasswordEncoder passwordEncoder;

    @GetMapping("/profile")
    public String loginForm(Model model) {
        SiteUser siteUser = appComponent.getCurrentUser();

        model.addAttribute("user", siteUser);

        return "admin/user/profile";
    }

    @GetMapping("/change-password")
    public String changePassowrdForm(Model model) {
        SiteUser siteUser = new SiteUser();

        model.addAttribute("user", siteUser);

        return "admin/user/change-password";
    }

    @PostMapping("/update-password")
    public String updatePassword(@Validated(SiteUser.PasswordValidationGroup.class) @ModelAttribute("user") SiteUser user,  BindingResult result, Model model, RedirectAttributes redirectAttributes) {
       
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/user/change-password";
        }

        siteUserService.updateUserPassword(appComponent.getCurrentUser().getId(), user.getPassword());

        redirectAttributes.addFlashAttribute("success", "Password successfully updated");
        return "redirect:/admin/";
    }

    @PostMapping("/save-profile")
    public String postSaveProfile(@Validated(SiteUser.ProfileValidationGroup.class) @ModelAttribute("user") SiteUser user,  BindingResult result, Model model, RedirectAttributes redirectAttributes) {

        SiteUser currentUser = appComponent.getCurrentUser();

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "admin/user/profile";
        }

        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());

        siteUserService.updateUser(currentUser);

        redirectAttributes.addFlashAttribute("success", "Profile information saved");
        return "redirect:/admin/";
    }
    
}
