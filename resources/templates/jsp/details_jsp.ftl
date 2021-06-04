${r'<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>'}
<!DOCTYPE HTML>
<html>
<head>
    <title>${class.name} Details</title>
</head>
<body>
<#if class.page??>
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
    </div>
<#else>
</#if>
</body>
</html>