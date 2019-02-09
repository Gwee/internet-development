<%-- 
    Document   : newStudent
    Created on : 30-Jan-2018, 13:07:50
    Author     : amitmarko
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

        <h1>Sorry you dont have a premission!</h1>

        <%      } else { %>  



        <article>


            <div class="fillBook" style="width: 300px;" >
                <h3 style="margin-bottom: 0xp; font-style: italic;"><b>Complete the form below to add a new student to the library:</b></h3>

                <form   action="newStudent"  method="get">
                    <input type="text" name="firstname" required placeholder="First name" />
                    <input type="text" name="secondname" required  placeholder="Second name"/><br />
                    <input type="number" name="id" min="100000000" max="999999999" required placeholder="ID"/>
                    <input type="text" name="password" required placeholder="Password"/>

                    <input type="email" name="email" required placeholder="Mail"/><br />

                    <b   style="font-style: italic;">Max loans:</b><br>
                    <select name="maxloans" >
                        <option value="10" >10</option>
                        <option value="50">50</option>
                        <option value="100">100</option>
                    </select>

                    <b style="font-style: italic; ">Permission type:</b><br>
                    <input  type="radio" name="per" value="0"  checked="checked">   <b style="font-style: italic; ">User</b><br>
                    <input  type="radio" name="per" value="1" > <b style="font-style: italic; ">Admin</b><br>


                    <br />
                    <br />
                    <input type="submit" name="add" value="Add"  class="login__submit"  style="position:center" />
                </form>
            </div>

        </article>
        <aside ><img  class="img2" src="Images/noun_191.png" alt="text" />
        </aside>


        <%  }%>












        <footer style="margin-top: 550px; margin-bottom: 30px;">
            <hr />
            Copyright (C) 2018. All rights reserved.
        </footer>






    </body>
</html>