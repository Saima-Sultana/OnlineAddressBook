<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <script language="javascript">
        function delContact(url) {
            var isOK = confirm("Are you sure to delete this contact?");
            if (isOK) {
                document.location.href = url
            }
        }
    </script>
</head>

<body>
<table>
    <tr>
        <td height="50px">
            &nbsp;
        </td>
    </tr>
    <c:if test="${empty contactList}">
        <tr>
            <td>
                Your contact list is still empty!
            </td>
        </tr>
    </c:if>
    <c:if test="${not empty contactList}">
        <tr>
            <td>
                <fieldset>
                    <legend>Contact List</legend>
                    <table width="400px" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
                        <c:forEach var="contact" items="${contactList}">
                            <tr>
                                <td>
                                    <a href="contactdetails.html?contactId=${contact.contactId}">
                                        <b><big>${contact.name}</big></b>
                                    </a>
                                    <br>
                                    ${contact.email}
                                </td>
                                <td>
                                    <a href="editcontact.html?contactId=${contact.contactId}">Edit</a>
                                </td>
                                <td>
                                   <a href="javascript:delContact('deletecontact.html?contactId=${contact.contactId}')">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </fieldset>
            </td>
        </tr>
    </c:if>
</table>
</body>

</html>