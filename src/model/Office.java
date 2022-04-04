package model;

public class Office {
	private Person occupant;
	
	public Office() {
		//System.out.println("Office created");
	}
	
	public Person getOccupant() {
		return occupant;
	}
	
	public void setOccupant(Person occupant) {
		if (this.occupant == null) this.occupant = occupant; // Si la oficina está disponible
		else System.out.println(occupant.getName() + " cannot be included on the offices of the edifice");
	}
}
