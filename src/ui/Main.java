package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.ElevatorSystem;

public class Main {
	private BufferedReader in;
	private ElevatorSystem backend;
	
	public Main() {
		in = new BufferedReader(new InputStreamReader(System.in));
		backend = new ElevatorSystem();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		try {
			main.start();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() throws NumberFormatException, IOException {
		int e = Integer.parseInt(in.readLine());
		for (int i=0; i<e; i++) {
			String[] l = in.readLine().split(" ");
			int officesPerFloor = Integer.parseInt(l[3]);
			int totalPeople = Integer.parseInt(l[1]);
			int totalFloors = Integer.parseInt(l[2]);
			backend.addEdifice(l[0], totalFloors, officesPerFloor, totalPeople, officesPerFloor * totalFloors);
			for (int j=0; j<totalPeople; j++) {
				String[] l2 = in.readLine().split(" ");
				int destination = Integer.parseInt(l2[2]);
				int targetFloor = (int) (Integer.parseInt(l[2]) - (Math.ceil((float) destination/(float)officesPerFloor)-1));
				backend.getEdifice(l[0]).getFloor(Integer.parseInt(l2[1])).addPerson(l2[0], destination, targetFloor);;
			}
		}
		backend.startSimulation();
	}
}
