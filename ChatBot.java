//for setting up the terminal for the conversation
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

//for setting the background color
import java.awt.Color;

//keylistener used to monitor when user presses enter
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class ChatBot extends JFrame implements KeyListener{
	//variable to create the conversation terminal

	//Used to setup the actual panel where the conversation will occur
	JPanel p=new JPanel();
	//text area that contains the entire conversation
	JTextArea dialog=new JTextArea(20,50);//size
	//text area for user input
	JTextArea input=new JTextArea(1,50);//size
	//to scroll up and down through the conversation
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
	);

	//2D array for all the phrases chatbot will reply to
	String[][] chatBot={
		//standard greetings
		{"hi","hello","What's up"},
		{"hi, do you want to talk about star wars","hello, let's talk about star wars",
		"hey, let's talk about star wars"},
		//question greetings
		{"how are you","how r you","how r u","how are u"},
		{"good","doing well"},
		//Asking for name
		{"whats your name", "what is your name", "whats ur name", "what's ur name"},
		{"My name is Chewy"},
		//Generic Star Wars Questions
		{"who is your favorite character"},
		{"My favorite star wars character is R2D2, yours?"},

		{"what is your favorite star wars movie"},
		{"My favorite star wars movie was A New Hope"},

		{"what do you think about the new star wars movie"},
		{"I dont really know, we will have to find out"},

		//Character responses
		{"anakin"},
		{"I don't really like Anakin, he is quite immature, who else do you know?"},
		{"obi-wan","obi-wan kenobi", "kenobi"},
		{"Obi-wan's hello there meme is still funny, who else do you know?"},
		{"jarjar", "jarjar binks", "binks"},
		{"JarJar Binks is the funniest character, who else do you know?"},
		{"darth vader", "vader"},
		{"Did you know darth vader is Luke's dad, who else do you know?"},
		{"padme"},
		{"padme is anakin's lover, who else do you know?"},
		{"darth vader", "vader"},
		{"Did you know darth vader is Luke's dad, who else do you know?"},

		//Star Trek
		{"star trek"},
		{"I don't want to talk to you anymore"},

		//yes
		{"yes"},
		{"yep"},
        //no
		{"no","NO","NO!"},
		{"no, we should talk about star wars"},
		//default
		{"Interesting, tell me more","Let's move on to something new","okay","hmmm",
		"Let's talk about something else"}
	};

	public static void main(String[] args){
		new ChatBot();
	}

	//constructor method to setup the actual panel or terminal for the dialogue
	public ChatBot(){
		super("Chat Bot");
		setSize(600,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		dialog.setEditable(false);
		input.addKeyListener(this);

		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(34,139,34));
		add(p);

		setVisible(true);
	}

	public void keyPressed(KeyEvent e){
		//if enter is pressed
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grabs message from the input-----------
			String quote=input.getText(); //gets text from input box
			input.setText("");//clears the input box for future inputs
			addText("-->You:\t"+quote);//calls method addText so that you can see the user input vs response
			quote=quote.trim();//takes away any extra space at the end or front
			while(
				//if the last character in the response is ! . ?
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
				//removes the last character if it's any of the char from above

			}
			quote=quote.trim();//takes away extra space
			byte response=0;//whether we got a response or not

			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/

			//-----check for matches from the 2D array----
			int j=0;//which group we're checking, each pair of user responses and answers
			while(response==0){
				//lowercase the response to correspond with array elements and multiply by 2 to only
				//search through the even index of arrays which are the possible user responses
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					//if the input is in the array, set the response to 2
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->Bot\t"+chatBot[(j*2)+1][r]);//gives the response
				}
				j++;
				//if it has reached the end, and there are no matches, set response to 1
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}

			//-----If there are no matches in the array, respond with default-------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);//randomly chooses one of the deafult responses
				addText("\n-->Bot\t"+chatBot[chatBot.length-1][r]);//responds with one of the defaults
			}
			addText("\n");
		}
	}

	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}

	public void keyTyped(KeyEvent e){}

	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}

	//returns true if the string is in the array, otherwise false. the string is the user input
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}
