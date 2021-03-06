// WARNING: This file was autogenerated by MagicDraw MBRS plugin.
// Do not update it because if you run generator again you changes will be deleted
<#assign serviceName="${class.name?uncap_first}GenService">
package ${class.typePackage};

import java.util.List;
<#if class.name?lower_case=="home">
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeGenController {

    @GetMapping({"/", "/index"})
    public String home() {
        return "index";
    }
}

<#else>
import lombok.RequiredArgsConstructor;
import ${class.typePackage?keep_before(".")}.model.${class.name};
import ${class.typePackage?keep_before(".")}.service.${class.name}GenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/${class.name?lower_case}")
@RequiredArgsConstructor
public class ${class.name}GenController {
<#if class.page??>
    private final ${class.name?cap_first}GenService ${serviceName};

    <#if class.page.details?c=="true">
    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Long id) throws Exception {
        ${class.name} ${class.name?uncap_first} = ${serviceName}.findById(id);
        model.addAttribute("${class.name?uncap_first}", ${class.name?uncap_first});
        return "${class.name}";
    }
    </#if>

    <#if class.page.getAll?c=="true">
    @GetMapping("/all")
    public String findAll(Model model) {
        List<${class.name}> ${class.name?uncap_first}s = ${serviceName}.findAll();
        model.addAttribute("${class.name?uncap_first}s", ${class.name?uncap_first}s);
        return "${class.name}s";
    }
    </#if>

    <#if class.page.create?c=="true">

    @GetMapping("/create")
    public String create() {
        return "Create${class.name}";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ${class.name} ${class.name?uncap_first}, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        ${class.name} saved${class.name} = ${serviceName}.save(${class.name?uncap_first});
        model.addAttribute("${class.name?uncap_first}", saved${class.name});
        return "${class.name}";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) throws Exception {
        ${class.name} entity = ${serviceName}.findById(id);
        ${serviceName}.delete(entity);

        List<${class.name}> ${class.name?uncap_first}s = ${serviceName}.findAll();
        model.addAttribute("${class.name?uncap_first}s", ${class.name?uncap_first}s);
        return "${class.name}s";
    }
    </#if>
    <#if class.page.update?c=="true">

    @GetMapping("/update/{id}")
    public String edit(@PathVariable Long id, Model model) throws Exception {
        ${class.name} ${class.name?uncap_first} = ${serviceName}.findById(id);
        model.addAttribute("${class.name?uncap_first}", ${class.name?uncap_first});
        return "Edit${class.name}";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute ${class.name} ${class.name?uncap_first}, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }

        ${class.name} saved${class.name} = ${serviceName}.save(${class.name?uncap_first});
        model.addAttribute("${class.name?uncap_first}", saved${class.name});
        return "${class.name}";
    }
    </#if>
</#if>
}

</#if>
