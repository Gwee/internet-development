<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="Model.*"%>
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

            <%  List<Loan> list = bean.getListAllLoans();
                if (list != null) {   %>


            <table style="background-color: white ;  margin-top: 5px">
                <tr>
                    <th>Student id</th>
                    <th>Book Isbn</th>
                    <th>Book Name</th>
                    <th>Category</th>
                    <th>Copy Number</th>
                    <th>From</th>
                    <th>To</th>
                <tr>

                    <%
                        for (Loan s : list) {
                    %>
                <tr>
                    <td><%=s.getId()%> </td>
                    <td><%=s.getIsbn()%> </td>
                    <td><%=s.getBookname()%> </td>
                    <td><%=s.getCategory()%> </td>
                    <td><%=s.getCopynumber()%> </td>
                    <td><%=s.getFrom()%> </td>
                    <td><%=s.getUntill()%> </td>
                </tr>
                <%   }  %>

            </table>
            <%   }%>


        </article>


       
        
    </body>
</html>