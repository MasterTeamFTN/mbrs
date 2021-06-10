<#assign serviceName="${class.name?uncap_first}GenService">
package ${class.typePackage};

import lombok.RequiredArgsConstructor;
import ${class.typePackage}.model.${class.name};
import ${class.typePackage}.service.${class.name}Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/${class.name?lower_case}")
@RequiredArgsConstructor
public class ${class.name}GenController {
<#if class.page??>
    private final ${class.name?cap_first}GenService ${serviceName};

    <#if class.page.details?c=="true">
    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) {
        ${class.name} ${class.name?uncap_first} = ${serviceName}.findById(id);
        model.addAttribute("${class.name?uncap_first}", ${class.name?uncap_first});
        return "${class.name?uncap_first}-by-id";
    }
    </#if>

    <#if class.page.getAll?c=="true">
    @GetMapping
    public String findAll(Model model) {
        List<${class.name}> ${class.name?uncap_first}s = ${serviceName}.findAll();
        model.addAttribute("${class.name?uncap_first}s", ${class.name?uncap_first}s);
        return "all-${class.name?uncap_first}s";
    }
    </#if>

    <#if class.page.getAll?c=="true">
    @PostMapping("/add")
    public String create(@ModelAttribute ${class.name}(${class.name?uncap_first}) ${class.name?uncap_first}, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        ${class.name} result = ${serviceName}.save(${class.name?uncap_first});
        model.addAttribute("${class.name?uncap_first}", result);
        return "${class.name?uncap_first}-by-id";
    }
    </#if>
</#if>
}
