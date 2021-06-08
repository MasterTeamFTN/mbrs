<!DOCTYPE HTML>
<html>
<head>
    <title>Create ${class.name}</title>
</head>
<body>
<#if class.page?? && class.page.create?c=="true">
    <div>
        <fieldset>
            <legend>New ${class.name}</legend>
            <form name="product" action="${r"${springMacroRequestContext.getContextPath()}"}/${class.name?uncap_first}/add" method="post">
            <#list properties as property>
            <#if property.name != "id">
            <#if property.type.name == "Integer">
                ${property.name?cap_first}: <input type="number" name="${property.name}" /><br/>
            <#elseif property.type.name == "String">
                ${property.name?cap_first}: <input type="text" name="${property.name}" /><br/>
            <#elseif property.type.name == "Boolean" >
                ${property.name?cap_first}: <input type="checkbox" id="${property.name}">
            <#else>
            </#if>
            </#if>
            </#list>
                <input type="submit" value="Add" />
            </form>
        </fieldset>
        <br/>
    </div>
<#else>
</#if>
</body>
</html>