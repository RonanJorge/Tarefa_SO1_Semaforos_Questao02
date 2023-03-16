package controller;

import java.util.concurrent.Semaphore;

public class CorredorThread extends Thread{
	int numPessoa;
	Semaphore semaforo;
	
	public CorredorThread(int numPessoa, Semaphore semaforo) {
		this.numPessoa = numPessoa;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		int distancia = 0;
		while(distancia < 200) {
			int andar = (int)(Math.random()*3) + 4;
			if(distancia + andar > 200)	distancia = 200;
			else distancia += andar;
			System.out.println("A pessoa "+numPessoa+" andou "+andar+" metros. "
					+ "Distância total = "+distancia+ " metros.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			semaforo.acquire();
			abre();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			semaforo.release();
		}
	}
	
	public void abre() {
		System.out.println("A pessoa "+numPessoa+ " abriu a porta!");
		fica();
		System.out.println("A pessoa "+numPessoa+" fechou a porta!");
	}
	
	public void fica() {
		int tempoEspera = (int)(Math.random()*1001 + 1000);
		try {
			Thread.sleep(tempoEspera);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
