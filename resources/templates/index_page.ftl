<html>
<head>
    <h1>Generated application</h1>
</head>
<body>
<ul>
    <#list allClasses as class>
        <li><a href="/${class.name?uncap_first}/all">See all ${class.name?uncap_first}s</a></li>
    </#list>
</ul>
</body>
</html>