package main.java.inputProcessor;

import java.util.HashMap;
import java.util.Map;

import main.java.verbs.Action;
import main.java.verbs.Ask;
import main.java.verbs.Attack;
import main.java.verbs.Climb;
import main.java.verbs.Close;
import main.java.verbs.Cut;
import main.java.verbs.Drink;
import main.java.verbs.Drop;
import main.java.verbs.Eat;
import main.java.verbs.Get;
import main.java.verbs.Give;
import main.java.verbs.Go;
import main.java.verbs.Light;
import main.java.verbs.List;
import main.java.verbs.Look;
import main.java.verbs.Loot;
import main.java.verbs.Open;
import main.java.verbs.Put;
import main.java.verbs.Read;
import main.java.verbs.Say;
import main.java.verbs.Sleep;
import main.java.verbs.Stab;
import main.java.verbs.Take;
import main.java.verbs.Unlock;
import main.java.verbs.Use;
import main.java.verbs.Wear;
import main.java.verbs.Whisper;
import main.java.verbs.Yell;

public class KnownVerbs {
	
	private Map< String, Action > commands = new HashMap<>();
	
	public KnownVerbs() {
		commands.put( "ask", new Ask() ); 
		commands.put( "attack", new Attack() );
		commands.put( "close", new Close() );
		commands.put( "climb", new Climb() );
		commands.put( "cut", new Cut() );
		commands.put( "drink", new Drink() );
		commands.put( "drop", new Drop() );
		commands.put( "e", new Go() );
		commands.put( "eat", new Eat() );
		commands.put( "get", new Get() );
		commands.put( "give", new Give() );
		commands.put( "go", new Go() );
		commands.put( "i", new List() );
		commands.put( "inv", new List() );
		commands.put( "inventory", new List() );
		commands.put( "k", new Attack() );
		commands.put( "kil", new Attack() );
		commands.put( "kill", new Attack() );
		commands.put( "move", new Go() );
		commands.put( "l", new Look() );
		commands.put( "light", new Light() );
		commands.put( "lok", new Look() );
		commands.put( "look", new Look() );
		commands.put( "loot", new Loot() );
		commands.put( "n", new Go() );
		commands.put( "open", new Open() );
		commands.put( "put", new Put() );
		commands.put( "read", new Read() );
		commands.put( "s", new Go() );
		commands.put( "say", new Say() );
		commands.put( "scream", new Yell() );
		commands.put( "sleep", new Sleep() );
		commands.put( "stab", new Stab() );
		commands.put( "t", new Take() );
		commands.put( "tak", new Take() );
		commands.put( "take", new Take() );
		commands.put( "talk", new Say() );
		commands.put( "w", new Go() );
		commands.put( "wear", new Wear() );
		commands.put( "whisper", new Whisper() );
		commands.put( "unlock", new Unlock() );
		commands.put( "use", new Use() );
		commands.put( "yell", new Yell() );
		
	}
	
	public Action getAction( String verb ) {
		try {
			return commands.get( verb );
		} catch( IllegalArgumentException e ) {
			return null;
		} 
	}
	
	public boolean check( String s ) {
		return commands.containsKey( s );
	}
}
