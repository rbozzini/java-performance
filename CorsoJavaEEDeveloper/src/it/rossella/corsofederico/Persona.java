package it.rossella.corsofederico;

public class Persona {

	private String name;
	private int age;

	private int conteggioSaluti = 0;

	public Persona(String name, int age) {
		this.name = name;
		this.age = age;

		System.out.println("Ho creato una istanza di Persona: name = " + name + ", age = " + age);
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void saluta() {
		conteggioSaluti++;

		System.out.println("Ciao da " + name);
	}

	public void saluta(Persona amico) {
		conteggioSaluti++;

		System.out.println("Ciao " + amico.name + " da " + name);
	}

	public void getCountSaluti() {

		if (conteggioSaluti == 1) {
			System.out.println(name + " ha salutato " + conteggioSaluti + " volta");
		} else {
			System.out.println(name + " ha salutato " + conteggioSaluti + " volte");
		}

	}

	public static void main(String[] args) {
		Persona federico = new Persona("Federico", 19);

		federico.saluta();

		federico.getCountSaluti();

		federico.saluta();

		federico.getCountSaluti();

		Persona eleonora = new Persona("Eleonora", 15);

		eleonora.saluta(federico);

		eleonora.getCountSaluti();
	}

}
