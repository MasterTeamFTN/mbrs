package ${class.typePackage};

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "TODO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
${class.visibility} class ${class.name} {  
<#list properties as property>
    @Column(name="TODO")
	<#if property.upper == 1 >   
      ${property.visibility} ${property.type} ${property.name};
    <#elseif property.upper == -1 > 
      ${property.visibility} Set<${property.type}> ${property.name} = new HashSet<${property.type}>();
    <#else>   
    	<#list 1..property.upper as i>
      ${property.visibility} ${property.type} ${property.name}${i};
		</#list>  
    </#if>     
</#list>

}
