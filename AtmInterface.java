import java.util.*;
public class AtmInterface{
	String cardnum;
	int pin;
	String name;
	float balance;
	static String transaction="";
	static public int c=0;
    public	ArrayList<AtmInterface> list = new ArrayList<>();

	AtmInterface()
	{

	}

	AtmInterface(String cardnum,int pin,String name,float balance)
	{
		this.cardnum=cardnum;
		this.pin=pin;
		this.name=name;
		this.balance=balance;
	}

	public String getcardnum()
	{
		return cardnum;
	}

	public int getpin()
	{
		return pin;
	}

	public String getname()
	{
		return name;
	}

	public float getbalance()
	{
		return balance;
	}

	public void setcardnum(String c1)
	{
		cardnum=c1;

	}

	public void setpin(int p1)
	{
		pin=p1;

	}

	public void setname(String n1)
	{
		name=n1;

	}

	public void setbalance(float b1)
	{
		balance=b1;

	} 

	public void addValues(String num[], int pin[],
                          String name[], float balc[])
    {

        for (int i = 0; i <4; i++) 
        {
            list.add(new AtmInterface(num[i], pin[i],name[i],
                              balc[i]));
        }

    }
 public static void printoptions()
	{
		System.out.println("----------------******Enter Your option******----------------");   
        System.out.println("1. Transaction history");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposite");
        System.out.println("4. Transfer");
        System.out.println("5. Check balance"); 
        System.out.println("6. Quit");                    
	}


	public static void transaction()
    {
        if(c>0)
        {
            System.out.println("You have performed "+c+" transactions");
            System.out.println("Transaction Details :");
            System.out.println(transaction);
        }
        else
        {
            System.out.println("No transactions are performed");
        }
    }


	public static void deposit(AtmInterface obj)
	{
		System.out.println("How much would you like to deposit");
		Scanner sc=new Scanner(System.in);
		float amount=sc.nextFloat();
		float temp=amount+obj.getbalance();
		obj.setbalance(temp);
		System.out.println("Now your account balance is"+obj.getbalance());
		 c++;
        transaction+="\n"+Float.toString(amount)+ " Rupees deposited into your account"+"\n";
	}


	public static void withdraw(AtmInterface obj)
	{
		System.out.println("How much would you like to withdraw");
		Scanner sc=new Scanner(System.in);
		float amount=sc.nextFloat();
		if(amount>obj.getbalance())
		{
			System.out.println("Insufficient balance!!");
		}
		else
		{

			while(amount%10!=0)
            {
                System.out.println("Enter amount in terms of 10's");
                amount = sc.nextFloat();
            }

			float temp=obj.getbalance()-amount;
			obj.setbalance(temp);
			System.out.println("You are good to go!! Thank You");
			c++;
			transaction+="\n"+Float.toString(amount)+" Rupees withdraw from your account"+"\n";
		}
	}

	 public  static void transfer(AtmInterface obj,String[] nums,float balc[])
    {
    	Scanner sc=new Scanner(System.in);
    	System.out.println("ENter Account number");
    	String acctno=sc.nextLine();
    	int i=0;
    	int flag=0;


    	for( i=0;i<nums.length;i++)
    	{
    		if(acctno.equals(nums[i]))
    		{
    			flag=1;
    			break;
    		}

    	}


    	if(flag==0)
    	{
    		System.out.println("Wrong Account number");
    		return;
    	}

    	System.out.println("Enter amount to transfer :");
        float amount = sc.nextFloat();   


            if(obj.balance >= amount)
            {
                c++;
                obj.balance -= amount;
                System.out.println("Successfully transferd ");
                c++;
                balc[i]+=amount;
                transaction+="\n"+Float.toString(amount)+" Rupees transferred from your account to "+acctno+" \n";
            }
            else
            {
                System.out.println("Insufficient balance");
            }


    }   


	public static void checkbalance(AtmInterface obj)
	{
		System.out.println("Your Account balance is "+obj.getbalance());
	} 


	public static void main(String args[])
	{
		String num[]={"123456789402","123456789445","123456789459","123456789428"};

		int pin[]={1999,2000,2001,2002};

		String name[]={"Rajesh","Alisha","Umesh","Harish"};

		float balc[]={10000,80000,75000,22000};

		AtmInterface data=new AtmInterface();

		data.addValues(num,pin,name,balc);

		System.out.println("Welcome to ATM");
		System.out.println("Please insert your card: ");
		String debitcardnum="";
		Scanner sc=new Scanner(System.in);
		AtmInterface obj=null;
		int no_tries=0;

		while(true)
		{
			System.out.println("Enter CardNumber");
			debitcardnum=sc.nextLine();

			for(int i=0;i<4;i++)
			{
				AtmInterface c1=data.list.get(i);
				if(c1.getcardnum().equals(debitcardnum))
				{
					obj=c1;
					break;
				}
			}

			if(obj!=null||no_tries>3)
			{
				break;

			}

			else
			{
				   no_tries=0;
					System.out.println("User not Recognized... Try again");
			}
		}

		int userpin=0;
		System.out.println("Enter pin");
	    no_tries=0;

		while(true)
		{
			userpin=sc.nextInt();

			if(obj.getpin()==userpin || no_tries>3)
			{
				break;
			}
			else
			{
				no_tries++;
				System.out.println("User not Recognized... Try again");
			}

		}

		System.out.println("Welcome "+obj.getname());
		System.out.println("ENter your option");
		printoptions();
		int option=sc.nextInt();


		while(option<=5)
		{
			if(option==1)
			{
				transaction();
			}
			else if(option==2)
			{
				withdraw(obj);
			}
			else if(option==3)
			{
				deposit(obj);
			}
			else if(option==4)
			{
				transfer(obj,num,balc);
			}
			else if(option==5)
			{
				checkbalance(obj);
			}
			else
			{
				break;
			}

			printoptions();
			option=sc.nextInt();
		}
		System.out.println("Thank you !! Have a nice day");

	}
}
