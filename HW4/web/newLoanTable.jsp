<%@page import="Model.Book"%>
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







            <table style="background-color: white ;  margin-top: 5px">
                <tr>
                    <th>Isbn</th>
                    <th>Book Name</th>
                    <th>Author</th>
                    <th>Copies</th>
                    <th></th>
                <tr>

                    <%
                        for (Book s : bean.getListOfBooksByCategory()) {
                    %>
                <tr>



                    <td><%=s.getID()%> </td>
                    <td><%=s.getBook_Name()%> </td>
                    <td><%=s.getAuthor()%> </td>
                    <td><%=s.getAvilabeCopys()%> </td>

                    <%  if (bean.getStudent().getFname().equals("guest")) {  %>
                    <td></td>
                    <%  } else {  %>
                    <% String tmp = "madeBarrow?isbn=" + s.getID() + "&copynum=" + s.getAvilabeCopys();%>
                    <td><a  href=<%= tmp%> </a>Made a Barrow</td>
                    <%   }  %>





                </tr>

                <%   }%>


            </table>















        </article>


        <aside ><img  class="img2" src="Images/noun_191.png" alt="text" />
        </aside>


        <footer>
            <hr />
            Copyright (C) 2018. All rights reserved.
        </footer>
    </body>
</html>