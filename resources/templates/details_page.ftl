<!--
WARNING: This file was autogenerated by MagicDraw MBRS plugin.
Do not update it because if you run generator again you changes will be deleted
-->
<!DOCTYPE HTML>
<html>
<head>
    <title>${class.name} Details</title>
</head>
<body>
<a href="/index">home</a>
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
                    <#if property.type.name == "Integer" || property.type.name == "String" || property.type.name == "Boolean">
                        <td>${r"${"}${class.name?uncap_first}.${property.name}${r"}"}</td>
                    <#else>
                        <td>
                            ${r"<#if "}${class.name?uncap_first}.${property.name}${r"?has_content?c=='false'>null"}
                            ${r"<#else>"}
                            ${r"${"}${class.name?uncap_first}.${property.name}.id${r"}"}
                            ${r"</#if>"}
                        </td>
                    </#if>
                </tr>
            </#list>
        </table>
        <#if class.page.update?c=="true">
        <a href="/${class.name?uncap_first}/update/${r"${"}${class.name?uncap_first}.id${r"}"}">Update ${class.name?uncap_first}</a>
        </#if>

        <a href="/${class.name?uncap_first}/delete/${r"${"}${class.name?uncap_first}.id${r"}"}">Delete ${class.name?uncap_first}</a>
    </div>
<#else>
</#if>
</body>
</html>