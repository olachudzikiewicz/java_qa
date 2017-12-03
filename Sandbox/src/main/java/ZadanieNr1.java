public class ZadanieNr1 {
	
	public static void main (String[] args ){

		System.out.println("Zadanie numer 1!");

		System.out.println("Zadanie numer 2!");

		Point p1 = new Point(6,2);
		Point p2 = new Point(-3,-1);



		System.out.println("Odległość między punktami wynosi: " + distance(p1,p2));
	}

	public static double distance(Point p1, Point p2) {
		return Math.sqrt((Math.pow(p1.x - p2.x,2))+ (Math.pow(p1.y - p2.y,2)));
	}
}
