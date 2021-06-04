${r'<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>'}
<!DOCTYPE HTML>
<html>
<head>
    <title>${class.name} List</title>
</head>
<body>
<#if class.page??>
<h1>${class.name} List</h1>
<div>
    <table border="1">
        <tr>
            <th>id</th>
            <#list properties as property>
            <th>${property.name}</th>
            </#list>
        </tr>
        <c:forEach items="${r"${"}${class.name?uncap_first}s${r"}"}" var="${class.name?uncap_first}">
            <tr>
                <td>
                    <a href="/${class.name?uncap_first}/${r"${"}${class.name?uncap_first}.id${r"}"}">${r"${"}${class.name?uncap_first}.id${r"}"}</a>
                </td>
                <#list properties as property>
                <td>${r"${"}${class.name?uncap_first}.${property.name}${r"}"}</td>
                </#list>

            </tr>
        </c:forEach>
    </table>
    <a href="${r'${pageContext.request.contextPath}'}/${'create${class.name}/'}">Create ${class.name?uncap_first}</a>
</div>
<#else>
    <h1>
        This is auto generated page.
        In order to have ${class.name?uncap_first} list overview, you need to assign Page stereotype to ${class.name}.
    </h1>
</#if>
</body>
</html>