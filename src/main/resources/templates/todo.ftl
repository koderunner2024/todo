<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>To-Do List</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
    <script src="/js/main.js"></script>
    <ul>
        <#list todos as todo>
            <li class="todo-item">${todo}</li>
        </#list>
    </ul>
</body>
</html>