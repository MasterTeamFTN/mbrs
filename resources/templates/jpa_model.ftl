// WARNING: This file was autogenerated by MagicDraw MBRS plugin.
// Do not update it because if you run generator again you changes will be deleted
package ${class.typePackage};

<#if class.name=="MbrsGeneratedApplication">
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ${class.name} {

    public static void main(String[] args) {
        SpringApplication.run(${class.name}.class, args);
    }
}
<#else>
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "${class.name?uncap_first}")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
${class.visibility} class ${class.name} {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

<#list properties as property>
    <#if property.upper == 1 >
        <#if property.reference==true>
    <#--    Ako je reference true, to znaci da je ovo polje referenca na neku drugu klasu    -->
            <#if property.referenceType =="shared" || property.referenceType =="composite" >
            <#--    Reference type (aggregation) se stavlja sa obe strane veze, Ovde se samo postavlja anotacija    -->
    @OneToOne
            <#else>
    @ManyToOne(fetch=FetchType.LAZY)
            </#if>
        <#else>
    @Column(name="${property.name}")
        </#if>
        <#-- Date se ispisuje kao date, zato ovde ova provera sa if property.type.name==date-->
<#--        ${property.visibility} <#if property.type.name == "date" > Date <#else>${property.type.name} </#if>  ${property.name};-->
    <#if property.type.name == "date" ||  property.type.name == "Date">
    @DateTimeFormat(pattern="yyyy-MM-dd")
    </#if>
    ${property.visibility} <#if property.type.name == "date" >Date <#else>${property.type.name} </#if><#if property.name != "" >${property.name}<#else>${property.type.name?uncap_first}</#if>;

    <#elseif property.upper == -1 >
    <#--    Ako je upper -1, u pitanju je neka kolekcija kao polje, Ako se stavi i shared, onda je manyToMany   -->
        <#if property.referenceType =="shared">
    @ManyToMany
        <#else>
    @OneToMany(mappedBy="${class.name?uncap_first}")
        </#if>
    ${property.visibility} Set<${property.type.name}> ${property.name} = new HashSet<${property.type.name}>();

    <#else>
        <#list 1..property.upper as i>
    @Column(name="${property.name}${i}")
    ${property.visibility} ${property.type} ${property.name}${i};
        </#list>
    </#if>
</#list>
}
</#if>
