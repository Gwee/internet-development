<!DOCTYPE html>
<html lang="en" >

    <head>
        <meta charset="UTF-8">
        <title>Login/Logout animation concept</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=yes">


        <link rel="stylesheet" href="css/style.css">


    </head>

    <body>

        <div class="cont">
            <div class="demo">
                <div class="login">
                    <div class="login__check"></div>
                    <div class="login__form">
                        <form action="LogIn" method="post">
                            <%    if (request.getParameter("usernotexist") != null) {  %>
                            <h1 style="width: 200px; color: white">Sorry the user not exist , try again!</h1>
                            <%  }%>    
                            
                             <%    if (request.getParameter("worngpassword") != null) {  %>
                            <h1 style="width: 200px; color: white">You enter a wrong password!</h1>
                            <%  }%>   
                            
                           
                            
                            
                            <div class="login__row">
                                <svg class="login__icon name svg-icon" viewBox="0 0 20 20">
                                <path d="M0,20 a10,8 0 0,1 20,0z M10,0 a4,4 0 0,1 0,8 a4,4 0 0,1 0,-8" />
                                </svg>

                                <%

                                    String id = null;
                                    String password = null;
                                    Cookie[] cookies = request.getCookies();
                                    if (cookies != null) {
                                        for (Cookie c : cookies) {
                                            if (c.getName().equals("id")) {
                                                id = c.getValue();
                                            } else if (c.getName().equals("password")) {
                                                password = c.getValue();
                                            }
                                        }
                                    }
                                    

                                    if (id != null) {%>
                                    <input type="text"   class="login__input name" placeholder="Id" name="id"  required="required" value=<%= id%> />

                                <%   } else {%>
                                <input type="text" class="login__input name" placeholder="Id" name="id"  required="required"/>
                                <%   }%>
                            </div>
                            <div class="login__row">
                                <svg class="login__icon pass svg-icon" viewBox="0 0 20 20">
                                <path d="M0,20 20,20 20,8 0,8z M10,13 10,16z M4,8 a6,8 0 0,1 12,0" />
                                </svg>

                                <%  if (password != null) {%>

                                <input type="password" class="login__input pass" placeholder="Password" name="password"  required="required" value=<%= password %> />
                                <%   } else {%>
                                <input type="password" class="login__input pass" placeholder="Password" name="password"  required="required"/>                                <%   }%>



                            </div>

                            <input type="submit" class="login__submit" value="Log In" >
                        </form>
                        <a href="LogIn" style=" color: #FF3366;"><h1>Continue as a guest</h1></a>
                    </div>
                </div>

            </div>
        </div>








    </body>

</html>
