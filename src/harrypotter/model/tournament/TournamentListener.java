package harrypotter.model.tournament;

public interface TournamentListener {
	
	public void onFinishingFirst(String string);
	public void onFinishingSecond(String string);
	public void onFinishingThird();
}
