<%@ include file="/WEB-INF/jsp/include.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <script language="javascript">
        function returnHome() {
            document.location.href = "mycontacts.html"
        }
    </script>
</head>

<body>
    <br>
    <br>
    <fieldset>
        <legend>Contact Info</legend>
        <table width="400px" bgcolor="f8f8ff" border="0" cellspacing="0" cellpadding="5">
            <tr>
                <td>First Name:</td>
                <td>${contact.firstName}</td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td>${contact.lastName}</td>
            </tr>
            <tr>
                <td>Organization:</td>
                <td>${contact.org}</td>
            </tr>
            <tr>
                <td>Title:</td>
                <td>${contact.title}</td>
            </tr>
            <tr>
                <td>PhotoUrl:</td>
                <td>${contact.photoUrl}</td>
            </tr>
            <tr>
                <td>Telephone(work):</td>
                <td>${contact.telWork}</td>
            </tr>
            <tr>
                <td>Telephone(Home):</td>
                <td>${contact.telHome}</td>
            </tr>
            <tr>
                <td>Address:</td>
                <td>${contact.address}</td>
            </tr>
            <tr>
                <td>E-mail:</td>
                <td>${contact.email}</td>
            </tr>
        </table>
    </fieldset>
    <br>
    <input type="button" align="right" value="Back" onClick="returnHome()">
    <br>
    <br>
    <a href="exportcontact.html?contactId=${contact.contactId}">Export this contact</a>
</body>

</html>