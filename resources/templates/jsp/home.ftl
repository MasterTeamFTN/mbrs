${r'<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>'}
<html>
<head>
    <h1>Generated application</h1>
</head>
<body>
    <ul>
        <#list allClasses as class>

        <li><a href="${r'${pageContext.request.contextPath}'}/${class.name?uncap_first}s">See all ${class.name?uncap_first}s</a></li>
        </#list>
    </ul>
</body>
</html>