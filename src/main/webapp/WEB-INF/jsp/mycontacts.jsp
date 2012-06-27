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
    <script language="JavaScript">
        function Validate() {
            var vcard = document.getElementById("file").value;
            var checking = vcard.toLowerCase();
            if (!checking.match(/(\.vcf)$/)) {
                alert("Please enter File Extensions .vcf");
                document.getElementById("file").focus();
                return false;
            }
            return true;
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
    <tr>
        <td>
            <b><big>Hi! ${User.loginName}</big></b>
        </td>
    </tr>
    <tr>
        <td height="50px">
            &nbsp;
        </td>
    </tr>
    <c:if test="${not empty searchText}">
        <tr>
            <td width="100px">
                &nbsp;
            </td>
            <td class="msgText">
                Search results with "${searchText}"
            </td>
        </tr>
    </c:if>
    <c:if test="${empty contactList}">
        <tr>
            <td width="100px">
                &nbsp;
            </td>
            <td>
                There is no contact list.
            </td>
        </tr>
    </c:if>
    <c:if test="${not empty contactList}">
        <tr>
            <td width="200px">
                &nbsp;
            </td>
            <td>
                <fieldset>
                    <legend>Contact List</legend>
                    <table width="400px" border="0" cellspacing="2" cellpadding="5">
                        <form method="POST" action = "searchcontact.html">
                            <tr>
                                <td>Enter Contact Name:</td>
                            </tr>
                            <tr>
                                <td><input type="text" name="name"/><td>
                                <td><input type="submit" value="Search"/></td>
                            </tr>
                        </form>
                        <c:forEach var="contact" items="${contactList}">
                            <tr bgcolor="f8f8ff">
                                <td>
                                    <a href="contactdetails.html?contactId=${contact.contactId}">
                                        <b><big>${contact.formattedName}</big></b>
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
    <form method="POST" action="importcontact.html" enctype="multipart/form-data" onsubmit="return Validate();">
        <tr>
            <td height="50px">
                &nbsp;
            </td>
        </tr>
        <tr>
            <td width="200px">
                &nbsp;
            </td>
            <td>
                Import Contact:  <input type="file" name="file" id="file"/>
                <br>
                <td><input type="submit" value="Import"/></td>
            </td>
        </tr>
    </form>
</table>
</body>

</html>