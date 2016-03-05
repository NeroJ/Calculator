package calculator;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.text.DecimalFormat;

public class calculator extends JFrame
{
	private JLabel model;
	private JScrollPane JP;
    private JRadioButton Usual;
	private JRadioButton Scientific;
    private JTextField output;
    private JTextArea formular;
    private JButton buttons[];
    private JButton button1, button2, button3, button4;
	private JButton buttonL1, buttonL2, buttonL3, buttonL4;
	private String calculation[] = {"!", "^2", "√","sin","D to R", "^3", "π", "e"};
	private JButton buttonB[];
	private DecimalFormat df;
	private String oper = "=";
	private double result = 0;
	private boolean IfResult = true, flag = false;
	public void calculatorS ()
	{
		setTitle("Scientific Calculator");
		setBounds(100,100,500,400);
		df = new DecimalFormat("#.########");
		//model
		model = new JLabel("Model: ");
		Usual = new JRadioButton("Usual",false);
		Scientific = new JRadioButton("Scientific",true);
		JPanel panelR = new JPanel();
		panelR.add(model);
		panelR.add(Usual);
		panelR.add(Scientific);
		//button
		buttons = new JButton[12];
		buttonB = new JButton[10];
		output = new JTextField("0");
        output.setBackground(new Color(155, 200, 230));
        output.setFont(new Font("宋体",Font.BOLD, 45));
        output.setBorder(BorderFactory.createLoweredBevelBorder());
		output.setEditable(false);
		output.setHorizontalAlignment(JTextField.RIGHT);
		formular = new JTextArea();
		formular.setEditable(false);
		formular.setBackground(new Color(244, 240, 129));
		formular.setFont(new Font("宋体",Font.BOLD, 45));
		JP = new JScrollPane(formular);
		button1 = new JButton("÷");
		button2 = new JButton("×");
		button3 = new JButton("+");
		button4 = new JButton("-");
		buttonL1 = new JButton("cos");
		buttonL2 = new JButton("tan");
		buttonL3 = new JButton("ln");
		buttonL4 = new JButton("-/+");
		buttonL1.setForeground(new Color(100,20,20));
		buttonL2.setForeground(new Color(100,20,20));
		buttonL3.setForeground(new Color(100,20,20));
		buttonL4.setForeground(new Color(100,20,20));
		//buttonAction()
		class NumAction implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String str = e.getActionCommand();
				formular.append(str);
				if(IfResult)
				{
					output.setText("");
					IfResult = false;
				}
				if(str.equals("π"))
				{
					output.setText(String.valueOf(Math.PI));
				}
				else if(str.equals("e"))
				{
					output.setText(String.valueOf(Math.E));
				}
				else
				{
					output.setText(output.getText().trim() + str);
					if(output.getText().equals("0"))
					{
						output.setText("0");
						IfResult = true;
						flag = true;
					}
				}
			}
		}	
		class dotAction implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				IfResult = false;
				formular.append(".");
				if(output.getText().trim().indexOf(".") == -1)
				{
					output.setText(output.getText() + ".");
				}
			}
		}	
		class buttonAction implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				String str = event.getActionCommand();
				formular.append(str);
				//sqrt
				if(str.equals("√"))
				{
					double i = Double.parseDouble(output.getText());
					if(i>=0)
					{
					   output.setText(String.valueOf(df.format(Math.sqrt(i))));
					}
					else{
						   output.setText("负数不能开平方根");
					    }
				}
				//-/+
				else if(str == "-/+")
				{
					double i = 0 - Double.parseDouble(output.getText().trim());
					output.setText(df.format(i));
					formular.setText(df.format(i));
				}
				//log
				else if(str.equals("ln"))
				{
					double i = Double.parseDouble(output.getText());
					if(i>0)
					{
						output.setText(String.valueOf(df.format(Math.log(i))));
					}
					else
					{
						output.setText("负数不能求对数");
					}
				}
				//sin
				else if(str.equals("sin"))
				{
					double i = Double.parseDouble(output.getText());
					output.setText(String.valueOf(df.format(Math.sin(i))));
				}
				//cos
				else if(str.equals("cos"))
				{
					double i = Double.parseDouble(output.getText());
					output.setText(String.valueOf(df.format(Math.cos(i))));
				}
				//tan
				else if(str.equals("tan"))
				{
					double i = Double.parseDouble(output.getText());
					output.setText(String.valueOf(df.format(Math.tan(i))));
				}
				//!
				else if(str.equals("!"))
				{
					double i = Double.parseDouble(output.getText());
					if((i%2==0)||(i%2==1))//判断为整数放进行阶乘操作
					{
						int j = (int)i;//强制类型转换
						int result=1;
						for(int k=1;k<=j;k++)
							result *= k;
						output.setText(String.valueOf(result));
					}
					else
					{
						output.setText("无法进行阶乘");
					}
				}
				//^2
				else if(str.equals("^2"))
				{
					double i = Double.parseDouble(output.getText());
					output.setText(String.valueOf(df.format(i*i)));
				}
				//^3
				else if(str.equals("^3"))
				{
					double i = Double.parseDouble(output.getText());
					output.setText(String.valueOf(df.format(i*i*i)));
				}
				//D to R
				else if(str.equals("D to R"))
				{
					double i = Double.parseDouble(output.getText());
					output.setText(String.valueOf(i/180*Math.PI));
				}
			}
		}
		class clearAction implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				String str = event.getActionCommand();
				if(str == "C")
				{
					output.setText("0");
					IfResult = true;
					result = 0;
					formular.setText("");
				}
				else if(str == "DEL")
				{
					if(Double.parseDouble(output.getText()) > 0)
					{
						if(output.getText().length() > 1)
						{
							output.setText(output.getText().substring(0, output.getText().length() - 1));
							formular.setText(output.getText().substring(0, output.getText().length()));
						}
						else
						{
							output.setText("0");
							formular.setText("");
							IfResult = true;
						}
					}
					else
					{
						if(output.getText().length() > 2)
						{
							output.setText(output.getText().substring(0, output.getText().length() - 1));
							formular.setText(output.getText().substring(0, output.getText().length()));
						}
						else
						{
							output.setText("0");
							formular.setText("");
							IfResult = true;
						}
					}
				}
			}
		}
		class basicAction implements ActionListener
		{
			private void getResult (double x)
			{
				if(oper == "+"){result += x;}
				else if(oper == "-"){result -= x;}
				else if(oper == "×"){result *= x;}
				else if(oper == "÷"){result /= x;}
				else if(oper == "="){result = x;}
				output.setText(df.format(result));
			}
			public void actionPerformed(ActionEvent event)
			{
				
				String str = event.getActionCommand();
				if (str != "=")
				{
					formular.append(str);
				}
				if(flag)
				{
					IfResult = false;
				}
				if(IfResult)
				{
					oper = str;
				}
				else
				{ 
					getResult(Double.parseDouble(output.getText()));
					oper = str;
					IfResult = true;
				}
			}
		}
	    Container panel0 = this.getContentPane();
	    panel0.setLayout(new GridLayout(2,0));
	    JPanel panelOut = new JPanel();
	    panelOut.setLayout(new GridLayout(2,0));
	    panelOut.add(JP);
	    panelOut.add(output);
	    panel0.add(panelOut);
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(0,3));
		JPanel panelRight = new JPanel();
		panelRight.setLayout(new GridLayout(0,1));
		JPanel panelL = new JPanel();
		panelL.setLayout(new GridLayout(0,1));
		JPanel panelUp = new JPanel();
		panelUp.setLayout(new GridLayout(0,5));
		panel0.add(panel);
		//1~10 calculate buttons
		for (int i= 0; i < 9; i++)
		{
			buttons[i] = new JButton(""+(i+1));
			panel1.add(buttons[i]);
			buttons[i].addActionListener(new NumAction());
			buttons[i].setForeground(new Color(153,153,0));
		}
		buttons[9] = new JButton(""+0);
		buttons[9].setForeground(new Color(153,153,0));
		panel1.add(buttons[9]);
		buttons[9].addActionListener(new NumAction());
		buttons[10] = new JButton(".");
		buttons[10].setForeground(new Color(203,0,25));
		panel1.add(buttons[10]);
		buttons[10].addActionListener(new dotAction());
		buttons[11] = new JButton("=");
		buttons[11].setForeground(new Color(203,0,25));
		panel1.add(buttons[11]);
		buttons[11].addActionListener(new basicAction());
		panelRight.add(button1);
		button1.addActionListener(new basicAction());
		panelRight.add(button2);
		button2.addActionListener(new basicAction());
		panelRight.add(button3);
		button3.addActionListener(new basicAction());
		panelRight.add(button4);
		button4.addActionListener(new basicAction());
		panelL.add(buttonL1);
		buttonL1.addActionListener(new buttonAction());
		panelL.add(buttonL2);
		buttonL2.addActionListener(new buttonAction());
		panelL.add(buttonL3);
		buttonL3.addActionListener(new buttonAction());
		panelL.add(buttonL4);
		buttonL4.addActionListener(new buttonAction());
		for (int i= 0; i < 6; i++)
		{
			buttonB[i] = new JButton(""+calculation[i]);
			buttonB[i].setForeground(new Color(155,53,102));
			panelUp.add(buttonB[i]);
			buttonB[i].addActionListener(new buttonAction());
		}
		buttonB[6] = new JButton("π");
		buttonB[6].setForeground(new Color(155,53,102));
		panelUp.add(buttonB[6]);
		buttonB[6].addActionListener(new NumAction());
		buttonB[7] = new JButton("e");
		buttonB[7].setForeground(new Color(155,53,102));
		panelUp.add(buttonB[7]);
		buttonB[7].addActionListener(new NumAction());
		buttonB[8] = new JButton("C");
		buttonB[8].setForeground(new Color(155,53,102));
		panelUp.add(buttonB[8]);
		buttonB[8].addActionListener(new clearAction());
		buttonB[9] = new JButton("DEL");
		buttonB[9].setForeground(new Color(155,53,102));
		panelUp.add(buttonB[9]);
		buttonB[9].addActionListener(new clearAction());
		//add
		panel.add(panelUp, BorderLayout.NORTH);
		panel.add(panelRight, BorderLayout.EAST);
		panel.add(panelL, BorderLayout.WEST);
		panel.add(panelR, BorderLayout.SOUTH);
		panel.add(panel1, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void calculatorU ()
	{
		setTitle("Usual Calculator");
		setBounds(100,100,500,400);
		df = new DecimalFormat("#.########");
		//model
		model = new JLabel("Model: ");
		Usual = new JRadioButton("Usual",true);
		Scientific = new JRadioButton("Scientific",false);
		JPanel panelR = new JPanel();
		panelR.add(model);
		panelR.add(Usual);
		panelR.add(Scientific);
		//button
		buttons = new JButton[12];
		buttonB = new JButton[10];
		output = new JTextField("0");
        output.setBackground(new Color(155, 200, 230));
        output.setFont(new Font("宋体",Font.BOLD, 45));
        output.setBorder(BorderFactory.createLoweredBevelBorder());
		output.setEditable(false);
		output.setHorizontalAlignment(JTextField.RIGHT);
		formular = new JTextArea();
		formular.setEditable(false);
		formular.setBackground(new Color(244, 240, 129));
		formular.setFont(new Font("宋体",Font.BOLD, 45));
		JP = new JScrollPane(formular);
		button1 = new JButton("÷");
		button2 = new JButton("×");
		button3 = new JButton("+");
		button4 = new JButton("-");
		buttonL1 = new JButton("C");
		buttonL2 = new JButton("DEL");
		buttonL3 = new JButton("CE");
		buttonL4 = new JButton("-/+");
		buttonL1.setForeground(new Color(100,20,20));
		buttonL2.setForeground(new Color(100,20,20));
		buttonL3.setForeground(new Color(100,20,20));
		buttonL4.setForeground(new Color(100,20,20));
		class NumAction implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				String str = e.getActionCommand();
				formular.append(str);
				if(IfResult)
				{
					output.setText("");
					IfResult = false;
				}
				if(str.equals("π"))
				{
					output.setText(String.valueOf(Math.PI));
				}
				else if(str.equals("e"))
				{
					output.setText(String.valueOf(Math.E));
				}
				else
				{
					output.setText(output.getText().trim() + str);
					if(output.getText().equals("0"))
					{
						output.setText("0");
						IfResult = true;
						flag = true;
					}
				}
			}
		}	
		class dotAction implements ActionListener{
			public void actionPerformed(ActionEvent e)
			{
				IfResult = false;
				formular.append(".");
				if(output.getText().trim().indexOf(".") == -1){
					output.setText(output.getText() + ".");
				}
			}
		}	
		class buttonAction implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				String str = event.getActionCommand();
				formular.append(str);
				//-/+
				if(str == "-/+")
				{
					double i = 0 - Double.parseDouble(output.getText().trim());
					output.setText(df.format(i));
					formular.setText(df.format(i));
				}
			}
		}
		class clearAction implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				String str = event.getActionCommand();
				if(str == "C")
				{
					output.setText("0");
					IfResult = true;
					result = 0;
					formular.setText("");
				}
				else if(str == "CE")
				{
					output.setText("0");
					IfResult = true;
					result = 0;
					formular.setText("");
				}
				else if(str == "DEL")
				{
					if(Double.parseDouble(output.getText()) > 0)
					{
						if(output.getText().length() > 1)
						{
							output.setText(output.getText().substring(0, output.getText().length() - 1));
							formular.setText(output.getText().substring(0, output.getText().length()));
							//使用退格删除最后一位字符
						}
						else
						{
							output.setText("0");
							formular.setText("");
							IfResult = true;
						}
					}
					else
					{
						if(output.getText().length() > 2)
						{
							output.setText(output.getText().substring(0, output.getText().length() - 1));
							formular.setText(output.getText().substring(0, output.getText().length()));
						}
						else
						{
							output.setText("0");
							formular.setText("");
							IfResult = true;
						}
					}
				}
			}
		}
		class basicAction implements ActionListener
		{
			private void getResult (double x)
			{
				if(oper == "+"){result += x;}
				else if(oper == "-"){result -= x;}
				else if(oper == "×"){result *= x;}
				else if(oper == "÷"){result /= x;}
				else if(oper == "="){result = x;}
				output.setText(df.format(result));
			}
			public void actionPerformed(ActionEvent event)
			{
				
				String str = event.getActionCommand();
				if (str != "=")
				{
					formular.append(str);
				}
				if(flag)
				{
					IfResult = false;
				}
				if(IfResult)
				{
					oper = str;
				}
				else
				{ 
					getResult(Double.parseDouble(output.getText()));
					oper = str;
					IfResult = true;
				}
			}
		}
	    Container panel0 = this.getContentPane();
	    panel0.setLayout(new GridLayout(2,0));
	    JPanel panelOut = new JPanel();
	    panelOut.setLayout(new GridLayout(2,0));
	    panelOut.add(JP);
	    panelOut.add(output);
	    panel0.add(panelOut);
	    JPanel panel = new JPanel();
	    panel.setLayout(new BorderLayout());
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(0,3));
		JPanel panelRight = new JPanel();
		panelRight.setLayout(new GridLayout(0,1));
		JPanel panelL = new JPanel();
		panelL.setLayout(new GridLayout(0,1));
		JPanel panelUp = new JPanel();
		panelUp.setLayout(new GridLayout(0,5));
		panel0.add(panel);
		//1~10 calculate buttons
		for (int i= 0; i < 9; i++)
		{
			buttons[i] = new JButton(""+(i+1));
			panel1.add(buttons[i]);
			buttons[i].addActionListener(new NumAction());
			buttons[i].setForeground(new Color(153,153,0));
		}
		buttons[9] = new JButton(""+0);
		buttons[9].setForeground(new Color(153,153,0));
		panel1.add(buttons[9]);
		buttons[9].addActionListener(new NumAction());
		buttons[10] = new JButton(".");
		buttons[10].setForeground(new Color(203,0,25));
		panel1.add(buttons[10]);
		buttons[10].addActionListener(new dotAction());
		buttons[11] = new JButton("=");
		buttons[11].setForeground(new Color(203,0,25));
		panel1.add(buttons[11]);
		buttons[11].addActionListener(new basicAction());
		panelRight.add(button1);
		button1.addActionListener(new basicAction());
		panelRight.add(button2);
		button2.addActionListener(new basicAction());
		panelRight.add(button3);
		button3.addActionListener(new basicAction());
		panelRight.add(button4);
		button4.addActionListener(new basicAction());
		panelL.add(buttonL1);
		buttonL1.addActionListener(new clearAction());
		panelL.add(buttonL2);
		buttonL2.addActionListener(new clearAction());
		panelL.add(buttonL3);
		buttonL3.addActionListener(new clearAction());
		panelL.add(buttonL4);
		buttonL4.addActionListener(new buttonAction());
		//add
		panel.add(panelUp, BorderLayout.NORTH);
		panel.add(panelRight, BorderLayout.EAST);
		panel.add(panelL, BorderLayout.WEST);
		panel.add(panelR, BorderLayout.SOUTH);
		panel.add(panel1, BorderLayout.CENTER);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public  static class Face extends JFrame
	{
		public String Select;
		private JButton UsualF, ScientificF;
		public void SelectFace()
		{
			setTitle("SelectFace");
			setBounds(225,225,225,225);
			Container Face = getContentPane();
			Face.setLayout(new GridLayout(2,0));
			JPanel panel1 = new JPanel();
			JPanel panel2 = new JPanel();
			panel1.setBackground(new Color(155, 200, 200));
			panel2.setBackground(new Color(155, 200, 200));
			JLabel label = new JLabel("Please Select Model:");
			panel1.add(label);
			class SelectAction implements ActionListener
			{
				private void S()
				{
					if (Select == "Usual")
				    {
					   calculator test = new calculator();
					   test.calculatorU();
					   dispose();
				    }
				    if (Select == "Scientific")
				    {
				    	calculator test = new calculator();
				    	test.calculatorS();
				    	dispose();
				    }
				}
				public void actionPerformed(ActionEvent event)
				{
					String str = event.getActionCommand();
					Select = str;
					S();
				}
			}
			UsualF = new JButton("Usual");
			ScientificF = new JButton("Scientific");
			UsualF.addActionListener(new SelectAction());
			ScientificF.addActionListener(new SelectAction());
			panel2.add(UsualF);
			panel2.add(ScientificF);
			Face.add(panel1);
			Face.add(panel2);
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			setVisible(true);
		}
	}
    public static void main (String args[])
	{
	    Face face = new Face();
	    face.SelectFace();
	}
} 
