<html>
    <head>
        <title>Digital Library</title>
        <link rel="stylesheet" type="text/css" href="css/StyleSheet.css" />
        <meta charset="utf-8" />
        <meta name="returnbook" content="libraray" />
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
            <div class="fillBook">
                <h1 style=" font-style:italic">Hi ! here you can see all the information on our library <br> Press the button you like:</h1>
                <form  action="show?books="  method="post" >
                    <input type="submit" class="login__submit"  value="Books" style="position:center; width: 120px; font-size: 17px; margin-left: 200px;" />
                </form>


                <form  action="show?students="  method="post">
                    <input type="submit" class="login__submit"  value="Students" style="position:center; width: 120px; font-size: 17px;  margin-left: 200px;" />
                </form>


                <form  action="show?loans="  method="post">
                    <input type="submit"  class="login__submit" value="Borrows" style="position:center; width: 120px; font-size: 17px;  margin-left: 200px;"  />
                </form>
            </div>
        </article>

        <footer id="footerreturnbook" style="margin-top:  300px;">
            <hr />
            Copyright (C) 2018. All rights reserved.
        </footer>

    </body>
</html>