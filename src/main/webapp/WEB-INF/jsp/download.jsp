<!-- <?xml version="1.0" encoding="ISO-8859-1" ?> -->
<?xml version="1.0" encoding="UTF8" ?>

<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <style>
        fieldset {
            width: 600px;
            margin: 0 auto;
        }

        table {
            width: 560px;
            margin: 0 auto;
        }
        table td {
            font-size: 10pt;
            text-align: center;
        }
        img {
            width: 40px;
            height: 40px;
            border: 1.2pt cadetblue solid;
        }
        pre {
            font-size: 8pt;
            font-family: monospace, serif;
            background-color: #f0f8ff;
        }
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Insert title here</title>
</head>
<body>
<fieldset>
    <legend>List of Files</legend>
    <ul>
        <li><a href="<c:url value="/"/>">Menu</a></li>
    </ul>
    <br>
    <table>
        <thead>
        <tr>
            <th>File</th>
            <th>File Name</th>
            <th>File Type</th>
            <th>File Size</th>
            <th>File Path</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="fileDocument" items="${fileDocuments}" varStatus="i">

            <tr bgcolor="${i.count % 2 == 0 ? '#f0f8ff' : '#f5f5dc'}">
                <td>
                    <a href="<c:url value="/downloads/files/${fileDocument.show.id}"/>" >
                        <c:choose>
                            <c:when test="${fileDocument.fileType eq 'application/pdf'}">
                                <img src="<c:url value="/image/pdf.png"/>" title="Download - ${fileDocument.fileName}"/>
                            </c:when>
                            <c:when test="${fileDocument.fileType eq 'application/octet-stream'}">
                                <img src="<c:url value="/image/rar.jpg"/>" title="Download - ${fileDocument.fileName}"/>
                            </c:when>
                            <c:when test="${fileDocument.fileType eq 'application/msword'}">
                                <img src="<c:url value="/image/doc.jpg"/>" title="Download - ${fileDocument.fileName}"/>
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value="/downloads/files/${fileDocument.show.id}"/>" title="Download - ${fileDocument.fileName}"/>
                            </c:otherwise>
                        </c:choose>
                    </a>
                </td>
                <td>${fileDocument.fileName}</td>
                <td>${fileDocument.fileType}</td>
                <td>
<%--                     <fmt:formatNumber var="value" value="${file.fileSize / 1024}" maxFractionDigits="2"/> --%>
                        ${value} KB
                </td>
                <td>${fileDocument.filePath}</td>
                <td><a href="<c:url value="/delete/file/${fileDocument.show.id}"/>">&cross;</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>
    <c:if test="${not empty mensagem}">
        <pre>${mensagem}</pre>
    </c:if>
</fieldset>
</body>
</html>