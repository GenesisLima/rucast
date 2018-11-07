<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${message}</h1>
<form role="form" id="contact-form" class="contact-form" action="/upload/file" method="post" enctype="multipart/form-data">
 <select id="sel1" name="name" id="name"  >
                        <option value="Opiniao Pernambuco" selected>Opini&atilde;o Pernambuco</option>
     
  </select></br>
  <input type="text" class="form-control" name="topic" autocomplete="off" id="topic" placeholder="Tema"></br>
  <textarea  rows="3"  name="synopsis" id="synopsis" placeholder="Sinopse" style="height:154px"></textarea></br>
   <button type="submit"   class="btn main-btn pull-right wow bounceIn" data-wow-duration="1s" data-wow-delay="1s">Salvar</button>   		                         
     <label>
              <labelstyle="margin-right:5px;" for="my-file-selector">
                   
<!--     <input id="my-file-selector" class="form-control " name="file" type="file" style="display:none; " data-wow-duration="1s" data-wow-delay="1s" onchange="$('#path').html($(this).val());" > -->
        <input id="my-file-selector" name="file" type="file" style="display:none; " data-wow-duration="1s" data-wow-delay="1s" >

    Click to Upload...
</label>
</form>
</body>
</html>