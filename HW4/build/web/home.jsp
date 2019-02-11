<%@page import="Model.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Digital Library</title>
        <meta charset="utf-8" />
        <meta  name="HomePage" content="libraray" />

        <link rel="stylesheet" type="text/css" href="css/StyleSheet.css" />
    </head>
    <body>



        <header>
            <div class="img"></div>
        </header>
        <nav>

            <form   action="home.jsp"  method="GET">

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

            </form>
        </nav>

        
        <%  
        
       String name = ((BeanClass)request.getSession().getAttribute("bean")).getStudent().getFname();
    
        %>
        
        <article >
            <h1>Hi  <%= name%> and welcome to the Virtual Library!</h1>
            <p style=" font-style:italic ;  font-weight: bold ; ">
                The virtual library has many books to offer<br />
                Please feel free to reach out to us below with any questions<br />
               
            </p>
            <form id="formHome" method="post">
                <b>Contact Us:</b><br />
                <textarea cols="55" rows="6" name="for" required></textarea>
                <br />
                <input type="submit" name="add" value="Send"  class="login__submit"  style="margin-left: 95px; font-size: 1.0rem;" />
            </form>
        </article>


        <aside ><img  class="img2" src="Images/noun_191.png" alt="text" />
        </aside>


        <footer>
            <hr />
            Copyright (C) 2019. All rights reserved.
        </footer>
    </body>
</html>
