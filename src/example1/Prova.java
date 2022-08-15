package example1;


public class Prova {

	public static void main(String[] args) throws InterruptedException {
		Player p = new Player("files/menu1.wav");
		p.start();
		Thread.sleep(3000);
		p.pause();
		Thread.sleep(1000);
		p.restart();
	}

}
