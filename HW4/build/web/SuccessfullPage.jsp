<!DOCTYPE html>
<html>
    <head>
        <title>Digital Library</title>
        <meta charset="utf-8" />
        <meta  name="HomePage" content="libraray" />

        <link rel="stylesheet" href="css/StyleSheet.css">
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





            <%  if (request.getParameter("addnewstudent") != null) {
                    out.write("<h2>You add new student succesfuly!</h2>\n");
                }         %>
            <%  if (request.getParameter("addnewBook") != null) {
                    out.write("<h2>You add new book succesfuly!</h2>\n");
                }         %>


            <%  if (request.getParameter("removeBook") != null) {
                    out.write("<h2>The  book removed succesfuly!</h2>\n");
                }%>

            <%  if (request.getParameter("payFine") != null) {
                    out.write("<h2>You pay the fine succesfuly!</h2>\n");
                }%>


            <%  if (request.getParameter("succbarrow") != null) {
                    out.write("<h2>You make the barrow succesfuly!</h2>\n");%>
            <a  href="newLoan"><h2>If you want another barrow click here.</h2></a>
            <%    }%>


            <%  if (request.getParameter("con") != null) {
                    out.write("<h2>You return the book on bad condition , you got fine of :" + request.getParameter("con") + "$</h2>\n");%>
            <%    }%>


            <%  if (request.getParameter("late") != null) {
                    out.write("<h2>You return the book on late, you got fine of :" + request.getParameter("late") + "$</h2>\n");%>
            <%    }%>

            <%  if (request.getParameter("returnBooks") != null) {
                       out.write("<h2>You return the book succesfuly!</h2>\n");%>
            <%    }%>

            <%  if (request.getParameter("con") != null || request.getParameter("late") != null) {%>
            <a href="payFine.jsp"> <b>To pay your fine click here</b></a>
            <%    }%>











        </article>


        <aside ><img  class="img2" src="Images/noun_191.png" alt="text" />
        </aside>


        <footer>
            <hr />
            Copyright (C) 2018. All rights reserved.
        </footer>
    </body>
</html>