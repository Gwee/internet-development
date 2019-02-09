<!DOCTYPE html>
<html>
    <head>
        <title>Digital Library</title>
        <meta charset="utf-8" />
        <meta  name="HomePage" content="libraray" />

        <link rel="stylesheet" type="text/css" href="css/StyleSheet.css" />
        <%@page import="Model.Student"%>


    </head>
    <body>



        <header>
            <div class="img"></div>
        </header>
        <nav>


            <ul>
                  <li><a href="home.jsp">Home</a></li>
                    <li><a href="newStudent.jsp">New Student</a></li>
                    <li><a href="newLoan">New Borrow</a></li>
                    <li><a href="returnBook">Return Book</a></li>
                    <li><a href="newBook">Add New Book</a></li>
                    <li><a href="removeBook.jsp">Remove Book</a></li>
                    <li><a href="searchBook.jsp">Search Book</a></li>
                    <li><a href="show.jsp">Show</a></li>
                    <li><a href="payFine.jsp">Pay Fine</a></li>
                    <li style="float: right;  background:linear-gradient(to bottom, #090909 0%, rgba(50, 50, 50, 0.85) 100%)"><a href="index.jsp">Log Out</a></li>
            </ul>

        </nav>

        <article>

            <jsp:useBean id="bean" class="Model.BeanClass" scope="session"/>

            <%

                if (bean != null) {
            %>
            <table style="background-color: white ;  margin-top: 5px">
                <tr>
                    <th>ID</th>
                    <th>First name</th>
                    <th>Second name</th>
                    <th>Mail</th>
                    <th>Fine</th>
                    <th>Number of books are borrow</th>
                    <th>Number of available borrow book</th>
                    <th>Admin</th>

                </tr>

                <%
                    if (bean.getList() != null)
                        for (Student s : bean.getList()) {
                %>
                <tr>

                    <td><%=s.getID()%> </td>
                    <td><%=s.getFname()%> </td>
                    <td><%=s.getSname()%> </td>
                    <td><%=s.getEmail()%> </td>
                    <td><%=s.getMyFine()%> </td>
                    <td><%=s.getMyBorrows()%> </td>
                    <td><%=s.getMaxBorrows() - s.getMyBorrows()%> </td>
                    <td><%=s.isPremission()%></td>
                </tr>

                <%   }  %>


            </table>
            <%      }%>















        </article>


    
    </body>
</html>