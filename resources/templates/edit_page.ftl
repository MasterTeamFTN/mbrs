<!DOCTYPE HTML>
<html>
<head>
    <title>Edit ${class.name}</title>
</head>
<body>
<a href="javascript:history.back()">back</a>
<#if class.page?? && class.page.update?c=="true">
    <div>
        <fieldset>
            <legend>Edit ${class.name}</legend>
            <form name="product" action="${r"${springMacroRequestContext.getContextPath()}"}/${class.name?uncap_first}/update/${r"${"}${class.name?uncap_first}.id${r"}"}" method="post">
            <#list properties as property>
                <#if property.name != "id">
                <#if property.type.name == "Integer" || property.type.name == "Double">
                ${property.name?cap_first}: <input type="number" name="${property.name}" value="${r"${"}${class.name?uncap_first}.${property.name}${r"}"}" /><br/>
                <#elseif property.type.name == "String">
                ${property.name?cap_first}: <input type="text" name="${property.name}" value="${r"${"}${class.name?uncap_first}.${property.name}${r"}"}" /><br/>
                <#elseif property.type.name == "Boolean" >
                ${property.name?cap_first}: <input type="checkbox" id="${property.name}" value="${r"${"}${class.name?uncap_first}.${property.name}${r"}"}">
                <#elseif property.type.name == "Date" || property.type.name == "date">
                ${property.name?cap_first}: <input type="date" id="${property.name}" /><br />
                <#else>
                </#if>
                </#if>
                </#list>
                <input type="submit" value="Update" />
            </form>
        </fieldset>
        <br/>
    </div>
<#else>
</#if>
</body>
</html>