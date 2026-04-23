package week1.week21;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class EmployeekoUI{
public static void main(String[] N){
JFrame f=new JFrame("Employee");
f.setSize(600,500);
f.setLayout(new BorderLayout());
JPanel header=new JPanel();
JLabel title=new JLabel("Employee Management");
header.add(title);
header.setBackground(Color.LIGHT_GRAY);
JPanel form=new JPanel();
form.setLayout(new FlowLayout());
JTextField name=new JTextField("EnterFull name",15);
name.setForeground(Color.GRAY);
JTextField salary=new JTextField(10);

JLabel pos=new JLabel("Mouse: ");
JLabel count=new JLabel("0");
JButton b1=new JButton("Add");
JButton b2=new JButton("Update");
JButton b3=new JButton("Delete");
form.add(name);
form.add(salary);
form.add(count);
form.add(pos);
form.add(b1);
form.add(b2);
form.add(b3);

header.addMouseListener(new MouseAdapter(){
public void mouseEntered(MouseEvent e){
title.setText("Employee Records View");
header.setBackground(Color.ORANGE);
}
public void mouseExited(MouseEvent e){
title.setText("Employee Management");
header.setBackground(Color.BLUE);
}
});

form.addMouseMotionListener(new MouseMotionAdapter(){
public void mouseMoved(MouseEvent e){
pos.setText("Mouse: "+e.getX()+","+e.getY());
}
public void mouseDragged(MouseEvent e){
pos.setText("Mouse: "+e.getX()+","+e.getY());
}
});

name.addFocusListener(new FocusListener(){
public void focusGained(FocusEvent e){
if(name.getText().equals("Enter full name")){
name.setText("");
name.setForeground(Color.BLACK);
}
}
public void focusLost(FocusEvent e){
if(name.getText().equals("")){
name.setText("Enter full name");
name.setForeground(Color.GRAY);
}
}
});

name.addKeyListener(new KeyAdapter(){
public void keyReleased(KeyEvent e){
int len=name.getText().length();
count.setText(""+len);
if(len<10){
count.setForeground(Color.GREEN);
}
else if(len<15){
count.setForeground(Color.YELLOW);
}
else{
count.setForeground(Color.RED);
}
}
});

salary.addKeyListener(new KeyAdapter(){
public void keyReleased(KeyEvent e){
String s=salary.getText();
String n="";
for(int i=0;i<s.length();i++){
char c=s.charAt(i);
if((c>='0'&&c<='9')||c=='.'){
n=n+c;
}
}
salary.setText(n);
}
});

JButton[] arr={b1,b2,b3};
for(int i=0;i<arr.length;i++){
JButton b=arr[i];
b.addMouseListener(new MouseAdapter(){
public void mouseEntered(MouseEvent e){
b.setBackground(Color.GREEN);
b.setForeground(Color.WHITE);
}
public void mouseExited(MouseEvent e){
b.setBackground(null);
b.setForeground(Color.BLACK);
}
});
}

f.add(header,BorderLayout.NORTH);
f.add(form,BorderLayout.CENTER);

f.setVisible(true);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}