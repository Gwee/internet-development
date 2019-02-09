<%-- 
    Document   : newStudent
    Created on : 30-Jan-2018, 13:07:50
    Author     : amitmarko
--%>

<html>
    <head>


        <title>Remove Book</title>
        <link rel="stylesheet" type="text/css" href="css/StyleSheet.css" />
        <meta charset="utf-8" />
        <meta name="addnewstudent" content="libraray" />
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



        <jsp:useBean id="bean" class="Model.BeanClass" scope="session"/>
        <%

            String name = bean.getStudent().getFname();
            bean.setBookname(request.getParameter("isbn"));
            bean.setCopynumber(Integer.parseInt(request.getParameter("copynumber")));
            session.setAttribute("bean", bean);
        %>  



        <article>

            <div class="fillBook" style="width: 300px;" >
                <h3 style="margin-bottom: 0xp; font-style: italic;"><b>Condition of the book:</b></h3>
                <form  action="returnBook"  method="post">
                    <input type="radio" name="condition" value="0" checked="checked">Condition as i got<br>
                    <input type="radio" name="condition" value="1">Damaged lightly<br>
                    <input type="radio" name="condition" value="2">Damaged badly<br>
                    <input type="radio" name="condition" value="3">Out of service<br><br>

                    <input type="submit" name="add" value="Return"  class="login__submit"  style="position:center;" />
                </form>
            </div>
        </article>
        <aside ><img  class="img2" src="Images/noun_191.png" alt="text" />
        </aside>



        <footer style="margin-top: 580px; margin-bottom: 30px;">
            <hr />
            Copyright (C) 2018. All rights reserved.
        </footer>






    </body>
</html>