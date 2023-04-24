<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document Catalog Report</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
        }
        th, td {
            text-align: left;
            padding: 8px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
        }
        h1 {
            margin-top: 0;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<h1>Document Catalog Report</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Location</th>
        <th>Tags</th>
    </tr>
    </thead>
    <tbody>
    <#list documents as doc>
        <tr>
            <td>${doc.id}</td>
            <td>${doc.title}</td>
            <td>${doc.location}</td>
            <td>
                <#if doc.tags?size gt 0>
                    ${doc.tags?join(", ")}
                <#else>
                    No tags
                </#if>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
