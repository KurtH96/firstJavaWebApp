<%-- 
    Document   : update
    Created on : Aug 8, 2020, 12:56:20 PM
    Author     : drych
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 
<%@page import="rychlik.dylan.website.*" %> 

<%
     //If submit button is clicked
    if (request.getParameter("submit") != null) {
         //Uses strings to request the parameters of the input textfields. 
        String TransactionID = request.getParameter("id");
        String Amount = request.getParameter("amount");
        String Description = request.getParameter("description");
        String ClientID = request.getParameter("clientID");
        String Date = request.getParameter("date");
         //Creates a Transaction object referenced in the backend
        new Transaction().update(new String[]{Amount, Description, ClientID, Date, TransactionID});


%>

<script>
    alert("Record Updated!");
</script>
<%        }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <h1>Transaction Update</h1>


        <div class="row">
            <div class="col-sm-4">
                <form  method="POST" action="#" >

                    <%  
                         //Creates a resultset object to read the MySQL query. 
                        ResultSet rs = new Transaction().selectWhere(id);
                        //reads through the result set. Mixed in the html to allow the record to be selected based upon a specified ID. 
                        while (rs.next()) {

                    %>


                    <div float="left">
                        <label class="form-label">Amount</label>
                        <input type="text" class="form-control" placeholder="Amount" name="amount" value="<%= rs.getInt("Amount")%>" id="course" required >
                    </div>

                    <div float="left">
                        <label class="form-label">Description</label>
                        <input type="text" class="form-control" placeholder="Description" name="description" id="description" value="<%= rs.getString("Description")%>" required >
                    </div>

                    <div float="left">
                        <label class="form-label">Client ID</label>
                        <input type="text" class="form-control" placeholder="ClientID" name="clientID" id="clientID" value="<%= rs.getInt("ClientID")%>" required >
                    </div>

                    <div float="Date">
                        <label class="form-label">Date</label>
                        <input type="text" class="form-control" placeholder="Date" name="date" value="<%= rs.getDate("Date")%>" id="Date" required >
                    </div>


                    <% }%>



                    <br>

                    <div float="right">
                        <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                        <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                    </div>  

                    <div float="right">

                        <p><a href="indexTransaction.jsp">Click Back</a></p>


                    </div>

                </form>
            </div>          
        </div>

    </body>
</html>