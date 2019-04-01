
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE HTML>
<html>
<head>
<title>Axis Online Banking Application</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="#">AXIS BANK</a>

		<!-- Links -->
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="index.html">Home</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="new_acc.html">New
					Registration</a></li>

			<!-- Dropdown -->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"> Explore </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="withdrawn.html">Withdrawl Amount
					</a> <a class="dropdown-item" href="Deposite.html">Deposite Amount</a>
					<a class="dropdown-item" href="Fund.html">Fund Transfer</a> <a
						class="dropdown-item" href="Checkbal.html">Check Balance</a> <a
						class="dropdown-item" href="getAllBankAccounts.html">Account
						Details</a> <a class="dropdown-item" href="Search.html">Search
						Account</a> <a class="dropdown-item" href="Delete.html">Delete
						Account</a>
				</div></li>
		</ul>
	</nav>
	<div class="container">
		<br>
		<h2 class="new-heading">Bank Account Details</h2>
		<br>

		<table class="table table-bordered">
			<thead class="table-headc">
				<tr>
					<th>Account ID</th>
					<th>Customer Name</th>
					<th>Account Type</th>
					<th>Account Balance</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="account" items="${accounts}">
					<tr>
						<td>${account.accountId }</td>
						<td>${account.accoundHolderName }</td>
						<td>${account.accountType }</td>
						<td>${account.accountBalance }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</body>
</html>