package infinitespire.helpers;

import java.util.ArrayList;
import java.util.HashMap;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import infinitespire.quests.Quest;
import infinitespire.quests.SlayQuest;

public abstract class QuestHelper {	
	
	private static HashMap<String, Class<? extends Quest>> questsMap = new HashMap<String, Class<? extends Quest>>();
	
	public static void addQuestTypeToMap(Class<? extends Quest> c) {
		questsMap.put(c.getName(), c);
	}
	
	@SuppressWarnings({ "unchecked" })
	public static Quest getRandomQuest() {
		Quest quest = createQuest(SlayQuest.class.getName());
		int randomNum = AbstractDungeon.miscRng.random(questsMap.size() - 1);
		Class<? extends Quest> c = (Class<? extends Quest>) questsMap.values().toArray()[randomNum];
		
		try {
			quest = c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return quest;
	}
	
	public static ArrayList<Quest> getRandomQuests(int amount){
		ArrayList<Quest> quests = new ArrayList<Quest>();
		for(int i = 0; i < amount; i++) {
			quests.add(getRandomQuest());
		}
		return quests;
	}
	
	public static Class<? extends Quest> getQuestClassFromMap(String string) {
		return questsMap.get(string);
	}
	
	public static Quest createQuest(String classNameAsString) {
		try {
			return questsMap.get(classNameAsString).newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
}