<!DOCTYPE HTML>
<html>
<head>
    <title>${class.name} List</title>
</head>
<body>
<a href="/index">home</a>
<#if class.page?? && class.page.getAll?c=="true">
    <h1>${class.name} List</h1>
    <div>
        <table border="1">
            <tr>
                <th>id</th>
                <#list properties as property>
                    <th>${property.name}</th>
                </#list>
            </tr>
            ${r'<#list '}${class.name?uncap_first}s as ${class.name?uncap_first}${r'>'}
                <tr>
                    <td>
                        <a href="/${class.name?uncap_first}/${r"${"}${class.name?uncap_first}.id${r"}"}">${r"${"}${class.name?uncap_first}.id${r"}"}</a>
                    </td>
                    <#list properties as property>
                        <#if property.type.name == "Integer" || property.type.name == "String" || property.type.name == "Boolean">
                            <td>${r"${"}${class.name?uncap_first}.${property.name}${r"}"}</td>
                        <#else>
                            ${r"<#if "}${class.name?uncap_first}.${property.name}${r"?has_content?c=='false'><td>field is null</td>"}
                            ${r"<#else>"}
                            <td>${r"${"}${class.name?uncap_first}.${property.name}.id${r"}"}</td>
                            ${r"</#if>"}
                        </#if>
                    </#list>
                </tr>
            ${r'</#list>'}
        </table>
        <#if class.page.create?c=="true">
        <a href="/${class.name?uncap_first}/create">Create ${class.name?uncap_first}</a>
        </#if>
    </div>
<#else>
    <h1>
        This is auto generated page.
        In order to have ${class.name?uncap_first} list overview, you need to assign Page stereotype to ${class.name}.
    </h1>
</#if>
</body>
</html>