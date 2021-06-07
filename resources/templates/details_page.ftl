<!DOCTYPE HTML>
<html>
<head>
    <title>${class.name} Details</title>
</head>
<body>
<#if class.page?? && class.page.details?c=="true">
    <h1>${class.name} Details</h1>
    <div>
        <table border="1">
            <tr>
                <td>id</td>
                <td>${r"${"}${class.name?uncap_first}.id${r"}"}</td>
            </tr>
            <#list properties as property>
                <tr>
                    <td>${property.name}</td>
                    <td>${r"${"}${class.name?uncap_first}.${property.name}${r"}"}</td>
                </tr>
            </#list>
        </table>
        <#if class.page.update?c=="true">
        <a href="/${class.name?uncap_first}/update/${r"${"}${class.name?uncap_first}.id${r"}"}">Update ${class.name?uncap_first}</a>
        </#if>
    </div>
<#else>
</#if>
</body>
</html>