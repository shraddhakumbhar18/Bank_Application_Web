<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">

		<h2 class="new-heading">Open New Account</h2>


		<form action="accountInfo.do" class="form-box1" method="post">
			<div class="row">
				<br>
				<br>
				<div class="form-group col-md-12">
					<label for="formGroupExampleInput">Account Id</label> <input
						type="text" name="account_id" class="form-control" maxlength="15"
						id="formGroupExampleInput" value="${account.accountId }"
						placeholder="Name" readonly="readonly">
				</div>
			</div>

			<div class="row">
				<br>
				<br>
				<div class="form-group col-md-12">
					<label for="formGroupExampleInput">Customer Name</label> <input
						type="text" name="customer_name" class="form-control"
						value="${account.accoundHolderName }" maxlength="15"
						id="formGroupExampleInput" placeholder="Name">
				</div>
			</div>
			<div class="row">
				<br>
				<br>
				<div class="form-group col-md-12">
					<label  for="formGroupExampleInput">Account Type</label> 
					<select 
						class="custom-select form-control" id="formGroupExampleInput"
						name="account_type" required>
						<option selected >${account.accountType}</option>
						<option  value="Current Type">Current Type</option>
						<option value="Saving Type">Saving Type</option>
					</select>
				</div>
			</div>
			<div class="row">
				<br>
				<br>
				<div class="form-group col-md-12">
					<label for="formGroupExampleInput">Initial Account Balance</label>
					<input type="text" name="account_balance" class="form-control"
						value="${account.accountBalance }" maxlength="15"
						id="formGroupExampleInput" placeholder="Account ID"
						readonly="readonly">
				</div>
			</div>
			<br>
			<button type="submit" class="btn btn-primary sbtn1">Open New
				Account</button>
		</form>

	</div>
</body>
</html>