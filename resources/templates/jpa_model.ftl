package ${class.typePackage};

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    </#if>
    @Column(name="${property.name}")
    ${property.visibility} ${property.type} ${property.name};
    <#elseif property.upper == -1 >
    @OneToMany(mappedBy="${class.name}")
    ${property.visibility} Set<${property.type}> ${property.name} = new HashSet<${property.type}>();

    <#else>
    <#list 1..property.upper as i>
    @Column(name="${property.name}${i}")
    ${property.visibility} ${property.type} ${property.name}${i};
    </#list>
    </#if>
</#list>

}
