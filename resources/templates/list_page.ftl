<!DOCTYPE HTML>
<html>
<head>
    <title>${class.name} List</title>
</head>
<body>
<#if class.page?? && class.page.getAll == true>
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
                        <td>${r"${"}${class.name?uncap_first}.${property.name}${r"}"}</td>
                    </#list>
                </tr>
            ${r'</#list>'}
        </table>
        <#if class.page.create == true>
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