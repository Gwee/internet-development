<!DOCTYPE html>
<html>
    <head>
        <title>Error Page</title>
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





            <%      if (request.getParameter("studentexist") != null) {
                    out.write("<h2>The ID is already exist!</h2>\n");
                }            %>

            <%      if (request.getParameter("Bookexist") != null) {
                    out.write("<h2>The Book is already exist!</h2>\n");
                }            %>

            <%      if (request.getParameter("DontEnterCat") != null) {
                    out.write("<h2>You didnt enter category!</h2>\n");
                }            %>

            <%      if (request.getParameter("CatExist") != null) {
                    out.write("<h2>Sorry the category is exist!</h2>\n");
                }            %>

            <%      if (request.getParameter("BookNotExist") != null) {
                    out.write("<h2>Sorry the book not exist!</h2>\n");
                }%>

            <%      if (request.getParameter("CopyNotFree") != null) {
                    out.write("<h2>Sorry someone is barrow one of the copys!</h2>\n");
                }%>

            <%      if (request.getParameter("maxFine") != null) {
                    out.write("<h2>Sorry you got your max fine ,  you need to pay him first!</h2>\n");
                }%>

            <%      if (request.getParameter("maxBarrow") != null) {
                    out.write("<h2>Sorry you got your max barrows ,  you need return one book first!</h2>\n");
                }%>





        </article>


        <aside ><img  class="img2" src="Images/noun_191.png" alt="text" />
        </aside>


        <footer>
            <hr />
            Copyright (C) 2018. All rights reserved.
        </footer>
    </body>
</html>