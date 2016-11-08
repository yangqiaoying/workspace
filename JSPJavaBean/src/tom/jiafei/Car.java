package tom.jiafei;
import java.io.UnsupportedEncodingException;
import java.sql.*;

public class Car
{
    public String number = "";
	public String name = "";
	public String madetime = "";
	StringBuffer queryResult;
	int count=0;
	public String getNumber()
	{   try
	    {
		    byte b[]=number.getBytes("ISO-8859-1");
			number=new String(b);
			return number;
	    }	
		catch(Exception e)
		{   return number;
		}
	}
	public void setNumber(String newnumber) throws UnsupportedEncodingException
	{	number= new String(newnumber.getBytes("ISO-8859-1"), "utf-8");
	}
	
	public String getName()
	{   try
	    {
		    byte b[]=name.getBytes("ISO-8859-1");
			name=new String(b);
			return name;
	    }	
		catch(Exception e)
		{   return name;
		}
	}
	public void setName(String s) throws UnsupportedEncodingException
	{	name= new String(s.getBytes("ISO-8859-1"), "utf-8");
	}	
	public String getMadetime()
	{	return madetime;
	}
	public void setMadetime(String s) throws UnsupportedEncodingException
	{	madetime= new String(s.getBytes("ISO-8859-1"), "utf-8");
	}	
	public int getCount()
	{   return count;
	}
	public void insertData()
	{
		if (number == "" || name == "" || madetime == "") {
			System.out.println("数据不完整");
			return ;
		}
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch (Exception e) {
			System.out.println(e);
		}
		
		Connection con;
		Statement sql;
		ResultSet rs;
		
		try
		{
			String uri="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=JspJavaBeanCar";
			con=DriverManager.getConnection(uri,"sa","yqy");
			sql=con.createStatement();
			String str="INSERT INTO car VALUES"+"("+"'"+number+"','"+name+"','"+madetime+"');";
			System.out.println("str ==> " + str);
			sql.execute(str);
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		
		

	}
	public StringBuffer getQueryResult()
	{
		queryResult=new StringBuffer();
		try
		{	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch(Exception e){
			System.out.println(e);
		}
		Connection con;
		Statement sql;
		ResultSet rs;
		try
		{
			String uri="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=JspJavaBeanCar";
			con=DriverManager.getConnection(uri,"sa","yqy");
			DatabaseMetaData metadata=con.getMetaData();
			ResultSet rsl=metadata.getColumns(null,null,"car",null);
			sql=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=sql.executeQuery("SELECT * FROM car;");
			
			if (rs.last()) {
				queryResult.append("最后一个提交的数据为:<BR>");
				queryResult.append("车牌号:"+rs.getString(1)+", 品牌:"+rs.getString(2)+", 生产日期:"+rs.getString(3)+"<BR><BR>");
			}
			queryResult.append("目前已登记车辆共"+rs.getRow()+"辆:");
			
			
			int n=0;
			queryResult.append("<table border=2>");
			queryResult.append("<tr>");
				queryResult.append("<td>"+" "+"</td>");
				while(rsl.next()){
					n++;
					String clumnName=rsl.getString(4);
					queryResult.append("<td>"+clumnName+"</td>");
				}
			queryResult.append("</tr>");
			
			
			
			
			if (rs.first()) {
				count++;
				queryResult.append("<tr>");
				queryResult.append("<td>"+count+"</td>");
				for(int k=1;k<=n;k++){
					queryResult.append("<td>"+rs.getString(k)+"</td>");
				}
				queryResult.append("</tr>");
			}
			while(rs.next()){
				count++;
				queryResult.append("<tr>");
				queryResult.append("<td>"+count+"</td>");
				for(int k=1;k<=n;k++){
					queryResult.append("<td>"+rs.getString(k)+"</td>");
				}
				queryResult.append("</tr>");
			}
			queryResult.append("</table>");
			con.close();
		}
		catch(SQLException e)
		{   queryResult.append(e);
		}
		return queryResult;
	}
}