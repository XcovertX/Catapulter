package inputProcessor;

import java.util.HashMap;
import java.util.Map;

import verbs.Action;
import verbs.Ask;
import verbs.Attack;
import verbs.Climb;
import verbs.Close;
import verbs.Cut;
import verbs.Drink;
import verbs.Drop;
import verbs.Eat;
import verbs.Get;
import verbs.Give;
import verbs.Go;
import verbs.Light;
import verbs.List;
import verbs.Look;
import verbs.Loot;
import verbs.Open;
import verbs.Put;
import verbs.Read;
import verbs.Say;
import verbs.Sleep;
import verbs.Stab;
import verbs.Take;
import verbs.Unlock;
import verbs.Use;
import verbs.Wear;
import verbs.Whisper;
import verbs.Yell;

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
