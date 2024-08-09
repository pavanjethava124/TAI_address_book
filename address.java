import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
class PersonInfo{
    String name;
    String address;
    String phoneNumber;

PersonInfo(String n, String a, String p)
{
    name=n;
    address=a;
    phoneNumber=p;
}
 void display()
 {
    JOptionPane.showMessageDialog(null,"Name:"+name+ "\nAddress:"+address+"\nPhone no:"+phoneNumber);

 }
}

class AddressBook{

    ArrayList persons;
    AddressBook(){
        persons=new ArrayList();
        loadPersons();
    }

    void addPerson(){
        String name=JOptionPane.showInputDialog("Enter name:");
        String add=JOptionPane.showInputDialog("Enter address:");
        String pNum=JOptionPane.showInputDialog("Enter phone no:");
        PersonInfo p=new PersonInfo(name,add,pNum);
        persons.add(p);
    }

    void searchPersons(String n){
        for(int i=0;i<persons.size();i++){
            PersonInfo p=(PersonInfo)persons.get(i);
            if(n.equals(p.name)){
                p.display();
            }
        }
    }

    void deletePerson(String n){
        for(int i=0;i<persons.size();i++){
            PersonInfo p=(PersonInfo)persons.get(i);
            if(n.equals(p.name)){
                persons.remove(i);
            }
        }
    }
    void savePersons(){
        try{
            PersonInfo p;
            String line;
            FileWriter fw= new FileWriter("person.txt");
            PrintWriter pw= new PrintWriter(fw);
            for(int i=0;i<persons.size();i++)
            {
                p=(PersonInfo)persons.get(i);
                line= p.name+ ","+p.address+ ","+p.phoneNumber;
                pw.println(line);
            }
            pw.flush();
            pw.close();
            fw.close();
        }
        catch(Exception ioEx){
            System.out.println(ioEx);
        }
    }

    void loadPersons(){
        String tokens[]=null;
        String name,add,ph;
        try{
            FileReader fr=new FileReader("persons.txt");
            BufferedReader br=new BufferedReader(fr);
            String line=br.readLine();
            
            while(line !=null){
                tokens= line.split(",");
                name=tokens[0];
                add=tokens[1];
                ph=tokens[2];
                PersonInfo p=new PersonInfo(name, add, ph);
                persons.add(p);
                line=br.readLine();
            }
            br.close();
            fr.close();
        }
        catch(IOException ioEx )
        {
            System.out.println(ioEx);
        }
    }
}

public class address {
public static void main(String[] args) {
    AddressBook ab=new AddressBook();
    String input,s;
    int ch;

    while(true){
        input=JOptionPane.showInputDialog("Enter 1 to Add\n Enter 2 to Search\n Enter 3 to Delete\n Enter 4 to Exit ");
        ch=Integer.parseInt(input);
        switch (ch) {
            case 1:
            ab.addPerson();
            break;
            case 2:
            s=JOptionPane.showInputDialog("Enter name to Search:");
           ab.searchPersons(s);
            break;
             case 3:
             s=JOptionPane.showInputDialog("Enter name to Delete:");
             ab.deletePerson(s);   
                break;
                case 4:
                ab.savePersons();
                System.exit(0);
        
        }
    }
}
    
}