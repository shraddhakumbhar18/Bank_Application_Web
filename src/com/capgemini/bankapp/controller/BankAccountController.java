package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.bankappjdbc.exception.BankAccountNotFoundException;
import com.capgemini.bankappjdbc.exception.LowBalanceException;
import com.capgemini.bankappjdbc.model.BankAccount;
import com.capgemini.bankappjdbc.service.BankAccountService;
import com.capgemini.bankappjdbc.service.impl.BankAccountServiceImpl;

@WebServlet(urlPatterns= {"*.do"}, loadOnStartup = 1)
public class BankAccountController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	private BankAccountService bankService;
	
    public BankAccountController() 
    {
    	bankService = new BankAccountServiceImpl();
      
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		
		response.setContentType("text/html");
		String path = request.getServletPath();
		
		if(path.equals("/getAllBankAccounts.do"))
		{
			List<BankAccount> bankAccounts = bankService.findAllBankAccounts();
			RequestDispatcher dispatcher = request.getRequestDispatcher("Accdetails.jsp");
			request.setAttribute("accounts", bankAccounts);
			dispatcher.forward(request, response);
		}
	}
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
    	response.setContentType("text/html");
    	PrintWriter out = response.getWriter();
    	
		String path = request.getServletPath();
		System.out.println(path);
		
		if(path.equals("/addNewBankAccount.do"))
		{
			String accountHolderName = request.getParameter("customer_name");
			String accountType = request.getParameter("account_type");
			double balance = Double.parseDouble(request.getParameter("account_balance"));
			
			BankAccount account = new BankAccount(accountHolderName,accountType, balance);
			if(bankService.addNewAccount(account))
			{
				
				out.println("<h2> BankAccount is created successfully...</h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
				out.close();
			}
			
		}
		else if(path.equals("/withdraw.do"))
		{
			long account_Id = Long.parseLong(request.getParameter("account_id"));
			double balance = Double.parseDouble(request.getParameter("account_balance"));
			try 
			{
				bankService.withdraw(account_Id, balance);
				out.println("<h2> Amount withdraw successfully </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
				out.close();
			} 
			catch (LowBalanceException e) 
			{
				out.println("<h2> Low Balance </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			} 
			catch (BankAccountNotFoundException e) 
			{
				out.println("<h2> Bank account not found </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
		}
		else if(path.equals("/deposit.do"))
		{
			long account_Id = Long.parseLong(request.getParameter("account_id"));
			double balance = Double.parseDouble(request.getParameter("account_balance"));
			try 
			{
				bankService.deposite(account_Id, balance);
				out.println("<h2> Amount deposite successfully </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
				out.close();
			} 
			catch (BankAccountNotFoundException e) 
			{
				out.println("<h2> Bank account not found </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
			
		}
		else if(path.equals("/checkBalance.do"))
		{
			long account_Id = Long.parseLong(request.getParameter("account_id"));
			
			try 
			{
				double balance = bankService.checkBalance(account_Id);
				out.println("<h2> current balance is: </h2>"+balance);
				out.println("<h2><a href='index.html'>|Home|</h2>");
				out.close();
			} 
			catch (BankAccountNotFoundException e) 
			{
				out.println("<h2> Bank account not found </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
		}
		
		else if(path.equals("/delete.do"))
		{
			long account_Id = Long.parseLong(request.getParameter("account_id"));
			try 
			{
				bankService.deleteBankAccount(account_Id);
				out.println("<h2> Account deleted successfully </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
				out.close();
			} 
			catch (BankAccountNotFoundException e) 
			{
				out.println("<h2> Bank account not found </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
		}
		else if(path.equals("/fund.do"))
		{
			long senderAccountId = Long.parseLong(request.getParameter("from_account"));
			long recipentAccountId = Long.parseLong(request.getParameter("to_account"));
			double balance = Double.parseDouble(request.getParameter("account_balance"));
			try 
			{
				bankService.fundTransfer(senderAccountId, recipentAccountId, balance);
				out.println("<h2> Amount transferred successfully </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
				out.close();
				
			} 
			catch (LowBalanceException e) 
			{
				out.println("<h2> Low Balance </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			} 
			catch (BankAccountNotFoundException e) 
			{
				
				out.println("<h2> Bank account not found </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
		}
		else if(path.equals("/searchAccount.do"))
		{
			long account_Id = Long.parseLong(request.getParameter("account_id"));
			try 
			{
				BankAccount bankAccount = bankService.search(account_Id);
				RequestDispatcher	dispatcher = request.getRequestDispatcher("AccountDetails.jsp");
				request.setAttribute("account", bankAccount);
				dispatcher.forward(request, response);
			} 
			catch (BankAccountNotFoundException e) 
			{
				out.println("<h2> Bank account not found </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
		}
		else if(path.equals("/updateAccount.do"))
		{
			long account_Id = Long.parseLong(request.getParameter("account_id"));
			try 
			{
				BankAccount bankAccount = bankService.search(account_Id);
				RequestDispatcher dispatcher = request.getRequestDispatcher("AccountInfo.jsp");
				request.setAttribute("account", bankAccount);
				dispatcher.forward(request, response);
			} 
			catch (BankAccountNotFoundException e) 
			{
				out.println("<h2> Bank account not found </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
			
		}
		else if(path.equals("/accountInfo.do"))
		{
			long account_Id = Long.parseLong(request.getParameter("account_id"));
			String accountHolderName = request.getParameter("customer_name");
			String accountType = request.getParameter("account_type");
			boolean result = bankService.update(account_Id, accountHolderName, accountType);
			if(result==true)
			{
				out.println("<h2> Account updated successfully </h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
			else
			{
				out.println("<h2> Account failed to update</h2>");
				out.println("<h2><a href='index.html'>|Home|</h2>");
			}
		
		}
	}

}
