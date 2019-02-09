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
            if (name.equals("guest")) {   %>

        <h1>Sorry you dont have a premission!</h1>

        <%      } else { %>  



        <article>

            <%
                if (bean.getStudent().getMyFine() >= bean.getStudent().getMaxFine()) {
                    RequestDispatcher dis = request.getRequestDispatcher("ErrorPage.jsp?maxFine=");
                    dis.forward(request, response);
                } else {
                    if (bean.getStudent().getMyBorrows() >= bean.getStudent().getMaxBorrows()) {
                        RequestDispatcher dis = request.getRequestDispatcher("ErrorPage.jsp?maxBarrow=");
                        dis.forward(request, response);
                    } else {  %>

            <div class="fillBook" style="width: 300px;" >
                <h3 style="margin-bottom: 0xp; font-style: italic;"><b>Please choose a category:</b></h3>

                <form   action="newLoan"  method="get">
                    <%
                        int i = 0;
                        for (String cate : bean.getCatlist()) {
                            if (i++ == 0) {%>
                    <input type="radio" name="cat"  checked="checked" value=<%= cate.replaceAll("\\s+", "~")%>  > <%= cate%><br>
                    <%   } else {%>
                    <input type="radio" name="cat"  value=<%= cate.replaceAll("\\s+", "~")%>  > <%= cate%><br>
                    <%   }%>
                    <%   }%>
                    <input type="submit" name="add" value="Send"  class="login__submit"  style="position:center; margin-top: 11px;" />
                </form>
            </div>

        </article>
        <aside ><img  class="img2" src="Images/noun_191.png" alt="text" />
        </aside>

        <%  }       %>
        <%   }      %>




        <%  }%>


        <footer >
            <hr />
            Copyright (C) 2018. All rights reserved.
        </footer>






    </body>
</html>