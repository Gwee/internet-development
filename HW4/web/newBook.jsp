<%-- 
    Document   : newStudent
    Created on : 30-Jan-2018, 13:07:50
    Author     : Guy Moyal
--%>

<html>
    <head>


        <title>Digital Library</title>
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
            if (name.equals("guest") || !bean.getStudent().isPremission()) {   %>

        <h1>Sorry, not enough permissions</h1>

        <%      } else { %>  



        <article>


            <div class="fillBook" style="width: 300px;" >
                <h3 style="margin-bottom: 0xp; font-style: italic;"><b>Complete the form below to add a new book to the library:</b></h3>

                <form   action="newBookcheck"  method="get">
                    <input type="text" name="bookname" required placeholder="Book Name" />
                    <input type="number" name="isbn" required  placeholder="Isbn" min="1000" max="9999"/><br />
                    <input type="text" name="author" required placeholder="Author"/><br><br>
                    <b style="font-style: italic;">Book Category:</b><br>
                    <%
                        int i = 0;
                        for (String cate : bean.getCatlist()) {
                            if (i++ == 0) {%>
                    <input type="radio" name="cat"  checked="checked" value=<%= cate%>  > <%= cate%><br>
                    <%   } else {%>
                    <input type="radio" name="cat"  value=<%= cate%>  > <%= cate%><br>
                    <%   }%>
                    <%   }%>

                    <input type="radio" name="cat" value="other"  > other 
                    <input type="text" name="othercat" placeholder="Other"  ><br><br>
                    <input type="number" name="relaseyear" min="0" max="2018" required placeholder="Relase Year"/>
                    <input type="number" name="copysnumber" min="1" max="100" required placeholder="Copys Number"/>

                    <br />
                    <br />
                    <input type="submit" name="add" value="Add"  class="login__submit"  style="position:center" />
                </form>
            </div>

        </article>
        <aside ><img  class="img2" src="Images/noun_191.png" alt="text" />
        </aside>


        <%  }%>












        <footer style="margin-top: 580px; margin-bottom: 30px;">
            <hr />
            Copyright (C) 2018. All rights reserved.
        </footer>






    </body>
</html>